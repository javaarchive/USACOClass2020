#include <iostream>
#define MOD 5000011
using namespace std;
int N, K;
int dp[100001];
int main(int argc, char const *argv[])
{
    cin >> N >> K;
    dp[0] = 1;
    dp[1] = 2; // Bull or cow it doesn't matter 
    for(int i = 2; i <= N; i ++){
        dp[i] = (dp[i] + dp[i - 1]) % MOD; // place yet another cow
        if((i - K - 1) < 0){
            dp[i] = (dp[i] + 1) % MOD; // this one bull
            continue;
        }
        dp[i] = (dp[i] + dp[i - K - 1]) % MOD;
    }
    /*for(int i = 0; i <= N; i ++){
        cout << dp[i] << " ";
    }
    cout << endl;*/
    cout << dp[N] << endl;
    return 0;
}
