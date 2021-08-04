#include <iostream>
#include <queue> // priority queue
#define MAXNM 1002
#define fullSearchState pair<int,SearchState>
using namespace std;

int rows, cols;
int tiles[MAXNM][MAXNM];
int progress[2][MAXNM][MAXNM]; // 1: smells like oranges

struct SearchState{
    int rows;
    int cols;
    bool smellsLikeOranges = false;
    bool operator<(const SearchState& rhs) const{
        if(smellsLikeOranges != rhs.smellsLikeOranges){
            return smellsLikeOranges;
        }
        if(rows != rhs.rows){
            return rows < rhs.rows;
        }
        return cols < rhs.cols;
    }
    
};

void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}

int main(int argc, char const *argv[])
{
    setIO("dream");
    cin >> rows >> cols;
    for(int i = 0; i < rows; i ++){
        for(int j = 0; j < cols; j ++){
            cin >> tiles[i][j];
            progress[0][i][j] = -1;
            progress[1][i][j] = -1;
        }
    }

    progress[0][0][0] = 0;
    priority_queue<fullSearchState, vector<fullSearchState>, greater<fullSearchState>> pq;
    SearchState initState;
    initState.rows = 0;
    initState.cols = 0;
    initState.smellsLikeOranges = false;
    if(tiles[0][0] == 3 || tiles[0][0] == 0){
        cout << "-1" << endl;
        return 0;
    }
    pq.push(make_pair(0,initState));
    int dx[] = {0, 1, 0, -1};
    int dy[] = {1, 0, -1, 0};
    int minVal = INT32_MAX;
    while(!pq.empty()){
        auto statePair = pq.top();
        pq.pop();
        int depth = statePair.first;
        SearchState state = statePair.second;
        if(state.rows == rows - 1 && state.cols == cols - 1){
            // cout << "NEWMIN " << depth << " oranges: " << state.smellsLikeOranges << endl;
            minVal = min(minVal, depth);        
            // cout << depth; // wait this is BFS
            // return 0;   
        }
        // cout << "Visiting " << state.rows << " " << state.cols << endl;
        for(int i = 0; i < 4; i ++){
            int newx = state.rows + dx[i];
            int newy = state.cols + dy[i];
            if(newx >= 0 && newx < rows && newy >= 0 && newy < cols){
                int depthIncrement = 1;
                bool stop = false;
                bool blocked = false; // used in case 4
                bool newOrangeState = state.smellsLikeOranges;
                switch(tiles[newx][newy]){
                    case 0:
                        stop = true;
                        break;
                    case 1:
                        break;
                    case 2:
                        newOrangeState = true;
                        break;
                    case 3:
                        if(!state.smellsLikeOranges){
                            stop = true;
                        }
                        break;
                    case 4:
                        depthIncrement = 0;
                        newOrangeState = false;
                        while(newx >= 0 && newx < rows && newy >= 0 && newy < cols){
                            if(tiles[newx][newy] == 0){
                                blocked = true;
                                break;
                            }
                            if(tiles[newx][newy] == 1){
                                depthIncrement ++;
                                break;
                            }
                            if(tiles[newx][newy] == 2){
                                newOrangeState = true;
                                depthIncrement ++;
                                break;
                            }
                            if(tiles[newx][newy] == 3){
                                blocked = (!newOrangeState); // obviously true
                                if(!blocked){
                                    depthIncrement ++;
                                }
                                break;
                            }
                            if(tiles[newx][newy] == 4){
                                // keep sliding
                                depthIncrement ++;
                                newOrangeState = false;
                            }
                            newx += dx[i];
                            newy += dy[i];
                        }
                        if(blocked || !(newx >= 0 && newx < rows && newy >= 0 && newy < cols)){ // blocked or edge
                            newx -= dx[i];
                            newy -= dy[i];
                        }
                        // cout << "Slider end " << newx << "," << newy << " depth " << (depth + depthIncrement) << " src: " << state.rows << "," << state.cols << endl;
                        // depthIncrement --;
                        break;
                    default:
                        stop = true;
                        break;
                }
                if(stop){
                    // cout << "stop dxdy: " << i << endl;
                    continue;
                }
                if(progress[newOrangeState][newx][newy] == -1 || (progress[newOrangeState][newx][newy] > depth + depthIncrement)){
                    progress[newOrangeState][newx][newy] = depth + depthIncrement; // update
                    SearchState newSearchState;
                    newSearchState.rows = newx;
                    newSearchState.cols = newy;
                    newSearchState.smellsLikeOranges = newOrangeState;
                    pq.push(make_pair(depth + depthIncrement, newSearchState));
                }else{
                    // cout << "dxdy " << i << " did not "
                }
            }
        }
    }
    
    /*cout << "Progress Array 0: " << endl;
    for(int i = 0; i < rows; i ++){
        for(int j = 0; j < rows; j ++){
            cout << progress[0][i][j] << " ";
        }
        cout << endl;
    }
    cout << endl;
    cout << "Progress Array 1: " << endl;
    for(int i = 0; i < rows; i ++){
        for(int j = 0; j < rows; j ++){
            cout << progress[1][i][j] << " ";
        }
        cout << endl;
    }
    cout << endl;*/
    if(minVal == INT32_MAX){
            cout << "-1" << endl;
            return 0;
    }
    cout << minVal << endl;
    
    return 0;
}
