#define MAXN 27
#include <iostream>
#include <tuple>
#include <set>
#include <iterator>
#include <queue>
#include <vector>
#include <map>
#include <unordered_set>
using namespace std;
int encode(int i, int j)
{
    return (i - 1) * 3 + j - 1;
}
pair<int, int> decode(int val)
{
    return make_pair(val / 3 + 1, val % 3 + 1);
}
int N;
int bx, by;
char mooTypes[16][9] = {
    {'M', 'O', 'O',
     '.', '.', '.',
     '.', '.', '.'},
    {'.', '.', '.',
     'M', 'O', 'O',
     '.', '.', '.'},
    {'.', '.', '.',
     '.', '.', '.',
     'M', 'O', 'O'},
    {'M', '.', '.',
     'O', '.', '.',
     'O', '.', '.'},
    {'.', 'M', '.',
     '.', 'O', '.',
     '.', 'O', '.'},
    {'.', '.', 'M',
     '.', '.', 'O',
     '.', '.', 'O'},
    {'M', '.', '.',
     '.', 'O', '.',
     '.', '.', 'O'},
    {'.', '.', 'M',
     '.', 'O', '.',
     'O', '.', '.'},
    // Reverse
    {
        'O', 'O', 'M',
        '.', '.', '.',
        '.', '.', '.'},
    {'.', '.', '.',
     'O', 'O', 'M',
     '.', '.', '.'},
    {'.', '.', '.',
     '.', '.', '.',
     'O', 'O', 'M'},
    {'O', '.', '.',
     'O', '.', '.',
     'M', '.', '.'},
    {'.', 'O', '.',
     '.', 'O', '.',
     '.', 'M', '.'},
    {'.', '.', 'O',
     '.', '.', 'O',
     '.', '.', 'M'},
    {'O', '.', '.',
     '.', 'O', '.',
     '.', '.', 'M'},
    {'.', '.', 'O',
     '.', 'O', '.',
     'M', '.', '.'}};
bool checkMoo(array<char, 9> state)
{
    for (int i = 0; i < 16; i++)
    {
        bool curWorks = true;
        for (int j = 0; j < 9; j++)
        {
            if (mooTypes[i][j] == '.')
            {
                continue;
            }
            else
            {
                if (mooTypes[i][j] != state[j])
                {
                    curWorks = false;
                    break;
                }
            }
        }
        if (curWorks)
        {
            return true;
        }
    }
    return false;
}
tuple<char, int, int> grid[MAXN][MAXN];
int dx[] = {0, 0, -1, 1};
int dy[] = {-1, 1, 0, 0};
set<int> winningCombs;
int charToInt(char c)
{
    if (c == 'M')
    {
        return 2;
    }
    if (c == 'O')
    {
        return 1;
    }
    if (c == '.')
    {
        return 0;
    }
    return -1;
}
int encodeState(array<char, 9> state)
{
    return charToInt(state[0]) + charToInt(state[1]) * 3 + charToInt(state[2]) * 9 + charToInt(state[3]) * 27 + charToInt(state[4]) * 81 + charToInt(state[5]) * 243 + charToInt(state[6]) * 729 + charToInt(state[7]) * 2187 + charToInt(state[8]) * 6561;
}
int encodePos(int x, int y)
{
    return MAXN * x + y;
}
pair<int, int> decodePos(int encoded)
{
    return make_pair(encoded / MAXN, encoded % MAXN);
}

map<int, vector<int>> graph;

