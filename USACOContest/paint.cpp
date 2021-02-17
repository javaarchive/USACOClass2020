// NOTE TO SELF: Greedy implementation of lower colors
#define MAXN 100001
#include <iostream>

using namespace std;
int fence[MAXN];
int forwardC[MAXN];
int backwardC[MAXN];
int N,Q;
int main(int argc, const char** argv) {
    char fenceStr[MAXN];
    cin >> N >> Q;
    cin >> fenceStr;
    for(int i = 0; i < N; i ++){
        fence[i] = (int) fenceStr[i];
    }
    forwardC[0] = 1; 
    for(int i = 1; i < N; i ++){
        forwardC[i] = forwardC[i - 1];
        if(fence[i] > fence[i - 1]){
            forwardC[i] ++;
        }
    }
    backwardC[N - 1] = 1;
    // backwardC[N] = 1;
    for(int i = N - 2; i >= 0; i --){
        backwardC[i] = backwardC[i + 1];
        if(fence[i - 1] < fence[i]){
            backwardC[i] ++;
        }
    }
    /*cout << "Forward: ";
    for(int i = 0; i < N; i ++){
        cout << forwardC[i] << " ";
    }
    cout << endl;
    cout << "Bakward: ";
    for(int i = 0; i < N; i ++){
        cout << backwardC[i] << " ";
    }
    cout << endl;*/
    int a,b;
    for(int i = 0; i < Q; i ++){
        cin >> a >> b;
        int sum = forwardC[a - 2] + backwardC[b];
        cout << sum << endl;
    }
    return 0;
}