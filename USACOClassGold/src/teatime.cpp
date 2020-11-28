#include <iostream>
#include <map>
#include <vector>
#include <queue>
#define MAXCOWS 1001
using namespace std;
int comp[MAXCOWS] = {0};
int N,M,Q;
map<int,vector<int>> graph;
void classify(){
    int comps = 0;
    bool visited[MAXCOWS] = {false};
    for(int i = 0; i <= N; i ++){
        if(visited[i]){
            continue;
        }
        comps ++;
        comp[i] = comps; // Comps is also id
        queue<int> next;
        next.push(i);
        visited[i] = true;
        while(!next.empty()){
            int node = next.front();
            next.pop();
            comp[node] = comps;
            if(graph[node].empty()){
                continue;
            }
            for(auto iter = graph[node].begin(); iter != graph[node].end(); iter ++){
                if(!visited[*iter]){
                    visited[*iter] = true;
                    next.push(*iter);
                }
            }
        }
    }
}
int main(int argc, const char** argv) {
    cin >> N >> M >> Q;
    for(int i = 0; i < M; i ++){
        int a,b;
        cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }
    classify();
    /*for(int i = 0 ; i < N; i ++){
        cout << comp[i] << " ";
    }*/
    //cout << endl;
    for(int i = 0; i < Q; i ++){
        int a,b;
        cin >> a >> b;
        cout << ((comp[a]==comp[b])?"Y":"N") << endl;
    }
    return 0;
}