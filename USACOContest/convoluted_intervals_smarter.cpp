#include <iostream>
#include <bits/stdc++.h>
#define doubleMaxM 10004
#define maxM 5001
using namespace std;

int ans[doubleMaxM] = {0};
int preans[doubleMaxM] = {0};
vector<pair<int,int>> intervals;
int prething[doubleMaxM] = {0};

void solve(){
    int N,M;
    cin >> N >> M;
    for(int i = 0;i < N; i ++){
        int a,b;
        cin >> a >> b;
        intervals.push_back(make_pair(a,b));
        for(int i = a; i <= b; i ++){
            preans[i] ++;
        }
        prething[a] ++;
        prething[b + 1] ++;
    }

    for(int i = 0; i <= 2 * M; i ++){
        cout << preans[i] << endl;
    }

    cout << endl;
    
    for(int i = 0; i <= 2 * M; i ++){
        if(prething[i] == 0){
            continue;
        }
        cout << "presumming" << endl;
        for(int j = 0; j <= (2 * M - i); j ++){
            cout << i + j << " ++ " << prething[i] << "*" << preans[j] << endl;
            ans[i + j] += prething[i] * preans[j];
        }
    }

    /*for(int i = 0; i < N; i ++){
        // remove overcount
        int a = intervals[i].first;
        int b = intervals[i].second;
        for(int j = a*2; j <= b*2; j ++){
            ans[j] --;
        }
    }*/

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
