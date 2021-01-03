#include <iostream>
#include <queue>
#include <utility>
#define MAXNM 51

using namespace std
;
struct Cell{
    // ! Cell class using maze like terminology
    bool up = true, down = true, left = true, right = true;
};
int N, M;
Cell grid[MAXNM][MAXNM];
int regionCount;
int regionID[MAXNM][MAXNM];
int regionCellCount[2501] = {0};
Cell buildFromNum(int num){
    Cell out;
    if(num >= 8){
        out.down = false;
        num -= 8;
    }
    if(num >= 4){
        out.right = false;
        num -= 4;
    }
    if(num >= 2){
        out.up = false;
        num -= 2;
    }
    if(num >= 1){
        out.left = false;
        num -= 1;
    }
    return out;
}
int dx[] = {1,0,-1,0};
int dy[] = {0,1,0,-1};
void fillGrid(int i, int j){
    queue<pair<int,int>> next;
    next.push(make_pair(i,j));
    regionCount ++;
    regionCellCount[regionCount] ++;
    regionID[i][j] = regionCount;
    //cout << "grid[1][1]" << grid[1][1].down << endl;
    while(!next.empty()){
        pair<int,int> item = next.front();
        next.pop();
        //cout << item.first << "," << item.second << endl;
        Cell c = grid[item.first][item.second];
        //cout << "C: " << "Up: " << c.up << " Down: " << c.down << " Left: " << c.left << "Right: " << c.right << endl;
        if(c.up){
            int newx = item.first - 1, newy = item.second;
            if(0 <= newx && newx < N && 0 <= newy && newy < M && regionID[newx][newy] == 0){
                //cout << "Add Upwards" << endl;
                regionID[newx][newy] = regionCount;
                regionCellCount[regionCount] ++;
                next.push(make_pair(newx,newy));
            }
        }
        if(c.down){
            int newx = item.first + 1, newy = item.second;
            if(0 <= newx && newx < N && 0 <= newy && newy < M && regionID[newx][newy] == 0){
                //cout << "Add Downwards" << endl;
                regionID[newx][newy] = regionCount;
                regionCellCount[regionCount] ++;
                next.push(make_pair(newx,newy));
            }
        }
        if(c.left){
            int newx = item.first, newy = item.second - 1;
            if(0 <= newx && newx < N && 0 <= newy && newy < M && regionID[newx][newy] == 0){
                //cout << "Add Left" << endl;
                regionID[newx][newy] = regionCount;
                regionCellCount[regionCount] ++;
                next.push(make_pair(newx,newy));
            }
        }
        if(c.right){
            int newx = item.first, newy = item.second + 1;
            if(0 <= newx && newx < N && 0 <= newy && newy < M && regionID[newx][newy] == 0){
                //cout << "Add Right" << endl;
                regionID[newx][newy] = regionCount;
                regionCellCount[regionCount] ++;
                next.push(make_pair(newx,newy));
            }
        }

        
    }
}
int main(int argc, char const *argv[])
{
    cin >> M >> N;
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < M; j ++){
            int num;
            cin >> num;
            grid[i][j] = buildFromNum(num);
            //cout << "i: " << i << " j: " << j << " = " << "Up: " << grid[i][j].up << " Down: " << grid[i][j].down << " Left: " << grid[i][j].left << "Right: " << grid[i][j].right << endl;
        }
    }
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < M; j ++){
            if(regionID[i][j] == 0){
                //cout << "FILL" << endl;
                fillGrid(i,j);
            }
        }
    }
    cout << regionCount << endl;
    int maxSize = 0;
    for(int i = 0; i <= regionCount; i ++){
        //cout << regionCellCount[i] << " ";
        maxSize = max(maxSize, regionCellCount[i]);
    }
    cout << maxSize << endl;
    int maxCombineSize = 0;
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < M; j ++){
            for(int k = 0; k < 2; k ++){
                int newx = dx[k] + i;
                int newy = dy[k] + j;
                if(newx < 0 || newx >= N || newy < 0 || newy >= M){
                    continue;
                }
                if(regionID[newx][newy] == regionID[i][j]){
                    continue; 
                }
                maxCombineSize = max(maxCombineSize, regionCellCount[regionID[newx][newy]] + regionCellCount[regionID[i][j]]);
            }
        }
    }
    //cout << endl;
    /*for(int i = 0; i < N; i ++){
        for(int j = 0; j < M; j ++){
            cout << regionCellCount[regionID[i][j]] << " ";
        }
        cout << endl;
    }*/
    //cout << endl;
    cout << maxCombineSize;
    return 0;
}
