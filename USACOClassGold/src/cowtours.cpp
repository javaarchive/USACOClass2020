#include <iostream>
#include <vector>
#include <cmath>
#include <iomanip>
#include <queue>
#define MAXN 151
#define INF 100000000000LL

using namespace std;
int N;
vector<pair<int,int>> coords;
double graph[MAXN][MAXN];
bool edges[MAXN][MAXN];
double dist(pair<int,int> p1, pair<int,int> p2){
    double a,b; // 10^5 max value may cause problems when squared due to overflow becuase of int
    a = p1.first - p2.first;
    b = p1.second - p2.second;
    return (double) sqrt(a*a + b*b);
}
int main(int argc, const char** argv) {
    cin >> N;
    for(int i = 0; i < N; i ++){
        pair<int,int> p;
        cin >> p.first >> p.second;
        coords.push_back(p);
    }
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < N; j ++){
            graph[i][j] = INF;
        }
    }
    string line;
    for(int i = 0; i < N; i ++){
        cin >> line;
        for(int j = 0; j < N; j ++){
            int state;
            state = (int)line[j] - 48;
            if(state == 1){
                graph[i][j] = dist(coords[i], coords[j]);
                edges[i][j] = true;
            }else{
                graph[i][j] = INF;
                edges[i][j] = false;
            }
            // graph[i][j] = state;
        }
    }
    //cout << "floyd-warshall";
    for(int k = 0; k < N; k ++){
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                if(graph[i][j] > (graph[i][k] + graph[k][j] )){
                    graph[i][j] = (graph[i][k] + graph[k][j]);
                    graph[j][i] = (graph[i][k] + graph[k][j]);
                }
            }
        }
    }
    int sector[MAXN] = {0};
    int nextID = 1;
    int fd[MAXN];
    for(int node = 0; node < N; node ++){
        if(sector[node] != 0){
            continue; // Node already explored;
        }
        queue<int> toExplore;
        toExplore.push(node);
        while(!toExplore.empty()){
            int node = toExplore.front();
            toExplore.pop();
            for(int i = 0; i < N; i ++){
                if(i == node){
                    continue;
                }
                if(sector[i] == 0 && (graph[node][i] != INF)){
                    sector[i] = nextID;
                    toExplore.push(i);
                }
            }
        }
        nextID ++;
    }
    double minDiameter = __DBL_MAX__;
    //cout << "Iter";
    double mx[MAXN];
    fill(mx,mx+MAXN, 0);
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < N; j ++){
            if(i == j){
                continue;
            }
            if((graph[i][j] + 0.0000001) >= INF){
                continue;
            }
            mx[i] = max(mx[i],graph[i][j]);
            mx[j] = max(mx[j],graph[i][j]);
        }
    }
    for(int i = 0; i < N; i ++){
        if(mx[i] > fd[sector[i]]){
            fd[sector[i]] = mx[i];
        }
    }
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < N; j ++){
            if(i == j){
                continue;
            }
            //cout << i << "," << j << " -> " << graph[i][j] << endl;
            /*if(){
                continue;
            }*/
            if((graph[i][j]+0.0000001) < INF) continue;
            /*if(edges[i][j]){
                continue; // Edge already exists
            }*/
            
            double newLength = dist(coords[i],coords[j]);
            //cout << "NL: " << newLength << endl;
            
            /*if(maxLenA == __DBL_MAX__){
                maxLenA = 0;
            }else if(maxLenB == __DBL_MAX__){
                maxLenB = 0;
            }*/
            double maxLenA = mx[i];
            double maxLenB = mx[j];
            //cout << "Diamater calc: " << maxLenA << " + " << maxLenB << " + " << newLength << " -> " << (maxLenA + maxLenB + newLength) << endl;
            if((graph[i][j]+0.0000001)>=INF)
            {
                minDiameter = min(minDiameter, maxLenA + maxLenB + newLength);
            }
            //if(newLength >= graph[i][j]){continue;}
            // Check distances to every other point
        }
    }
    if(((int) minDiameter) == minDiameter){
        cout << minDiameter << ".000000";
        //cout << "Integer" << endl;
    }else{
        cout << setprecision(log10(minDiameter)+7) << minDiameter;
    }
    return 0;
}