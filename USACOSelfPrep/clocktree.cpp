#include <iostream>
#include <vector>

using namespace std;
int N;
int clocks[2501];
vector<int> graphends;
vector<int> connections[2501];
void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}
bool dfs(int* curState,bool* visited, int a, int b){
    int toAdd = 12 - curState[a];
    curState[a] += toAdd;
    curState[b] += toAdd;
    visited[a] = true;
    visited[b] = true;
    if(connections[b].size() == 1){
        // Edge
        if(curState[b] != 12){
            return false;
        }
    }
    for(auto iter = connections[b].begin(); iter != connections[b].end(); iter ++){
        int neighbor = *iter;
        if(visited[neighbor]){
            continue;
        }
        visited[neighbor] = true;
        if(!dfs(curState, visited, b, neighbor)){
            return false;
        }
    }
    return true;
}
void dfsEven(bool* visited ,bool* startable, bool even, int node){
    if(even){
        startable[node] = true;
    }
    for(auto iter = connections[node].begin(); iter != connections[node].end(); iter ++){
        int next = *iter;
        if(!visited[next]){
            visited[next] = true;
            dfsEven(visited, startable, !even, next);
        }
    }
}
int solve(){
    bool startable[2501] = {false};
    
    vector<pair<int,int>> toTry;
    for(auto iter = graphends.begin(); iter != graphends.end(); iter ++){
        toTry.push_back(make_pair(*iter,connections[*iter][0]));
    }
    
    for(auto iter = toTry.begin(); iter != toTry.end(); iter ++){
        int localClocks[2501];
        bool visited[2501] = {false};
        copy(begin(clocks), end(clocks), begin(localClocks));
        pair<int,int> p = *iter;
        //int toAdd = 12 - clocks[p.first];
        //localClocks[p.first] += toAdd;
        //localClocks[p.second] += toAdd;
        if(dfs(localClocks,visited,p.first,p.second)){
            bool visited2[2501] = {false};
            dfsEven(visited2, startable, true, p.first);
        }
    }   
    int out = 0;
    for(int i = 0; i < N; i ++){
        if(startable[i]){
            out ++;
        }
    }
    return out;
}
int main(int argc, const char** argv) {
    setIO("clocktree");
    cin >> N; 
    for(int i = 0;i < N; i ++){
        cin >> clocks[i];
    }
    for(int i = 0; i < (N - 1); i ++){
        int a,b;
        cin >> a >> b;
        a --; b --;
        connections[a].push_back(b);
        connections[b].push_back(a);
    }
    for(int i = 0; i < N; i ++){
        if(connections[i].size() == 1){
            graphends.push_back(i);
        }
    }
    cout << solve();
    return 0;
}