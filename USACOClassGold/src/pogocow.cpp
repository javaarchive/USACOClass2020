#include <iostream>
#include <vector>
#include <algorithm>
#define MAXN 1002
using namespace std;
int N;
int dp[MAXN][MAXN];
int dpRev[MAXN][MAXN];
vector<pair<int,int>> targets;
int main(int argc, const char** argv) {
    // cout << "INIT" << endl;
    cin >> N;
    targets.reserve(N);
    // cout << "INIT READ" << endl;
    for(int i = 0; i < N; i ++){
        pair<int,int> target;
        cin >> target.first >> target.second; 
        targets.push_back(target);  
    }
    // targets.push_back(make_pair(0,0)); // Starting point
    // cout << "SORTING" << endl;
    sort(targets.begin(), targets.end());
    // cout << "FILLNG" << endl;
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < N; j ++){
            dp[i][j] = 0; // minimal
            dpRev[i][j] = 0; // minimal
        }
    }
    /*for(int i = 1; i < N; i ++){
        dp[0][i] = targets[i].second; 
    }*/
    // cout << "INITIAL" << endl;
    for(int i = 0 ;i < N; i ++){
        for(int j = i + 1; j < N; j ++){
            dp[i][j] = targets[i].second + targets[j].second;
            dpRev[j][i] = targets[i].second + targets[j].second;
        }
    }
    // cout << "GOING TO MAINS" << endl;
    for(int i = 0; i < N; i ++){
        for(int j = i + 1; j < N; j ++){
            for(int k = j + 1; k < N; k ++){
                // i < j < k 
                /*if(!(i < j && j < k)){
                    continue;
                }*/
                if(abs(targets[j].first - targets[i].first) <= abs(targets[k].first - targets[j].first)){
                    dp[j][k] = max(dp[j][k], dp[i][j] + targets[k].second);
                }
            }
        }
    }
    
    for(int i = N - 1; i >= 0; i --){
        for(int j = i - 1; j >= 0; j --){
            for(int k = j - 1; k >= 0; k --){
                // k < j < i
                /*if(!(k < j && j < i)){
                    continue;
                }*/
                if(abs(targets[j].first - targets[i].first) <= abs(targets[k].first - targets[j].first)){
                    dpRev[j][k] = max(dpRev[j][k], dpRev[i][j] + targets[k].second);
                }
            }
        }
    }
    int ans = 0;
    /*for(int i = 0; i < N; i ++){
        for(int j = 0; j < N; j ++){
            cout << dp[i][j] << " ";
        }
        cout << endl;
    }
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < N; j ++){
            cout << dpRev[i][j] << " ";
        }
        cout << endl;
    }*/
    /*for(int i = 0; i < N; i ++){
        ans = max(dp[i][N], ans);
    }
    for(int i = 0; i < N; i ++){
        ans = max(dpRev[i][N], ans);
    }*/
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < N; j ++){
            ans = max(ans, dp[i][j]);
            ans = max(ans, dpRev[i][j]);
        }
    }
    cout << ans << endl;
    return 0;
}