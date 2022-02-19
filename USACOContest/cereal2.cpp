#include <iostream>
#include <bits/stdc++.h>

#define MAXNM 100002

using namespace std;

vector<int> depFirst[MAXNM];
vector<int> depSecond[MAXNM];
int dependCount[MAXNM] = {0};

int preferences[MAXNM][2];

int getAlternate(int id){
    if(preferences[id][0] == id){
        return preferences[id][1];
    }else{
        return preferences[id][0];
    }
}

bool checkQ[MAXNM] = {false};

int gives[MAXNM] = {-1};

int getDepender(int ar){
    int leastDependingAlternateIndex = -1;
    int leastDepAlternate = INT32_MAX;
    for(int i = 0; i < depFirst[ar].size(); i ++){
        int dep = depFirst[ar][i];
        if(checkQ[dep]){
            continue;
        }
        int depAlternate = dependCount[getAlternate(dep)];
        if(depAlternate < leastDepAlternate){
            leastDepAlternate = depAlternate;
            leastDependingAlternateIndex = dep;
        }
    }
     for(int i = 0; i < depSecond[ar].size(); i ++){
        int dep = depSecond[ar][i];
        if(checkQ[dep]){
            continue;
        }
        int depAlternate = dependCount[getAlternate(dep)];
        if(depAlternate < leastDepAlternate){
            leastDepAlternate = depAlternate;
            leastDependingAlternateIndex = dep;
        }
    }
    return leastDependingAlternateIndex;
}

void solve(){
    int N, M;
    cin >> N >> M;
    for(int i = 0; i < N; i ++){
        int A,B;
        cin >> A >> B;
        A --; B --;
        depFirst[A].push_back(i);
        depFirst[B].push_back(i);
        preferences[i][0] = A;
        preferences[i][1] = B;
    }
    int leastDeps = INT32_MAX;
    int leastDepIndex = -1;
    for(int i = 0; i < N; i ++){
        dependCount[i] = depFirst[i].size() + depSecond[i].size();
        if((dependCount[i]) < leastDeps){
            leastDeps = dependCount[i];
            leastDepIndex = i;
        }
    }
    int starving = 0;
    stack<int> toFix;
    checkQ[leastDepIndex] = true;
    toFix.push(leastDepIndex);
    vector<int> rec;
    while(!toFix.empty()){
        int cur = toFix.top();
        toFix.pop();
        int toGiveTo = getDepender(cur);
        rec.push_back(toGiveTo);
        for(int i = 0; i < depFirst[cur].size(); i ++){
            int dep = getAlternate(depFirst[cur][i]);
            if(checkQ[dep]){
                continue;
            }
            checkQ[dep] = true;
            toFix.push(dep);
        }
        for(int i = 0; i < depSecond[cur].size(); i ++){
            int dep = getAlternate(depSecond[cur][i]);
            if(checkQ[dep]){
                continue;
            }
            checkQ[dep] = true;
            toFix.push(dep);
        }
    }
    starving = N;
    for(int i = 0; i < N; i ++){
        if(gives[i] > -1){
            starving --;
        }
    }
    cout << starving << endl;
    for(int c: rec) cout << c << endl;
}

int main(int argc, char const *argv[])
{
    solve();
    return 0;
}
