#include <iostream>
#include <vector>
#include <set>
using namespace std;
int N,K;
vector<pair<int,int>> cowSwaps;
//     from      i   dest
vector<vector<pair<int,int >>> graph;
vector<int> cycLength;
int main(int argc, const char** argv) {
    cin >> N >> K;
    cowSwaps.resize(K);
    // visits.resize(K);
    cycLength.resize(N);
    graph.resize(N);
    fill(cycLength.begin(),cycLength.end(),1);
    for(int i = 0; i < K; i ++){
        cin >> cowSwaps[i].first >> cowSwaps[i].second;
        cowSwaps[i].first --;
        cowSwaps[i].second --;
    }
    cout << "L1";
    for(int i = 0; i < K; i ++){
        graph[cowSwaps[i].first].push_back(make_pair(i, cowSwaps[i].second));
        graph[cowSwaps[i].second].push_back(make_pair(i, cowSwaps[i].first));
    }
    cout << "L2";
    for(int i = 0; i < N; i ++){
        if(cycLength[i] > 1){
            continue;
        }
        if(graph[i].empty()){
            continue;
        }
        int curNode = i;
        int iter = -1;
        pair<int,int> p;
        vector<int> places;
        while(true){
            places.push_back(curNode);
            for(int i = 0; i < graph[curNode].size(); i ++){
                int cost = -(curNode - graph[curNode][i].first);
                if(cost < bestVal){
                    p = graph[curNode][i];
                }
            }
            cout << "Visit " << curNode << " moving to " << p.second << endl;
            if(p.second == i){
                cout << "Traverse End" << endl;
                int sz = places.size();
                for(auto iter = places.begin(); iter != places.end(); iter ++){
                    cycLength[*iter] = sz;
                }
                break;
            }
            curNode = p.second;
        }
        for(int i = 0; i < N; i ++){
            cout << cycLength[i] << endl;
        }
    }
    return 0;
}