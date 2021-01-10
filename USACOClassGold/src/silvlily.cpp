#include <iostream>
#include <vector>
#include <queue>
#define MAXN 31

using namespace std;
int N, M;
int grid[MAXN][MAXN];
int sx, sy; // start x,y
int fx, fy; // final x,y
vector<pair<int, int>> lilypads;
int dist(pair<int, int> p1, pair<int, int> p2)
{
    int a = p1.first - p2.first;
    int b = p1.second - p2.second;
    return a * a + b * b;
}
int dx[8];
int dy[8];
int main(int argc, const char **argv)
{
    // Constants
    dx[0] = 1;
    dy[0] = 2;
    dx[1] = 2;
    dy[1] = 1;
    dx[2] = -1;
    dy[2] = -2;
    dx[3] = -2;
    dy[3] = -1;
    dx[4] = -1;
    dy[4] = 2;
    dx[5] = -2;
    dy[5] = 1;
    dx[6] = 1;
    dy[6] = -2;
    dx[7] = 2;
    dy[7] = -1;

    // IO
    cin >> N >> M;
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            cin >> grid[i][j];
            switch (grid[i][j])
            {
            case 1:
                lilypads.push_back(make_pair(i, j));
                break;
            case 3:
                lilypads.push_back(make_pair(i, j));
                sx = i;
                sy = j;
                break;
            case 4:
                lilypads.push_back(make_pair(i, j));
                fx = i;
                fy = j;
                break;
            }
        }
    }
    
    
    int visited[MAXN][MAXN];
    for (int i = 0; i < N; i ++)
    {
        for (int j = 0; j < M; j++)
        {
            visited[i][j] = -1;
        }
    }
    queue<pair<int, pair<int, int>>> q;
    q.push(make_pair(0, make_pair(sx, sy)));
    visited[sx][sy] = 0;
    while (!q.empty())
    {
        pair<int, pair<int, int>> tri = q.front();
        int dist = tri.first;
        pair<int, int> p;
        q.pop();
        for (int i = 0; i < 8; i++)
        {
            int newx = p.first + dx[i];
            int newy = p.second + dy[i];
           
            if (0 <= newx && newx < N && 0 <= newy && newy < M)
            {
                 
                if (grid[newx][newy] == 1 || grid[newx][newy] == 3 || grid[newx][newy] == 4)
                {
                    // cout << "Travel to " << newx << "," << newy << endl;
                    if (visited[newx][newy] == -1)
                    {
                        visited[newx][newy] = dist + 1;
                        q.push(make_pair(dist + 1, make_pair(newx, newy)));
                    }
                }
                
            }
        }
    }
    cout << "Printing initial" << endl;
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < M; j ++){
            cout << visited[i][j] << " ";
        }
        cout << endl;
    }
    int visited2[MAXN][MAXN]; // Dist from nearest preexisting lilypad
    for (int i = 0; i < N; i ++)
    {
        for (int j = 0; j < M; j++)
        {
            visited2[i][j] = -1;
        }
    }
    for (int i = 0; i < lilypads.size(); i++)
    {
        // cout << "lpad " << lilypads[i].first << " " << lilypads[i].second << endl;
        // q.push(make_pair(0, lilypads[i]));
        // visited2[lilypads[i].first][lilypads[i].second] = 0;
    }
    q.push(make_pair(0,make_pair(sx,sy)));
    visited2[sx][sy] = 0;
    bool solFound = false;
    int solUnits = -1;
    int minJumps = INT32_MAX;
    int distCounts = 0;
    // cout << "Main bfs" << endl;
    while (!q.empty())
    {
        pair<int, pair<int, int>> tri = q.front();
        int dist = tri.first;
        pair<int, int> p = tri.second;
        cout << "Visit: " << p.first << "," << p.second << endl;
        q.pop();
        for (int i = 0; i < 8; i++)
        {
            int newx = p.first + dx[i];
            int newy = p.second + dy[i];
            bool lilypadExistsAlready = (grid[newx][newy] == 1) || (grid[newx][newy] == 3) || ((grid[newx][newy] == 4));
            int addDist = lilypadExistsAlready?0:1;
            if(!(0 <= newx && newx < N && 0 <= newy && newy < M)){
                continue;
            }
            if(visited2[newx][newy] != -1){
                continue;
            }
            cout << "Explore: "<< newx << "," << newy << endl;
            if(visited[newx][newy] == -1){
                visited[newx][newy] = visited[p.first][p.second] + addDist;
                
            }
            visited2[newx][newy] = visited2[p.first][p.second] + addDist;
            if(newx == fx && newy == fy){

                solUnits = min(dist + addDist, solUnits);
                minJumps = min(minJumps, visited[newx][newy]);
            }
            q.push(make_pair(dist + 1, make_pair(newx,newy)));
        }
    }
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < M; j ++){
            cout << visited[i][j] << " ";
        }
        cout << endl;
    }
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < M; j ++){
            cout << visited2[i][j] << " ";
        }
        cout << endl;
    }
    cout << solUnits << endl;
    if (solUnits != -1)
    {
        cout << minJumps << endl;
        cout << distCounts << endl;
    }
    return 0;
}