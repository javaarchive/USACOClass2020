#define MAXN 50002

#include <iostream>
#include <algorithm>

using namespace std;

int L, M, N, smallestGap = INT32_MAX;
int rocks[MAXN];
bool canIncreaseTo(int gap){
    int removed = 0;
    for(int i = 0; i < N + 2; i ++){
        // horrible programming pattern
        int start = rocks[i];
        for(int j = i + 1; j < N + 2; j ++){
            // cout << start << " -> " << rocks[j] << " <gap: " << gap << " >" << endl;
            if((rocks[j] - start) < gap){
                // cout << "removed " << j << endl;
                removed ++;
                if(removed > M){
                    return false;
                }
            }else{
                i = j - 1; // will increment later
                break;
            }
        }
    }
    return true;
}
int main(int argc, char const *argv[])
{
    cin >> L >> N >> M;
    for(int i = 0; i < N; i ++){
        cin >> rocks[i];
    }
    rocks[N] = 0;
    rocks[N + 1] = L + 1;
    sort(rocks, rocks + N + 2);
    for(int i = 0; i < N + 1; i ++){
        smallestGap = min(smallestGap, rocks[i + 1] - rocks[i]);
    }
    int high = L, low = smallestGap;
    while(low < high){
        //cout << low << " < " << high << endl;
        int mid = (low + high)/2;
        if(canIncreaseTo(mid)){
            //cout << "Trying " << mid << " got true" << endl;
            low = mid + 1;
        }else{
            //cout << "Trying " << mid << " got false" << endl;
            high = mid;
        }
    }
    cout << low-1 << endl;
    return 0;
}
