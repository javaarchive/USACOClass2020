#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
using namespace std;
int N;
int M;
//          time,count
vector<pair<int ,int  >> cows;
map<int,int> freq;
void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}
int main(int argc, const char** argv) {
    setIO("pairup");
    cin >> N;
    cows.resize(N);
    
    M = 0;
    for(int i = 0; i < N; i ++){
        int amount,time;
        cin >> amount >> time;
        cows[i] = make_pair(time,amount);
        freq[time] = amount;
        M += amount;
    }
    // cout << "Sorting";
    sort(cows.begin(),cows.end());
    int forward = 0, backward = N - 1;
    int maxTime = 0;
    // cout << "Var init";
    while(freq[cows[forward].first] > 0 || freq[cows[backward].first] > 0){
        int fAmount = freq[cows[forward].first];
        int bAmount = freq[cows[backward].first];
        int fTime = cows[forward].first;
        int bTime = cows[backward].first;
        int subAmount = min(bAmount,fAmount);
        int sum = fTime + bTime;
        maxTime = max(sum, maxTime);
       //  cout << "subtracting " << subAmount << endl; 
        freq[fTime] -= subAmount;
        freq[bTime] -= subAmount;
        if(freq[cows[forward].first] == 0 && forward < N-1 && freq[cows[forward + 1].first] > 0){
            forward ++;
        }
        if(freq[cows[backward].first] == 0 && backward > 0 && freq[cows[backward - 1].first] > 0){
            backward --;
        }
    }
    cout << maxTime;
    return 0;
}