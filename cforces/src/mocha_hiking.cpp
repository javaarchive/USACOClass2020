#include <iostream>
#define MAXN 10001

using namespace std;

void solve(){
    
    int N;

    cin >> N;
    int A[MAXN];
    for(int i = 0; i < N; i ++){
        cin >> A[i];
    }
    if(A[0] == 1){
;        // cout << "A";
        cout << (N + 1) << " ";
        for(int i = 0; i < N; i ++){
            cout << (i + 1) << " ";
        }
    }else if(A[N - 1] == 0 && false){
        // cout << "B";
        for(int i = 0; i <= N; i ++){
            cout << (i + 1) << " "; // 1 through N + 1
        }
    }else{
        // cout << "C";
        for(int i = 0; i < N - 1; i ++){
            if(A[i] == 0 && A[i + 1] == 1){
                // cout << "F " << i << endl;
                for(int j = 0; j <= i; j ++){
                    cout << (j + 1) << " ";
                }
                cout << ""<< (N + 1) << " ";
                for(int j = i + 1; j < N; j ++){
                    cout << (j + 1) << " ";
                }
                cout << endl;
                return;
            }
        }
        for(int i = 0; i <= N; i ++){
            cout << (i + 1) << " ";
        }
    }
    cout << endl;
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
