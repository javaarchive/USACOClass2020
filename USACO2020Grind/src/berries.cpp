#include <bits/stdc++.h>
#define MAXN 1001
#define MAXB 1001
using namespace std;

void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}

void solve(){
    int N, K;
    cin >> N >> K;
    int treeAmounts[MAXN];
    for(int i = 0; i < N; i ++){
        cin >> treeAmounts[i];
    }
    sort(treeAmounts, treeAmounts + N);
    reverse(treeAmounts, treeAmounts + N);

    int ans = 0;
    for(int i = 1; i < 1000; i ++){
        // cout << "### " << i << " ###" << endl;
        int basketsFilled = 0;
        int total = 0;
        for(int j = 0; j < N; j ++){
            basketsFilled += floor(treeAmounts[j]/i);
        }
        if(basketsFilled < (K/2)){
            // cout << "Failed to fill " << K/2 << " baskets could only fill " << basketsFilled << " with " << i << " t " << total <<  endl;
            break;
        }else if(basketsFilled >= K){
            // cout << "Result: " << total << "-" << i*(K/2) << " = " << total - i * (K/2) << endl;
            ans = max(ans, i * (K/2));
            continue;
        }
        sort(treeAmounts, treeAmounts + N, [i](int a, int b){
            return a % i > b % i;
        });
        int other = i * (basketsFilled - K/2);
        for(int j = 0; j < N && j + basketsFilled < K; j ++){
            other += treeAmounts[j] % i;
        }
        ans = max(ans, other);
    }
    cout << ans << endl;
}

int main(int argc, char const *argv[])
{
    setIO("berries");
    solve();
    return 0;
}
