#include <iostream>
#include <bits/stdc++.h>

using namespace std;

vector<int> nums;

void solve(){
    int N;
    cin >> N;
    nums.clear();
    for(int i=0; i<N; i ++){
        int num;
        cin >> num;
        nums.push_back(num);
    }
    sort(nums.begin(), nums.end());

    for(int i = 0; i < N; i ++){
        for(int j = i + 1; j < N; j ++){
            int moreSum = max(nums[i],nums[j]);
            int lessSum = min(nums[i],nums[j]);
            int diff = moreSum - lessSum; // let this be the value of x?
            int y = -1;
            int z = -1;
            bool oops = false;
            for(int k = 0; k < N; k ++){
                if(k == i || k == j) continue;
                if(diff > nums[k]){
                    if(y == -1) {y = nums[k];}
                    else if(z == -1){z = nums[k];}else{
                        cout << "out of nums previous " << y << " and " << z << " with a " << diff << endl;
                        oops = true;
                        break;
                    }
                }
            }
            if(y != -1 && z != -1){
                cout << "sol? " << diff << " and " << nums[i] << " with sum " << nums[j] << endl;
            }
            for(int k = 0; k < N; k ++){
                if(k == j) continue; // drop requirement for x,y,z,x+y cases, j -> x + y, i => x
                if(diff > nums[k]){
                    if(y == -1) {y = nums[k];}
                    else if(z == -1){z = nums[k];}else{
                        cout << "narrow out of nums previous " << diff << " and " << nums[i] << " with sum " << nums[j] << endl;
                        oops = true;
                        break;
                    }
                }
            }
            if(oops) continue;
            if(y == -1 || z == -1) continue;
            cout << "Finalized a comb (possibly narrow) " << diff << " (" << y << "," << z << ")" << endl;
        }
    }

}

int main(int argc, char const *argv[])
{
    int T;
    cin >> T;

    for(int i = 0; i < T; i ++){
        cout << "Case " << i << endl;
        solve();
    }
    return 0;
}
