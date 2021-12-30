#include <iostream>
#include <bits/stdc++.h>
#define MAXN 500001

using namespace std;

int getSmallestDiff(vector<int>& a, vector<int>& b){
    int smallest = INT32_MAX;
    int i = 0, j = 0;
    /*cout << "A vs B" << endl;
    for(auto elem: a){cout << elem << " ";}
    cout << endl;
    for(auto elem: b){cout << elem << " ";}
    cout << endl;*/
    
    while(i < a.size() && j < b.size()){
        int diff = abs(a[i] - b[j]);
        if(diff < smallest){
            smallest = diff;
        }
        if(a[i] < b[j]){
            i ++;
        }else{
            j ++;
        }
    }
    return smallest;
}


void solve(){
    // cout << "INIT" << endl;
    int N, M;
    map<int,vector<int>> graph;
    
    bool visited[MAXN] = {false};
    int comp[MAXN] = {-1};
    // cout << "READ LENS ";
    cin >> N >> M;
    vector<vector<int>> compNodes(N + 2);
    /*for(int i = 0; i < N; i ++){
        compNodes.push_back(vector<int>());
    }*/
    // compNodes.push_back(vector<int>()); // initial empty
    // cout << "READ EDGES";
    for(int i = 0; i < M; i ++){
        int a,b;
        cin >> a >> b;
        a --; b --; // Offsetting
        graph[a].push_back(b);
        graph[b].push_back(a);     
    }
    // cout << " READED EDGES" << endl;
    // cout << endl;
    int compID = 1;
    // cout << "DFSing" << endl;
    stack<int> next;
    for(int i = 0; i < N; i ++){
        if(visited[i]){
            continue; // skip already visited
        }
        // dfs
        visited[i] = true;
        comp[i] = compID;
        // cout << "DFSing from " << i << " for comp " << compID << endl;
        next.push(i);
        compNodes[compID].push_back(i);
        while(!next.empty()){
            int node = next.top();
            next.pop();
            for(int otherNode: graph[node]){
                if(visited[otherNode]){
                    continue; // already visited
                }
                // mark as same component
                comp[otherNode] = compID;
                // mark visited and add to stack
                visited[otherNode] = true;
                // cout << "Marked " << otherNode << " as " << compID << endl;
                compNodes[compID].push_back(otherNode);
                next.push(otherNode);
            }
        }
        compID ++;
    }

    for(auto vec: compNodes){
        if(vec.size() < 2){
            continue;
        }
        sort(vec.begin(), vec.end());
    }

    // 1 is starting
    // compID - 1 is ending
    // cout << "ANS: ";
    // cout << "( compID is " << compID << " ) " ;

    /*for(int i = 0; i <= compID; i ++){
        cout << i << ": ";
        for(int elem: compNodes[i]){
            cout << elem << " ";
        }
        cout << endl;
    }*/


    if(comp[0] == comp[N - 1]){
        // everything is already connected
        cout << 0 << endl;
    }else{
         // single connection required
        long long sq_root = getSmallestDiff(compNodes[comp[0]],compNodes[comp[N - 1]]);
        // cout << (sqrt*sqrt) << endl;
        long long best = sq_root * sq_root;
        for(int i = 2; i < (compID - 1); i ++){
            long long distA = getSmallestDiff(compNodes[1],compNodes[i]);
            long long distB = getSmallestDiff(compNodes[i],compNodes[comp[N - 1]]);
            long long cost = distA * distA + distB * distB;
            // cout << "1 -> " << i << " -> " << " : " << distA * distA << " + " << distB * distB << " = " << cost << endl;
            best = min(best, cost);
        }
        cout << best << endl;
    }
    return;
}

int main(int argc, char const *argv[])
{
    int T;
    cin >> T;
    while(T -- ){
        solve();
    }
    return 0;
}
