#include <bits/stdc++.h>
#define MAXNM 100001
using namespace std;

void solve(){
    int N;
    cin >> N;
    int A[MAXNM] = {0};
    int B[MAXNM] = {0};
    // map<int,int> revA;
    // map<int,int> revB;
    map<int,int> rev;
    for(int i = 0; i < N; i++){
        cin >> A[i];
        rev[A[i]] = i;
    }
    for(int i = 0; i < N; i++){
        cin >> B[i];
        rev[B[i]] = i;
    }
    
    if(A[0] < B[0]){
        cout << 0 << endl;
        return;
    }

    // sort(A, A + N);
    // sort(B, B + N);
    int ans = INT32_MAX;
    int lastB = INT32_MAX;
    // sort(A, A + N);
    // sort(B, B + N);
    // reverse(A, A + N);
    // reverse(B, B + N);
    // cout << " HEAD " << A[0] << " and " << B[0] << endl;
    for(int i = 2 * N; i >= 1; i --){
        // cout << i << endl;
        if(i % 2 == 0){
            lastB = min(lastB,rev[i]);  
        }else{
            // cout << "min " << rev[i] << " + " << lastB << endl;
            ans = min(ans, rev[i] + lastB);
        }
    }
    cout << ans << endl;

}

int main(int argc, char const *argv[])
{
    int T;
    cin >> T;
    while(T --){
        solve();
    }
    return 0;
}
