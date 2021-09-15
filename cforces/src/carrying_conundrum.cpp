#include <iostream>
#define MAXDIGITS 13

using namespace std;

//                                 sum to 10,11,12,13,14,15,16,17,18
int precalcCarrySumPossibltiesTable[10] = {9,8 ,7 ,6 ,5 , 4,3 ,2 ,1, 0 };

void solve(){
    int N;
    int dp[4][MAXDIGITS]; // dp 1,2,3 queue for carry
    cin >> N;
    string Nstr = to_string(N);
    int curLen = Nstr.length();
    for(int i = 0; i < curLen; i++){
        for(int j = 0; j < 4; j ++){
            dp[j][i] = 0;
        }
    }
    int cur = 0;
    int lastDigit = Nstr[curLen - 1] - '0';
    if(lastDigit != '9'){
        dp[2][curLen - 1] = precalcCarrySumPossibltiesTable[lastDigit];   
    }
    dp[0][curLen - 1] = lastDigit + 1; // simplest formula

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
