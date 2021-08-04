#include <iostream>
#define MAXM 100001

using namespace std;


int T;

void solve(){
    int m;
    cin >> m;
    int dpAlice[2][MAXM];
    int dpBob[2][MAXM];
    int grid[2][MAXM];
    int tracker[2][MAXM]; // 1 right, 2 down, 0 nothing
    for(int i = 0; i < m; i ++){
        cin >> grid[0][i]; 
    }
    for(int i = 0; i < m; i ++){
        cin >> grid[1][i]; 
    }
    for(int i = 0; i < m; i ++){
        dpAlice[0][i] = INT32_MAX;
        dpAlice[1][i] = INT32_MAX;
        dpBob[0][i] = 0;
        dpBob[1][i] = 0;
        tracker[0][i] = 0;
        tracker[1][i] = 0;
    }
    dpAlice[0][0] = grid[0][0];
    dpBob[0][0] = 0;
    // alice the minimizer
    for(int i = 0; i < 2; i ++){
        for(int j = 0; j < m; j ++){
            if(j > 0){
                if(dpAlice[i][j - 1] + grid[i][j] < dpAlice[i][j]){
                    tracker[i][j - 1] = 1;
                }
                dpAlice[i][j] = min(dpAlice[i][j],dpAlice[i][j - 1] + grid[i][j]);
            }
            if(i == 1){
                if(dpAlice[i - 1][j] + grid[i][j] < dpAlice[i][j]){
                    // Update Tracker
                    tracker[i - 1][j] = 2;
                }
                dpAlice[i][j] = min(dpAlice[i][j],dpAlice[i - 1][j] + grid[i][j]);
            }
            
        }
    }
    int curX = 0, curY = 0;
    while(curX != 1 || curY != m - 1){
        grid[curX][curY] = 0;
        // cout << "Blanking " << curX << " and " << curY << endl;
        if(tracker[curX][curY] == 1){
            curY ++;
        }else if(tracker[curX][curY] == 2){
            curX ++;
        }
    }
    grid[1][m - 1] = 0;
    cout << "NEW GRID" << endl;
    for(int i = 0; i < 2; i ++){
        for(int j = 0 ; j < m; j ++){
            cout << grid[i][j] << " ";
        }
        cout << endl;
    }
    int bobScore = 0;
    

    // bob the maximizer
    for(int i = 0; i < 2; i ++){
        for(int j = 0; j < m; j ++){
            // cout << "BLOOP " << i << " " << j << endl;
            if(i == 1){
                // cout << "Max from down " << dpBob[i][j] << " cmp " << dpBob[i - 1][j] + grid[i][j] << endl;
                dpBob[i][j] = max(dpBob[i][j],dpBob[i - 1][j] + grid[i][j]);
            }
            if(j > 0){
                // cout << "Max from right " << dpBob[i][j] << " cmp " << dpBob[i][j - 1] + grid[i][j] << endl;
                dpBob[i][j] = max(dpBob[i][j],dpBob[i][j - 1] + grid[i][j]);
            }
        }
    }
    cout << "Alice: " << endl;
     for(int i = 0; i < 2; i ++){
        for(int j = 0 ; j < m; j ++){
            cout << dpAlice[i][j] << " ";
        }
        cout << endl;
    }
    cout << "Tracer: " << endl;
    for(int i = 0; i < 2; i ++){
        for(int j = 0 ; j < m; j ++){
            cout << tracker[i][j] << " ";
        }
        cout << endl;
    }
    cout << "Bob:" << endl;
    for(int i = 0; i < 2; i ++){
        for(int j = 0 ; j < m; j ++){
            cout << dpBob[i][j] << " ";
        }
        cout << endl;
    }
    cout << "ANS: " << dpBob[1][m - 1] << endl;
}

int main(int argc, char const *argv[])
{
    cin >> T;
    for(int i=0; i< T; i ++){
        solve();
    }
    return 0;
}
