#include <iostream>
using namespace std;
int N;
int grid[101][101];
int dp[101][101];
int main(int argc, const char** argv) {
    cin >> N;
    
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < (i + 1); j ++){
            cin >> grid[i][j];
            dp[i][j] = grid[i][j];
        }
    }
    for(int i = N - 2; i >= 0; i --){
        for(int j = 0; j <= i; j ++){
            dp[i][j] = max(max(dp[i + 1][j],dp[i + 1][j + 1]) + grid[i][j],dp[i][j])
        }
    }
    cout << dp[0][0];

    return 0;
}