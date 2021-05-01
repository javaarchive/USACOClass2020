#include <iostream>
#include <algorithm>
#define SENSIBLE_LIMIT 2000000
#define MAXN 51
#define MAXK 202

using namespace std;

int N, K;
int stamps[MAXN] = {0};
int dp[SENSIBLE_LIMIT] = {0};

int main(int argc, char const *argv[])
{
    cin >> K >> N;
    for(int i = 0; i < N; i ++){
        cin >> stamps[i];
    }
    sort(stamps, stamps + N);
    fill(dp, dp + SENSIBLE_LIMIT, -1);
    
    dp[0] = 0;
    for(int i = 1; i < SENSIBLE_LIMIT; i ++){
        for(int j = 0; j < N; j ++){
            if((i - stamps[j]) >= 0 && dp[i - stamps[j]] != -1 && dp[i - stamps[j]] < K){
                if(dp[i] == -1){
                    dp[i] = dp[i - stamps[j]] + 1;
                }else{
                    dp[i] = min(dp[i - stamps[j]] + 1, dp[i]);
                }
            }
        }
    }
    int streak = 0;
    for(int i = 1; i < SENSIBLE_LIMIT; i ++){
        if(dp[i] != -1){
            // cout << i << " " << dp[i] << endl;
            streak ++;
        }else{
            break;
        }
    }
    cout << streak << endl;
    return 0;
}
