#include <iostream>
#include <algorithm>
#include <set>
using namespace std;
int N;
int cards[50001];
int sortedCards[50001]; // elsie's sorted cars
int bcards[50001];

int main(int argc, const char** argv) {
    cin >> N;
    for(int i = 0; i < N; i ++){
        cin >> cards[i];
        sortedCards[i] = cards[i];
    }
    sort(sortedCards, sortedCards + N);
    int b_index = 0;
    int e_index = 0;
    for(int i = 1; i <= 2 * N; i ++){
        if(i != sortedCards[e_index]){
            bcards[b_index] = i;
            b_index ++;
        }else{
            e_index ++;
        }
    }
    cout << "Bessie has the cards ";
    for(int i = 0; i < N; i ++){
        cout << bcards[i] << " ";
    }
    cout << endl;
    // sort(cards, cards + N/2);
    reverse(cards, cards + N/2);
    reverse(bcards,bcards + N);
    reverse(bcards + N/2,bcards + N);
    sort(cards + N/2, cards + N);
    int b_index2 = 0;
    int wins = 0;
    for(int i = 0; i < N; i ++){
        cout << bcards[i] << " ";
    }
    cout << endl;
    for(int i = 0; i < N/2 && b_index2 < N/2; i ++){
        bool signalLoopEnd = false;
        while(bcards[b_index2] <= cards[i]){ // while not good enough to beat
            
            cout << b_index2 << " ..." << endl;
            if(b_index2 >= N/2){
                signalLoopEnd = true;
                break;
            }
            b_index2 ++;
        }
        if(signalLoopEnd || b_index2 >= N/2){
            break;
        }
        cout << "bcards[" << b_index2 << "] and cards[" << i << "] " << endl;
        cout << bcards[b_index2] << " beats " << cards[i] << endl;
        wins ++;
        b_index2 ++;
    }
    cout << "Flip" << endl;
    b_index2 = N/2; // set to a bit above mid
    for(int i = N/2; i < N && b_index2 < N; i ++){
        bool signalLoopEnd = false;
        while(bcards[b_index2] >= cards[i]){ // while not good enough to beat
            cout << b_index2 << " ..." << endl;
            if(b_index2 >= N){
                break;
                signalLoopEnd = true;
            }
            b_index2 ++;
            
        }
        if(signalLoopEnd || b_index2 >= 0){
            break;
        }
        cout << "bcards[" << b_index2 << "] and cards[" << i << "] " << endl;
        cout << bcards[b_index2] << " beats " << cards[i] << endl;
        wins ++;
        b_index2 ++;
    }
    cout << wins << endl;
    return 0;
}