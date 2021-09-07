#include <iostream>
#include <string>

using namespace std;

void solve(){
    long long num;
    cin >> num;
    string numstr = to_string(num);
    int N = numstr.length();
    int best = INT32_MAX;
    for(int i = 0; i < 63; i ++){
        long long pow = (long long) (1LL << i);
        string powstr = to_string(pow);
        int ops = 0;
        int pIndex = 0;
        for(int j = 0; j < N; j ++){
            // cout << powstr[pIndex] << " cmp " << numstr[j] << endl;
            if(powstr[pIndex] == numstr[j]){
                pIndex ++;     
                continue;
            }       
            ops ++; // no match
        }
        // cout << powstr << " : " << ops << " and " << max((int) powstr.length() - pIndex,0) << " = " << (max((int) powstr.length() - pIndex,0) + ops) << endl;
        // cout << pow << " is " << "+= " << powstr.length() << " - " << pIndex << endl;
        ops += max((int) powstr.length() - pIndex,0); // insert the rest
        // cout << pow << " -> " << ops << endl;
        best = min(best, ops);
    }
    cout << best << endl; 

}

int main(int argc, char const *argv[])
{
    int N;
    cin >> N;
    for(int i = 0; i < N; i++){
        solve();
    }
    return 0;
}
