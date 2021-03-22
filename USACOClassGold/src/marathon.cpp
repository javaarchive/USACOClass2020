#include <iostream>
#define MAXN 502
using namespace std;
int N,K;
int dp[MAXN][MAXN]; // checkpoint,skipped
pair<int,int> points[MAXN];
int distTo[MAXN];
int dist(pair<int,int> a, pair<int,int> b){
    return abs(a.first - b.first) + abs(a.second - b.second);
}
int main(int argc, const char** argv) {
    int N,K;
    cin >> N >> K; 
    
    for(int i = 0; i < N; i ++){
        cin >> points[i].first >> points[i].second;
    }
    pair<int,int> last = points[0];
    int curDist = 0;
    for(int i = 0; i <= K; i ++){
        for(int j = 0; j <= N; j ++){
            dp[i][j] = INT32_MAX/2;
        }
    }
    dp[0][0] = 0;
    for(int i = 1; i < N; i ++){
        int AtoB = abs(last.first - points[i].first) + abs(last.second - points[i].second);
        distTo[i] = AtoB;
        // cout << AtoB << endl;
        curDist += AtoB;
        dp[0][i] = curDist;
        last = points[i];
    }
    
    for(int i = 0; i <= K; i++) {
      for(int j = 0; j < N; j++) {
        for(int l = j + 1; l < N && i + l - j - 1 <= K; l ++) {
          int upI = i + (l - j - 1);
          int upJ = l;
          // cout << "Overrided " << upI << "," << upJ << " from " << i << "," << j << " : " << dp[i][j] << " + " << dist(points[j], points[l]) << endl;
          dp[upI][upJ] = min(dp[upI][upJ], dp[i][j] + dist(points[j], points[l]));
        }
      }
    }
    cout << dp[K][N - 1];
    /*for(int i = 0; i <= N; i ++){
        for(int j = 0; j <= N; j ++){
            cout << ((dp[i][j] == INT32_MAX/2)?99:dp[i][j]) << " ";
        }
        cout << endl;
    }*/
    // char a;
    //cin >> a;
    return 0;
}