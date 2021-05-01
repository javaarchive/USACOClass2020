#include <iostream>
#include <algorithm>
#define MAXN 30000
using namespace std;
int N;
int line[MAXN];
int dp[3][MAXN];

int main(int argc, const char** argv) {
    cin >> N;
    for(int i = 0; i < N; i ++){
        cin >> line[i];
        line[i] --;
    }
    int best = INT32_MAX;
    // Ascending
    dp[0][0] = line[0] != 0;
    dp[1][0] = line[0] != 1;
    dp[2][0] = line[0] != 2;
    for(int i = 1; i < N; i ++){
        dp[0][i] = INT32_MAX;
        dp[1][i] = INT32_MAX;
        dp[2][i] = INT32_MAX;
    }
    for(int i = 1; i < N; i ++){
        for(int j = 0; j < 3; j ++){
            for(int k = j; k < 3; k ++){
                dp[k][i] = min(dp[k][i], dp[j][i - 1] + (line[i] != k));
            }
        }
    }
    /*for(int i = 0; i < 3; i ++){
        for(int j = 0; j < N; j ++){
            cout << dp[i][j] << " ";
        }
        cout << endl;
    }*/
    best = min(dp[0][N - 1], best);
    best = min(dp[1][N - 1], best);
    best = min(dp[2][N - 1], best);
    // Descending
    reverse(line, line + N);
    dp[0][0] = line[0] != 0;
    dp[1][0] = line[0] != 1;
    dp[2][0] = line[0] != 2;
    for(int i = 1; i < N; i ++){
        dp[0][i] = INT32_MAX;
        dp[1][i] = INT32_MAX;
        dp[2][i] = INT32_MAX;
    }
    for(int i = 1; i < N; i ++){
        for(int j = 0; j < 3; j ++){
            for(int k = j; k < 3; k ++){
                dp[k][i] = min(dp[k][i], dp[j][i - 1] + (line[i] != k));
            }
        }
    }
    /*for(int i = 0; i < 3; i ++){
        for(int j = 0; j < N; j ++){
            cout << dp[i][j] << " ";
        }
        cout << endl;
    }*/
    best = min(dp[0][N - 1], best);
    best = min(dp[1][N - 1], best);
    best = min(dp[2][N - 1], best);
    
    cout << best << endl;
    return 0;
}