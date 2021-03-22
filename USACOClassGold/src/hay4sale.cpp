#include <iostream>
#include <algorithm>
#include <set>
#include <queue>
using namespace std;

int C, N;
int dp[50001] = {0};
int bales[5001];
set<int> startLocs;
queue<int> addBuffer;
int main(int argc, const char** argv) {
    cin >> C >> N;
    for(int i = 0; i < N; i ++){
        cin >> bales[i];
    }
    //cout << "sortstart";
    for(int i = 0; i < N; i ++){
        for(int j = C - bales[i]; j >= 0; j --){
            dp[j + bales[i]] = max(dp[j] + bales[i], dp[j + bales[i]]);
        }
    }
    int best = 0;
    for(int i = 0; i <= C; i ++){
        //cout << dp[i] << " ";
        best = max(best, dp[i]);
    }
    cout << best;
    return 0;
}