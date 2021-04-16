#include <iostream>
#include <tuple>
#include <algorithm>
#define MAXN 10001
#define MAXB 1001
#define MAXL 1001
using namespace std;
// DP dimensions, position and cost
int dp[MAXL][MAXB];

int L,N,B;
// L: Length
// N: tracks
// B: budget
struct Component{
    int length;
    int minimumX;
    int cost;
    int fun;
};
Component options[MAXN];
int main(int argc, const char** argv) {
    cin >> L >> N >> B;
    for(int i = 0; i < N; i ++){
        int startX, length, fun, cost;
        cin >> startX >> length >> fun >> cost;
        options[i].length = length;
        options[i].cost = cost;
        options[i].minimumX = startX;
        options[i].fun = fun;
    }
    sort(options, options + N, [](Component a, Component b){
        if(a.fun == b.fun){
            return a.cost < b.cost;
        }
        return a.fun > b.fun; // most fun first
    });
    for(int i = 0; i <= L; i ++){
        for(int j = 0; j <= MAXB; j ++){
            dp[i][j] = -1; // best init value and nicest
        }
    }
    dp[0][0] = 0;
    for(int j = 0; j < B; j ++){
        for(int i = 0; i < L; i ++){
            if(dp[i][j] == -1){
                continue;
            }
            for(int k = 0; k < N; k ++){
                if(i < options[k].minimumX){
                    continue;
                }
                int newCost = j + options[k].cost;
                int newLength = i + options[k].length;
                if(newCost > B || newLength > L){
                    continue;
                }
                dp[newLength][newCost] = max(dp[newLength][newCost], dp[i][j] + options[k].fun);
            }
        }
    }
    int best = -1;
    for(int i = 0; i <= L; i ++){
        for(int j = 0; j <= B; j ++){
            best = max(dp[i][j],best);
        }
    }
    for(int i = 0; i <= L; i ++){
        for(int j = 0; j <= B; j ++){
            cout << dp[i][j] << " ";
        }
        cout << endl;
    }
    cout << best << endl;
    return 0;
}