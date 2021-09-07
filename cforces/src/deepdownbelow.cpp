#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

void solve(){
    // cout << "New Solve Unit" << endl;

    int n;
    cin >> n;

    int a[n];
    vector<pair<int,int>> caves;

    int bsMin = INT32_MAX;
    int bsMax = 0;

    for(int i = 0; i < n; i++){
        int k_i;
        cin >> k_i;
        int minEntryLevel = 0;
        for(int j = 0; j < k_i; j++){
            int armorLevel;
            cin >> armorLevel;
            bsMax = max(bsMax,armorLevel + 1);
            minEntryLevel = max(minEntryLevel,armorLevel + 1 - j);
        }
        bsMin = min(bsMin, minEntryLevel);
        // cout << "Calculated minEntryLevel " << minEntryLevel << " monsters " << k_i << endl;
        caves.push_back(make_pair(minEntryLevel,k_i));
    }
    sort(caves.begin(), caves.end());

    int low = bsMin;
    int high = bsMax;
    // cout << low << " - " << high << endl;
    while(low < high){
        int mid = (low + high)/2;
        
        bool passed = true;
        int curLevel = mid;
        for(int i = 0; i < n; i++){
            if(curLevel >= caves[i].first){
                curLevel += caves[i].second;
            }else{
                passed = false;
                break;
            }
        }
        // cout << "Trying " << mid << " got " << passed << endl;
        if(!passed){
            low = mid + 1;
        }else{
            high = mid;
        }
    }
    cout << low << endl;

}

int main(int argc, char const *argv[])
{
    int t;
    cin >> t;
    for(int i = 0; i < t; i ++){
        solve();
    }

    return 0;
}
