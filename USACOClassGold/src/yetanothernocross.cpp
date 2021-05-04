#include <iostream>
#define MAXN 1006
using namespace std;
int N;
int leftSide[MAXN];
int rightSide[MAXN];
int leftInverseIndex[MAXN];
int rightInverseIndex[MAXN];
int dp[MAXN][MAXN];
int main(int argc, char const *argv[])
{
    cin >> N;
    for(int i = 0; i < N; i ++){
        cin >> leftSide[i];
        leftInverseIndex[leftSide[i]] = i;
    }
    for(int i = 0; i < N; i ++){
        cin >> rightSide[i];
        rightInverseIndex[rightSide[i]] = i;
    }
    for(int i = 0; i <= N; i ++){
        for(int j = 0; j <= N; j ++){
            dp[i][j] = -1;
        }
    }
    // r  l
    dp[0][0] = 0;
    dp[0][1] = 0;
    dp[1][0] = 0;
    dp[1][1] = 0;
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < N; j ++){
            // we choose the next on the right
            // and find ranges on the left
            
            if(i > 0 && j > 0){
                dp[i][j] = max(dp[i][j],dp[i - 1][j - 1]);
            }
            if(i >= 1 && j >= 0){
                dp[i][j] = max(dp[i - 1][j],dp[i][j]);
            }
            if(i >= 0 && j >= 1){
                dp[i][j] = max(dp[i][j],dp[i][j - 1]);
            }
            if(dp[i][j] == -1){
                continue; // skip impossible/unreachable
            }
            int rightID = rightSide[i];
            for(int k = max(1, rightID - 4); k <= min(N,rightID + 4); k ++){
                int leftIndex = leftInverseIndex[k];
                // logic to prevent crossing
                // also two crosswalks cannot share a field in their endpoints
                if(leftIndex < j){
                    continue;
                }
                dp[i + 1][leftIndex + 1] = max(dp[i + 1][leftIndex + 1], dp[i][j] + 1);
                dp[i + 1][leftIndex + 2] = max(dp[i + 1][leftIndex + 2], dp[i][j] + 1);
                dp[i + 2][leftIndex + 1] = max(dp[i + 2][leftIndex + 1], dp[i][j] + 1);
                dp[i + 2][leftIndex + 2] = max(dp[i + 2][leftIndex + 2], dp[i][j] + 1);
            }
        }
    }
    /*for(int i = 0; i <= N; i ++){
        for(int j = 0; j <= N; j ++){
            cout << dp[i][j] << " ";
        }
        cout << endl;
    }*/
    int best = 0;
    for(int i = 0; i <= N; i ++){
        for(int j = 0; j <= N; j ++){
            best = max(dp[i][j], best);
        }
    }
    cout << best << endl;
    return 0;
}
