#define MAXN 26
#include <iostream>
#include <tuple>
#include <set>
#include <iterator>
#include <queue>
using namespace std;
int encode(int i, int j){
    return (i - 1) * 3 + j - 1;
}
pair<int,int> decode(int val){
    return make_pair(val/3 + 1,val % 3 + 1);
}
int N;
int bx,by;
char mooTypes[16][9] = {
    {
        'M','O','O',
        '.','.','.',
        '.','.','.'
    },
    {
        '.','.','.',
        'M','O','O',
        '.','.','.'
    },
    {
        '.','.','.',
        '.','.','.',
        'M','O','O'
    },
    {
        'M','.','.',
        'O','.','.',
        'O','.','.'
    },
     {
        '.','M','.',
        '.','O','.',
        '.','O','.'
    }
    , {
        '.','.','M',
        '.','.','O',
        '.','.','O'
    }
    , {
        'M','.','.',
        '.','O','.',
        '.','.','O'
    }
    , {
        '.','.','M',
        '.','O','.',
        'O','.','.'
    },
    // Reverse
     {
        'O','O','M',
        '.','.','.',
        '.','.','.'
    },
    {
        '.','.','.',
        'O','O','M',
        '.','.','.'
    },
    {
        '.','.','.',
        '.','.','.',
        'O','O','M'
    },
    {
        'O','.','.',
        'O','.','.',
        'M','.','.'
    },
     {
        '.','O','.',
        '.','O','.',
        '.','M','.'
    }
    , {
        '.','.','O',
        '.','.','O',
        '.','.','M'
    }
    , {
        'O','.','.',
        '.','O','.',
        '.','.','M'
    }
    , {
        '.','.','O',
        '.','O','.',
        'M','.','.'
    }
};
bool checkMoo(array<char,9> state){
    for(int i = 0; i < 16; i ++){
        bool curWorks = true;
        for(int j = 0; j < 9; j ++){
            if(mooTypes[i][j] == '.'){
                continue;
            }else{
                if(mooTypes[i][j] != state[j]){
                    curWorks = false;
                    break;
                }
            }
        }
        if(curWorks){
            return true;
        }
    }
    return false;
}
tuple<char,int,int> grid[MAXN][MAXN];
int dx[] = {0,0,-1,1};
int dy[] = {-1,1,0,0};
set<int> winningCombs;
int charToInt(char c){
    if(c == 'M'){
        return 2;
    }
    if(c == 'O'){
        return 1;
    }
    if(c == '.'){
        return 0;
    }
    return -1;
}
int encodeState(array<char,9> state){
    return charToInt(state[0])
    + charToInt(state[1]) * 3
    + charToInt(state[2]) * 9
    + charToInt(state[3]) * 27
    + charToInt(state[4]) * 81
    + charToInt(state[5]) * 243
    + charToInt(state[6]) * 729
    + charToInt(state[7]) * 2187
    + charToInt(state[8]) * 6561;
}

int main(int argc, const char** argv) {
    cin >> N;
    for(int i = 0; i < N; i ++){
        char line[MAXN];
        cin >> line;
        for(int j = 0; j < N; j ++){
            char type = line[j * 3];
            if(type == 'B'){
                grid[i][j] = make_tuple('B',-1,-1); // Bessie line
                bx = i;
                by = j;
            }else if(type == 'M'){
                grid[i][j] = make_tuple('M',line[j * 3 + 1] - '0',line[j * 3 + 2] - '0');
            }else if(type == 'O'){
                grid[i][j] = make_tuple('O',line[j * 3 + 1] - '0',line[j * 3 + 2] - '0');
            }else if(type == '#'){
                grid[i][j] = make_tuple('#',-1,-1); // Wall
            }else if(type == '.'){
                grid[i][j] = make_tuple('.',-1,-1); // Air
            }
        }
    }
    //         state   position
    queue<pair<array<char,9>,pair<int,int>>> nextUp;
    array<char,9> initState = {'.','.','.','.','.','.','.','.','.'};
    nextUp.push(make_pair(initState, make_pair(bx, by)));
    //     state,     cooords
    set<pair<array<char,9>,pair<int,int>>> seen;

    seen.insert(make_pair(initState, make_pair(bx, by)));
    while(!nextUp.empty()){
        pair<array<char,9>, pair<int,int>> stateAndPos = nextUp.front();
        array<char,9> state = stateAndPos.first;
        int curX = stateAndPos.second.first;
        int curY = stateAndPos.second.second;
        nextUp.pop();
        for(int i = 0; i < 9; i ++){
                /*if(i % 3 == 0){
                    cout << endl;
                }*/
                cout << state[i];
        }
        cout << endl;
        if(checkMoo(state)){
            
            winningCombs.insert(encodeState(state));
            continue;
        }
        for(int i = 0; i < 4; i ++){
            int newX = curX + dx[i];            
            int newY = curY + dy[i];
            if(newX >= N || newY >= N || 0 > newX || 0 > newY){
                continue; // out of bounds
            }
            if(get<0>(grid[newX][newY]) == 'M' || get<0>(grid[newX][newY]) == 'O'){
                // Apply state change
                
                int changePos = encode(get<1>(grid[newX][newY]),get<2>(grid[newX][newY]));
                if(state[changePos] == '.'){
                    state[changePos] = get<0>(grid[newX][newY]);
                }
            }else if(get<0>(grid[newX][newY]) == '#'){
                // Wall
                continue;
            }
            if(seen.find(make_pair(state, make_pair(newX, newY))) == seen.end()){
                seen.insert(make_pair(state, make_pair(newX, newY)));
                nextUp.push(make_pair(state,make_pair(newX,newY)));
            }
        }
    }
    cout << winningCombs.size() << endl;
    /*for(int i = 0; i < 16; i ++){
        
    }*/
    return 0;
}