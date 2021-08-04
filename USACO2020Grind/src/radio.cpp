#include <iostream>
#include <map>
#define MAXNM 1002

using namespace std;

long long dp[MAXNM][MAXNM];
int N,M;

pair<int,int> initialF;
pair<int,int> initialB;
string Fstring;
string Bstring;
pair<int,int> Fpoints[MAXNM];
pair<int,int> Bpoints[MAXNM];

map<char, pair<int,int>> directionInterpeter;

pair<int,int> addUp(pair<int,int> a, pair<int,int> b){
    return make_pair(a.first+b.first,a.second+b.second);
}

long long calcSquaredDistance(pair<int,int> a, pair<int,int> b){
    return ((long long)(a.first-b.first))*((long long)(a.first-b.first))+((long long) (a.second-b.second))*((long long)(a.second-b.second));
}

void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}

int main(int argc, char const *argv[])
{
    setIO("radio");
    cin >> N >> M;
    for(int i = 0; i <= N; i ++){
        for(int j = 0; j <= M; j ++){
            dp[i][j] = INT32_MAX;
        }
    }
    cin >> initialF.first >> initialF.second;
    cin >> initialB.first >> initialB.second;
    cin >> Fstring;
    cin >> Bstring;

    directionInterpeter['N'] = make_pair(0,1);
    directionInterpeter['E'] = make_pair(1,0);
    directionInterpeter['S'] = make_pair(0,-1);
    directionInterpeter['W'] = make_pair(-1,0);


    Fpoints[0] = initialF;
    //cout << "Building F from initial position of " << initialF.first << " and " << initialF.second << endl;
    for(int i = 0; i < Fstring.size(); i ++){
        char dir = Fstring[i];
        Fpoints[i + 1] = addUp(Fpoints[i], directionInterpeter[dir]);
        //cout << " -> " << Fpoints[i + 1].first << " " << Fpoints[i + 1].second << endl;
    }
    //cout << "Building B from initial position of " << initialB.first << " and " << initialB.second << endl;
    Bpoints[0] = initialB;
    for(int i = 0; i < Bstring.size(); i ++){
        char dir = Bstring[i];
        Bpoints[i + 1] = addUp(Bpoints[i], directionInterpeter[dir]);
        //cout << " -> " << Bpoints[i + 1].first << " " << Bpoints[i + 1].second << endl;        
    }

    dp[0][0] = 0;

    for(int i = 1; i <= N + M + 1; i ++){
        for(int j = 0; j <= i; j ++){
            // validate access
            int k = i - j;
            
            if(k < 0 || j < 0 || j > N+1 || k > M+1){\
                if(k < 0){
                    break;
                }
                continue;
            }
            //cout << "Setting " << j << " and " << k << endl;
            if(j >= 1){
                dp[j][k] = min(dp[j][k], dp[j-1][k] + calcSquaredDistance(Fpoints[j], Bpoints[k]));
            }
            if(k >= 1){
                dp[j][k] = min(dp[j][k], dp[j][k-1] + calcSquaredDistance(Fpoints[j], Bpoints[k]));
            }
            if(j >= 1 && k >= 1){
                dp[j][k] = min(dp[j][k], dp[j-1][k-1] + calcSquaredDistance(Fpoints[j], Bpoints[k]));
            }
        }
    };
    /*cout << "Final Result " << endl;
    for(int i = 0; i <= N; i ++){
        for(int j = 0; j <= M; j ++){
            cout << dp[i][j] << " ";
        }
        cout << endl;
    }
    */
    cout << dp[N][M] << endl;

    return 0;
}
