#include <bits/stdc++.h>
#define MAXN 100005
using namespace std;

map<int,vector<int>> adjList;
int respecting[MAXN] = {0};
int respectsParent[MAXN] = {0};
int parent[MAXN] = {-1};
bool removed[MAXN] = {false};
 
void solve(){
    int N;
    cin >> N;
    for(int i=0; i<N; i ++){
        cin >> parent[i];
        parent[i] --;
        bool respect;
        int respectNum = 0;
        cin >> respectNum;
        respect = respectNum != 1;
        if(respect){
            if(parent[i] >= 0){
                respecting[parent[i]] += 1;
            }
            respectsParent[i] = 1;
        }else{
            respectsParent[i] = 0;
        }
        if(parent[i] >= 0){
            adjList[parent[i]].push_back(i);
        }
    }

    priority_queue<int,vector<int>,greater<int>> nextUp;
    /*cout << "DEBUG: ";
    cout << endl;
    cout << "Respecting: ";
    for(int i = 0; i < N; i ++){
        cout << respecting[i] << " ";
    }
    cout << endl;
    cout << "Respectful: ";
    for(int i = 0; i < N; i ++){
        cout << respectsParent[i] << " ";
    }
    cout << endl;*/
    set<int> removedNodes;
    for(int i=0; i<N; i ++){
        if(respecting[i] == 0 && respectsParent[i] == 0){
            nextUp.push(i);
            removedNodes.insert(i);
        }
    }
    int count = 0;
    
    // sort(removedNodes.begin(), removedNodes.end());
    for(int elem: removedNodes){
        cout << elem + 1 << " ";
    }
    if(removedNodes.size() <= 0){
        cout << -1 << endl;
    }
    cout << endl;
}

int main(int argc, char const *argv[])
{
    solve();
    return 0;
}
