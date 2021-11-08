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
    for(int i=0; i<N; i ++){
        if(respecting[i] == 0 && respectsParent[i] == 0){
            nextUp.push(i);
        }
    }
    int count = 0;
    while(!nextUp.empty()){
        int toRemove = nextUp.top();
        nextUp.pop();
        count ++;
        cout << (toRemove + 1) << " "; //<< endl;

        int myParent = parent[toRemove];
        removed[toRemove] = true;
        for(int child: adjList[toRemove]){
            // cout << "relocated child " << child;
            parent[child] = myParent;
            adjList[myParent].push_back(child);
            if(respectsParent[child]){
                respecting[myParent] ++;
            }else if(respecting[myParent] == 0 && !removed[toRemove]){
                // To quueue deletion
                nextUp.push(child);
            }
        }
        // adjList[toRemove].clear();
        
        adjList.erase(toRemove);
        
    }
    if(count <= 0){
        cout << -1 << endl;
    }
    cout << endl;
}

int main(int argc, char const *argv[])
{
    solve();
    return 0;
}
