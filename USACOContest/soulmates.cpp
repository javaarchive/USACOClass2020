#include <iostream>
#include <bits/stdc++.h>

using namespace std;


void solve(){
    int movesBase = 0;
    // Lower enough
    long long a,b;
    cin >> a >> b;
    long long operand = a;
    while(operand > b){
        if(operand % 2 == 1){
            operand ++;
            movesBase ++;
        }
        operand /= 2;
        movesBase ++;
    }
    long long baseVal = operand;
    cout << "Reduced to " << baseVal << " after " << movesBase << " moves " << endl;
    // BFS time
    //         depth  val
    queue<pair<int  ,long long>> bfsQueue;
    unordered_set<long long> seen;
    seen.insert(baseVal);
    bfsQueue.push(make_pair(0, baseVal));
    while(!bfsQueue.empty()){
        pair<int,long long> cur = bfsQueue.front();
        bfsQueue.pop();
        if(cur.second == b){
            cout << (movesBase + cur.first) << endl;
            return;
        }
        if(cur.second * 2 <= b && seen.find(cur.second * 2) == seen.end() ){
            bfsQueue.push(make_pair(cur.first + 1, cur.second * 2));
            seen.insert(cur.second * 2);
        }else if(seen.find(cur.second + 1) == seen.end() ){
            long long dist = b - cur.second;
            bfsQueue.push(make_pair(cur.first + 1, cur.second + 1));
            seen.insert(cur.second + 1);
        }
        if(cur.second % 2 == 0 && seen.find(cur.second/2) == seen.end() && cur.second >= 2){
            bfsQueue.push(make_pair(cur.first + 1, cur.second/2));
            seen.insert(cur.second/2);
        }
       
    }

}

int main(int argc, char const *argv[])
{
    int N;
    cin >> N;
    while(N --){
        solve();
    }
    return 0;
}
