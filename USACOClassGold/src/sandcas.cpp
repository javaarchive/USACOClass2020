#include <iostream>
#include <algorithm>
#define MAXN 25001
using namespace std;
int N, X, Y;
int M[MAXN];
int D[MAXN];
int calcTransfer(int a, int b){
    if(a > b){
        return (a - b) * Y; // reduce
    }else{
        return (b - a) * X; // add 
    }
}
int main(int argc, char const *argv[])
{
    cin >> N >> X >> Y;
    for(int i = 0; i < N; i ++){
        cin >> M[i];
        cin >> D[i];
    }
    sort(M, M + N);
    sort(D, D + N);
    int cost = 0;
    int bestCost = INT32_MAX;
    /*int minStartCost = INT32_MAX;
    int minStartPos = -1;
    for(int i = 0; i < N; i ++){
        int startCost = calcTransfer(M[0],D[i]);
        if(startCost < minStartCost){
            minStartPos = i;
            minStartCost = startCost;
        }
    }*/
    for(int i = 0; i < N; i ++){
        cost += calcTransfer(M[i], D[i]);
    }
    bestCost = cost; // only cost yet
    /*for(int i = 0; i < N; i ++){
        cost = 0;
        for(int j = 0; j < N; j ++){
            cost += calcTransfer(M[(i + j) % N], D[j]);
        }
        bestCost = min(bestCost, cost);
    }*/
    cout << bestCost << endl;
    return 0;
}
