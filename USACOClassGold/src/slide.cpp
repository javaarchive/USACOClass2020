// Dijkstra's LONGEST path
#include <iostream>
#include <vector>
#include <tuple>
#include <queue>
#include <map>
#include <algorithm>
using namespace std;
int V,E,K;
//    A             fun B         
map<int,vector<pair<long long,int>>> graph;
bool cmp(tuple<long long,int,int> a, tuple<long long,int,int> b)
{
    if(get<0>(a) == get<0>(b)){
        if(get<1>(a) == get<1>(b)){
            return get<2>(a) < get<2>(b);
        }
        return get<1>(a) < get<1>(b);
    }
    return get<0>(a) > get<0>(b);
}
//                   fun,node,slips
priority_queue<tuple<long long,int,int>> pq;
int main(int argc, char const *argv[])
{
    cin >> V >> E >> K;
    long long best = 9223372036854775807;
    pq.push(make_tuple(0,1,0));
    for(int i = 0; i < E; i ++){
        int a,b,f;
        cin >> a >> b;
        cin >> f;
        graph[a].push_back(make_pair(f,b));
    }
    for(int i = 0; i < E; i ++){
        if(!graph[i].empty()){
            sort(graph[i].begin(),graph[i].end(),[](pair<int,int> a, pair<int,int> b){
                return b.first < a.first;
            });
        }
    }
    while(!pq.empty()){
        tuple<long long,int,int> opt = pq.top();
        pq.pop();
        long long fun = get<0>(opt);
        int node = get<1>(opt);
        int slips = get<2>(opt);
        
        // cout << "fun: " << fun << " node: "<< node << " slips: " << slips;
        if(node == V && slips <= K){
            best = min(best,fun);   
        }
        long long mostFun = -1;
        if(!graph[node].empty()){
            mostFun = graph[node][0].first;
        }
        /*for(auto iter = graph[node].begin(); iter != graph[node].end(); iter ++){
            mostFun = max(mostFun, iter->first); 
        }*/
        for(auto iter = graph[node].begin(); iter != graph[node].end(); iter ++){
            long long additionalFun = iter->first;
            int newNode = iter->second;
            int newSlips = slips + 1;
            if(additionalFun == mostFun){
                // pq.push(make_tuple(fun + additionalFun, newNode, newSlips - 1));
                newSlips = slips;
            }
            if(newSlips > K){
                continue;
            }
            if((fun + additionalFun) > best){
                continue;
            }
            pq.push(make_tuple(fun + additionalFun, newNode, newSlips));
        }
    }
    cout << best << endl;
    return 0;
}
