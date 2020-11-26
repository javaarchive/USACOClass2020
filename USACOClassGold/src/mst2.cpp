#include <iostream>

#define LIMIT 50000

#include <queue>
#include <limits>
#include <map>

using namespace std; 
int V,E;
//   A               dist  B
map<int, vector<pair<int ,int>>> lookup;
int solve(){
    priority_queue<pair<int,int>,vector<pair<int,int>>, greater<pair<int,int>>> pq;
    bool finalized[LIMIT] = {false};
    int keys[LIMIT] = {0};
    fill(keys, keys + V, INT32_MAX);
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
    }
    int output = 0;
    for(int i = 0; i < V; i ++){
        //cout << (i+1) << " " << (prevNode[i] + 1) << endl;
        output += lens[i];
    }
    return output;
}
int main(int argc, const char** argv) {
    cin >> V >> E;
    for(int i = 0 ;i < E; i ++){
        int a,b,len;
        cin >> a >> b >> len;
        a --;
        b --;
        lookup[a].push_back(make_pair(len,b));
        lookup[b].push_back(make_pair(len,a));
    }
    cout << solve();
    return 0;
}