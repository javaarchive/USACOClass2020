#include <iostream>
#include <set>
#include <vector>
#include <utility>
#include <algorithm>
#include <queue>
#define MAXN 200002

using namespace std;
int N,K;
int swapA[MAXN];
int swapB[MAXN];
int origPos[MAXN];
int freq[MAXN] = {0};
int pregen[MAXN];
vector<int> cycles[MAXN]; 
bool visited[MAXN] = {false};
int uniqs = 0;
int output[MAXN] = {0};
queue<int> nextq;
void recur_dfs(int node){
    //cout << "DFS " << node << endl;
    if(visited[node]){
        return;
    }
    visited[node] = true;
    for(int connected: cycles[node]){
        // cout << "Connected node found " << connected << "-> " << freq[connected]<< endl;
        if(freq[connected] == 0){
            uniqs ++;
        }
        freq[connected] ++;
    }
    nextq.push(node);
    //cout << "next node " << pregen[node] << endl;
    if(!visited[pregen[node]]){
        recur_dfs(pregen[node]);
    }
}
int main(int argc, const char** argv) {
    cin >> N >> K;
    for(int i = 0; i < K; i ++){
        cin >> swapA[i];
        cin >> swapB[i];
    }
    //cout << "In done" << endl;
    for(int i = 1; i <= N; i ++){
        origPos[i] = i;
    }
    for(int i = 1; i <= N; i ++){
        cycles[i].push_back(i); // obviously include itself
    }
    for(int i = 1; i <= K; i ++){
        int a = swapA[i - 1];
        int b = swapB[i - 1];
        cycles[origPos[a]].push_back(b);
        cycles[origPos[b]].push_back(a);
        swap(origPos[a],origPos[b]);
    }
    for(int i = 1; i <= N; i ++){
        pregen[origPos[i]] = i;
    }
    // fill(visited,visited + N + 1, false);
    //cout << "Start loop" << endl;
    for(int i = 1; i <= N; i ++){
        //cout << "PROC: " << i << endl;
        if(!visited[i]){
            recur_dfs(i);
            int uniqCopy = uniqs;
            while(!nextq.empty()){
                int fr = nextq.front();
                for(int connected: cycles[fr]){
                    if(freq[connected] == 1){
                        uniqs --;
                    }
                    freq[connected] --;
                }
                output[fr] = uniqCopy;
                nextq.pop();
            }
        }
    }
    for(int i = 1; i <= N; i ++){
        cout << output[i] << endl;
    }
    return 0;
}