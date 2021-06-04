#include <iostream>
#define MAXN 100003
using namespace std;
int N,M;
int dp[MAXN] = {0};
int D[MAXN];

int main(int argc, const char** argv) {
    cin >> N >> M;
    for(int i=0; i < N; i ++){
        cin >> D[i];
    }
    for(int i = 0; i < N; i ++){
        int totalDist = 0;
        for(int j = 0; j <= M && (i + j) < N && (i + (j+1) * 2) <= (N + 1); j ++){
            /*if(j == 0 && i != 0){
                continue;
            }*/
            totalDist += D[i + j];
            dp[i + (j + 1)*2] = max(dp[i] + totalDist, max(dp[i + (j + 1) * 2], dp[i + (j + 1)*2 -1]));
        }
    }
    /*for(int i = 0; i <= N; i ++){
        cout << dp[i] << " ";
    }
    cout << endl;*/
    int best = 0;
    for(int i = 0; i <= N; i ++){
        best = max(best, dp[i]);
    }
    cout << best;
    return 0;
}