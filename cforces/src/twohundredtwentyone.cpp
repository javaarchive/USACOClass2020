#include <iostream>
#define MAXN 300003
using namespace std;

void solve(){
    int n, q;
    cin >> n >> q;
    string line;
    cin >> line;
    int ps[MAXN] = {0};
    
    for(int i=0; i<n; i ++){
        if(line[i] == '+'){
            ps[i + 1] = ps[i] + (1 * (i % 2 == 0) ? 1 : -1);
        }else if(line[i] == '-'){
            ps[i + 1] = ps[i] - (1 * (i % 2 == 0) ? 1 : -1);
        }
    }
    /* cout << "Giving a PS table ";
    for(int i = 0; i <= n; i ++){
        cout << ps[i] << " ";
    }
    cout << endl;*/
    for(int i=0; i<q; i ++){
        int x,y;
        cin >> x >> y;
        // cout << " ans: ";
        int sum = ps[y] - ps[x - 1];
        /*if(x % 2 == 0){
            sum = -sum;
        }*/
        if(sum == 0){
            cout << "0" << endl;
        }else if((y - x + 1) % 2 == 0){
            cout << "2" << endl;
        }else{
            cout << "1" << endl;
        }
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