#include <bits/stdc++.h>
#define MAXN 51
using namespace std;

const int dx[4] = {0, 0, 1, -1};
const int dy[4] = {1, -1, 0, 0};

void solve(){
    int N;
    int r1,c1; // start
    int r2,c2; // dest
    cin >> N;
    cin >> r1 >> c1;
    cin >> r2 >> c2;
    r1 --; c1 --; r2 --; c2 --;
    vector<pair<int,int>> points1;
    vector<pair<int,int>> points2;

    queue<pair<int,int>> next;
    next.push(make_pair(r1,c1));
    // next.push(make_pair(r2,c2));
    set<pair<int,int>> visitedFromStart;
    visitedFromStart.insert(make_pair(r1,c1));
    int grid[MAXN][MAXN];
    for(int i = 0; i < N; i ++){
        string temp;
        cin >> temp;
        for(int j = 0; j < N; j ++){
            grid[i][j] = temp[j] - '0';
        }
    }

    while(!next.empty()){
        pair<int,int> cur = next.front();
        next.pop();
        if(cur.first == r2 && cur.second == c2){
            cout << 0 << endl;
            return;
        }
        bool touchingWater = false;
        for(int i = 0; i < 4; i ++){
            int newx = dx[i] + cur.first;
            int newy = dy[i] + cur.second;
            if(0 <= newx && newx < N && 0 <= newy && newy < N){
                if(visitedFromStart.find(make_pair(newx,newy)) == visitedFromStart.end() && grid[newx][newy] == 0){
                    visitedFromStart.insert(make_pair(newx,newy));
                    next.push(make_pair(newx,newy));
                }
                if(newx == r2 && newy == c2){
                    cout << 0 << endl;
                    return;
                }
            }

            if(grid[newx][newy] == 1){
                touchingWater = true;
            }
        }
        if(touchingWater){
            points1.push_back(cur);
        }
    }

    next.push(make_pair(r2,c2));

    set<pair<int,int>> visitedFromDest;

    while(!next.empty()){
        pair<int,int> cur = next.front();
        next.pop();
        bool touchingWater = false;
        // cout << "(" << cur.first << "," << cur.second << ")" << endl;
        for(int i = 0; i < 4; i ++){
            int newx = dx[i] + cur.first;
            int newy = dy[i] + cur.second;
            // cout << "A (" << newx << "," << newy << ")" << endl;
            // cout << (0 <= newx) << (newx < N) << (0 <= newy) << (newy < N) << endl;
            if(0 <= newx && newx < N && 0 <= newy && newy < N){
                // cout << "(" << newx << "," << newy << ")" << endl;
                if(visitedFromDest.find(make_pair(newx,newy)) == visitedFromDest.end() && grid[newx][newy] == 0){
                    visitedFromDest.insert(make_pair(newx,newy));
                    next.push(make_pair(newx,newy));
                }
            }

            if(grid[newx][newy] == 1){
                touchingWater = true;
            }
        }
        if(touchingWater){
            points2.push_back(cur);
        }
    }

    int best = INT32_MAX;    
    // cout << "SIZES " << points1.size() << " vs " << points2.size() << endl;
    for(auto p1: points1){
        for(auto p2: points2){
            best = min(best, (p1.first - p2.first)*(p1.first - p2.first) + (p1.second - p2.second)*(p1.second - p2.second));
        }
    }

    cout << best << endl;
}

int main(int argc, char const *argv[])
{
    solve();
    return 0;
}
