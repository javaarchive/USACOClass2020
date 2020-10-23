#include <iostream>
#include <math.h>
using namespace std;
int A,B,T;
void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}
int dp[2][5000002];
int solve(){
    dp[0][0] = 1;
    for(int count = 0; count < 2; count ++){
        for(int i = 0; i <= T; i ++){
            if(dp[count][i] != 0){
                if((i+A) <= T) {
					dp[count][i+A] = 1;//i + 1;
				}
				if((i+B) <= T) {
					dp[count][i+B] = 1;//i + 1;
				}
                if(count == 0){
                    // Can drink
                    int idx = floor(i/2);
                    dp[1][idx] = 1;//i + 1;
                }
            }
        }
    }
    for(int i = T; i >= 0; i --){
        if(dp[0][i] != 0){
            return i;
        }
        if(dp[1][i] != 0){
            return i;
        }
    }
    return -1;
    //return -1;
}
int main(int argc, const char** argv) {
    setIO("feast");
    cin >> T >> A >> B;
    cout << solve();
    return 0;
}
