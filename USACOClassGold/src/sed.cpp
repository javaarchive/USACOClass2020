#include <iostream>
int dp[1002][1002];
using namespace std;
int main(int argc, const char** argv) {
    string a,b;
    cin >> a;
    cin >> b;
    int N = a.length();
    int M = b.length();
    for(int i = 0; i <= N; i ++){
        for(int j = 0; j <= M; j ++){
            dp[i][j] = 0;
        }
    }
    for(int i = 0; i <= N; i ++){
        dp[i][0] = i;
    }
    for(int j = 0; j <= M; j ++){
        dp[0][j] = j;
    }
     /*for(int i = 0; i <= N; i ++){
        for(int j = 0; j <= M; j ++){
            cout << dp[i][j] << " ";
        }
        cout << endl;
    }*/
    for(int j = 1; j <= M; j ++){
        for(int i = 1; i <= N; i ++){
            /*if(i <= 2 && j <= 2){
                cout << i << ", " << j << " (" << dp[i - 1][j] + 1 << " " << dp[i][j - 1] + 1 << " " << (dp[i - 1][j - 1] ) << " + "<< ( ((a[i - 1] == b[j - 1])?0:1)) << ") " << endl;
            }*/
            int replaceCost = 1;
            if(a[i - 1] == b[j - 1]){
                replaceCost = 0;
            }
            dp[i][j] = min(dp[i - 1][j] + 1, min(dp[i][j - 1] + 1, (dp[i - 1][j - 1] + replaceCost )));
        }
    }
    /*for(int i = 0; i <= N; i ++){
        for(int j = 0; j <= M; j ++){
            cout << dp[i][j] << " ";
        }
        cout << endl;
    }*/
    cout << dp[N][M] << endl;
    return 0;
}