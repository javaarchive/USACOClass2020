#include <iostream>
#include <set>
#include <vector>
#include <algorithm>
using namespace std;

int N, K;
long long factorialTable[21] = {1,1,2,6,24,120,720,5040,40320,362880,3628800,39916800,479001600,6227020800,87178291200,1307674368000,20922789888000,355687428096000,6402373705728000,121645100408832000,2432902008176640000};
int main(int argc, const char** argv) {
    cin >> N >> K;
    for(int i = 0; i < K; i ++){
        char type;
        cin >> type;
        if(type == 'P'){
            long long index;
            cin >> index;
            index --;
            vector<int> digitsNotUsed;
            for(int i = 1; i <= N; i ++){
                digitsNotUsed.push_back(i);
            }
            long long indexSection = index;
            vector<int> output;
            for(int i = N; i > 0; i --){
                long long pos = indexSection/factorialTable[i - 1];
                output.push_back(digitsNotUsed[pos]);
                indexSection -= (long long) (factorialTable[i - 1] * pos);
                digitsNotUsed.erase(digitsNotUsed.begin() + pos);
            }
            for(int i = 0; i < N; i ++){
                cout << output[i] << " ";
            }
            cout << endl;
        }else if(type == 'Q'){
            long long output = 0;
            vector<int> digitsNotUsed;
            for(int i = 1; i <= N; i ++){
                digitsNotUsed.push_back(i);
            }
            for(int i = 0; i < N; i ++){
                int cowNo;
                cin >> cowNo;
                //                          -1 offset again because for the Nth value we want (N - 1)!
                int indexInDigits = find(digitsNotUsed.begin(), digitsNotUsed.end(), cowNo) - digitsNotUsed.begin();
                // cout << "Calculating " << (N - i - 1 ) << "! * " << indexInDigits << endl;
                output += factorialTable[N - i - 1] * indexInDigits;
                digitsNotUsed.erase(digitsNotUsed.begin() + indexInDigits);
            }
            cout << (output + 1) << endl;
        }
    }
    return 0;
}