#include <bits/stdc++.h>
using namespace std;

void solve(){
    string a,b;
    cin >> a;
    cin >> b;
    int N = a.length();
    int M = b.length();
    set<string> asubs;
    set<string> bsubs;
    for(int i = 0; i < N; i ++){
        for(int j = 0; i + j <= N; j ++){
            asubs.insert(a.substr(i,j));
        }
    }

    for(int i = 0; i < M; i ++){
        for(int j = 0; i + j <= M; j ++){
            bsubs.insert(b.substr(i,j));
        }
    }

    vector<string> commons;
    set_intersection(asubs.begin(),asubs.end(),bsubs.begin(),bsubs.end(),back_inserter(commons));
    string best = "";
    for(string commonstr: commons){
        if(commonstr.length() > best.length()){
            best = commonstr;
        }
    }
    // cout << best << endl;
    cout << (N+M - 2*best.length()) << endl;
}

int main(int argc, char const *argv[])
{
    int T;
    cin >> T;
    while(T--){
        solve();
    }
    return 0;
}
