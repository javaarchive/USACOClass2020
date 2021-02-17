#include <iostream>

using namespace std;
int N;
int grid[21][21];
int main(){
    cin >> N;
    for(int i = 0; i < N; i ++){
        char line[21];
        cin >> line;
        for(int j = 0; j < N; j ++){
            grid[i][j] = line[j];
        }
    }
    for(int x1 = 0; x1 < N; x1 ++){
        for(int y1 = 0; y1 < N; y1 ++){
            for(int x2 = x1 + 1; x2 < N; x2 ++){
                for(int y2 = x1 + 1; y2 < N; y2 ++){
                    // BFS at unfilled regions
                    
                }
            }
        }
    }
}