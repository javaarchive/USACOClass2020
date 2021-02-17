#include <iostream>
#include <algorithm>
using namespace std;
int N,K;
int trees[1001];
int i;
int main(int argc, const char** argv) {
    cin >> N >> K;
    for(int i = 0; i < N; i ++){
        cin >> trees[i];
    }
    sort(trees,trees + N);
    int mostberries = trees[i];
    int best = 0;
    for(i = 1;i <= mostberries; i ++){
        int f = 0;
        for(int j = 0; j < N; j ++){
            f += trees[j]/i;
        }
        if(f < K/2){
            break;
        }
        if(f >= K){
            best = max(best, i * (K/2));
        }
        sort(trees,trees + N, [](int a, int b){return (a % i) > (b % i)});
        int currentAmount = (full - K/2) * b;
    }
    return 0;
}
    
