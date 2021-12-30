#include <iostream>
#include <bits/stdc++.h>
#define doubleMaxM 10004
#define maxM 5001
using namespace std;

int ans[doubleMaxM] = {0};
vector<pair<int,int>> intervals;

void solve(){
    int N,M;
    cin >> N >> M;
    for(int i = 0;i < N; i ++){
        int a,b;
        cin >> a >> b;
        intervals.push_back(make_pair(a,b));
    }
    for(int i = 0; i < N; i ++){
        for(int j = i; j < N; j ++){
            // cout << i << " and " << j << endl;
            int a = intervals[i].first;
            int b = intervals[i].second;
            int c = intervals[j].first;
            int d = intervals[j].second;
            int start = a + c;
            int end = b + d;
            int add = 1;
            if(i != j){
                add ++;
            }
            end = min(end, 2 * M);
            for(int k = start; k <= end; k ++){
                ans[k] += add;
            }
        }
    }
    for(int i = 0; i <= 2 * M; i ++){
        cout << ans[i] << endl;
    }
    // cout << "ANSWERED" << endl;
}

int main(int argc, char const *argv[])
{
    solve();
    return 0;
}
