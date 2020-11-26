#include <iostream>
//#include <vector>
//#include <queue>
//#include <limits>
//#include <utility>
#define LIMIT 104
using namespace std;

int N;
int graph[LIMIT][LIMIT]; // Max N is 100

int solve(int a){
    int total = 0;
    bool finalized[LIMIT]={false};
    //int costs[100];
    int prevNode[LIMIT] = {0};
    int keys[LIMIT];
    fill(keys, keys + LIMIT, INT32_MAX);
    keys[a] = 0;

    int finalizedCount = 0;
    while(finalizedCount < N){
        int minKey = INT32_MAX;
        int minPos = -1;
        for(int i = 0; i < N; i ++){
            if(keys[i] < minKey && !finalized[i]){
                minKey = keys[i];
                minPos = i;
            }
        }
        //cout << "Visit " << minPos << " " << minKey << endl;
        //assert minPos != -1;
        //if(!finalized[minPos]){
            finalizedCount ++;
        //}
        finalized[minPos] = true;
        
        for(int i = 0; i < N; i ++){
            if(graph[minPos][i] != 0 && !finalized[i]){
                if((graph[minPos][i]) < keys[i]){
                    keys[i] = graph[minPos][i];
                    prevNode[i] = minPos;
                }
            }
        }
        
    }
    
    /*for(int i = 0; i < N; i ++){
        total += keys[i];
    }*/
    for(int i = 0; i < N; i ++){
            total += graph[i][prevNode[i]];
        }
    return total;
}
int main(){
    cin >> N;
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < N; j ++){
            int dist;
            cin >> dist;
            graph[i][j] = dist;
        }
    }
    int best = INT32_MAX;
    /*for(int i = 0; i < N; i ++){
        //cout << solve(i) << " " ;
       // cout << "Solving  " << i << endl;
        int res = solve(i);
        best = min(res,best);
        cout << "Result " << i << " is " << res << endl;
    }*/
    best = solve(0);
    cout << best;
}