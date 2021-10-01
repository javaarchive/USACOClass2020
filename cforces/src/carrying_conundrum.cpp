#include <iostream>
#define MAXDIGITS 13

using namespace std;

//                                 sum to 10,11,12,13,14,15,16,17,18
int precalcCarrySumPossibltiesTable[10] = {9,8 ,7 ,6 ,5 , 4,3 ,2 ,1, 0 };

void solve(){
    int N;
    // int dp[4][MAXDIGITS]; // dp 1,2,3 queue for carry
    cin >> N;
    string Nstr = to_string(N);
    int curLen = Nstr.length();
    string a = "0",b = "0";
    for(int i = 0; i < curLen; i ++){
        if(i % 2 == 0){
            a = a + Nstr[i];
        }else{
            b = b + Nstr[i];
        }
    }
    // cout << a << " and " << b << endl;

    int aint = stoi(a);
    int bint = stoi(b);
    cout << ((aint + 1)*(bint + 1) - 2) << endl;
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
