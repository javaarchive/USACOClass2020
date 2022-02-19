#include <bits/stdc++.h>

// 10e6 + 1
#define MAXN 1000001
// 10x max N
#define MAXVAL 10000001

using namespace std;

long long f(long long x){
    // Given x find it's index
    // cout << "f(" << x << ") = " << x << "-" << x/3 << "-" << x/5 << "+" << x/15 << endl; 
    return x - x/3 - x/5 + x/15;
}

void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}

void solve(){
    // Test code
    // cout << "Position of 7 is " << f(7) << endl;
    assert(f(7) == 4); // Sample Case

    int target;
    cin >> target;

    long long low = 0, high = MAXN;

    long long ans = -1;

    while(low < high){
        long long mid = (low + high)/2;

        // cout << low << " - " << mid << " - " << high << endl;

        int calculatedIndex = f(mid);
        if(calculatedIndex < target){
            low = mid + 1; // higher
        }else{
            high = mid; // lower
        }
    }

    // cout << "ANS: ";
    cout << low << endl;
}

int main(int argc, char const *argv[])
{
    if(argc <= 1) setIO("moobuzz"); // io setup
    solve();
    return 0;
}
