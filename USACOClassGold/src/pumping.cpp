#include <iostream>
#include <queue>
#include <vector>
#define MAXNODES 1001


using namespace std;
int graph[MAXNODES][MAXNODES] = {0};
int flowage[MAXNODES][MAXNODES] = {0};
int minDist[1001];
int minFlow[1001];
int N,M;
int dijkstra(int minflow){
    fill(minDist, minDist + MAXNODES, INT32_MAX);
    priority_queue<pair<int,int>, vector<pair<int,int>>, greater<pair<int,int>>> next;
    next.push(make_pair(0,N));
    minDist[N] = 0;
    minFlow[N] = INT32_MAX;
    while(!next.empty()){
        pair<int,int> curPair = next.top();
        int dist = curPair.first;
        int node = curPair.second;
        next.pop();
        for(int i = 0; i <= N; i ++){
            if(graph[node][i] == 0){
                continue;
            }
            if(flowage[node][i] >= minflow && (dist + graph[node][i]) < minDist[i]){
                int newDist = dist + graph[node][i];
                next.push(make_pair(newDist, i));
                minDist[i] = newDist;
                minFlow[i] = min(minFlow[node], flowage[node][i]);
            }
        }
    }
    /*for(int i = 0; i <= N; i ++){
        cout << i << ": " << minFlow[i]<<"/"<<minDist[i] << endl;
    }*/
    return ((double) minFlow[1]/minDist[1])*1000000;
}
int main(int argc, const char** argv) {
    cin >> N >> M;
    for(int i = 0; i < M; i ++){
        int a,b,cost,flow;
        cin >> a >> b >> cost >> flow;
        graph[a][b] = cost;
        graph[b][a] = cost;
        flowage[a][b]  = flow;
        flowage[b][a]  = flow;
    }
    int best = 0;
    for(int i = 0; i < 1001; i ++){
        best = max(dijkstra(i),best);
    }
    cout << best;
    return 0;
}