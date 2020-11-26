#include <iostream>
//#include <vector>
//#include <queue>
//#include <limits>
//#include <utility>
#include <iomanip>
#include <cmath>

#define LIMIT 1004
using namespace std;

int N, M;
double graph[LIMIT][LIMIT]; 
bool allowZero[LIMIT][LIMIT] = {false}; // Allows 0 length edges to force prim's to choose certain edges hahahaha

pair<int,int> nodes[LIMIT];
double dist(pair<int,int> a, pair<int,int> b){
    double sideA = (a.first - b.first);
    double sideB = (a.second - b.second);
    return sqrt(sideA * sideA + sideB * sideB);
}

int solve(int a){
    double total = 0;
    bool finalized[LIMIT]={false};
    //int costs[100];
    int prevNode[LIMIT] = {-1};
    double keys[LIMIT];
    fill(keys, keys + LIMIT, INT32_MAX);
    keys[a] = 0;

    int finalizedCount = 0;
    while(finalizedCount < N){
        double minKey = INT32_MAX;
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
            if((graph[minPos][i] != 0 || allowZero[minPos][i]) && !finalized[i]){
                if((graph[minPos][i]) < keys[i] || (graph[minPos][i] == 0 && allowZero[minPos][i])){
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
        //cout << i << " ---[ " << graph[i][prevNode[i]] << "]--- " << prevNode[i] << endl;
            total += graph[i][prevNode[i]];
        }
    return total;
}
int main(){
    //setprecision(2);
    cin >> N >> M;
    for(int i = 0; i < N; i ++){
        int a,b;
        cin >> a >> b;
        nodes[i].first = a;
        nodes[i].second = b;

        
    }
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < N; j ++){
            if(i != j){
                double edist = dist(nodes[i],nodes[j]);
                graph[i][j] = edist;
                graph[j][i] = edist;
            }
            //cout << graph[i][j] << " ";
        }
        //cout << endl;
    }
    for(int i = 0; i < M; i ++){
       int a,b;
       cin >> a >> b;
       graph[a - 1][b - 1] = 0;
       graph[b - 1][a - 1] = 0;
       allowZero[a - 1][b - 1] = true;
       allowZero[b - 1][a - 1] = true;
    }
    /*for(int i = 0; i < N; i ++){
        for(int j = 0; j < N; j ++){
            cout << graph[i][j] << " ";
        }
        cout << endl;
    }*/
    //setprecision(2);
    double best = INT32_MAX;
    /*for(int i = 0; i < N; i ++){
        //cout << solve(i) << " " ;
       // cout << "Solving  " << i << endl;
        int res = solve(i);
        best = min(res,best);
        cout << "Result " << i << " is " << res << endl;
    }*/
    
    best = solve(0);
    
    cout << fixed << setprecision(2) << best;
}