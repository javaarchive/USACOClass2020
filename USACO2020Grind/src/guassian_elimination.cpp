#include <iostream>
#include <bits/stdc++.h>

using namespace std;

vector<int> nums;

struct MatrixRow{
    int coefA = 0;
    int coefB = 0;
    int coefC = 0;

    int val = 0;
    
    MatrixRow(int a, int b, int c, int v){
        coefA = a;
        coefB = b;
        coefC = c;
        val = v;
    }

    MatrixRow sub(MatrixRow other){
        return MatrixRow(coefA - other.coefA, coefB - other.coefB, coefC - other.coefC, val - other.val);
    }

    int zeros(){
        int zeros = 0;
        if(coefA == 0) zeros++;
        if(coefB == 0) zeros++;
        if(coefC == 0) zeros++;
        return zeros;
    }

    bool isolated(){
        return this->zeros() == 2;
    }

    MatrixRow simplify(){
        if(coefA % 2 == 0 && coefB % 2 == 0 && coefC % 2 == 0){
            return MatrixRow(coefA / 2, coefB / 2, coefC / 2, val / 2);
        }
        return *this;
    }

    int rowDiff(MatrixRow other){
        int diffs = 0;
        if(coefA != other.coefA) diffs++;
        if(coefB != other.coefB) diffs++;
        if(coefC != other.coefC) diffs++;
    }
};

bool solvable(MatrixRow rows[], int N){
    for(int i = 0; i < N; i++){
        for(int j = i + 1; j < N; j++){
            if(rows[i].rowDiff(rows[j]) == 1){
                
            }
        }
    }
    return false;
}


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
