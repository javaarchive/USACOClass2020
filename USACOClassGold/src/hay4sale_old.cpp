#include <iostream>
#include <algorithm>
#define MAXN 5004
#define MAXC 1004
#define MAXITEMS 5004
#define MAXVAL 5004
using namespace std;
int N,C;
int dp[MAXITEMS][MAXC];
pair<int,int> items[MAXN];
int main(){
    //cout << "read";
    cin >> C >> N;
    //cout << "init";
    for(int i = 0; i <= N; i++){
        for(int j = 0; j <= C; j ++){
            dp[i][j] = 0;
        }
    }
    //cout << "reading";
    for(int i = 0; i < N; i ++){
        int t; // temp
        cin >> t;
        items[i].first = t;
        items[i].second = t;
    }
    //cout << "sorting";
    sort(items, items + N);
    //cout << "looping";
    // dp[1][items[0].first] = items[0].second;
    for(int i = 0; i < N; i ++){
        for(int j = 0; j <= C; j ++){
            if(dp[i][j] == 0 && j != 0){
                continue;
            }
            int newPos = j + items[i].first;
            if(newPos <= C){
                //cout << "setting " << (i + 1) << "," << newPos << " from possibly " << (dp[i][j] + items[i].second) << " compare " << dp[i + 1][newPos] << endl;
                dp[i + 1][newPos] = max(dp[i][j] + items[i].second,dp[i + 1][newPos]);
            }
           // cout << i + 1 << "," << j << " max(" << dp[i + 1][j] << ", " << dp[i][j] << ");" << endl;
            dp[i + 1][j] = max(dp[i + 1][j],dp[i][j]);
        }
    }
    int best = 0;
    /*for(int i = 0; i <= N; i ++){
        for(int j = 0; j <= C; j ++){
            cout << dp[i][j] << " ";
        }
        cout << endl;
    }*/
    //cout << "answering";
    for(int i = 0; i <= C; i ++){
        best = max(best, dp[N][i]);
    }
    cout << best;
}