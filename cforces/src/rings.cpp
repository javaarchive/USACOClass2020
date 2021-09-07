// Lord of the rings reference probaly
#include <iostream>
#include <cmath>
using namespace std;
void solve(){
    int n;
    cin >> n;
    string s;
    cin >> s;
    int azero = -1;
    int threshold = floor(n/2);
    for(int i=0; i<n; i ++){
        if(s[i] == '0'){
            azero = i;
            if(azero <= threshold && (n - azero - 1) >= threshold){
                cout << "" << (azero + 1) << " " << n << " " << (azero + 2) << " " << n << endl;
                return;
            }else if((azero + 1) >= threshold){
                cout << "" << 1 << " " << (azero + 1) << " " << 1 << " " << (azero) << endl;
                return;
            }
        }
    }
    if(azero == -1){
        cout << 1 << " " << n - 1 << " " << 2 << " " << n << endl;
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
