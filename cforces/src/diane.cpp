#include <iostream>
using namespace std;

void solve(){
    int n;
    cin >> n;
    string precalc = "abcdefghijklmnopqrstuvwxyz";
    if(n < 26){
        cout << precalc.substr(0,n) << endl;
        return;
    }
    char c1 = 'p',c2 = 'o',c3 = 'g';
    int half = -1;
    if(n % 2 == 0){
        half = n/2 - 1;
        cout << string(half + 1,c1) << c2 << string(half,c1) << endl;
    }else{
        half = (n - 1)/2;
        cout << string(half,c1) << c2 << c3  << string(half - 1,c1) << endl;
    }
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
