#include <iostream>
#include <cmath>

using namespace std;
void setIO(string s) {
ios_base::sync_with_stdio(0); cin.tie(0);
freopen((s + ".in").c_str(), "r", stdin);
freopen((s + ".out").c_str(), "w", stdout);
}
//                                                                
int myshinytable[] = {1,2,4,7,8,11,13,14,16,17,19,22,23,26,28,29};//31 32 34 37 38 41 43 44 46 47 49 52 53 56 58 59 61
//                    1 2 3 4 5 6  7  8  9  10 11 12 13 14 15 16    17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33
int main(int argc, const char **argv)
{
    //setIO("moobuzz");
    int len = sizeof(myshinytable)/sizeof(myshinytable[0]);
    int N;
    cin >> N;
    N --;
    cout << ((myshinytable[(N)%8])) << " " << (((int)(N/8)) * 15) << endl;
    cout << (myshinytable[(N)%8]) << " " << floor((N)/8) * 15 << endl;
    cout << (myshinytable[(N)%8]) << " " << N/8 * 15;

    return 0;
}
