#include <iostream>
#include <algorithm>
using namespace std;

#define MAXVAL 100001
#define MAXSUM 300004
#define MAXN 5001

long long sVals[MAXN];
int dp[4][MAXSUM];

int N, Q;

int main(int argc, char const *argv[])
{
    cin >> N >> Q;
    dp[0][0] = 0;
    for(int i = 0; i < N; i ++){
        cin >> sVals[i];
        if(sVals[i] >= 0){
            dp[1][sVals[i]] = 1;
        }
    }
    sort(sVals, sVals + N); // Sorting is lovely
    for(int i = 2; i <= 3; i ++){
        for(int j = 0; j < MAXSUM; j ++){
            for(int k = 0; k < N; k ++){
                int newPos = j + sVals[k];
                if(newPos >= MAXSUM){
                    break; // Early terminate because sorting
                }
                if(newPos < 0){
                    continue; // binary search optimization possible
                }
                dp[i][jnewPos += dp[i - 1][j];
            }
        }
    }
    return 0;
}
