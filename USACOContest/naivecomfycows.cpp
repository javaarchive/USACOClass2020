#include <iostream>
#include <queue>
#include <set>
#include <vector>
#define MAXN 100002
#define MAXCOORD 2201
using namespace std;

int N;
pair<int,int> points[MAXN];
int cgrid[MAXCOORD][MAXCOORD];
int extras = 0;

set<pair<int,int>> present;
set<pair<int,int>> futureplace;
queue<pair<int,int>> toResolve;
bool original = true;
bool cowExists(int x,int y){
    pair<int,int> p = make_pair(x,y);
    return (present.find(p) != present.end() || futureplace.find(p) != futureplace.end());
}
void upVal(int x,int y){
    // when one is placed, another cow may get comfortable
    cgrid[x][y] ++;
    if(cgrid[x][y] == 3 && cowExists(x,y)){
        toResolve.push(make_pair(x,y));
    }
}

void place(int x, int y){
    if(original){
        present.insert(make_pair(x,y)); // part of input
    }else{
        extras ++;
        futureplace.insert(make_pair(x,y));
    }
    // mark adjacent
    upVal(x + 1,y); 
    upVal(x - 1,y); 
    upVal(x,y + 1);
    upVal(x,y - 1);
}
int dx[] = {0,0,-1,1};
int dy[] = {-1,1,0,0};

int main(int argc, const char** argv) {
    for(int i = 0; i < MAXCOORD; i ++){
        for(int j = 0; j < MAXCOORD; j ++){
            cgrid[i][j] = 0;
        }
    }
    cin >> N;
    
    for(int i = 0; i < N; i ++){
        cin >> points[i].first >> points[i].second; 
        points[i].first += 1001; // OFFSET
        points[i].second += 1001; // OFFSET
    }
    
    for(int i = 0; i < N; i ++){
        original = true; // turn on placing to original queue
        if(futureplace.find(points[i]) != futureplace.end()){
            // transfer from to be placed to actually placed
            futureplace.erase(points[i]);
            present.insert(points[i]);
            extras --;
        }else{
            place(points[i].first, points[i].second);
        }
        original = false;
        while(!toResolve.empty()){
            pair<int,int> p = toResolve.front(); // get center of comofrtable cow
            toResolve.pop();

            if(cgrid[p.first][p.second] != 3){
                continue; // no longer a problem
            }
            if(!cowExists(p.first,p.second)){
                continue;
            }
            for(int i = 0; i < 4; i ++){
                // find the only unfilled point around the point
                int adjx = p.first  + dx[i];
                int adjy = p.second + dy[i];
                if(!cowExists(adjx,adjy)){
                    place(adjx,adjy);
                    break;
                }
            }
        }
        cout << extras << endl;
    }

    
    return 0;
}