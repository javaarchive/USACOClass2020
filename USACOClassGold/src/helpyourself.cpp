
#define MAXN 1000001

#include <iostream>
#include <cmath>
#include <vector>
using namespace std;
int N;
const int MOD = 1e9+7;
int powers[MAXN];
int covered[MAXN] = {0};
int main(){
    cin >> N;
    
    powers[0] = 1;
    for(int e = 1; e < N; e ++){
        powers[e] = (2 * powers[e - 1]) % (MOD);
    }
    // cout << powers[0] << "," << powers[1];
    vector<pair<int,int>> ranges;
    ranges.resize(N);
    for(int i = 0; i < N; i ++){
        int a,b;
        cin >> a >> b;
        pair<int,int> p;
        p.first = a;
        p.second = b;
        ranges[i] = p;
        covered[a] ++; // Prefix differences
        covered[b] --;
    }
    for(int i = 1; i <= 2*N; i ++){
        covered[i] += covered[i - 1];
    }
    int ans = 0;
    // below is ok
    for(auto iter = ranges.begin(); iter != ranges.end(); iter ++){
        ans = (ans + powers[N - 1 - covered[(*iter).first - 1]]) % MOD;
    }
    cout << ans << endl;
}
