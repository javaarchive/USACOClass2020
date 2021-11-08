#include <bits/stdc++.h>
using namespace std;

void solve(){
    int N;
    cin >> N;
    int qx,qy;
    cin >> qx >> qy;
    int kx,ky;
    cin >> kx >> ky;
    int tx,ty; // target x,y
    cin >> tx >> ty;
    if(kx == tx && ky == ty){
        cout << "0" << endl;
        return;
    }
    bool kxPositive = (qx - kx) > 0;
    bool kyPositive = (qy - ky) > 0;

    bool txPositive = (qx - tx) > 0;
    bool tyPositive = (qy - ty) > 0;

    if(txPositive == kxPositive && tyPositive == kyPositive){
        cout << "YES" << endl;
    }else{
        cout << "NO" << endl;
    }
}

int main(int argc, char const *argv[])
{
    solve();
    return 0;
}
