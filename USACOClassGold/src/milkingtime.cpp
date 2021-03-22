#include <iostream>
#include <algorithm>
#define MAXM 1001
using namespace std;
int N,M,R;
pair<pair<int,int>,int> ranges[MAXM];
int dp[MAXM] = {0};
int main(int argc, const char** argv) {
    cin >> N >> M >> R;
    for(int i = 0; i < M; i ++){
        cin >> ranges[i].first.first >> ranges[i].first.second >> ranges[i].second;
    }
    // cout << "IN!";
    /*sort(ranges, ranges + M, [](pair<pair<int,int>,int> a, pair<pair<int,int>,int> b){
        if(a.first.first == b.first.first){
            return a.first.second < b.first.second;
        }
        return a.first.first < b.first.first;
    });*/
     sort(ranges, ranges + M, [](pair<pair<int,int>,int> a, pair<pair<int,int>,int> b){
        if(a.first.second == b.first.second){
            return a.first.second < b.first.first;
        }
        return a.first.second < b.first.second;
    });

    dp[0] = ranges[0].second; // no restrictions here
    for(int i = 1; i < M; i ++){
        int maxMilk = ranges[i].second;
        for(int j = 0; j < i; j ++){
            if((ranges[j].first.second + R) <= ranges[i].first.first){
                maxMilk = max(dp[j] + ranges[i].second,maxMilk);
            }else{
                // maxMilk = max(dp[j], maxMilk);
            }
            
        }
        dp[i] = maxMilk;
    }
    /*for(int i = 0; i < M; i ++){
        cout << dp[i] << " ";
    }*/
    int maxPRODUCTIVITY = 0;
    for(int i = 0 ;i < M; i ++){
        maxPRODUCTIVITY = max(maxPRODUCTIVITY,dp[i]);
    }
    cout << maxPRODUCTIVITY;
    return 0;
}