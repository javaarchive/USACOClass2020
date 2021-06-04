#include <iostream>
#define MAXN 101
#define MAXDP 1001

using namespace std;

int N,X,Y,Z;
// X: Insert cost
// Y: Delete Cost
// Z: Replace near
int A[MAXN];
int B[MAXN];
int dp[MAXN][MAXN] = {0};
// int startArr[MAXN];
// int endArr[MAXN];
int main(int argc, char const *argv[])
{
    cin >> N >> X >> Y >> Z;
    for(int i = 0; i < N; i ++){
        cin >> A[i] >> B[i];
    }
    for(int i = 0; i < MAXN; i ++){
        for(int j = 0; j < MAXN; j ++){
            dp[i][j] = INT32_MAX/2; 
        }
    }

    int curRemoveCostInitial = 0;
    int curInsertCostInitial = 0;
    for(int i = 1; i <= N; i ++){
        curRemoveCostInitial += A[i - 1] * Y;
        curInsertCostInitial += B[i - 1] * X;
        dp[i][0] = curRemoveCostInitial;
        dp[0][i] = curInsertCostInitial;
    }
    for(int j = 1; j <= N; j ++){
        for(int i = 1; i <= N; i ++){
            int subCost = 0;
            // bool skipAdd = 0;
            if(A[i] == B[j]){
                if(A[i] < B[j]){
                    // Add
                    subCost = X * (B[j] - A[i]);
                }else{
                    // Remove
                    subCost = Y * (A[i] - B[j]);
                }
            }else{
                // aren't the same so skip
                subCost = 0;
            }
            dp[i][j] = min(min(dp[i][j],dp[i - 1][j - 1] + subCost), min(dp[i - 1][j] + Z, dp[i][j - 1] + Z));
        }
        
    }
    for(int i = 0; i <= N; i ++){
        for(int j = 0; j <= N; j ++){
            cout << dp[i][j] << " ";
        }
        cout << endl;
    }
    return 0;
}
