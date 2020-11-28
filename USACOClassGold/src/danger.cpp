// Not really dangerous
#include <iostream>
#include <vector>
#define MAXN 101

using namespace std;

int N, M;
vector<int> seq;
int graph[MAXN][MAXN];
int solve(){
    int ans = 0;
    for(int k = 1; k <= N; k ++){
        for(int i = 1; i <= N; i ++){
            for(int j = 1; j <= N; j ++){
                graph[i][j] = min(graph[i][k] + graph[k][j], graph[i][j]);
            }
        }
    }
    for(int i = 0; i < M-1; i ++){
        ans += graph[seq[i]][seq[i + 1]];
    }
    return ans;
}
int main(int argc, const char** argv) {
    cin >> N >> M;
    for(int i = 0; i < M; i ++){
        int node;
        cin >> node;
        seq.push_back(node);
    }
    for(int i = 0; i <= N; i ++){
        for(int j = 0; j <= N; j ++){
            graph[i][j] = INT32_MAX/2;
        }
    }
    for(int i = 1; i <= N; i ++){
        for(int j = 1; j <= N; j ++){
            cin >> graph[i][j];
        }
    }
    cout << solve();
    return 0;
}