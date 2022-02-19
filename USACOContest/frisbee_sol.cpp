#include <iostream>
#include <bits/stdc++.h>

#define MAXN 900004

using namespace std;

long long ans = 0;
int N;

vector<int> heights;

void calc(){
    int inv[MAXN] = {-1};
    for(int i = 0; i < N; i ++){
        inv[heights[i]] = i;
        
    }
    set<int> lset;
    for (int h = N; h > 0; h --) {
        auto iter = lset.insert(inv[h]).first;
        if (lset.size() > 0 && next(iter) != end(lset)){ // not out of elements
            // cout << "++ " << *next(iter) - *iter + 1 << endl;
            ans += *next(iter) - *iter + 1;
        }
    }
}

void solve(){
    cin >> N;
    heights.resize(N);
    for(int i = 0; i < N; i ++){
        cin >> heights[i];
    }
   
    calc();
    reverse(heights.begin(), heights.end());
    calc();
    cout << ans << endl;
}

int main(int argc, char const *argv[])
{
    solve();
    return 0;
}
