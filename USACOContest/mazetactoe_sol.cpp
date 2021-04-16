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
bool visited[19683][25][25];
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

int main(int argc, const char **argv)
{
    cin >> N;
    // Init
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < N; j ++){
            for(int k = 0; k < 19683; k ++){
                visited[i][j][k] = false;
            }
        }
    }
    for (int i = 0; i < N; i++)
    {
        char line[76];
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
            }
            else if (type == 'O')
            {
                grid[i][j] = make_tuple('O', line[j * 3 + 1] - '0', line[j * 3 + 2] - '0');
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
    //         state            position
    queue<pair<array<char, 9>, pair<int, int>>> nextUp;
    array<char, 9> initState = {'.', '.', '.', '.', '.', '.', '.', '.', '.'};
    nextUp.push(make_pair(initState,make_pair(bx,by)));
    visited[encodeState(initState)][bx][by] = true;
    int dx[4] = {0,0,-1,1};
    int dy[4] = {-1,1,0,0};
    while(!nextUp.empty()){
        pair<array<char, 9>, pair<int, int>> stateAndPos = nextUp.front();
        nextUp.pop();
        array<char,9> state = stateAndPos.first;
        pair<int,int> pos = stateAndPos.second;
        // out << pos.first << " and " << pos.second << endl;
        for(int i = 0; i < 4; i ++){
            int newx = pos.first + dx[i];
            int newy = pos.second + dy[i];
            if(0 <= newx && newx < N && 0 <= newy && newy < N){
                if(get<0>(grid[newx][newy]) != '#'){
                    array<char,9> newState = state;
                    if(get<0>(grid[newx][newy]) == 'M' || get<0>(grid[newx][newy]) == 'O'){
                        int stateChangeX = get<1>(grid[newx][newy]);
                        int stateChangeY = get<2>(grid[newx][newy]);
                        int stateChangeIndex = encode(stateChangeX, stateChangeY);
                        // cout << stateChangeIndex << endl;
                        // for(int i = 0; i < 9; i ++){cout << state[i] << " ";}
                        // cout << endl;
                        if(state[stateChangeIndex] == '.'){
                            newState[stateChangeIndex] = get<0>(grid[newx][newy]);
                            // cout << "Placed " << get<0>(grid[newx][newy]) << endl;
                        }
                        if(checkMoo(newState)){
                            // cout << "Moo!" << endl;
                            winningCombs.insert(encodeState(newState));
                            continue;
                        }
                    }
                    if(!visited[encodeState(newState)][newx][newy]){
                        visited[encodeState(newState)][newx][newy] = true;
                        nextUp.push(make_pair(newState, make_pair(newx,newy)));
                    }
                }
            }
        }
    }
    //char c;
    //cin >> c;
    cout << winningCombs.size();
    return 0;
}