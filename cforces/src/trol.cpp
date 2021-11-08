// may not be a codeforces problem
#include <bits/stdc++.h>
using namespace std;

void solve(){
    long long a,b;
    cin >> a >> b;
    long long ans = 0;
    long long smallestMult = a;
    long long largestMult = b;
    if((b - a) <= 12){
        for(long long i = a; i <= b; i++){
            ans += i % 9LL;
            if(i % 9LL == 0LL){
                ans += 9LL;
            }
        }
        cout << ans << endl;
        return;
    }
    for(; smallestMult <= b && smallestMult % 9LL != 0LL; smallestMult ++){}
    for(; largestMult >= a && largestMult % 9LL != 0LL; largestMult --){}
    long long loops = (largestMult - smallestMult)/9LL;
    ans += loops * 45LL;
    for(long long i = a; i < smallestMult; i ++){
        ans += i % 9LL;
            if(i % 9LL == 0LL){
                ans += 9LL;
        }
    }

    for(long long i = largestMult; i <= b; i ++){
        ans += i % 9LL;
            if(i % 9LL == 0LL){
                ans += 9LL;
        }
    }
    cout << ans << endl;
}

int main(int argc, char const *argv[])
{
    int Q;
    cin >> Q;
    for(int i = 0; i < Q; i ++){
        solve();
    }
    return 0;
}
