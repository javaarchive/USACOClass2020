#include <iostream>
#include <vector>
#include <cmath>
#include <iomanip>
#include <queue>
#define MAXN 151

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
            graph[i][j] = INT32_MAX;
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
                graph[i][j] = INT32_MAX;
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
    bool firstSector[MAXN] = {false};
    fill(firstSector, firstSector + MAXN, false);
    firstSector[0] = true;
    queue<int> toExplore;
    toExplore.push(0);
    while(!toExplore.empty()){
        int node = toExplore.front();
        toExplore.pop();
        for(int i = 0; i < N; i ++){
            if(i == node){
                continue;
            }
            if(firstSector[i] == false && (edges[node][i] || edges[i][node])){
                firstSector[i] = true;
                toExplore.push(i);
            }
        }
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
            if(firstSector[i] != firstSector[j]){
                continue;
            }
            mx[i] = max(mx[i],graph[i][j]);
        }
    }
    for(int i = 0; i < N; i ++){
        if(firstSector[i] == false){
            continue;
        }
        for(int j = i + 1; j < N; j ++){
            //cout << i << "," << j << " -> " << graph[i][j] << endl;
            if(firstSector[j]){
                continue;
            }
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
            minDiameter = min(minDiameter, maxLenA + maxLenB + newLength);
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