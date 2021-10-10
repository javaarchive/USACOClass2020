#include <bits/stdc++.h>
#define MAXNM 100001
using namespace std;

void solve(){
    int N, M;
    cin >> N >> M;
    int arr[MAXNM];
    map<int,int> freq;
    
    for(int i = 0; i < N; i++){
        cin >> arr[i];
        arr[i] = arr[i] % M;
        freq[arr[i]]++;
    }

    if(freq[0] == N){
        cout << "" << 1 << endl;
        return;
    }

    int ans = 0,half = -1;
    // removed switch
    if(M % 2 == 0){
        half = M/2;
    }else{
        half = M/2;
    }
    /*cout << "Frequency Chart: ";
    for(int i = 0; i < M; i++){
        cout << freq[i] << " ";
    }
    cout << endl;*/
    for(int i = 1; i <= half; i ++){
        
        if(abs(freq[i] - freq[M-i]) > 1){
            int a = max(freq[i], freq[M - i]);
            int b = min(freq[i], freq[M - i]);
            ans += 1; // group some
            ans += a - b - 1;
            // cout << "a n b " << a << " " << b << endl;
            // cout << "+ " << (a - b) << " comp: " << i << " " << M - i << endl;
        }else if(freq[i] == 0 && freq[M - i] == 0){
            // cout << "+ " << 0 << " comp: " << i << " " << M - i << endl;
            // do nothing
        }else{
            // cout << "+ " << 1 << " comp: " << i << " " << M - i << endl;
            ans ++; // either pair each one up or one extra of a type
        }
    }
    if(freq[0] > 0){
        ans ++;
    }

    cout << "" << ans << endl;

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
