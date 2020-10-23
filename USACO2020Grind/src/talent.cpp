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
int solve(){
    sort(weightAndTalent, weightAndTalent + N);
    //cout << "Loop detected size "<< sizeof(dp)/sizeof(dp[0]) << endl;
    for(int i = 0; i < sizeof(dp)/sizeof(dp[0]); i ++){
        //cout << "Iter " << i << endl;
        if(dp[i] != 0 || i == 0){
            //cout << "Layer  1" << endl;
            for(int j = 0; j < sizeof(weightAndTalent)/sizeof(weightAndTalent[0]); j ++){
                 //cout << "Layer  2 " << j<< endl;
                int newVal = dp[i] + weightAndTalent[j].second;
                if(dp[weightAndTalent[j].first + i] == 0 || dp[weightAndTalent[j].first + i] > newVal){
                     //cout << weightAndTalent[j].first + i << endl;
                    dp[weightAndTalent[j].first + i] = newVal;
                }
            }
        }
    }
    for(int i = 0; i < 1000; i ++){
        cout << i << " : " << dp[i] << endl;
    }
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