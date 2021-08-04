#include <iostream>
#include <vector>
#include <map>
#include <queue>
#include <algorithm>
#define MAXN 10001
using namespace std;

int N,M,T;
int C[MAXN];
//   a               b  time
map<int,vector<pair<int,int >>> graph;
//                  dist node
priority_queue<pair<long long,int>, vector<pair<long long,int>>, greater<pair<long long,int>>> pq;

void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}

int main(int argc, char const *argv[])
{
    setIO("shortcut");
    cin >> N >> M >> T;
    for(int i = 0; i < N; i ++){
        cin >> C[i];
    }
    for(int i = 0; i < M; i ++){
        int a,b,time;
        cin >> a >> b;
        a --;
        b --;
        cin >> time;
        graph[a].push_back(make_pair(b,time));
        graph[b].push_back(make_pair(a,time));
    }
    for(int i = 0; i < N; i ++){
        sort(graph[i].begin(),graph[i].end(), [](pair<int,int> a, pair<int,int> b){
            if(a.first == b.first){
                return a.second < b.second;
            }
            return a.first < b.first;
        });
    }
    // Start from 1
    long long distances[MAXN] = {__LONG_LONG_MAX__};
    int prevNode[MAXN] = {-1};
    fill(prevNode, prevNode + N,-1);
    fill(distances, distances + N,__LONG_LONG_MAX__);
    distances[0] = 0LL;
    bool visited[MAXN] = {false};
    fill(visited, visited + N,false);
    pq.push(make_pair(0LL,0));
    visited[0] = true;
    prevNode[0] = -1; 
    while(!pq.empty()){
        pair<long long,int> pairing = pq.top();
        pq.pop();
        long long dist = pairing.first;
        int node = pairing.second;
        visited[node] = true;
        // Explore other edges
        for(auto nextChoice:graph[node]){
            int nextNode = nextChoice.first;
            // cout << "nextdist " << dist << " + " << nextChoice.second << endl;
            long long nextDist = dist + (long long) nextChoice.second;
            // if offers improvement in distance AND is not visited
            if(nextDist < distances[nextNode] && !visited[nextNode]){
                // visited[nextNode] = true;
                // cout << "[" << nextNode << "] " << nextDist << endl;
                distances[nextNode] = nextDist;
                prevNode[nextNode] = node;
                pq.push(make_pair(nextDist,nextNode));
            }else if(nextDist == distances[nextNode]){
                if(node < prevNode[nextNode]){
                    distances[nextNode] = nextDist;
                    prevNode[nextNode] = node;
                    pq.push(make_pair(nextDist,nextNode));
                }
            }
        }
    }
    long long cowStepCounts[MAXN] = {0LL}; // cows that will step on this path
    fill(cowStepCounts, cowStepCounts + N,0LL);
    for(int i = 0; i < N; i ++){
        int curNode = i;
        cowStepCounts[curNode] += (long long) C[i];
        while(prevNode[curNode] != -1){
            curNode = prevNode[curNode];
            cowStepCounts[curNode] += (long long) C[i];
        }
    }
    
    /*cout << "Dijkstra's" << endl;
    for(int i = 0; i < N; i ++){
        cout << distances[i] << " ";
    }
    cout << endl;*/
    // obviously don't connect 0 to 0
    long long bestImprovement = 0;
    for(int i = 1; i < N; i ++){
        if(distances[i] < T){
            // can't improve
            continue;
        }
        long long unitImprovement = distances[i] - (long long) T;
        // cout << "Unit Improvement " << distances[i] << " - " << T << " = " << unitImprovement << endl;
        // cout << "Improvement for " << i << " is each " << unitImprovement << " * cow steps on this: " << cowStepCounts[i] << " = " << unitImprovement * cowStepCounts[i] << endl;
        bestImprovement = max(bestImprovement,unitImprovement * cowStepCounts[i]);
    }
    cout << bestImprovement << endl;
    return 0;
}
