#include <iostream>
#include <algorithm>
using namespace std;
#define MAXN 1000001
int N,K;
int hints[MAXN];
bool duplicateChecker[MAXN];
int calc(int actualTrues, int guessedTrues){
    // in the worst case all the guesses go to the wrong answer
    if(guessedTrues >= (N - actualTrues)){
        return (guessedTrues - (N - actualTrues));
    }else{
        int guessedFalses = N - guessedTrues;
        return guessedFalses - actualTrues; // worst case all the falses go into the actually trues
    }
    // returns: best
}
int main(int argc, const char** argv) {
    cin >> N >> K;
    for(int i = 0; i < K; i ++){
        int trueCount;
        cin >> trueCount;
        if(duplicateChecker[trueCount]){
            i --;
            K --;
            continue;   
        }
        duplicateChecker[trueCount] = true;
        hints[i] = trueCount;
    }
    sort(hints, hints + K);
    int best = 0;
    int topIndex = K - 1;

    for(int i = 0; i <= N; i ++){
        // cout << "Guessing " << i << " trues we have " << (calc(hints[0],i)) << " and " << (calc(hints[K - 1],i)) << endl; 
        int hintTop = hints[i];
        int hintBottom = hints[i - 1];
        int worstCase = min(abs(i + hints[topIndex] - N),abs(i + hints[topIndex - 1] - N));
        // cout << "Wcase: " << worstCase << endl;
        if(i >= 2){
            int belowWorstCase = min(abs(i + hints[topIndex - 1] - N),abs(i + hints[topIndex - 2] - N));
            if(belowWorstCase < worstCase){
                topIndex --;
                // cout << "Down: " << belowWorstCase << endl;
                best = max(best, belowWorstCase);
                continue;
            }
        }
        best = max(best, worstCase);
        // best = max(min(calc(hints[0],i),min(calc(hints[K - 1],i), calc(hints[K/2], i))), best);//max(min(abs(hints[0] + i - N), abs(hints[K - 1] + i - N)), best);
        
    }
    cout << best << endl;
    return 0;
}