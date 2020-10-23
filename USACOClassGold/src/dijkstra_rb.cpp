#include <iostream>
#include <queue>
#include <utility>
#include <limits>

using namespace std;
//                  Dist Node
//                         ,vector<pair<int,int>>,greater<pair<int,int>>
priority_queue<pair<int,int>,vector<pair<int,int>>,greater<pair<int,int>>> nextChoices;
int V, E, S = 1;
vector<int> distsFromSource(101,0);
int prevNode[101];
//          dist node
vector<pair<int,int>> graph[101];
void dijkstra(){
    for(int i = 0; i <= V; i ++){
        distsFromSource[i] = INT32_MAX;
    }
    distsFromSource[S] = 0;
    prevNode[S] = -1; 
    /*for(auto iter = graph[S].begin(); iter != graph[S].end(); iter ++){
        nextChoices.push(*iter);
    }*/
    nextChoices.push(make_pair(0, S)); // needed for path tracable version
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
                prevNode[iter->second] = p.second;
                distsFromSource[iter->second] = p.first + iter->first;
                nextChoices.push(make_pair(p.first + iter->first, iter->second));
            }
        }
    }

    //fill(distsFromSource.begin(), distsFromSource.end(), INT32_MAX);
}
int main(int argc, const char** argv) {
    
    cin >> V >> E;
    for(int i = 0; i < E; i ++){
        int a, b ,d;
        cin >> a >> b >> d;
        graph[a].push_back(make_pair(d,b));
        graph[b].push_back(make_pair(d,a));
    }
    dijkstra();
    for(int i = 1; i <= V; i ++){
        if(distsFromSource[i] == INT32_MAX){
            distsFromSource[i] = -1;
        }
    }
    /*for(int i = 1; i <= V; i ++){
        cout << i << " <- " << prevNode[i] << endl;
    }*/
    int orig = distsFromSource[V];
    vector<int> path;
    int curNode = V;
    while(curNode != 1){
        path.insert(path.begin(), curNode);
        curNode = prevNode[curNode];
    }
    path.insert(path.begin(), 1);
    int best = 0;
    /*for(auto iter = path.begin(); iter != path.end(); iter ++){
        cout << "Tracing: " << *iter << endl;
    }*/
    for(int i = 0; i < (path.size() - 1); i ++){
        //pair<int,int> toModify;
        for(auto iter = graph[path[i]].begin(); iter != graph[path[i]].end(); iter ++){
            if(iter->second == path[i + 1]){
                (*iter).first = (iter -> first) * 2; // Double Length
        dijkstra();
        best = max(distsFromSource[V], best);
        (*iter).first = (iter -> first) / 2; // Double length
                break;
            }
        }
    }
    cout << (best - orig);
    //cout << distsFromSource[V];
    return 0;
}