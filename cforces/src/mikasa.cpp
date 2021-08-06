#include <iostream>
#include <cmath>
#include <bitset>
// really strange problem name tbh
using namespace std;
int getBit(int input, int i){
    return (input % (1 << (i+1))) >= (1 << i); 
}
void solve(){
    int n,m;
    cin >> n >> m;
    // cout << "M: " << bitset<8>(m) << endl; 
    // cout << "M + 1: " << bitset<16>(m + 1) << endl; 
    // cout << "N    : " << bitset<16>(n) << endl; 
    // naive solution old
    /*for(int i = 0; i < INT32_MAX; i++){
        
        // cout << n << "^" << i << " = " << (n^i) << " > " << m << endl;
        if((n ^ i) > m){
            cout << i << endl;
            return;
        }
    }*/
    int out = 0;
    int upBound = max(ceil(log2(n)),ceil(log2(m + 1)));
    for(int i = upBound ; i >= 0; i --){
        int bitM1 = getBit(m + 1, i);
        int bitN  = getBit(n    , i);
        // cout << bitM1 << " and " << bitN << endl;
        if(bitN == 0 && bitM1 == 1){
            out += 1 << i;
        }
        if(bitN == 1 && bitM1 == 0){
            // power is greater than everything else to the right of it
            break;
        }
    }
    cout << out << endl;
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
