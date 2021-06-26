#include <iostream>
#include <map>
#include <vector>
#include <set>
#include <queue>

#define MAXN 100001
#define MAXD 1000000001LL

using namespace std;

int N;
long long D;
struct Pie{
    int index; // Original index
    long long bessieTaste;
    long long elsieTaste;
};
// vector<Pie> Bs;
// vector<Pie> Es;
/*struct cmpE
{
	bool operator()(int a,int b) const
	{
		return Es[a].bessieTaste < Es[b].bessieTaste;
	}
};
struct cmpB
{
	bool operator()(int a,int b) const
	{
		return Bs[a].elsieTaste < Bs[b].elsieTaste;
	}
};*/
struct cmpB
{
	bool operator()(const Pie a,const Pie b) const
	{
		return a.bessieTaste < b.bessieTaste;
	}
};
struct cmpE
{
	bool operator()(const Pie a,const Pie b) const
	{
		return a.elsieTaste < b.elsieTaste;
	}
};
// map<long long,vector<Pie>> bPies;
// map<long long,vector<Pie>> ePies;
// multiset<long long> bTasteVals;
// multiset<long long> eTasteVals;
multiset<int> bTasteINDEXES; // best elsie taste of bessies
multiset<int> eTasteINDEXES; // best bessie taste of elsies
int shortestPathB[MAXN] = {INT32_MAX};
int shortestPathE[MAXN] = {INT32_MAX};
vector<Pie> bessieDeadEnds; // where e=0
vector<Pie> elsieDeadEnds; // where b=0

multiset<Pie,cmpE> bessiePies;
multiset<Pie,cmpB> elsiePies;


struct State{
    bool isBessiePie;
    Pie p;
    int depth = 1;
};


struct StateCMP {
    bool operator()(State const& p1, State const& p2)
    {
        // return "true" if "p1" is ordered
        // before "p2", for example:
        return p1.depth > p2.depth; // lower depth in front
    }
};
queue<State> pq;
multiset<long long>::iterator bs(const multiset<long long>& st,long long target){
    auto exactIT = st.find(target);
    if(exactIT != st.end()){
        // cout << "bs(" << target << ") -> " << " exact found";
        return exactIT;
    }
    auto lb = st.lower_bound(target);
    if(lb == st.begin()){
        return st.end();
    }else{
        return --lb;
    }
    //cout << "bs(" << target << ") -> " << " lb: " << (((--st.lower_bound(target)) == st.end())?-1:*st.upper_bound(target)) << endl;
    //return st.upper_bound(target);
}
/*multiset<Pie>::iterator bs2(const multiset<Pie>& st,Pie target){
    auto exactIT = st.find(target);
    if(exactIT != st.end()){
        // cout << "bs2(" << target << ") -> " << " exact found";
        return exactIT;
    }
    auto lb = st.lower_bound(target);
    if(lb == st.begin()){
        return st.end();
    }else{
        return -- lb;
    }
    //cout << "bs(" << target << ") -> " << " lb: " << (((--st.lower_bound(target)) == st.end())?-1:*st.upper_bound(target)) << endl;
    //return st.upper_bound(target);
}*/

multiset<Pie,cmpB>::iterator bs3(const multiset<Pie,cmpB>& st,Pie target){
    // cout << "bs2(" << target.bessieTaste << ")" << endl;
    /*auto exactIT = st.find(target);
    if(exactIT != st.end()){
        // cout << "bs2(" << target << ") -> " << " exact found";
        return exactIT;
    }*/
    auto lb = st.lower_bound(target);
    if(lb == st.begin()){
        // cout << "bs targeted beginning at " << (*lb).bessieTaste << endl;
        return st.end();
    }else{
        return -- lb;
    }
    //cout << "bs(" << target << ") -> " << " lb: " << (((--st.lower_bound(target)) == st.end())?-1:*st.upper_bound(target)) << endl;
    //return st.upper_bound(target);
}

multiset<Pie,cmpE>::iterator bs4(const multiset<Pie,cmpE>& st,Pie target){
    /*auto exactIT = st.find(target);
    if(exactIT != st.end()){
        // cout << "bs2(" << target << ") -> " << " exact found";
        return exactIT;
    }*/
    auto lb = st.lower_bound(target);
    if(lb == st.begin()){
        return st.end();
    }else{
        return --lb;
    }
    //cout << "bs(" << target << ") -> " << " lb: " << (((--st.lower_bound(target)) == st.end())?-1:*st.upper_bound(target)) << endl;
    //return st.upper_bound(target);
}

