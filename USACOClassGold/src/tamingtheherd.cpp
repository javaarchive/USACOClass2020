#include <iostream>
#define MAXN 106
using namespace std;
int N;
int entries[MAXN];
//  BREAKINS NEXTPOS
int dp[MAXN][MAXN];
int main(int argc, char const *argv[])
{
    cin >> N;
    for(int i = 0; i < N; i ++){
        cin >> entries[i];
    }
    int init = 0;
    for(int i = 1; i <= N + 2; i ++){
        for(int j = 0; j <= N + 2; j ++){
            dp[i][j] = INT32_MAX/2;
        }
    }
    for(int i = 0; i < N; i ++){
        dp[0][i] = 0;
    }
    dp[1][0] = 0;
    for(int i = 0; i < N; i ++){
        if(entries[i] != i){
            init ++;
        }
        dp[1][i + 1] = init;
    }
    
    for(int i = 0; i <= N; i ++){ // breakouts
        for(int j = 0; j < N; j ++){ // start index
            int diffs = 0;
            if(i == 0 && j != 0){
                continue;
            }
            if(j < i){
                continue;
            }
            for(int k = j; k < N; k ++){
                // cout << "entries[" << k << "] = " << entries[k] << " ? " << (k - j) << endl;
                if(entries[k] != (k - j)){
                    diffs ++;
                }
                //cout << "dp[" << (i + 1) << "][" << (k + 1) << "] maybe = " << (dp[i][j] + diffs) << " jumping from " << " breakins: " << i << " and start " << j <<endl;
                //cout << "formula: " << dp[i][j] << " + " << diffs << endl;  
                dp[i + 1][k + 1] = min(dp[i][j] + diffs, dp[i + 1][k + 1]);
            }
        }
    }
    /*for(int i = 0; i <= N + 1; i ++){
        for(int j = 0; j <= N; j ++){
            cout << dp[i][j] << " ";
        }
        cout << endl;
    }*/
    for(int i = 1; i <= N; i ++){
        cout << dp[i][N] << endl;
    }
    return 0;
}
