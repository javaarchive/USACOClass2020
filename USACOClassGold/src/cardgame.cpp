#include <iostream>
#include <algorithm>
#include <set>
using namespace std;
int N;
int cards[50001];
int bcards[50001];
int sortedCards[50001];


void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}

int main(int argc, const char** argv) {

    setIO("cardgame");
    
    cin >> N;
    int wins = 0;
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
    sort(bcards, bcards + N);
    reverse(bcards, bcards + N);
    reverse(bcards, bcards + N/2);
    sort(cards, cards + N/2);
    sort(cards + N/2, cards + N); 
    reverse(cards + N/2, cards + N);
    /*for(int i = 0; i < N; i ++){
        cout << bcards[i] << " ";
    }
    cout << endl;
    for(int i = 0; i < N; i ++){
        cout << cards[i] << " ";
    }
    cout << endl;*/
    int index = 0;
    for(int i = 0; i < N/2; i ++){
        while(index < N/2 && bcards[index] < cards[i]){
            index ++;
        }
        if(index < N/2){
            // cout << bcards[index] << " beats " << cards[i] << endl;
            wins ++;
            index ++;
        }
    }
    // cout << "INVERT" << endl;
    for(int i = N/2; i < N; i ++){
        while(index < N && bcards[index] > cards[i]){
            index ++;
        }
        if(index < N){
            // cout << bcards[index] << " beats " << cards[i] << endl;
            wins ++;
            index ++;
        }
    }
    cout << wins << endl;
}