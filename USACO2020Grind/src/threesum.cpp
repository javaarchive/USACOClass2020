#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

#define MAXVAL 100001
#define MAXSUM 300004
#define MAXN 5001

long long sVals[MAXN];
int dp[4][MAXSUM];

int N, Q;

int subsolve(vector<int>* opts){
    for(int i=0; i < MAXSUM; i ++){
        for(int j=0; j < 4; j ++){
            dp[j][i] = 0;
        }
    }
    dp[0][0] = 1;
    for(int i = 1; i <= 3; i ++){ // used to be multiple
        for(int j = 0; j < MAXSUM; j ++){
            for(int k = 0; k < (*opts).size(); k ++){
                int newPos = j + (*opts)[k];
                if(newPos >= MAXSUM){
                    break; // Early terminate because sorting
                }
                if(newPos < 0){
                    continue; // binary search optimization possible
                }
                if(dp[i - 1][j] == 0){
                    continue;
                }

                //cout << "Placing " << newPos << " level " << i << " starting at " << j << endl;
                dp[i][newPos] += dp[i - 1][j];
            }
        }
    }
    for(int i = 0; i <= 3; i ++){
        for(int j = 0; j < 25; j ++){
            cout << dp[i][j] << " ";
        }
        cout << endl;
    }
    return dp[3][0];
}

int main(int argc, char const *argv[])
{
    cin >> N >> Q;
    for(int i = 0; i < N; i ++){
        cin >> sVals[i];
        /*if(sVals[i] >= 0){
            dp[1][sVals[i]] = 1;
        }*/
    }
    sort(sVals, sVals + N); // Sorting is lovely
    for(int i = 0; i < Q; i ++){
        int a,b;
        cin >> a >> b;
        vector<int> subsection;
        for(int j = a; j <= b; j ++){
            subsection.push_back(sVals[j]);
        }
        cout << endl << "OUT: " <<  subsolve(&subsection) << endl;
    }
    return 0;
}
