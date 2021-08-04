#include <iostream>
#define MAXM 100002

using namespace std;

void solve(){
    int M;
    cin >> M;
    int grid[2][MAXM];
    int ps1[MAXM];
    int ps2[MAXM];
    for (int i = 0; i < M; i++){
        cin >> grid[0][i];
    }
    for (int i = 0; i < M; i++){
        cin >> grid[1][i];
    }
    if(M == 1){
        // padding hack
        grid[0][1] = 0;
        grid[1][1] = 0;
        M ++;
    }
    ps1[0] = 0;
    for(int i = 0; i < M; i ++){
        ps1[i + 1] = ps1[i] + grid[0][i];
    }
    ps2[0] = 0;
    for(int i = 0; i < M; i ++){
        ps2[i + 1] = ps2[i] + grid[1][i];
    }

    // int smallestPs = INT32_MAX;

    /*for(int i = 1; i < M; i ++){
        cout << ps1[i] << " + " << ps2[M] << " - " << ps2[i - 1] << endl;
        smallestPs = min(smallestPs,ps1[i] + ps2[M] - ps2[i - 1]);
    }*/
    // cout << "Smallest Prefix Sum Achievable " << smallestPs << endl;
    int best = INT32_MAX;
    for(int i = 1; i <= M; i ++){
        int sum = ps1[i] + ps2[M] - ps2[i - 1];
        // if(sum == smallestPs){
            // cout << ps2[i - 1] << " OR " << ps1[M]  << " - " << ps1[i] << endl;
            best = min(best, max(ps1[M] - ps1[i],ps2[i - 1]));
        // }
    }
    cout << best << endl;
}

int main(int argc, char const *argv[])
{
    int T;
    cin >> T;
    for(int i = 0; i < T; i ++){
        solve();
    }
    return 0;
}
