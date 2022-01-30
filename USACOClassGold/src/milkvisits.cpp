#include <iostream>
#include <bits/stdc++.h>
#define MAXNM 100009

using namespace std;

set<int> types;
int typeMapping[MAXNM] = {-1};
int distinctTypes = 0;
int N, M;

stack<int> ord;

map<int,vector<int>> graph;

map<int,stack<pair<int,int>>> typeDists;

bool visited[MAXNM] = {false};

int queries[MAXNM][3]; 

pair<int,int> flatrange[MAXNM];

int depthRecord[MAXNM] = {0};

int counter = 0;

int tourFirst[MAXNM] = {-1};
int tourLast[MAXNM] = {-1};

vector<int> tourpath;

bool isAnc(int a, int b){
    return (flatrange[a].first <= flatrange[b].first) && (flatrange[a].second <= flatrange[b].second );
}

pair<int,int> path(){
    
};

void tour(int node){
    if(tourFirst[node] < 0){
        tourFirst[node] = tourpath.size();
        counter ++;
    }
    tourLast[node] = tourpath.size();
    tourpath.push_back(node);
}

void dfs(int nextNode, int depth){
    ord.push(nextNode);
    visited[nextNode] = true;
    depthRecord[nextNode] = depth;
    typeDists[typeMapping[nextNode]].push(make_pair(nextNode,depth));
    counter ++;
    flatrange[nextNode].first = counter;

    tour(nextNode);

    for(int node: graph[nextNode]){
        if(!visited[node]){
            visited[node] = true;
            dfs(node, depth + 1);
        }
        // skip
    }

    tour(nextNode);

    flatrange[nextNode].second = counter - 1;
    ord.pop();
}

void solve(){
    cin >> N >> M;
    for(int i = 0; i < N; i ++){
        int type;
        typeMapping[i + 1] = type;
        cin >> type;
        types.insert(type);
    }
    distinctTypes = types.size();


    for(int i = 0; i < (N - 1); i ++){
        int a, b;
        cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }

    // dfs!

    dfs(1,1);

    // read queries

    int a,b,c;
    for(int i = 0; i < M; i ++){
        cin >> a >> b >> c;
        queries[i][0] = a;
        queries[i][1] = b;
        queries[i][2] = c;
    }

    
}

int main(int argc, char const *argv[])
{
    solve();

    return 0;
}
