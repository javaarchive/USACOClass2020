#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>
#define MAXN 7501
using namespace std;
int N,K;
int solve(){
    vector<int> weights;
    weights.resize(N);
    vector<bool> visited;
    visited.resize(N);
    for(int i = 0; i < N; i ++){
        weights[i] = INT32_MAX;
    }
    int startNode = 0;
    weights[startNode] = 0;
    //                  weight    id
    priority_queue<pair<int  ,int   >, vector<pair<int,int>>, greater<pair<int,int>>> pq;
    pq.push(make_pair(0, startNode));
    while(!pq.empty()){
        pair<int,int> p = pq.top();
        pq.pop();
        int weight = p.first;
        int oldNode = p.second;
        int node = 0;
        for(int i = 0; i < N; i ++){
            if(!visited[i] && weights[node] > weights[i]){
                node = i;
            }
        }
        visited[node] = true;
        for(int i = 0; i < N; i ++){
            if(node == i){
                continue;
            }
            //               2019201913x+2019201949y
            int a = min(node, i);
            int b = max(node, i);
            int newWeight = ((2019201913LL)*((long long) (a+1)) + ((2019201949LL)*((long long) (b + 1)))) % 2019201997;
            if(!visited[i]){
                if(newWeight < weights[i]){
                    weights[i] = newWeight;
                    pq.push(make_pair(newWeight, node));
                }
            }

        }
    }
    sort(weights.begin(), weights.end());
    /*for(int i = 0; i < N; i ++){
        cout << weights[i] << " ";
    }*/
    return weights[K - 1];

}
int main(int argc, const char** argv) {
    cin >> N >> K;
    cout << solve();
    return 0;
}