#include <iostream>
#include <algorithm>
using namespace std;
int N;
int cards[50001];
int bcards[50001];

int main(int argc, const char** argv) {
    cin >> N;
    for(int i = 0; i < N; i ++){
        cin >> cards[i];
    }
    sort(cards, cards + N);
    int b_index = 0;
    int e_index = 0;
    for(int i = 1; i <= 2 * N; i ++){
        if(i != cards[e_index]){
            bcards[b_index] = i;
            b_index ++;
        }else{
            e_index ++;
        }
    }
    /*cout << "Bessie has the cards ";
    for(int i = 0; i < N; i ++){
        cout << bcards[i] << " ";
    }
    cout << endl;*/
    int b_index2 = N/2 - 1;
    int wins = 0;
    for(int i = 0; i < N/2; i ++){
        bool signalLoopEnd = false;
        while(bcards[b_index2] >= cards[i]){ // while not good enough to beat
            b_index2 --;
            if(b_index2 < 0){
                signalLoopEnd = true;
                break;
            }
        }
        if(signalLoopEnd){
            break;
        }
        wins ++;
    }
    b_index2 = N/2; // set to a bit above mid
    for(int i = N/2; i < N; i ++){
        bool signalLoopEnd = false;
        while(bcards[b_index2] <= cards[i]){ // while not good enough to beat
            b_index2 --;
            if(b_index2 >= 0){
                break;
                signalLoopEnd = true;
            }
        }
        if(signalLoopEnd){
            break;
        }
        wins ++;
    }
    cout << wins;
    return 0;
}