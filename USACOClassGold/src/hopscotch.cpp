#include <iostream>
using namespace std;
int R,C,K;
int grid[1001][1001];
long long dp[1001][1001];
int main(){
    cin >> R >> C >> K;
    for(int i = 0; i < R; i ++){
        for(int j = 0; j < C; j ++){
            cin >> grid[i][j];
            dp[i][j] = 0;
        }
    }
    dp[0][0] = 1;
    // dp[0][1] = 0;
    // dp[1][0] = 0;
    for(int i = 1; i < R; i ++){
        for(int j = 1; j < C; j ++){
            for(int x1 = i - 1; x1 >= 0; x1 --){
                for(int y1 = j - 1; y1 >= 0; y1 --){
                    if(grid[i][j] == grid[x1][y1]){
                        continue;
                    }
                    dp[i][j] = (dp[i][j] + dp[x1][y1]) % 1000000007;
                }
            }
        }
    }
    /*for(int i = 0; i < R; i ++){
        for(int j = 0; j < C; j ++){
            cout << dp[i][j] << " ";
        }
        cout << endl;
    }*/
    cout << dp[R - 1][C - 1];
}