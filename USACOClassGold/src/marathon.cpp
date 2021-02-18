#include <iostream>
#define MAXN 502
using namespace std;
int N,K;
int dp[MAXN][MAXN]; // checkpoint,skipped
pair<int,int> points[MAXN];
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
        // cout << AtoB << endl;
        curDist += AtoB;
        dp[0][i] = curDist;
        last = points[i];
    }
    
    for(int l = 0; l < K; l ++){ // cur level
        for(int i = 0; i < N; i ++){ // position
            for(int j = 1; j <= K - l; j ++){ // up K rows
                if(i + l > N){
                    continue;
                }
                dp[l][i + l] = min(dp[l][i],dp[l][i + l]); // base code
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