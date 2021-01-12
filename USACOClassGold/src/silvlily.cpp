#include <iostream>
#include <vector>
#include <queue>
#include <cmath>
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
    //   existing lilypads,newpads
    pair<int, int> visited[MAXN][MAXN];
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            visited[i][j] = make_pair(-1, INT32_MAX);
        }
    }
    queue<pair<int, pair<int, int>>> q;
    q.push(make_pair(0, make_pair(sx, sy)));
    visited[sx][sy] = make_pair(0, 0);
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
                    if (visited[newx][newy].first == -1)
                    {
                        visited[newx][newy].first = dist + 1;
                        q.push(make_pair(dist + 1, make_pair(newx, newy)));
                    }
                }
            }
        }
    }
    /*cout << "Printing initial" << endl;
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < M; j ++){
            cout << visited[i][j] << " ";
        }
        cout << endl;
    }*/

    for (int i = 0; i < lilypads.size(); i++)
    {
        // cout << "lpad " << lilypads[i].first << " " << lilypads[i].second << endl;
        // q.push(make_pair(0, lilypads[i]));
        // visited2[lilypads[i].first][lilypads[i].second] = 0;
    }
    q.push(make_pair(0, make_pair(sx, sy)));
    bool solFound = false;
    int solUnits = INT32_MAX;
    int minJumps = INT32_MAX;
    int distCounts = 0;
    // cout << "Main bfs" << endl;
    while (!q.empty())
    {
        pair<int, pair<int, int>> tri = q.front();
        int dist = tri.first;
        pair<int, int> p = tri.second;
        // cout << "Visit: " << p.first << "," << p.second << endl;
        q.pop();
        for (int i = 0; i < 8; i++)
        {
            int newx = p.first + dx[i];
            int newy = p.second + dy[i];
            bool lilypadExistsAlready = (grid[newx][newy] == 1) || (grid[newx][newy] == 3) || ((grid[newx][newy] == 4));
            int addDist = lilypadExistsAlready ? 0 : 1;
            if (!(0 <= newx && newx < N && 0 <= newy && newy < M))
            {
                continue;
            }
            if (grid[newx][newy] == 2)
            {
                continue;
            }
            // cout << "Explore: " << newx << "," << newy << endl;
            if (visited[newx][newy].second <= (visited[p.first][p.second].second + addDist))
            {
                continue;
            }
            // cout << "Better path for " << newx << "," << newy << " " << (visited[p.first][p.second].second + addDist) << " < " << visited[newx][newy].second << endl;
            visited[newx][newy].second = visited[p.first][p.second].second + addDist;
            visited[newx][newy].first = visited[p.first][p.second].first + 1 - addDist; // If an extra lilypad was not added then a existing one was used
            if (newx == fx && newy == fy)
            {
                solUnits = min(visited[p.first][p.second].second + addDist, solUnits);
                // cout << "New minjumps = " << visited[newx][newy].first << " + " << visited[newx][newy].second << endl;
                // minJumps = min(minJumps, visited[newx][newy].first + visited[newx][newy].second);
                // distCounts ++;
            }
            q.push(make_pair(dist + addDist, make_pair(newx, newy)));
        }
    }
    /*for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            cout << visited[i][j].first << " ";
        }
        cout << endl;
    }
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            cout << visited[i][j].second << " ";
        }
        cout << endl;
    }*/
    if (solUnits == INT32_MAX)
    {
        cout << "-1";
        return 0;
    }
    cout << solUnits << endl;
    if (solUnits != -1)
    {
        minJumps = visited[fx][fy].first + visited[fx][fy].second;
        //        preexist,new
        queue<pair<pair<int, int>, pair<int, int>>> next;
        
        next.push(make_pair(make_pair(0, 0), make_pair(sx, sy)));
        while (!next.empty())
        {
            pair<pair<int, int>, pair<int, int>> quad = next.front();
            next.pop();
            pair<int, int> counts = quad.first;
            pair<int, int> p = quad.second;

            if(counts.first > visited[fx][fy].first || counts.second > visited[fx][fy].second){
                continue;
            }

            // check steps left, imagine worse case scenario 
            int left = (visited[fx][fy].first - counts.first) + (visited[fx][fy].second - counts.second);
            int worstcase = ceil((abs(p.first - fx))/4) + ceil((abs(p.second - fy))/4);
            if(worstcase > left){
                continue; // does not work in worst case = does not work at all
            }

            
            if(p.first == fx && p.second == fy && counts.first == visited[fx][fy].first && counts.second == visited[fx][fy].second){
                // cout << "Match " << p.first << "," << p.second << " counts: " << counts.first << " , " << counts.second << endl; 
                distCounts ++;
                continue;
            }
            for (int i = 0; i < 8; i++)
            {
                int newx = p.first + dx[i];
                int newy = p.second + dy[i];
                bool lilypadExistsAlready = (grid[newx][newy] == 1) || (grid[newx][newy] == 3) || ((grid[newx][newy] == 4));
                if (!(0 <= newx && newx < N && 0 <= newy && newy < M))
                {
                    continue;
                }
                if(grid[newx][newy] == 2){continue;}
                //cout << lilypadExistsAlready << " : " << p.first << "," << p.second << " -> " << newx << "," << newy << endl;
                if (lilypadExistsAlready)
                {
                    if(counts.first == visited[fx][fy].first){continue;}   
                    next.push(make_pair(make_pair(counts.first + 1, counts.second), make_pair(newx, newy)));
                }
                else
                {
                    if(counts.second == visited[fx][fy].second){continue;}
                    next.push(make_pair(make_pair(counts.first, counts.second + 1), make_pair(newx, newy)));
                }
            }
        }
        cout << minJumps << endl;
        cout << distCounts << endl;
        
    }
    return 0;
    // 2,5 sample case
}