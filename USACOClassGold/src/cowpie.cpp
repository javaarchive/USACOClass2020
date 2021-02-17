#include <iostream>

using namespace std;
int N,M;
int grid[101][101];
int dp[101][101];
bool inBounds(int i, int j){
    
    return (i <= j) && (0 <= i) && (0 <= j) && (i < N) && (j < M);
}
int main(){
    cin >> N >> M;
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < M; j ++){
            cin >> grid[i][j];
            dp[i][j] = grid[i][j];
        }
    }
    // dp[0][0] = grid[0][0];
    // cout << "======================" << endl;
    
    for(int i = 1; i < min(N,M); i ++){
        for(int j = 0; j <= i + 1; j ++){
            dp[i][j] = 0;
        }
        //dp[i][i + 1] = 0;
    }
    /*for(int i = 0; i < N; i ++){
        for(int j = 0; j < i; j ++){
            cout << dp[i][j] << " ";
        }
        cout << endl;
    }*/
    for(int j = 0; j < M; j ++){
        for(int i = 0; i < N; i ++ ){
            if(inBounds(i - 1, j - 1)){
                dp[i][j] = max(dp[i][j], dp[i - 1][j - 1] + grid[i][j]);
            }
            if(inBounds(i + 1, j - 1)){
                dp[i][j] = max(dp[i][j], dp[i + 1][j - 1] + grid[i][j]);
            }
            if(inBounds(i, j - 1)){
                dp[i][j] = max(dp[i][j], dp[i][j - 1] + grid[i][j]);
            }
        }
    }
    //cout << "======================" << endl;
    /*for(int i = 0; i < N; i ++){
        for(int j = 0; j < M; j ++){
            cout << dp[i][j] << " ";
        }
        cout << endl;
    }*/
    cout << dp[N - 1][M - 1];
}