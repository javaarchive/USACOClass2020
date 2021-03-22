#include <iostream>
#include <queue>
#define MAXN 100001
using namespace std;
int N;
pair<int,int> points[MAXN];
queue<pair<int,int>> toPlace;
const int XYOffset = 1000;
bool grid[3001][3001];
int spread[3001][3001];
int dx[] = {0,0,-1,1};
int dy[] = {1,-1,0,0};
int placeCount = 0;

void put(int x, int y){
    if(grid[x][y]) return;
    placeCount ++;
    grid[x][y] = true;
    // update spreads
    for(int i = 0; i < 4; i ++){
        int newx = dx[i] + x;
        int newy = dy[i] + y;
        spread[newx][newy] ++;
        if(spread[newx][newy] == 3 && grid[newx][newy]){
            for(int j = 0; j < 4; j ++){
                if(!grid[newx + dx[j]][newy + dy[j]]){
                    toPlace.push(make_pair(newx + dx[j], newy + dy[j]));
                }
            }
            
        }
        if(spread[x][y] == 3){
            if(!grid[newx][newy]){
                toPlace.push(make_pair(newx,newy));
            }
        }
    }
}

int main(int argc, const char** argv) {
    for(int i = 0; i < 3001; i ++){
        for(int j = 0; j < 3001; j ++){
            // init
            grid[i][j] = false;
            spread[i][j] = 0;
        }
    }
    cin >> N;
    for(int i = 0; i < N; i ++){
        cin >> points[i].first >> points[i].second;
        points[i].first  += XYOffset;
        points[i].second += XYOffset;
    }
    for(int i = 0; i < N; i ++){
        //cout << "processing " << i;
        toPlace.push(points[i]);
        while(!toPlace.empty()){
            pair<int,int> next = toPlace.front();
            toPlace.pop();
            /*if(grid[next.first][next.second]){
                // already placed
                continue;    
            }*/
            // cout << "popped";
            put(next.first, next.second);
            
        }
        cout << (placeCount - (i + 1)) << endl;
    }

    return 0;
}