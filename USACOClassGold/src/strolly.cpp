#include <iostream>
#include <string>
#include <vector>
#include <queue>
#include <tuple>
#define MAXN 121
//                 1        2                     3    4    5       4     5       43210
#define pqelem pair<int,pair<pair<int,int>,vector<pair<pair<int,int>,pair<int,int>>>>>

using namespace std;
int N, B;
int grid[MAXN][MAXN];
void copyAllIntoVector(vector<pair<pair<int,int>,pair<int,int>>>& first, vector<pair<pair<int,int>,pair<int,int>>>& second){
    for(int i = 0; i < first.size(); i ++){
        second.push_back(first[i]);
    }
    
}
bool intersectsWith(vector<pair<pair<int,int>,pair<int,int>>>& lines, int x, int y){
    for(auto iter = lines.begin(); iter != lines.end(); iter ++){
        if((iter->first).first <= x && x <= (iter->second).first){
            if((iter->first).second <= y && y <= (iter->second).second){
                return false;
            }
        }
    }
    return true;
}
pair<int,int> dxdy[4] = {
    make_pair(0,1),
    make_pair(1,0),
    make_pair(0,-1),
    make_pair(-1,0)
};
pair<vector<pair<int,pair<int,int>>>,vector<pair<int,pair<int,int>>>> explore(pair<int,int> curPos,vector<pair<pair<int,int>,pair<int,int>>> lines){
    vector<pair<int,pair<int,int>>> ans;
    vector<pair<int,pair<int,int>>> intersected;
    for(int i = 0; i < 4; i ++){
        int incx = dxdy[i].first;
        int incy = dxdy[i].second;
        if((curPos.first  + incx) >= N || ( curPos.second + incy) >= N){

        }
        for(int j = 1; j < N; i ++){
            int newx = curPos.first  + incx * j;
            int newy = curPos.second + incy * j;
            if(intersectsWith(lines, newx,newy)){
                intersected.push_back(make_pair(j,make_pair(newx,newy)));
                break;
            }
            bool isEnd = (newx >= (N-1) || newy >= (N-1));
            bool endMove = isEnd || (grid[newx + incx][newy + incy] == -1);
            if(endMove){
                ans.push_back(make_pair(j,make_pair(newx,newy)));
            }
        }
    }
    return make_pair(ans,intersected);
}
int solve(){
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < N; j ++){
            grid[i][j] = 0;
        }
    }
    int best = 0;
    priority_queue<pqelem,vector<pqelem>,greater<pqelem>> pq;
    vector<pair<pair<int,int>,pair<int,int>>> initial;
    pq.push(make_pair(1, make_pair(make_pair(0,0),initial))); // count A1 as visited square
    while(!pq.empty()){
        pqelem opt = pq.top();
        cout << "Dist: " << opt.first << endl;
        pq.pop();
        int curDist = opt.first;
        pair<pair<int,int>,vector<pair<pair<int,int>,pair<int,int>>>> coordAndLines;
        pair<int,int> coord = coordAndLines.first;
        vector<pair<pair<int,int>,pair<int,int>>> lines = coordAndLines.second;
        pair<vector<pair<int,pair<int,int>>>,vector<pair<int,pair<int,int>>>> result = explore(coord,lines);
        vector<pair<int,pair<int,int>>> toExpand = result.first;
        vector<pair<int,pair<int,int>>> finalized = result.second;
        for(int i = 0; i < finalized.size(); i ++){
            best = max(best, curDist + finalized[0].first);
        }
        for(int i = 0; i < toExpand.size(); i ++){
            int newDist = toExpand[i].first + curDist;
            vector<pair<pair<int,int>,pair<int,int>>> newLines;
            copyAllIntoVector(lines, newLines);
            newLines.push_back(make_pair(coord, toExpand[i].second));
            pq.push(make_pair(newDist, make_pair(toExpand[i].second,newLines)));
        }
    }
    return best;
}
int main(int argc, const char** argv) {
    cin >> N >> B;
    
    for(int i = 0; i < B; i ++){
        string temp;
        cin >> temp;
        //cout << temp << endl;
        int x,y;
        x = ((int) temp[0]) - 65;
        y = stoi(temp.substr(1));
        //cout << x << "," << y << endl;
        grid[x][y] = -1;
        //cout << temp[0] << (int) temp[0] <<endl;
        
    }
    cout << solve();
    return 0;
}