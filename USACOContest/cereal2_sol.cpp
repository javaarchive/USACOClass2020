#include <iostream>
#include <bits/stdc++.h>

#define MAXNM 100002

using namespace std;

int N, M;

struct OppositeEnd{
    int cow;
    int otherEnd;
    bool isFirst;
};

vector<OppositeEnd> adj[MAXNM];

bool cycleCheckedVisited[MAXNM] = {false};

void detect_cycle(int cur, int previous){
    for(OppositeEnd next: adj[cur]){
        // iterate through adjacent end
    }
}


void solve(){
    cin >> N >> M;
    for(int i = 0; i < N; i ++){
        int firstChoice, secondChoice;
        cin >> firstChoice >> secondChoice;
        OppositeEnd oe1;
        oe1.cow = i + 1;
        oe1.otherEnd = firstChoice;
        oe1.isFirst = true;
        OppositeEnd oe2;
        oe2.cow = i + 1;
        oe2.otherEnd = secondChoice;
        oe2.isFirst = false;
        adj[secondChoice].push_back(oe1);
        adj[firstChoice].push_back(oe2);
        
    }



}

int main(int argc, char const *argv[])
{
    solve();
    return 0;
}
