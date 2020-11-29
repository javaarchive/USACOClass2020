#include <iostream>
#include <cmath>
#include <algorithm>  
#include <utility>
// Debug Tools  
#include <iomanip>  
using namespace std;
int W,H,l1,l2;
long long dist(int x1, int y1, int x2, int y2){
    int a = x1-x2;
    int b = y1-y2;
    return a*a + b*b;
}
bool coprime(int a, int b){
   //cout << a << "," << b << endl;
    // hahaha std go brrr
    if(a == 0 || b == 0 && a != b){
        return false; // Just in case
    }
    
    //cout << "exec origin: " << a << "," << b << endl;
    return (__gcd(a,b) == 1);
}
long long solve(){
    long long ans = 0;
    for(int i = 0; i <= W; i ++){
        for(int j = 0; j <= H; j ++){
            if(i == 0 && j == 0){
                continue; // Just a point
            }
            
            //cout << "Proc: "<< i << "," << j << endl;
            if(coprime(abs(i),abs(j))){
                long long eDist = dist(0,0,i,j);
                //cout << "i: " << i << " j: " << j << " dist: " << setprecision(2) << eDist<< endl;
                //cout << W << "  - " << i << endl;
                if(l1 <= eDist && eDist <= l2){
                    long long toAdd = (W - abs(i) + 1) * (H - abs(j) + 1);
                    if(j != 0){
                        toAdd = toAdd * 2;
                    }
                    //cout << "Adding " << toAdd << endl;
                    ans += toAdd;
                }
            }
        }
    }
    return ans;
}  
int main(int argc, const char** argv) {
    cin >> W >> H;
    //W ++;
    //H ++;
    cin >> l1 >> l2;
    l1 = l1 * l1;
    l2 = l2 * l2;
    cout << solve();
    return 0;
}