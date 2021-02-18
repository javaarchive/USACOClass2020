#include <iostream>
#define MAXN 502
using namespace std;
int N,K;
int dp[MAXN][MAXN]; // checkpoint,skipped
pair<int,int> points[MAXN];
int distTo[MAXN];
int main(int argc, const char** argv) {
    int N,K;
    cin >> N >> K; 
    
    for(int i = 0; i < N; i ++){
        cin >> points[i].first >> points[i].second;
    }
    pair<int,int> last = points[0];
    int curDist = 0;
    for(int i = 0; i <= N; i ++){
        for(int j = 0; j <= N; j ++){
            dp[i][j] = INT32_MAX;
        }
    }
    dp[0][0] = 0;
    for(int i = 1; i < N; i ++){
        int AtoB = abs(last.first - points[i].first) + abs(last.second - points[i].second);
        distTo[i] = AtoB;
        // cout << AtoB << endl;
        curDist += AtoB;
        if(i == 1){
            dp[0][i] = 0;
            curDist -= AtoB;
        }else{
            dp[0][i] = curDist;
        }
        last = points[i];
    }
    
    for(int i = 1 ; i <= K; i ++){
        for(int j = 0; j < N; j ++){
            if(j > 0 && !(dp[i][j] == INT32_MAX)){
                dp[i][j] = dp[i][j - 1] + distTo[j];
                cout << "overridden " <<  dp[i][j] << endl;
            }
            for(int k = 1; k <= i; k ++){
                if((j - k) < 0){
                    continue;
                }
                cout << dp[i][j] << " << " << dp[i - k][j - k] << endl;
                dp[i][j] = min(dp[i][j],dp[i - k][j - k]);
            }
        }
    }
    for(int i = 0; i <= N; i ++){
        for(int j = 0; j <= N; j ++){
            cout << ((dp[i][j] == INT32_MAX)?99:dp[i][j]) << " ";
        }
        cout << endl;
    }
    // char a;
    //cin >> a;
    return 0;
}