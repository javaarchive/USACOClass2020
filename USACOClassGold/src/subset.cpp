#include <iostream>
#define MAXSUM 781
using namespace std;
int N;
long long dp[MAXSUM] = {0};
int main(int argc, const char** argv) {
    cin >> N;
    dp[0] = 0; // sum of 0
    if((N * (N + 1)/2) % 2 == 1){
        cout << 0; // impossible case
        return 0;
    }
    // dp[1] = 1;
    for(int i = N; i > 0; i --){
        dp[i] ++;
        // cout << "A: " << i << ": + " << 1 << endl;
        for(int j = (N * (N + 1)/2); j >= 0; j --){
            
            if(dp[j] > 0 && (j != i)){
                if((i + j) < MAXSUM){
                    //cout << "B: " << i + j << ": + " << dp[j] << " from " << j << endl;
                    dp[(i + j)] += dp[j]; // ways to get to j
                }
            }
        }
    }
    /*for(int i = 0; i < N * (N - 1); i ++){
        cout << dp[i] << " ";
    }*/
    // cout << endl;
    cout << dp[(N * (N + 1))/4]/2 << endl;
    return 0;
}