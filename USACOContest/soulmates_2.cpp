#include <iostream>
#include <bits/stdc++.h>

using namespace std;

void solve(){
    long long a,b;
    cin >> a >> b;
    vector<long long> arrA, arrB;
    arrA.push_back(a);
    arrB.push_back(b);
    while(a > 1){
        if(a % 2 == 1){
            a ++;
            arrA.push_back(a);
        }
        a /= 2;
        arrA.push_back(a);
    }
    // other way
    while(b > 1){
        if(b % 2 == 1){
            b --;
            arrB.push_back(b);
        }
        b /= 2;
        arrB.push_back(b);
    }
    // common!
    int A = arrA.size();
    int B = arrB.size();

    /*cout << "A: ";
    for(int i = 0; i < A; i ++){
        cout << " " << arrA[i];
    }
    cout << endl;

    cout << "B: ";
    for(int i = 0; i < B; i ++){
        cout << " " << arrB[i];
    }
    cout << endl;*/

    long long best = __LONG_LONG_MAX__;
    for(int i = 0; i < A; i ++){
        for(int j = 0; j < B; j ++){
            if(arrA[i] > arrB[j]){
                continue;
            }
            long long val = i + j + abs(arrA[i] - arrB[j]);
            if(val < best){
                // cout << arrA[i] << " to " << arrB[j] << endl;
                // cout << "New best of " << val << " from " << i << " downsteps and " << j << " upsteps" << " with a walk of " << abs(arrA[i] - arrB[j]) << endl;
                best = val;
            }
        }
    }
    cout << best << endl;
}

int main(int argc, char const *argv[])
{
    int N;
    cin >> N;
    while(N --){
        solve();
    }
    return 0;
}
