#include <iostream>
#include <limits.h>
#define maxn 100001

using namespace std;


void solve(){
    int n,k;
    cin >> n >> k;

    long long a[maxn];
    long long best = LLONG_MIN;
    for(int i = 0; i < n; i ++){
        cin >> a[i];
    }
    for(long long i = max(n - 2 * k,0); i < n; i ++){
        for(long long j = i + 1; j < n; j ++){
            // evaluate
            // cout << "calc " << (i + 1) << " * " << (j + 1) << " - " << "(" << k << " * (" << a[i] << "|" << a[j] << ")" << ")" << endl; 
            long long value = (i + 1) * (j + 1) - (k * (a[i] | a[j]));
            best = max(best, value);
        }
    }
    cout << best << endl;
}

int main(int argc, char const *argv[])
{
    int T;
    cin >> T;
    for(int i = 0; i < T; i ++){
        solve();
    }
    return 0;
}
