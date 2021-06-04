#include <iostream>
#include <vector>
#include <cmath>
#define MAXDIM 1001
using namespace std;
int N,X,Y,Z;
vector<int> A,B;
int dp[MAXDIM][MAXDIM];

int main(int argc, char const *argv[])
{
    cin >> N >> X >> Y >> Z;

    for(int i = 0; i < N; i ++){
        int a,b;
        cin >> a >> b;
        for(int j = 0; j < a; j ++){
            A.push_back(i);
        }
        for(int j = 0; j < b; j ++){
            B.push_back(i);
        }
    }
    for(int i = 0; i <= A.size(); i ++){
        for(int j = 0; j <= B.size(); j ++){
            dp[i][j] = INT32_MAX/2;
        }
    }
    for(int i = 0; i < B.size(); i ++){
        dp[0][i] = i * X;
    }

    for(int i = 0; i < A.size(); i ++){
        dp[i][0] = i * Y;
    }
   
    for(int i = 1; i <= A.size(); i ++){
        for(int j = 1; j <= B.size(); j ++){
            dp[i][j] = min(dp[i][j - 1] + X, dp[i - 1][j] + Y);
            dp[i][j] = min(dp[i][j], dp[i - 1][j - 1] + Z * abs((A[i] - B[j])));
        }
    }
    /*for(int i = 0; i <= A.size(); i ++){
        for(int j = 0; j <= B.size(); j ++){
            cout << dp[i][j] << " ";
        }
        cout << endl;
    }*/
    cout << dp[A.size()][B.size()] << endl;
    return 0;
}
