#include <bits/stdc++.h>
using namespace std;

void solve(){
    long long N, M; // M -> cables
    cin >> N >> M;
    long long installedComputers = 1LL;
    for(long long hours = 0; hours < __LONG_LONG_MAX__; hours ++){
        if(installedComputers >= N){
            cout << (hours) << endl;
            break;
        }else if(installedComputers >= M){
            // Bottlenecked
            // cout << "Bottleknecked we have " << (N - installedComputers) << " left we can go at rate of " << M << " per hour so it'll take " << ceil((N - installedComputers)/M) << " more hours, " << hours << " hours have elapsed" << endl;
            hours += ceil(((double) N - (double)installedComputers)/M);
            cout << hours << endl;
            break;
        }
        long long toAdd = min(min(installedComputers, M), N - installedComputers); // cables
        installedComputers += toAdd;
    }
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
