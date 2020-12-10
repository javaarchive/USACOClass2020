#include <iostream>
#include <vector>
#include <map>
#include <queue>

using namespace std;
//        A   B          ROUTEID
map<pair<int,int>,vector<int    >> graph;
int routeCosts[1000];
int A,B,N;
int solve(){
    int lastAirline = -1;
    //                  node,dist
    priority_queue<pair< int,int >,vector<pair<int,int>>,greater<pair<int,int>>> pq;
    pq.push(make_pair(A,0));
    while(!pq.empty()){
        pair<int,int> opt = pq.top();
        pq.pop();
        for(int i = 1; i <= 1000; i ++){

        }
    }
}
int main(int argc, const char** argv) {
    cin >> A >> B >> N;
    for(int i = 0; i < N; i ++){
        int cost, flights;
        routeCosts[i] = cost;        
        int A,B;
        cin >> A;
        int first = A;
        for(int j = 0; j < (flights - 1); j ++){
            cin >> B; 
            graph[make_pair(A,B)].push_back(i);
            A = B; // Shift down
        }
    }
    return 0;
}