int main(int argc, const char **argv)
{
    cin >> N;
    //          data                position
    vector<pair<tuple<char, int, int>, int>> actions;
    for (int i = 0; i < N; i++)
    {
        char line[MAXN];
        cin >> line;
        for (int j = 0; j < N; j++)
        {
            char type = line[j * 3];
            if (type == 'B')
            {
                grid[i][j] = make_tuple('B', -1, -1); // Bessie line
                bx = i;
                by = j;
            }
            else if (type == 'M')
            {
                grid[i][j] = make_tuple('M', line[j * 3 + 1] - '0', line[j * 3 + 2] - '0');
                actions.push_back(make_pair(grid[i][j], encodePos(i, j)));
            }
            else if (type == 'O')
            {
                grid[i][j] = make_tuple('O', line[j * 3 + 1] - '0', line[j * 3 + 2] - '0');
                actions.push_back(make_pair(grid[i][j], encodePos(i, j)));
            }
            else if (type == '#')
            {
                grid[i][j] = make_tuple('#', -1, -1); // Wall
            }
            else if (type == '.')
            {
                grid[i][j] = make_tuple('.', -1, -1); // Air
            }
        }
    }
    //         state   position
    queue<pair<array<char, 9>, pair<int, int>>> nextUp;
    array<char, 9> initState = {'.', '.', '.', '.', '.', '.', '.', '.', '.'};
    // Construct graph
    for (int i = 0; i < actions.size(); i++)
    {
        pair<int, int> pos = decodePos(actions[i].second);
        bool visited[MAXN][MAXN];
        for (int j = 0; j < N; j++)
        {
            for (int k = 0; k < N; k++)
            {
                visited[j][k] = false;
            }
        }
        queue<pair<int, int>> next;
        next.push(pos);
        visited[pos.first][pos.second] = true;
        int dx[] = {0, 0, -1, 1};
        int dy[] = {-1, 1, 0, 0};
        while (!next.empty())
        {
            pair<int, int> cur = next.front();
            next.pop();
            for (int j = 0; j < 4; j++)
            {
                int newx = cur.first + dx[j];
                int newy = cur.second + dy[j];
                if (newx < 0 || newy < 0 || newx >= N || newy >= N)
                {
                    continue; // out of bounds
                }
                if (visited[newx][newy])
                {
                    continue;
                }
                if (get<0>(grid[newx][newy]) == '#')
                {
                    continue;
                }
                else if (get<0>(grid[newx][newy]) == 'M' || get<0>(grid[newx][newy]) == 'O')
                {
                    // Create connections
                    graph[actions[i].second].push_back(encodePos(newx, newy)); // the other order will be found in another loop iteration
                    continue;
                }
                visited[newx][newy] = true;
                next.push(make_pair(newx, newy));
            }
        }
    }
    // Getting started code
    bool visited[MAXN][MAXN];
    for (int j = 0; j < N; j++)
    {
        for (int k = 0; k < N; k++)
        {
            visited[j][k] = false;
        }
    }
    visited[bx][by] = true;
    queue<pair<int, int>> next;
    vector<pair<int,int>> adj;
    next.push(make_pair(bx, by));
    while (!next.empty())
    {
        pair<int, int> cur = next.front();
        next.pop();
        for (int j = 0; j < 4; j++)
        {
            int newx = cur.first + dx[j];
            int newy = cur.second + dy[j];
            if (newx < 0 || newy < 0 || newx >= N || newy >= N)
            {
                continue; // out of bounds
            }
            if (visited[newx][newy])
            {
                continue;
            }
            if (get<0>(grid[newx][newy]) == '#')
            {
                continue;
            }
            else if (get<0>(grid[newx][newy]) == 'M' || get<0>(grid[newx][newy]) == 'O')
            {
                adj.push_back(make_pair(newx,newy));
                continue;
            }
            visited[newx][newy] = true;
            next.push(make_pair(newx, newy));
        }
    }
    //             state
    queue<pair<int,array<char,9>>> nextNodes;
    set<pair<int,array<char,9>>> seen;
    for(int i = 0; i < adj.size(); i ++){
        pair<int,int> pos = adj[i];
        array<char, 9> newState = initState;
        int encodedPos = encodePos(pos.first, pos.second);
        tuple<char,int,int> posChange = grid[pos.first][pos.second];
        int encodedChange = encode(get<1>(posChange), get<2>(posChange));
        newState[encodedChange] = get<0>(posChange);
        seen.insert(make_pair(encodedPos, newState));
        nextNodes.push(make_pair(encodedPos, newState));
    }
    while(!nextNodes.empty()){
        pair<int,array<char,9>> nodeAndState = nextNodes.front();
        int node = nodeAndState.first;
        // cout << node << endl;
        array<char,9> curState = nodeAndState.second;
        nextNodes.pop();
        if(checkMoo(curState)){
            winningCombs.insert(encodeState(curState));
            continue;
        }
        for(auto iter = graph[node].begin(); iter != graph[node].end(); iter ++){
            // Apply
            array<char,9> newState = curState;
            pair<int,int> decodedPos = decodePos(*iter);
            tuple<char,int,int> change = grid[decodedPos.first][decodedPos.second];
            int encodedChange = encode(get<1>(change), get<2>(change));
            if(curState[encodedChange] == '.'){
                newState[encodedChange] = get<0>(change);
            }
            if(seen.find(make_pair(*iter, newState)) == seen.end()){
                seen.insert(make_pair(*iter, newState));
                nextNodes.push(make_pair(*iter, newState));
            }
        }
    }

    cout << winningCombs.size() << endl;
    /*for(int i = 0; i < 16; i ++){
        
    }*/
    return 0;
}