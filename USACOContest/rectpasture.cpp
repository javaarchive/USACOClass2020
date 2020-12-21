#include <iostream>
#include <algorithm>

using namespace std;
int N;

int solve(){
    int ans = 0;

    return ans;
}
pair<int,int> points[2501];
int main(int argc, const char** argv) {
    cin >> N;
    for(int i = 0; i < N; i ++){
        cin >> points[i].first >> points[i].second;
    }
    sort(points, points + N, [](pair<int,int> a, pair<int,int> b){
        return a.first < b.first;
    });
    for(int i = 0; i < N; i ++){
        
    }

    cout << solve();
    return 0;
}