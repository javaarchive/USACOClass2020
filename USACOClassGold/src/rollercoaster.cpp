#include <iostream>
#include <tuple>
#include <algorithm>
#define MAXN 10002
#define MAXB 1002
#define MAXL 1002
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
        //return a.minimumX < b.minimumX;
        return (a.minimumX + a.length) < (b.minimumX + b.length); ;
    });
    for(int i = 0; i < N; i ++){
        for(int j = 0; j <= B; j ++){
            dp[i][j] = -1;
        }
    }
    Component first = options[0];
    for(int i = 0; i < N; i ++){
        if(options[i].minimumX == (first.minimumX + first.length)){
            dp[i][first.cost] = first.fun;
        }
    }
    dp[0][0] = 0;
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < B; j ++){
            if(dp[i][j] != -1){
                if(){
                    
                }
                // Use this component
                Component c = options[i];
                int newCost = j + c.cost;
                if(newCost <= B){
                    dp[i + 1][newCost] = max(dp[i + 1][newCost], dp[i][j] + c.fun);
                }
            }
        }
    }
    return 0;
}