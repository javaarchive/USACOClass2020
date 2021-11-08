#include <bits/stdc++.h>
#define MAXN 101
using namespace std;

int depths[MAXN];
int permutation[MAXN];
int N;

void recur(int start, int ending, int depth){
    // cout << start << " --- " << ending << " DEPTH: " << depth << endl;
    if(start == ending && depths[start] < 0){
        depths[start] = depth;
        return;
    }
    if(ending < start){
        return;
    }
    // if(depth > 5){return;}

    int maximus = 0;
    int index = 0;
    for(int i = start; i <= ending; i++){
        if(permutation[i] > maximus){
            maximus = permutation[i];
            index = i;
        }
    }
    /*for(int i = start; i <= ending; i++){
        depths[i] = depth;
    }*/
    depths[index] = depth;

    int upBound = max(index - 1,0);
    if(!(upBound == start && index == start)){
        recur(start,upBound, depth + 1);
    }
    int lowBound = min(index + 1, N - 1);
    if(lowBound == index && lowBound == ending){
        return;
    }
    recur(lowBound,ending, depth + 1);
}

void solve(){
    cin >> N;
    for(int i = 0; i < N; i ++){
        cin >> permutation[i];
    }
    for(int i = 0; i < N; i ++){
        depths[i] = -1;
    }
    // cout << "Entering recur " << endl; 

    recur(0, N - 1, 0);
    // cout << "ANS: ";
    for(int i = 0; i < N; i ++){
        cout << depths[i] << " ";
    }
    cout << endl;
}

int main(int argc, char const *argv[])
{
    int T;
    cin >> T;
    while(T --){
        solve();
    }
    return 0;
}
