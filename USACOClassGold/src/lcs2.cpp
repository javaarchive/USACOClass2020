#include <iostream>
#define MAXNM 10001
using namespace std;

int main(int argc, const char** argv) {
    string a,b;
    cin >> a;
    cin >> b;
    int N = a.length();
    int M = b.length();
    int arr[MAXNM] = {0};
    int temp[MAXNM] = {0};
    for(int i = N - 1; i >= 0; i --){
        for(int j = M - 1; j >= 0; j --){
            if(a[i] == b[j]){
                temp[j] = arr[j + 1] + 1; // go backwards
            }else{
                temp[j] = max(arr[j], temp[j+  1]); // prev or from the iteration before
            }
        }
        copy(temp, temp + max(N,M), arr); // copy?
        /*for(int i = 0; i < max(N,M); i ++){
            cout << temp[i] << " ";
        }*/
        //cout << endl;
    }
    cout << arr[0] << endl;
}