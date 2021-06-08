#include <iostream>
#include <algorithm>
#include <tuple>
#include <unordered_set>
#include <set>

#define MAXN 21
using namespace std;

long long states[1048577] = {-1L};
long long preweight[1048577] = {0L};

/*struct tuple_hash : public std::unary_function<tuple<long long,long long,long long,int>, std::size_t>
{
 std::size_t operator()(const tuple<long long,long long,long long,int>& k) const
 {
   return std::get<0>(k) ^ std::get<1>(k) ^ std::get<2>(k) ^ get<3>(k);
 }
};*/

void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}

long long N, H;

struct FrisCow{
    long long height;
    long long weight;
    long long strength;
    int id;
    tuple<long long,long long,long long,int> as_tuple(){
        return make_tuple(this->height, this->weight, this->strength, this->id);
    }
};

FrisCow cows[MAXN];

long long weightSum(unordered_multiset<int>* t){
    long long out = 0;
    for(auto it = (*t).begin(); it != (*t).end(); it ++){
        out += cows[*it].weight;
    }
    return out;
}

long long heightSum(unordered_multiset<int>* t){
    long long out = 0;
    for(auto it = (*t).begin(); it != (*t).end(); it ++){
        out += cows[*it].height;
    }
    return out;
}

long long best = 0;

long long recurSolve(unordered_multiset<int> C, int state){
    if(C.empty()){
        return 536870912LL * 536870912LL; //* 16LL; // 2^29 * 2^29 * 2^4
    }
    if(states[state] > -1LL){
        // cout << "Early term " << states[state] << endl;
        return states[state];
    }
    long long ans = 0L;
    for(auto iter = C.begin(); iter != C.end(); iter ++){
        unordered_multiset<int> Cclone(C);
        Cclone.erase(*iter);
        long long exprA = recurSolve(Cclone, state - (1 << *iter));
        long long exprB = cows[*iter].strength - weightSum(&Cclone);//preweight[state - (1 << get<3>(*iter))];
        // cout << "min(" << exprA << "," << exprB << ")" << endl;
        ans = max(min(
            exprA, 
            exprB
            ), ans);
        // cout << "Pending ans " << ans << endl;
    }
    states[state] = ans;
    if(heightSum(&C) >= H){
        best = max(best, ans);
    }
    cout << "recur(" << state << ") = " << ans << endl;
    return ans;
}

void sumGen(int pos, int state, int curSum){
    if(pos == N){
        preweight[state] = curSum;
    }else{
        int elem = cows[pos].weight;
        sumGen(pos + 1, (state << 1) + 1,curSum + elem);
        sumGen(pos + 1, state << 1      ,curSum);
    }
}

int main(int argc, char const *argv[])
{
    // setIO("guard");
    cin >> N >> H;
    int total = 1 << N;
    unordered_multiset<int> everything;
    for(int i = 0; i < N; i ++){
        cin >> cows[i].height >> cows[i].weight >> cows[i].strength;
        //cows[i].id = i;
        everything.insert(i);
        //everything.insert(cows[i].as_tuple());
    }
    for(int i = 0; i <= (1 << (N)); i ++){
        states[i] = -1LL;
    }
    // cout << everything.size() << endl;

    
    // algo

    

    /*sort(cows, cows + N, [](FrisCow a, FrisCow b){
        if(a.strength == b.strength){
            if(a.weight == b.weight){
                return a.height > b.height;
            }
            return a.weight > b.weight;
        }
        return a.strength > b.strength;
    });*/
    // cout << everything.empty() << endl;
    sumGen(0,0,0);
    recurSolve(everything, total);
    // cout <<  << endl;
    cout << best << endl;
    /*for(int i = 0; i <= (1 << (N)); i ++){
        cout << i << " : " << preweight[i] << endl;
    }*/
    return 0;
}
