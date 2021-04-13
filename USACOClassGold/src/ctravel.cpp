#include <iostream>
#define MAXNM 101
#define MAXT 17

using namespace std;

int N, M, T;
int R1, C1, R2, C2;
int dp[MAXT][MAXNM][MAXNM];
bool grid[MAXNM][MAXNM];
void inc(int t, int x, int y, int inc = 1){
    if(dp[t][x][y] == -1){
        dp[t][x][y] = inc;
    }else{
        dp[t][x][y] += inc;
    }
}
int main(int argc, const char** argv) {
    cin >> N >> M >> T;
    // Init
    for(int i = 0; i <= T; i ++){
        for(int j = 0 ; j < N; j ++){
            for(int k = 0; k < M; k ++){
                dp[i][j][k] = -1;
            }
        }
    }
    // Read
    for(int i = 0; i < N; i ++){
        char line[MAXNM];
        cin >> line;
        for(int j = 0; j < M; j ++){
            if(line[j] == '*'){
                // cout << "WALL" << endl;
                grid[i][j] = true;
            }else{
                grid[i][j] = false;
            }
        }
    }
    cin >> R1 >> C1 >> R2 >> C2;
    R1 --; C1 --; R2 --; C2 --;
    dp[0][R1][C1] = 1;
    int dx[4] = {0,0,1,-1};
    int dy[4] = {-1,1,0,0};
    for(int t = 0; t < T; t ++){
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < M; j ++){
                if(dp[t][i][j] >= 1 && !grid[i][j]){
                    for(int k = 0; k < 4; k ++){
                        int newx = i + dx[k];
                        int newy = j + dy[k];
                        if(0 <= newx && newx < N && 0 <= newy && newy < M && grid[newx][newy] != true){
                            inc(t + 1,newx,newy, dp[t][i][j]);
                        }
                    }
                }
            }
        }
    }
    /*for(int t = 0; t <= T; t ++){
        cout << "===== T: " << t << " =====" << endl;
        for(int i =0 ; i < N; i ++){
            for(int j = 0; j < M; j ++){
                cout << dp[t][i][j] << " ";
            }
            cout << endl;
        }
    }*/
    if(dp[T][R2][C2] == -1){
        cout << 0 << endl;
        return 0;
    }
    cout << dp[T][R2][C2] << endl;
    return 0;
}