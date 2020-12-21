#include <iostream>
#include <vector>
#include <queue>
#include <cmath>

using namespace std;
vector<vector<int>> graph;
int N;

int solve(){
    int out = 0;
    queue<int> next;
    next.push(1);
    bool visited[2501] = {false};
    visited[1] = true;
    while(!next.empty()){
        int node = next.front();
        next.pop();
        int n = 0;
        for(auto iter = graph[node].begin(); iter != graph[node].end(); iter ++){
            if(visited[*iter]){
                continue; // Do not traverse up the tree
            }
            n ++;
            visited[*iter] = true;
            next.push(*iter);
        }
        if(n > 0){
            //cout << ceil(log2(n)) << " " << n << endl;
            out += ceil(log2(n));
            out += n;
        }
       
    }
    return out;
}
int main(int argc, const char** argv) {
    cin >> N;
    graph.resize(N + 1);
    for(int i = 0; i < N-1; i ++){
        int a,b;
        cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }
    cout << solve();
    return 0;  
}