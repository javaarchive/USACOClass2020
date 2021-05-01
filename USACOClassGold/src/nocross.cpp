#include <iostream>
#define MAXN 1002
using namespace std;

int N;
int leftSide[MAXN]; // Cow IDs on the left side
int rightSide[MAXN]; // Cow IDs on the right side
// Inverse Indexing 
int leftRev[MAXN];
int rightRev[MAXN];

// DP state: Next avalible index to place crossing on both sides
int dp[MAXN][MAXN];

int main(int argc, char const *argv[])
{
    cin >> N;
    int temp;
    for(int i = 0; i < N; i ++){
        cin >> temp;
        leftSide[i] = temp;
        leftRev[temp] = i;
    }
    for(int i = 0; i < N; i ++){
        cin >> temp;
        rightSide[i] = temp;
        rightRev[temp] = i; 
    }
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < N; j ++){
            dp[i][j] = 0;
        }
    }
    // dp[0][0] = 0;
    int best = 0;
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < N; j ++){
            if(i < N){
                int newBreed = rightSide[i];
                for(int k = max(1, newBreed - 4); k <= min(N,newBreed + 4); k ++){
                    if(leftRev[k] >= j){
                        dp[i + 1][leftRev[k] + 1] = max(dp[i][j] + 1,dp[i + 1][leftRev[k] + 1]);
                        best = max(dp[i + 1][leftRev[k] + 1],best);
                    }
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
    cout << best << endl;
    return 0;
}
