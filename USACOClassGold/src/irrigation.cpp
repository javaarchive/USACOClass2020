#include <iostream>

#define LIMIT 2000

#include <queue>
#include <limits>
#include <map>
#include <cmath>

using namespace std; 
int N,C;
//   A               dist  B
map<int, vector<pair<int ,int>>> lookup;
pair<int,int> points[2000];
int solve(){
    priority_queue<pair<int,int>,vector<pair<int,int>>, greater<pair<int,int>>> pq;
    bool finalized[LIMIT] = {false};
    int keys[LIMIT] = {0};
    fill(keys, keys + N, INT32_MAX);
    int prevNode[LIMIT] = {-1};
    keys[0] = 0;
    pq.push(make_pair(0,0));
    int lens[LIMIT] = {0};
    while(!pq.empty()){
        pair<int,int> minkey = pq.top();
        pq.pop();
        //cout << "Visit " << minkey.first << " " << minkey.second << endl;
        finalized[minkey.second] = true;
        for(auto iter = lookup[minkey.second].begin(); iter != lookup[minkey.second].end(); iter ++){
            if(!finalized[iter->second] && keys[iter->second] > iter->first){
                keys[iter->second] = iter->first;
                pq.push(make_pair(iter->first, iter->second));
                prevNode[iter->second] = minkey.second;
                lens[iter->second] = iter->first;
            }
        }
        if(lookup[minkey.second].empty()){
            return -1;
        }
    }
    int output = 0;
    int hasParent = 0;
    for(int i = 0; i < N; i ++){
        if(prevNode[i] >= 0){
            hasParent ++;
        }
    }
    if(hasParent != N-1){
        return -1;
    }
    for(int i = 0; i < N; i ++){
        //cout << "Prev " <<(i+1) << " " << (prevNode[i] + 1) << endl;
        output += lens[i];
    }
    return output;
}
int dist(pair<int,int> a, pair<int,int> b){
    int A = a.first - b.first,B = a.second - b.second;
    return A*A + B*B;
}
int main(int argc, const char** argv) {
    cin >> N >> C;
    for(int i = 0 ;i < N; i ++){
        cin >> points[i].first >> points[i].second;
    }
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < N; j ++){
            int len = dist(points[i],points[j]);
            if(len >= C){
                lookup[i].push_back(make_pair(len, j));
            }
        }
    }
    for(int i = 0; i < N; i ++){
        if(lookup[i].empty()){
            cout << -1;
            return 0;
        }
    }
    cout << solve();
    return 0;
}