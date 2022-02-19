#include <bits/stdc++.h>

using namespace std;

int N, M, C;

vector<int> times;

int countBusesUsingRange(int busRange){
    
    int busCount = 0;
    int busCowCount = 0;
    int startCow = INT32_MIN/16;

    for(int i = 0; i < N; i ++){
        int cowTime = times[i];
        if(busCowCount == C){
            // Start a new bus
            busCount ++;
            startCow = cowTime;
            busCowCount = 1;
        }else if((cowTime - startCow) > busRange){
            // Start a new bus
            busCount ++;
            startCow = cowTime;
            busCowCount = 1;
        }else{
            busCowCount ++;
        }
    }
    
    return busCount;
}

void solve(){
    cin >> N >> M >> C;

    int time;

    for(int i = 0; i < N; i ++){
        cin >> time;
        times.push_back(time);
    }

    sort(times.begin(), times.end());

    //                   123456789
    int low = 1, high = 1000000001; 
    while(low < high){
        long long mid = (low + high)/2;

        // cout << low << " - " << mid << " - " << high << endl;

        int trialBusCount = countBusesUsingRange(mid);
        // cout << mid << " uses " << trialBusCount << " buses " << endl;
        if(trialBusCount > M){
            // if we are using too many buses increase the bus waiting time!
            low = mid + 1; 
        }else{
            // else let's try to lower the bus waiting time to us more buses!
            high = mid; 
        }
    }

    // cout << "Final Bus Size ";
    cout << low << endl;
}

void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}

int main(int argc, char const *argv[])
{
    if(argc <= 1) setIO("convention"); // io setup
    solve();
    return 0;
}
