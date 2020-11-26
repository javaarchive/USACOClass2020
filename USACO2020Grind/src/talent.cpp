#include <iostream>
#include <utility>
#include <algorithm>

using namespace std;

pair<int, int> weightAndTalent[251];
int dp[100000000] = {0};
int N, W;
bool cmp(const pair<int, int>&i, const pair<int, int>&j) 
{ 
    return i.first< j.first; 
} 
bool works(int y){
    
}
int solve(){
     
    return 0;
}
int main(int argc, const char** argv) {
    cin >> N >> W;
    for(int i = 0; i < N; i ++){
        int w,t;
        cin >> w >> t;
        weightAndTalent[i] = make_pair(w,t);
    }
    
    cout << solve();
    return 0;
}