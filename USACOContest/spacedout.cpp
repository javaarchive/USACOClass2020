#include <iostream>
#define MAXN 1002
using namespace std;
int N;
int bueaty[MAXN][MAXN];
int horizontal[MAXN][2];
int vertical[MAXN][2];

int main(int argc, const char** argv) {
    cin >> N;
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < N; j ++){
            cin >> bueaty[i][j];
        }
    }
    for(int i = 0; i < N; i ++){
        vertical[i][0] = 0;
        vertical[i][1] = 0;
        horizontal[i][0] = 0;
        horizontal[i][1] = 0;
    }
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < N; j ++){
            if(j % 2 == 0){
                horizontal[i][0] += bueaty[i][j];
                vertical[i][0] += bueaty[j][i];
            }else{
                horizontal[i][1] += bueaty[i][j];
                vertical[i][1] += bueaty[j][i];
            }
        }
    }
    int h = 0,v = 0;
    for(int i = 0; i < N; i ++){
        h += max(horizontal[i][0],horizontal[i][1]);
        v += max(vertical[i][0],vertical[i][1]);
    }
    cout << max(h,v) << endl;
    return 0;
}