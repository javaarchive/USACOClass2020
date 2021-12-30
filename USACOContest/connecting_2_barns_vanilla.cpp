#include <iostream>
#include <bits/stdc++.h>
// #include <chrono>
#define MAXN 100001

using namespace std;

bool visited[MAXN] = {false};
int comp[MAXN] = {-1};
unordered_map<int,vector<int>> graph;
unordered_map<int,vector<int>> compNodes;
stack<int> nextNode;

int compID;

int getSmallestDiff(int a, int b){
    
    int smallest = INT32_MAX;
    int i = 0, j = 0;
    /*cout << "A vs B" << endl;
    for( auto elem: a){cout << elem << " ";}
    cout << endl;
    for( auto elem: b){cout << elem << " ";}
    cout << endl;*/
    
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
    /*for(int i = 0; i < compNodes[a].size(); i ++){
        for(int j = 0; j < compNodes[b].size(); j ++){
            int diff = abs(compNodes[a][i] - compNodes[b][j]);
            if(diff < smallest){
                smallest = diff;
            }
        }
    }*/
    return smallest;
}

int smallestDiff4(int a, int b, int c, int d){
    int ab = abs(a - b);
    int cd = abs(c - d);
    int ac = abs(a - c);
    int bd = abs(b - d);
    int smallest = min(ab, min(cd, min(ac, bd)));
    return smallest;
}

void dfs(int node){
    visited[node] = true;
    comp[node] = compID;
    compNodes[compID].push_back(node);
    for(auto elem: graph[node]){
        if(!visited[elem]){
            dfs(elem);
        }
    }
}


void solve(){
    // cout << "INIT" << endl;
    int N, M;
    graph.clear();
    // cout << "READ LENS ";
    cin >> N >> M;
    fill(comp, comp + N, -1);
    fill(visited, visited + N, false);
    compNodes.clear();
    /*for(int i = 0; i <= N; i ++){
        compNodes[i].clear();
    }*/
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
    compID = 1;
    // cout << "DFSing" << endl;
    for(int i = 0; i < N; i ++){
        if(!visited[i]){
            dfs(i);
            compID ++;
        }
    }
    
    // auto sort_start = chrono::high_resolution_clock::now();
    for(int i = 1; i < compID; i ++){
        /*if(vec.size() < 2){
            continue;
        }*/
        sort(compNodes[comp[i]].begin(), compNodes[comp[i]].end());
    }
    // auto sort_stop = chrono::high_resolution_clock::now();
    // cout << "SORT TIME " << (sort_stop - sort_start).count() << " microsecs " << endl;

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
        // auto smalldiff_start = chrono::high_resolution_clock::now();
        long long sq_root = getSmallestDiff(comp[0],comp[N - 1]);
        // auto smalldiff_stop = chrono::high_resolution_clock::now();
        // cout << "SMALLDIFF TIME " << (smalldiff_stop - smalldiff_start).count() << " microsecs " << endl;

        // cout << (sqrt*sqrt) << endl;
        // auto loopdiff_start = chrono::high_resolution_clock::now();
        long long best = sq_root * sq_root;
        int lastComp = comp[N - 1];
        for(int i = 2; i < lastComp; i ++){
            // peek
            /*if(compNodes[comp[0]].size() > 1 && compNodes[comp[N - 1]].size() > 1 && compNodes[comp[i]].size() > 1){
                 long long peekA = smallestDiff4(compNodes[comp[0]][0], compNodes[comp[0]].back() - 1, compNodes[comp[i]][0], compNodes[comp[i]].back() - 1);
                long long peekB = smallestDiff4(compNodes[comp[0]][0], compNodes[comp[0]].back() - 1, compNodes[comp[i]][0], compNodes[comp[i]].back() - 1);
                if((peekA * peekA + peekB * peekB) > best){
                    // PRUNE!
                    continue;
                }
            }*/
           

            long long distA = getSmallestDiff(1,i);
            long long distB = getSmallestDiff(i,comp[N - 1]);
            long long cost = distA * distA + distB * distB;
            // cout << "1 -> " << i << " -> " << " : " << distA * distA << " + " << distB * distB << " = " << cost << endl;
            best = min(best, cost);
        }
        for(int i = lastComp + 1; i < compID; i ++){
            // peek
            /*if(compNodes[comp[0]].size() > 1 && compNodes[comp[N - 1]].size() > 1 && compNodes[comp[i]].size() > 1){
                 long long peekA = smallestDiff4(compNodes[comp[0]][0], compNodes[comp[0]].back() - 1, compNodes[comp[i]][0], compNodes[comp[i]].back() - 1);
                long long peekB = smallestDiff4(compNodes[comp[0]][0], compNodes[comp[0]].back() - 1, compNodes[comp[i]][0], compNodes[comp[i]].back() - 1);
                if((peekA * peekA + peekB * peekB) > best){
                    // PRUNE!
                    continue;
                }
            }*/
           

            long long distA = getSmallestDiff(1,i);
            long long distB = getSmallestDiff(i,comp[N - 1]);
            long long cost = distA * distA + distB * distB;
            // cout << "1 -> " << i << " -> " << " : " << distA * distA << " + " << distB * distB << " = " << cost << endl;
            best = min(best, cost);
        }
        // auto loopdiff_stop = chrono::high_resolution_clock::now();
        // cout << "LOOPDIFF TIME " << (loopdiff_stop - loopdiff_start).count() << " microsecs " << endl;
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
