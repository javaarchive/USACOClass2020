#include <iostream>
#include <map>
#include <algorithm>
#include <unordered_map>
#define MAXN 100001

using namespace std;

void solve(){
    int N,K;
    cin >> N >> K;
    int arr[MAXN];
    int sortarr[MAXN];
    for(int i = 0; i < N; i ++){
        cin >> arr[i];
        sortarr[i] = arr[i];        
    }
    sort(sortarr, sortarr + N);
    int contigBlockCount = 0;
    unordered_map<int,int> revIndex;
    for(int i = 0; i < N; i ++){
        if(i > 0 && sortarr[i] != sortarr[i - 1]){
            revIndex[sortarr[i]] = i;
        }
    }
    for(int i = 0; i < N - 1; i ++){
        /*if(arr[i] == arr[i + 1]){
            // we're ok
            continue;
        }*/
        if(revIndex[arr[i]] != (revIndex[arr[i + 1]] - 1)){
            // staty new section
            contigBlockCount ++;
        }
    }
    
    contigBlockCount ++;
    // cerr << "CCOUNT: " << contigBlockCount << endl;
    if(contigBlockCount > K){
        cout << "No" << endl;
    }else{
        cout << "Yes" << endl;
    }
}

int main(int argc, char const *argv[])
{
    int T;
    cin >> T;
    for(int i = 0 ; i < T; i ++){
        solve();
    }
    return 0;
}
