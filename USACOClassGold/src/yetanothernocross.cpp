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
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < N; j ++){
            // we choose the next on the right
            // and find ranges on the left
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
                // next usable i,j starting point
                for(int l = i + 1; l <= N; l ++){
                    // apply to col
                    // l: row num
                    dp[l][leftIndex + 1] = max(dp[l][leftIndex + 1], dp[i][j] + 1);
                }
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
