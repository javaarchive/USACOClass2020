#include <iostream>
#include <bits/stdc++.h>
#define MAXN 500001

using namespace std;

int N,M;
unordered_set<int> visited;
map<int,vector<int>> edges;

int compMin[MAXN];
int compMax[MAXN];
vector<int> compNodes[MAXN];
int nodeToComp[MAXN];
int compCounter;

/*set<int> startNodes;
set<int> endNodes;

int closestStartNode(int pos){
    set<int>::iterator lowIter = startNodes.lower_bound(pos);
    lowIter --;
    set<int>::iterator highIter = startNodes.upper_bound(pos);
    if(lowIter == startNodes.end()){
        return *highIter;
    }else if(highIter == startNodes.end()){
        return *lowIter;
    }
    int low = *lowIter;
    int high = *highIter;
    return (pos - low < high - pos) ? low : high;
}

int closestEndNode(int pos){
    set<int>::iterator lowIter = endNodes.lower_bound(pos);
    lowIter --;
    set<int>::iterator highIter = endNodes.upper_bound(pos);
    if(lowIter == endNodes.end()){
        return *highIter;
    }else if(highIter == endNodes.end()){
        return *lowIter;
    }
    int low = *lowIter;
    int high = *highIter;
    return (pos - low < high - pos) ? low : high;
}*/

int getSmallestDiff2(int a, int b){
    int smallest = INT32_MAX;
    int i = 0, j = 0;
    while(i < compNodes[a].size() && j < compNodes[b].size()){
        int diff = abs(compNodes[a][i] - compNodes[b][j]);
        if(diff < smallest){
            smallest = diff;
        }
        if(compNodes[a][i] < compNodes[b][j]){
            i ++;
        }else{
            j ++;
        }
    }
    return smallest;
}

int getSmallestDiff(int a, int b){
    int smallest = INT32_MAX;
    int i = 0, j = 0;
    
    while(i < compNodes[a].size() && j < compNodes[b].size()){
        int diff = abs(compNodes[a][i] - compNodes[b][j]);
        if(diff < smallest){
            smallest = diff;
        }
        if(compNodes[a][i] < compNodes[b][j]){
            i ++;
        }else{
            j ++;
        }
    }
    return min(smallest,getSmallestDiff2(b,a));
}

void dfs(int node, int comp){
    visited.insert(node);
    nodeToComp[node] = comp;
    compNodes[node].push_back(node);
    compMin[comp] = min(compMin[comp], node);
    compMax[comp] = max(compMax[comp], node);
    for(int other : edges[node]){
        if(visited.find(other) == visited.end()){
            dfs(other,comp);
        }
    }
    
}

void solve(){
    cin >> N >> M;
    edges.clear();
    visited.clear();
    for(int i = 0; i < MAXN; i ++){
        compNodes[i].clear();
    }
    compCounter = 0;
    for(int i = 0; i < N; i ++){
        compMin[i] = INT32_MAX;
        compMax[i] = -1;
        nodeToComp[i] = -1;
    }
    
    for(int i = 0; i < M; i++){
        int a,b;
        cin >> a >> b;
        // offsetting
        a --;
        b --;
        edges[a].push_back(b);
        edges[b].push_back(a);
    }
    for(int i = 0; i < N; i ++){
        if(visited.find(i) == visited.end()){
            dfs(i,compCounter);
            compCounter ++;
        }
    }
    int startComp = nodeToComp[0];  // should be 0
    assert(startComp == 0);
    int endComp = nodeToComp[N - 1];
    int startEnd = compMax[startComp];
    int endStart = compMin[endComp];
    long long best = __LONG_LONG_MAX__;
    if(startComp == endComp){
        cout << 0 << endl;
        return;
    }
    // cout << "start ends at " << startEnd << " and end starts at " << endStart << endl;
    for(int i = 0; i <= endComp; i ++){
        sort(compNodes[i].begin(), compNodes[i].end());
    }
    for(int i = 1; i < endComp; i ++){
        // intermediary
        long long diffA = getSmallestDiff(startComp, i);
        long long costA = diffA * diffA;
        long long diffB = getSmallestDiff(i, endComp);
        long long costB = diffB * diffB;
        // cout << " through " << i << " with  cost = " <<  costA << " + " << costB << endl;
        best = min(best, costA + costB);
    }
    

    
    /*cout << "Comps " << endl;
    for(int i = 0; i < N; i ++){
        cout << nodeToComp[i] << " ";
    }
    cout << endl;
    cout << "MIN/MAX" << endl;
    for(int i = 0; i < N; i ++){
        cout << compMin[i] << " ";
    }
    cout << endl;
    for(int i = 0; i < N; i ++){
        cout << compMax[i] << " ";
    }
    cout << endl;*/
    // if(best == INT32_MAX){ // direct connection
    long long direct = (endStart - startEnd); // getSmallestDiff(startComp,endComp);
    best = min(best,direct * direct);//(endStart - startEnd) * (endStart - startEnd));
    // }
    
    

    cout << best << endl;

}

int main(int argc, char const *argv[])
{
    int T;
    cin >> T;
    while(T --){
        solve();
    }
    return 0;
}