void dij(){
    bool visitedB[MAXN] = {false};
    bool visitedE[MAXN] = {false};
    // cout << "Starting new search..." << endl;
    while(!pq.empty()){
        State st = pq.front();
        if(st.isBessiePie){
            visitedB[st.p.index] = true;
        }else{
            visitedE[st.p.index] = true;
        }
        // cout << "st: " << st.isBessiePie << " index: " << st.p.index << " (" << st.p.bessieTaste << "," << st.p.elsieTaste << ")" << endl;
        pq.pop();

        if(st.isBessiePie && shortestPathB[st.p.index] < st.depth){
            // cout << "Better path explored already " << endl;
            continue;
        }
        if(!st.isBessiePie && shortestPathE[st.p.index] < st.depth){
            // cout << "Better path explored already " << endl;
            continue;
        }
        if(st.isBessiePie){
            shortestPathB[st.p.index] = st.depth;            
        }else{
            shortestPathE[st.p.index] = st.depth;  
        }

        // Explore other options
        if(!st.isBessiePie){
            // Find a reason for bessie to gift elsie this
            // bool signalEnd = false;
            // find a pie of bessie's that tastes similar in elsie POV
            // for(auto iter = bs(eTasteVals,st.p.elsieTaste); !signalEnd; iter --){ // Going backwards !!
            //auto iter = bs(eTasteVals,st.p.elsieTaste);
            auto iter = bs4(bessiePies,st.p);
            while(true){
                
                if(iter == bessiePies.end()){
                    break;
                }
                
                //else if(iter == eTasteVals.begin()){
                //    signalEnd = true;
                // }
                    long long taste = (*iter).bessieTaste;
                    //cout << "Checking taste value " << taste << endl;
                    if((st.p.elsieTaste - taste) > D){
                        // out of range
                        break;
                    }

                    // Loop the list
                    // cout << "All like " << taste << endl;
                    // for(Pie newp: bPies[taste]){
                        // Check if we've visited this already
                        Pie newp = *iter;
                        if(visitedB[newp.index]){
                            iter --;
                            continue;
                        }
                        if((st.depth + 1) >= shortestPathB[newp.index]){
                            iter --;
                            continue; // skip
                        }
                        // Otherwise, add it to explore
                        State newSt;
                        newSt.depth = st.depth + 1;
                        newSt.p = newp;
                        newSt.isBessiePie = !st.isBessiePie; // Alternate! Used for easy copy paste
                        shortestPathB[newp.index] = newSt.depth;
                        visitedB[newp.index] = true;
                        // cout << "++ " << newp.bessieTaste << "," << newp.elsieTaste << endl;
                        pq.push(newSt);
                        // signalEnd = true;
                        elsiePies.erase(newp);
                        // break;
                    // }
            }
            // }
        }else{
            // Find a reason for elsie to gift bessie this
            // bool signalEnd = false;
            // find a pie of elsie's that tastes similar in bessie POV
            /*for(auto iter = bs(bTasteVals, st.p.bessieTaste); !signalEnd; iter --){ // Going backwards !!
                if(iter == bTasteVals.end()){
                    break;
                    //signalEnd = true; // about to end
                }else if(iter == bTasteVals.begin()){
                    signalEnd = true;
                }
                long long taste = *iter;
                // cout << "Checking taste value " << taste << endl;
                if((st.p.bessieTaste - taste) > D){
                    // out of range
                    break;
                }

                // Loop the list
                
                for(Pie newp: ePies[taste]){*/
                    // Check if we've visited this already
                auto iter = bs3(elsiePies, st.p);
                while(true){

                    
                    if(iter == elsiePies.end()){
                        // cout << "ERR: Not found " << elsiePies.size() << endl;
                        break;
                    }
                    
                    Pie newp = *iter;
                    long long taste = newp.elsieTaste;

                    if(visitedE[newp.index]){
                        iter --;
                        continue;
                    }
                    if((st.depth + 1) >= shortestPathE[newp.index]){
                        iter --;
                        continue; // skip
                    }
                    if((st.p.elsieTaste - taste) > D){
                        // out of range
                        break;
                    }
                    // Otherwise, add it to explore
                    State newSt;
                    newSt.depth = st.depth + 1;
                    newSt.p = newp;
                    newSt.isBessiePie = !st.isBessiePie; // Alternate! Used for easy copy paste
                    shortestPathE[newp.index] = newSt.depth;
                    visitedE[newp.index] = true;
                    // cout << "++ " << newp.bessieTaste << "," << newp.elsieTaste << endl;
                    pq.push(newSt);
                    // signalEnd = true;
                    bessiePies.erase(newp);
                    // break;
                //}
            // }
                }
        }
    }
}

void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}

int main(int argc, char const *argv[])
{
    if(argc != 2){
        setIO("piepie");
    }
    cin >> N >> D;
    for(int i = 0; i < N; i ++){
        Pie p;
        cin >> p.bessieTaste >> p.elsieTaste;
        p.index = i;
        //Bs.push_back(p);
        // bPies[p.elsieTaste].push_back(p);
        // Bindexes.insert(i);
        // bTasteVals.insert(p.bessieTaste);
        // eTasteVals.insert(p.elsieTaste); // orig
        bessiePies.insert(p);
        if(p.elsieTaste == 0){
            bessieDeadEnds.push_back(p);
        }
    }
    for(int i = 0; i < N; i ++){
        Pie p;
        cin >> p.bessieTaste >> p.elsieTaste;
        p.index = i;
        // Es.push_back(p);
        // ePies[p.bessieTaste].push_back(p);
        // Eindexes.insert(i);
        // bTasteVals.insert(p.bessieTaste); // orig
        // eTasteVals.insert(p.elsieTaste);
        elsiePies.insert(p);
        if(p.bessieTaste == 0){
            elsieDeadEnds.push_back(p);
        }
    }
    fill(shortestPathB, shortestPathB + N,INT32_MAX);
    fill(shortestPathE, shortestPathE + N,INT32_MAX);
    for(const Pie& p : bessieDeadEnds){
        State initState;
        initState.isBessiePie = true;
        initState.p = p;
        pq.push(initState);
        // dij();
    }

    for(const Pie& p : elsieDeadEnds){
        State initState;
        initState.isBessiePie = false;
        initState.p = p;
        pq.push(initState);
        
    }
    dij();
    /*for(int i = 0; i < N; i ++){
        cout << shortestPathB[i] << " ";
    }
    cout << endl;
    for(int i = 0; i < N; i ++){
        cout << shortestPathE[i] << " ";
    }
    cout << endl;*/
    for(int i = 0; i < N; i ++){
        cout << ((shortestPathB[i]!=INT32_MAX)?shortestPathB[i]:-1) << endl;
    }
    

    return 0;
}
