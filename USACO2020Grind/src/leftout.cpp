#include <iostream>
using namespace std;
int N;
bool grid[1001][1001];
void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}
int main(int argc, const char** argv) {
    if(argc != 2){
        setIO("leftout"); // automatic io if not running local
    }
    cin >> N;
    for(int i = 0; i < N; i ++){
        char s[1001];
        cin >> s;
        for(int j = 0; j < N; j ++){
            grid[i][j] = (s[j] == 'R');
        }
    }
    // First row
    for(int i = 0; i < N; i ++){
        if(grid[0][i]){
            for(int j = 0; j < N; j ++){
                grid[j][i] = !grid[j][i];
            }
        }
    }
    for(int i = 0; i < N; i ++){
        if(grid[i][0]){
            for(int j = 0; j < N; j ++){
                grid[i][j] = !grid[i][j];
            }
        }
    }
    int truCount = 0;
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < N; j ++){
            if(grid[i][j]){
                //cout << "Got 1 at " << i << " " << j << endl;
                truCount++;
            }
        }
    }
    //cout << "TRUCOUNT " << truCount << endl;
    /*for(int i = 0; i < N; i ++){
        for(int j = 0; j < N; j ++){
            cout << grid[i][j] << " ";
        }
        cout << endl;
    }*/
    if(truCount == ((N - 1) * (N - 1))){
        cout << "1 1" << endl;
        return 0;
    }else if(truCount == 1){
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                if(grid[i][j]){
                    cout << (i + 1) << " " << (j + 1) << endl;
                }
            }
        }
    }else if(truCount == 0){
        cout << -1;
    }else{
        //cout << "Running through rows" << endl;
        for(int i = 1; i < N; i ++){
            int allt = true;
            for(int j = 1; j < N; j ++){
                if(!grid[i][j]){
                    //cout << "BREAK ON " << i << " " << j << endl;
                    allt = false;
                    break;
                }
            }
            if(allt){
                cout << (i + 1) << " " << 1;
                return 0;
            }
        }
        //cout << "Running through cols" << endl;
        for(int i = 1; i < N; i ++){
            int allt = true;
            for(int j = 1; j < N; j ++){
                if(!grid[j][i]){
                    //cout << "BREAK ON " << j << " " << i << endl;
                    allt = false;
                    break;
                }
            }
            if(allt){
                cout << 1 << " " << (i + 1);
                return 0;
            }
        }
        cout << -1; // confused
        //cout << "reloop fail?";
    }
    //cout << "END?";

    return 0;
}