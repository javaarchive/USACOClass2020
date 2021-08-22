// yet another xor problem
#include <iostream>
#define MOD 1000000007
#define PRECALC_AREA 1000
#include <assert.h>
using namespace std;

int precalc[PRECALC_AREA] = {0};
//  ---- k ----
// |
// |
// n
// |
// |

long long fastTwoPow(int pow){
    if(pow <= 0){
        return 1;
    }
    if(pow < PRECALC_AREA){
        return precalc[pow];
    }
    if(pow % 2 == 1){
        long long squarebase = fastTwoPow((pow - 1)/2);
        return (squarebase * squarebase * 2) % MOD;
    }else{
        long long squarebase = fastTwoPow(pow/2);
        return (squarebase * squarebase) % MOD;
    }
}

void genPrecalc(){
    precalc[0] = 1;
    for(int i = 1; i < PRECALC_AREA; i++){
        precalc[i] = (precalc[i-1]*2) % MOD;
    }
}

int calcEvenBinom(int top){
    if(top == 0 || top == 1){
        return 1;
    }
    long long out = fastTwoPow(top - 1);
    // cout << "ceb(" << top << ") = " << out << endl;
    return (int) out;
}

long long superExponent(long long base, int power){
    // cout << "called " << base << "^" << power << endl;
    if(power <= 0){
        return 1;
    }else if(power == 1){
        return base;
    }
    if(power % 2 == 0){
        long long sqrt = superExponent(base, power/2);
        // cout << "exp(" << base << "," << (power/2) << ") = " << sqrt << endl; 
        return (sqrt * sqrt) % MOD;
    }else{
        long long sqrt = superExponent(base, (power - 1)/2);
        // cout << "exp(" << base << "," << ((power - 1)/2) << ") = " << sqrt << endl; 
        return (((sqrt * sqrt) % MOD) * base) % MOD;
        // return (sqrt * sqrt * base) % MOD;
    }
}


void solve(){
    int N,K;
    cin >> N >> K; // K: digits
    long long evenBinomsCount = calcEvenBinom(N);
    long long base = evenBinomsCount + 1 - 2 * (N % 2 == 0); // +1 is for all 1 case if not counted
    // cout << "base: " << base << "out: "<< endl;
    /*long long out = 1;
   for(int i = 0; i < K; i ++){ // for each bit row
        out = (out * base) % MOD;
    }*/
    if(N % 2 == 0){
        // base += evenBinomsCount;
        // base = base % MOD;
    }
    long long ans = superExponent(base,K);
    if(N % 2 == 0){
        // all 1
        // and evaluates to 1
        // xor evaluates to 0
        // everything afterwards nobody cares
        // we're undercounting

        /*for(int i = 0; i < K - 1; i ++){
            // placement of all 1s is i
            ans += ((fastTwoPow(i) * superExponent(evenBinomsCount,K - i - 1)) % MOD);
            ans = ans % MOD;
        }*/

        // ans += superExponent(evenBinomsCount,K);
        ans = ans % MOD;
        long long n2power = fastTwoPow(N);
        for(int i = 1; i <= K; i ++){
            ans += ((superExponent(evenBinomsCount - 1, i - 1) * superExponent(n2power, K - i)) % MOD);
            ans = ans % MOD;
        }
    }
    cout << ans << endl;
}

int main(int argc, char const *argv[])
{
    int T;
    cin >> T;
    genPrecalc();
    assert((precalc[3] == 8));
    assert((calcEvenBinom(5) == 16));
    assert((fastTwoPow(10005) == 979577564LL));
    // cout << "assert: " << (superExponent(3,10)) << endl;
    assert((superExponent(3,10) == 59049LL));
    long long largeTest = superExponent(99324,45332);
    // cout << "largest test: " << largeTest << endl;
    assert((largeTest == 924338940LL));
    for(int i = 0; i < T; i ++){
        solve();
    }

    return 0;
}
