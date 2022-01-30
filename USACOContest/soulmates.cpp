#include <iostream>
#include <bits/stdc++.h>

using namespace std;

vector<int> pow2(62);
void genPowTable(){
    long long val = 1;
    for(int i = 0; i < 62; i ++){
        pow2[i] = val;
        val *= 2;
    }
}

int pow2above(long long l){
    for(int i = 1; i < 62; i ++){
        if(l <= pow2[i] && pow2[i - 1] < l){
            return pow2[i];
        }
    }
}


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
    //                  depth  val
    priority_queue<pair<int  ,long long>, vector<pair<int  ,long long>>, greater<pair<int  ,long long>> > bfsQueue;
    unordered_set<long long> seen;
    seen.insert(baseVal);
    bfsQueue.push(make_pair(0, baseVal));
    while(!bfsQueue.empty()){
        pair<int,long long> cur = bfsQueue.top();
        bfsQueue.pop();
        if(cur.second == b){
            cout << (movesBase + cur.first) << endl;
            return;
        }
        if(cur.second * 2 <= b && seen.find(cur.second * 2) == seen.end() ){
            bfsQueue.push(make_pair(cur.first + 1, cur.second * 2));
            seen.insert(cur.second * 2);
        }

        
        if(cur.second % 2 == 0 && seen.find(cur.second/2) == seen.end() && cur.second >= 2){
            bfsQueue.push(make_pair(cur.first + 1, cur.second/2));
            seen.insert(cur.second/2);
        }
        long long diff = cur.second - pow2above(cur.second);
        bfsQueue.push(make_pair(cur.first + diff, cur.second + diff));
        seen.insert(cur.second + diff);       
    }

}

int main(int argc, char const *argv[])
{
    int N;
    cin >> N;
    genPowTable();
    while(N --){
        solve();
    }
    return 0;
}
