#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N,K;
vector<int> timeframes;
vector<int> tstarts;
vector<int> tends;
vector<pair<int,pair<int,int>>> jumps;
//  nearest ox years
int nearestDown(int s){
    for(int i = s; i >= 0; i --){
        if(i % 12 == 0){
            return i;
        }
    }
    return -1;
}
int nearestUp(int s){
    for(int i = s; i <= INT32_MAX; i ++){
        if(i % 12 == 0){
            return i;
        }
    }
    return -1;
}
int main(int argc, const char** argv) {
    cin >> N >> K;
    int segements = N - 1;
    timeframes.resize(N);
    tstarts.resize(N + 1);
    tends.resize(N + 1);
    for(int i = 0; i < N; i ++){
        cin >> timeframes[i];
    }
    timeframes.push_back(0); // starting
    sort(timeframes.begin(), timeframes.end());
    reverse(timeframes.begin(),timeframes.end());
    // cout << timeframes[0] << " " << timeframes[1] << endl;
    for(int i = 0; i < N; i ++){
        tstarts[i] = nearestUp(timeframes[i]);
    }
    for(int i = 0; i < N; i ++){
        tends[i] = nearestDown(timeframes[i]);
    }
    // cout << "Processed" << endl;

    for(int i = 0; i < N; i ++){
        if(tstarts[i] == tstarts[i + 1]){
            continue;
        }
        int jumpLen = tends[i] - tstarts[i + 1];
        // cout << "if I jump from " << timeframes[i] << " " << timeframes[i + 1] << " : "<< jumpLen << endl;
        jumps.push_back(make_pair(jumpLen, make_pair(tstarts[i + 1],tends[i])));
    }
    int oldest = nearestUp(timeframes[0]);
    int gapsDistance = 0;
    sort(jumps.begin(),jumps.end());
    reverse(jumps.begin(),jumps.end());
    for(int i = 0; i < segements - 1; i ++){
        gapsDistance += jumps[i].first;
    }
    cout << oldest - gapsDistance << endl;

    return 0;
}