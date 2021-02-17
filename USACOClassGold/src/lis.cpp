#include <iostream>
#include <vector>

using namespace std;
int N;
vector<int> seq;

int main(){
    cin >> N;
    seq.resize(N);
    vector<int> lisubseq;
    lisubseq.resize(N);
    
    for(int i = 0; i < N; i ++){
        // cout << "READING"<<i<<endl;
        lisubseq[i] = 1;
        cin >> seq[i];
    }
    for(int i = 0; i < N; i ++){
        // cout << i;
        for(int j = 0; j < i; j ++){
            if(seq[i] > seq[j]){
                lisubseq[i] = max(lisubseq[j] + 1, lisubseq[i]);
            }
        }   
    }
    int best = 0;
    for(int i = 0; i < N; i ++){
        // cout << i << ": " << lisubseq[i] << endl;
        best = max(best,lisubseq[i]);
    }
    cout << best;
    
}