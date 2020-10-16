#include <iostream>
#include <queue>
#include <utility>
#include <limits>

using namespace std;
//                  Dist Node
//                         ,vector<pair<int,int>>,greater<pair<int,int>>
priority_queue<pair<int,int>,vector<pair<int,int>>,greater<pair<int,int>>> nextChoices;
int V, E, S;
vector<int> distsFromSource;
//          dist node
vector<pair<int,int>> graph[2501];
void solve(){
    for(int i = 0; i <= V; i ++){
        distsFromSource.push_back(INT32_MAX);
    }
    distsFromSource[S] = 0;
    for(auto iter = graph[S].begin(); iter != graph[S].end(); iter ++){
        nextChoices.push(*iter);
    }
    //cout << "S";
    while(!nextChoices.empty()){
        pair<int,int> p = nextChoices.top();
        //cout << p.first << " at node " << p.second << endl;
        nextChoices.pop();
        if(distsFromSource[p.second] < p.first){
            continue;
        }
        distsFromSource[p.second] = p.first;
        for(auto iter = graph[p.second].begin(); iter != graph[p.second].end(); iter ++){
            if((p.first + iter->first)< distsFromSource[iter->second]){
                nextChoices.push(make_pair(p.first + iter->first, iter->second));
            }
        }
    }

    //fill(distsFromSource.begin(), distsFromSource.end(), INT32_MAX);
}
int main(int argc, const char** argv) {
    cin >> V >> E >> S;
    for(int i = 0; i < E; i ++){
        int a, b ,d;
        cin >> a >> b >> d;
        graph[a].push_back(make_pair(d,b));
        graph[b].push_back(make_pair(d,a));
    }
    solve();
    for(int i = 1; i <= V; i ++){
        if(distsFromSource[i] == INT32_MAX){
            distsFromSource[i] = -1;
        }
    }
    for(int i = 1; i <= V; i ++){
        cout << distsFromSource[i] << endl;
    }
    //cout << distsFromSource[V];
    return 0;
}