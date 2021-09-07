#include <iostream>
#include <cmath>
#define MAX 200001
using namespace std;

void solve(){
    int A,B;
    cin >> A >> B;
    int LIMIT = A + B;
    int oddGames = ceil(LIMIT/2.0);
    int evenGames = floor(LIMIT/2.0);

    bool works[MAX] = {false};
    int counter = 0;
    for(int aVal = 0; aVal <= LIMIT; aVal ++){
        int bVal = oddGames - aVal;
        int cVal = A - aVal;
        int dVal = evenGames - cVal;
        int sum1 = aVal + dVal;
        int sum2 = bVal + cVal;
        if(aVal < 0 || bVal < 0 || cVal < 0 || dVal < 0) continue;
        if(sum1 <= LIMIT && sum1 >= 0){
            if(works[sum1] == false){
                // cout << "ADD " << sum1 << endl;
                counter ++;
            }
            works[sum1] = true;
        }
        if(sum2 <= LIMIT && sum2 >= 0){
             if(works[sum2] == false){
                // cout << "ADD " << sum2 << endl;
                counter ++;
            }
            works[sum2] = true;
        }
    }
    cout << counter << " " << endl;
    for(int i = 0; i <= LIMIT; i ++){
        if(works[i]){
            cout << i << " ";
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
