

#include <iostream>
#include <vector>
#include <queue>
#include <cmath>

using namespace std;
vector<vector<int>> graph;
int N;

int solve() {
    int out = 0;
    queue<int> next;
    next.push(1);
    for(int i = 1; i <= N; i ++){
        int childCount = graph[i].size();
        if(i != 1){
            childCount --;
        }
        if(childCount == 0){
            continue;
        }
        out+=childCount; // Exclude Parent
        out+=ceil(log2(childCount + 1)); // Exclude Parent
    }
    return out;
}
int main(int argc, const char** argv) {
    cin >> N;
    graph.resize(N + 1);
    for (int i = 0; i < N - 1; i++) {
        int a, b;
        cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }
    cout << solve();
    return 0;
}