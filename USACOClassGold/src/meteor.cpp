#include <iostream>
#include <utility>
#include <queue>

using namespace std;
const int maxcoord = 1001;
int hitmap[maxcoord][maxcoord] = {0};
int visited[maxcoord][maxcoord] = {0};
int M;
struct opt
{
    int x, y, time;
};
queue<opt> nextOpts;
int checkHit(int x, int y, int time)
{
    return (hitmap[x][y] != 0) && (hitmap[x][y] <= time);
}
int dx[] = {0, 0, -1, 1};
int dy[] = {1, -1, 0, 0};
int solve()
{
    opt start = {0, 0, 1};
    visited[0][0] = 1;
    nextOpts.push(start);
   
    while (!nextOpts.empty())
    {
        opt curOpt = nextOpts.front();
        //cout << curOpt.x << " " << curOpt.y << " " << curOpt.time << endl;
        nextOpts.pop();
        for (int i = 0; i < 4; i++)
        {
            int newx = curOpt.x + dx[i];
            int newy = curOpt.y + dy[i];
            if ((0 <= newx) && (newx < maxcoord) && (0 <= newy) && (newy < maxcoord))
            {
                if (visited[newx][newy] == 0)
                {
                    if (!checkHit(newx, newy, curOpt.time + 1))
                    {
                        visited[newx][newy] = curOpt.time + 1;
                        opt newopt = {newx, newy, curOpt.time + 1};
                        if (hitmap[newx][newy] == 0)
                        {
                            return curOpt.time + 1;
                        }
                        nextOpts.push(newopt);
                    }else{
                        //cout << "Getting hit at " << newx << " " << newy << " t: " << curOpt.time + 1 << endl; 
                    }
                }
            }
        }
    }
    return 0; // to be offset to -1
}
int main(int argc, const char **argv)
{
    cin >> M;
    int x, y, t;
    for (int i = 0; i < M; i++)
    {
        cin >> x >> y >> t;
        if (hitmap[x][y] == 0)
        {
            hitmap[x][y] = t + 1;
        }
        else
        {
            hitmap[x][y] = min(t + 1, hitmap[x][y]);
        }
        for (int i = 0; i < 4; i++)
        {
            int newx = x + dx[i];
            int newy = y + dy[i];
            if ((0 <= newx) && (newx < maxcoord) && (0 <= newy) && (newy < maxcoord))
            {
                if (hitmap[newx][newy] == 0)
                {
                    hitmap[newx][newy] = t + 1;
                }
                else
                {
                    hitmap[newx][newy] = min(t + 1, hitmap[newx][newy]);
                }
            }
        }
    }
    /*for(int i = 0; i < 10; i ++){
        for(int j = 0; j < 10; j ++){
            cout << hitmap[i][j];
        }
        cout << endl;
    }*/
    cout << (solve() - 1);
    return 0;
}