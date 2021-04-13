#include <iostream>
#include <vector>
#include <set>
#include <tuple>
using namespace std;
vector<int> nums;
set<tuple<int,int,int>> answers;
bool checkSol(int a, int b, int c){
    // all positive integers
    cout << "Testing " << a << " " << b << " " << c << endl;
    if(a > 0 && b > 0 && c > 0){
        // If this is a valid combination all of the input values should be one of the 2^3 - 1 combination
        set<int> combinations;
        combinations.insert(0);
        combinations.insert(a);
        combinations.insert(b);
        combinations.insert(c);
        combinations.insert(a + b);
        combinations.insert(b + c);
        combinations.insert(a + c);
        combinations.insert(a + b + c);
        
        for(int i = 0; i < nums.size(); i ++){
            if(combinations.find(nums[i]) == combinations.end()){
                cout << "Fail to B" << endl;
                return false; 
            }
        }
        return true;
    }
    cout << "Fail to A" << endl;
    return false;
}
tuple<int,int,int> make_sorted_tuple(int x, int y, int z){
    int minNum = min(x,min(y,z));
    int maxNum = max(x,max(y,z));
    return make_tuple(minNum,x+y+z - maxNum - minNum,maxNum);
}
void solve(){
    int N;
    cin >> N;
    
    answers.clear();
    nums.clear();
   

    for(int i = 0; i < N; i ++){
        int temp;
        cin >> temp;
        nums.push_back(temp);
    }
    nums.push_back(0);

    for(int i = 0; i < nums.size(); i ++){
        for(int j = i + 1; j < nums.size(); j ++){
            bool breakout = false;
            // One of these will sum to the sum
            int pSum = nums[i] + nums[j]; // Possible sum
            set<int> possiblities;
            for(int term: nums){
                //cout << "Iter" << endl;
                if(term > pSum){
                    breakout = true;
                    break;
                }
                if(term == 0 || term == pSum){
                    continue;
                }
                possiblities.insert(min(pSum, pSum - term));
            }
            if(breakout){
                continue;
            }
            // Select first two
            // cout << "SZ: " << possiblities.size() << endl;
            /*if(possiblities.size() < 2){
                continue;
            }*/
            /*cout << "pset ";
            for(auto iter = possiblities.begin(); iter != possiblities.end(); iter++){
                cout << *iter << " ";
            }
            cout << endl;*/
            int a = *possiblities.begin();
            int b = *next(possiblities.begin());
            cout << a << " and " << b << " psum " << pSum << endl;
            if(checkSol(pSum - a - b ,a,b)){
                answers.insert(make_sorted_tuple(pSum - a - b,a,b));
            }
            if(checkSol(b - a, a, pSum - b)){
                answers.insert(make_sorted_tuple(b - a, a, pSum - b));
            }
            if(checkSol(a - b, b, pSum - a)){
                answers.insert(make_sorted_tuple(a - b, b, pSum - a));
            }
            

        }
    }
    if(checkSol(nums[0],nums[1],nums[2])){ 
        answers.insert(make_sorted_tuple(nums[0],nums[1],nums[2]));
    }
    if(checkSol(nums[1],nums[2],nums[3])){
        answers.insert(make_sorted_tuple(nums[1],nums[2],nums[3]));
    }
    cout << "Ans: " << answers.size() << endl;
}
int main(int argc, const char** argv) {
    int cases;
    cin >> cases;
    for(int i = 0; i < cases; i ++){
        solve();
    }
    return 0;
}