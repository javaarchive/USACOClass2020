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
    //                 val  depthat
    unordered_map<long long,int    > seen;
    seen[baseVal] = 1; // 0 means not found
    bfsQueue.push(make_pair(1, baseVal));
    while(!bfsQueue.empty()){
        pair<int,long long> cur = bfsQueue.top();
        cout << cur.second << " depth: " << (cur.first - 1) << endl;
        bfsQueue.pop();
        if(cur.second == b){
            cout << (movesBase + cur.first - 1) << endl;
            return;
        }
        if(cur.second * 2 <= b && (seen[cur.second * 2] == 0 || seen[cur.second * 2] > (cur.first + 1)) ){
            bfsQueue.push(make_pair(cur.first + 1, cur.second * 2));
            seen[cur.second * 2] = cur.first + 1;
        }
        
        if(cur.second % 2 == 0 && (seen[cur.second/2] > (cur.first + 1) || seen[cur.second/2] == 0) && cur.second >= 2){
            bfsQueue.push(make_pair(cur.first + 1, cur.second/2));
            seen[cur.second/2] = cur.first + 1;
        }
        long long diff = pow2above(cur.second) - cur.second;
        if(diff == 0){
            continue;
        }
        bfsQueue.push(make_pair(cur.first + diff, cur.second + diff));
        seen[cur.second + diff] = cur.first + diff;       
    }
    cout << "ERROR: NO ANS" << endl;
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
