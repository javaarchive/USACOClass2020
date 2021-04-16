#include <iostream>
#include <tuple>
#define MAXN 100002
#define MAXK 22
using namespace std;
//int ps[MAXN][3];
int dp[MAXK][MAXN][3];
int N,K;
char moves[MAXN];
/*tuple<int,int,int> query(int start, int end){
    return make_tuple(ps[end + 1][0] - ps[start][0], ps[end + 1][1] - ps[start][1], ps[end + 1][2] - ps[start][2]);
}*/
int numify(char c){
    if(c == 'H'){
        return 0;
    }else if(c == 'P'){
        return 1;
    }else if(c == 'S'){
        return 2;
    }
    return 0;
}
int main(int argc, const char** argv) {
    cin >> N >> K;
    K ++;
    /*for(int i = 0; i < N; i ++){
        ps[i][0] = 0;
        ps[i][1] = 0;
        ps[i][2] = 0;
    }*/
    for(int i = 0; i < K; i ++){
        for(int j = 0; j < N; j ++){
            for(int k = 0; k < 3; k ++){
                dp[i][j][k] = 0;
            }
        }
    }
    char c;
    for(int i = 0; i < N; i ++){
        cin >> c;
        moves[i] = c;
    }
    /*for(int i = 0; i < N; i ++){
        cin >> c;
        ps[i + 1][0] = ps[i][0];
        ps[i + 1][0] = ps[i][0];
        ps[i + 1][0] = ps[i][0];
        if(c == 'H'){
            ps[i][0] ++;
        }else if(c == 'P'){
            ps[i][1] ++;
        }else if(c == 'S'){
            ps[i][2] ++;
        }
    }*/
    for(int i = 1; i <= K; i ++){
        for(int j = 1; j <= N; j ++){
            for(int k = 0; k < 3; k ++){
                for(int l = 0; l < 3; l ++){
                    // cout << i << " " << j << " " << k << "->" << l << " " << ((numify(moves[j - 1]) == l) ? 1 : 0) << endl;
                    if(k == l){
                        dp[i][j][l] = max(dp[i][j][l],dp[i][j - 1][k] + ((numify(moves[j - 1]) == l) ? 1 : 0));
                    }else{
                        dp[i][j][l] = max(dp[i][j][l],dp[i - 1][j - 1][k] + ((numify(moves[j - 1]) == l) ? 1 : 0));
                    }
                }
            }
        }
    }
    /*for(int i = 0; i <= K; i ++){
        for(int j = 0; j < 3; j ++){
            for(int k = 0; k <= N; k ++){
                cout << dp[i][k][j] << " ";
            }
            cout << endl;
        }
        cout << " ------ " << endl << endl;
    }*/
    int ans = 0;
    for(int i = 0; i <= K; i ++){
        ans = max(max(dp[i][N][0],ans),max(dp[i][N][1],dp[i][N][2]));
    }
    cout << ans;
    return 0;
}