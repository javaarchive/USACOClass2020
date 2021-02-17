#include <iostream>
#include <vector>
#include <map>
#include <queue>
#include <tuple>

using namespace std;
//   A        B  RouteID
map<int,map<int,int>> graph;
int routeCosts[1001];
int startNode,endNode,N;
int solve(){
    int lastAirline = -1;
    //                  node,     dist route
    priority_queue<pair< int,pair<int ,int> >,vector<pair<int,pair<int,int>>>,greater<pair<int,pair<int,int>>>> pq;
    pq.push(make_pair(startNode,make_pair(0,-1)));
    int shortest[1001];
    fill(shortest,shortest, INT32_MAX);
    shortest[startNode] = 0;
    while(!pq.empty()){
        pair<int,pair<int,int>> opt = pq.top();
        int dist = opt.second.first;
        int routeID = opt.second.second;
        pq.pop();
        if(graph[opt.first].empty()){
            continue; // Core Dump prevention
        }
        for(auto iter = graph[opt.first].begin(); iter != graph[opt.first].end(); iter ++){
            pair<int,int> p = *iter;
            int newDist = dist;
            if(p.second != routeID){
                newDist += p.first;
            }
            if(newDist < shortest[p.first]){
                shortest[p.first] = newDist;
                pq.push(make_pair(p.first, make_pair(newDist,p.second)));
            }
        }
    }
    return shortest[endNode];
}
int main(int argc, const char** argv) {
    cin >> startNode >> endNode >> N;
    for(int i = 0; i < N; i ++){
        int cost, flights;
              
        int A,B;
        cin >> cost;
        cin >> flights;
        cin >> A;
        int first = A;
        routeCosts[i] = cost;  
        //cout << "READ"<<i<<endl;
        for(int j = 0; j < (flights - 1); j ++){
            cin >> B;
            if(graph[A].find(B) == graph[A].end() || routeCosts[i] < routeCosts[graph[A][B]]){
                graph[A][B] = i;
            }
            A = B;
        }
    }
    cout << solve() << endl;
    return 0;
}