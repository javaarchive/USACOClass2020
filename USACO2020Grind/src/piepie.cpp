#include <iostream>
#include <map>
#include <vector>
#include <set>
#include <queue>

#define MAXN 100001
#define MAXD 1000000001LL

using namespace std;

int N,D;
struct Pie{
    int index; // Original index
    long long bessieTaste;
    long long elsieTaste;
};
map<long long,vector<Pie>> bPies;
map<long long,vector<Pie>> ePies;
multiset<long long> bTasteVals;
multiset<long long> eTasteVals;
int shortestPathB[MAXN] = {INT32_MAX};
int shortestPathE[MAXN] = {INT32_MAX};
vector<Pie> bessieDeadEnds; // where e=0
vector<Pie> elsieDeadEnds; // where b=0


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
        return exactIT;
    }
    return st.upper_bound(target);
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
            bool signalEnd = false;
            for(auto iter = bs(eTasteVals,st.p.elsieTaste); !signalEnd; iter --){ // Going backwards !!
            //auto iter = bs(eTasteVals,st.p.elsieTaste);
                if(iter == eTasteVals.end()){
                    break;
                }else if(iter == eTasteVals.begin()){
                    signalEnd = true;
                }
                    long long taste = *iter;
                    // cout << "Checking taste value " << taste << endl;
                    if((st.p.elsieTaste - taste) > D){
                        // out of range
                        break;
                    }

                    // Loop the list
                    // cout << "All like " << taste << endl;
                    for(Pie newp: bPies[taste]){
                        // Check if we've visited this already
                        if(visitedB[newp.index]){
                            continue;
                        }
                        if((st.depth + 1) >= shortestPathB[newp.index]){
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
                        signalEnd = true;
                        eTasteVals.erase(taste);
                        break;
                    }
                    
            }
        }else{
            // Find a reason for elsie to gift bessie this
            bool signalEnd = false;
            for(auto iter = bs(bTasteVals, st.p.bessieTaste); !signalEnd; iter --){ // Going backwards !!
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
                for(Pie newp: ePies[taste]){
                    // Check if we've visited this already
                    if(visitedE[newp.index]){
                        continue;
                    }
                    if((st.depth + 1) >= shortestPathE[newp.index]){
                        continue; // skip
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
                    signalEnd = true;
                    bTasteVals.erase(taste);
                    break;
                }
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
        bPies[p.elsieTaste].push_back(p);
        bTasteVals.insert(p.bessieTaste);
        eTasteVals.insert(p.elsieTaste);
        if(p.elsieTaste == 0){
            bessieDeadEnds.push_back(p);
        }
    }
    for(int i = 0; i < N; i ++){
        Pie p;
        cin >> p.bessieTaste >> p.elsieTaste;
        p.index = i;
        ePies[p.bessieTaste].push_back(p);
        bTasteVals.insert(p.bessieTaste);
        eTasteVals.insert(p.elsieTaste);
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
