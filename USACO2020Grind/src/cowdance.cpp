#include <iostream>
#include <queue>
#define MAXN 10001
#define MAXSIZE 10001

using namespace std;

int N, M;
int times[MAXN];
int tmax = 0;

void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}

bool bs(int size){
    int cur[MAXSIZE];
    int time = 0;
    if(size > N){
        return tmax <= M;
    }
    if(tmax > M){
        return false;
    }
    priority_queue<int,vector<int>,greater<int>> pq;
    for(int i = 0; i < size; i ++){
        pq.push(times[i]);
    }
    for(int i = size; i < N; i++){
        int startTime = pq.top();
        pq.pop();
        pq.push(times[i] + startTime);
    }
    for(int i = 0; i < size - 1; i ++){
        // cout << "pop " << pq.top() << endl;
        pq.pop();
    }
    // cout << pq.top() << endl;
    return pq.top() <= M;
}

void solve(){
    setIO("cowdance");
    cin >> N >> M;
    for (int i = 0; i < N; i ++){
        cin >> times[i];
        tmax = max(tmax, times[i]);
    }
    int top = M;
    int bottom = 1;
    int mid;
    while (top > bottom){
        // cout << bottom << " -- " << top << endl;
        mid = (top + bottom) / 2;
        // cout << "bs(" << mid << ")" << endl;
        if (bs(mid)){
            top = mid;
        }else{
            bottom = mid + 1;
        }
    }
    cout << ((top + bottom) / 2) << endl;
}

int main(int argc, char const *argv[])
{
    solve();
    return 0;
}
