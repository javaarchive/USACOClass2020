#include <iostream>
#include <algorithm>
#define MAXN 101
#define MAXC 5001
#define MAXT 50001
#define UNKNOWN_BOUND 100000
using namespace std;

int N, T;
pair<int,int> games[MAXN]; // prize/cost

int main(int argc, const char** argv) {
    cin >> N >> T;
    for(int i = 0; i < N; i ++){
        cin >> games[i].first >> games[i].second;
    }
    bool works[UNKNOWN_BOUND] = {false};
    works[0] = true;
    int dp[UNKNOWN_BOUND] = {0};

    for(int i = 0; i < UNKNOWN_BOUND; i ++){
        if(!works[i]){
            continue;
        }
        if(dp[i] >= T){
            cout << i << endl;
            break;
        }
        for(int j = 0; j < N; j ++){
            int prize = games[j].first;
            int cost = games[j].second;
            if((i + cost) >= UNKNOWN_BOUND){
                continue;
            }
            works[(i + cost)] = true;
            dp[(i + cost)] = max(dp[(i + cost)],dp[i] + prize);
            // cout << "Advanced to " << (i + cost) << " = " << dp[(i + cost)] << endl;
        }
    }

    return 0;
}