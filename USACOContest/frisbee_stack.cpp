#include <iostream>
#include <bits/stdc++.h>

using namespace std;

vector<int> heights;

stack<int> stk;

void solve(){
    int N;
    cin >> N;
    for(int i = 0; i < N; i ++){
        cin >> heights[i];
    }
    long long ans;
    for(int i = 0 ; i < N ; i ++){
        while(!stk.empty() && heights[i] >= stk.top()){
            stk.pop();
        }
        ans += stk.size();
        stk.push(heights[i]);
    }
}

int main(int argc, char const *argv[])
{
    solve();
    return 0;
}
