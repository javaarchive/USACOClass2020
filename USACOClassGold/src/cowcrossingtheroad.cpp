#define MAXN 101
#include <iostream>
#include <queue>
#include <vector>
#include <utility>
#include <tuple>

using namespace std;
int N,T;
int grid[MAXN][MAXN];
vector<tuple<int,int,int>> graph[MAXN][MAXN];
int jumps[][2] = {
    {0,3},
    {1,2},
    {2,1},
    {3,0},
    {2,-1},
    {1,-2},
    {0,-3},
    {-1,-2},
    {-2,-1},
    {-1,2},
    {-2,1},
    {-3,0},
    {0,1},
    {0,-1},
    {1,0},
    {-1,0}
};
int distOneJumps[][2] = {
    {0,1},
    {0,-1},
    {1,0},
    {-1,0}
};
int distTwoJumps[][2] = {
    {0,2},
    {1,1},
    {2,0},
    {1,-1},
    {0,-2},
    {-1,-1},
    {-2,0},
    {-1,1}
};
int solve(){
    int moveInitialCost = 3 * T;
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < N; j ++){
            for(int k = 0; k < 16; k ++){
                int newx = i + jumps[k][0];
                int newy = j + jumps[k][1];
                if(0 <= newx && newx < N && 0 <= newy && newy < N){
                    graph[i][j].push_back(make_tuple(grid[newx][newy] + moveInitialCost,newx,newy));
                }
            }
        }
    }
    // ! Backwards tracing
    // dist one
    for(int i = 0; i < 4; i ++){
        int newx = (N - 1) + distOneJumps[i][0];
        int newy = (N - 1) + distOneJumps[i][1];
        if(0 <= newx && newx < N && 0 <= newy && newy < N){
            graph[newx][newy].push_back(make_tuple(T,N-1,N-1));
        }
    }
    // dist two
    for(int i = 0; i < 8; i ++){
        int newx = (N - 1) + distTwoJumps[i][0];
        int newy = (N - 1) + distTwoJumps[i][1];
        if(0 <= newx && newx < N && 0 <= newy && newy < N){
            graph[newx][newy].push_back(make_tuple(2*T,N-1,N-1));
        }
    }




    int minDist[MAXN][MAXN];
    for(int i = 0; i < MAXN; i ++){
        for(int j = 0; j < MAXN; j ++){
            minDist[i][j] = INT32_MAX;
        }
    }
    minDist[0][0] = 0;
    // pair<int,int>,vector<pair<int,int>>,greater<pair<int,int>>
    priority_queue<pair<int,pair<int,int>>,vector<pair<int,pair<int,int>>>,greater<pair<int,pair<int,int>>>> pq;
    pq.push(make_pair(0,make_pair(0,0)));
    while(!pq.empty()){
        
        pair<int,pair<int,int>> p = pq.top();
        pair<int,int> coord = p.second;
        pq.pop();
        //cout << coord.first << "," << coord.second  << " ------ " << p.first << endl;
        if(coord.first == (N - 1) && coord.second == (N - 1)){
            return p.first;
        }
        for(auto iter = graph[coord.first][coord.second].begin(); iter != graph[coord.first][coord.second].end(); iter ++ ){
            tuple<int,int,int> choice = *iter;
            int dist = get<0>(choice);
            int newDist = p.first + dist;
            if(newDist < minDist[get<1>(choice)][get<2>(choice)]){
                minDist[get<1>(choice)][get<2>(choice)] = newDist;
                pq.push(make_pair(newDist, make_pair(get<1>(choice),get<2>(choice))));
            }
        }
    }
    return minDist[N - 1][N - 1];
}

int main(int argc, const char** argv) {
    cin >> N >> T;
    
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < N; j ++){
            cin >> grid[i][j];
        }
    }
    cout << solve();
    return 0;
}