#include <iostream>
#include <algorithm>
#include <set>
#include <queue>
using namespace std;

int C, N;
int dp[50001] = {0};
int bales[5001];
set<int> startLocs;
queue<int> addBuffer;
int main(int argc, const char** argv) {
    cin >> C >> N;
    for(int i = 0; i < N; i ++){
        cin >> bales[i];
    }
    //cout << "sortstart";
    sort(bales, bales + N); // cout << ",sortdone" << endl;
    startLocs.insert(0);
    for(int i = 0; i < N; i ++){
        cout << "executing " << i << " current set size " << startLocs.size() << endl;
        for(auto iter = startLocs.begin(); iter != startLocs.end(); iter ++){
            int oldPos = (*iter);
            int newPos = oldPos + bales[i];
            if(newPos > C){
                continue;
            }
            //cout << "Jump to " << newPos << " from " << oldPos << " adding " << bales[i] << endl;
            if(dp[newPos] < (dp[oldPos] + bales[i])){
                if(dp[newPos] == 0){
                    addBuffer.push(newPos);
                }
                dp[newPos] = dp[oldPos] + bales[i];
            }
        }
        /*for(int i = 0; i <= C; i ++){
            cout << dp[i] << " ";
        }*/
        cout << "add buffer size " << addBuffer.size() << endl;
        while(!addBuffer.empty()){
            startLocs.insert(addBuffer.front());
            addBuffer.pop();
        }
        //startLocs.insert
        //cout << endl;
    }
    int best = 0;
    for(int i = 0; i <= C; i ++){
        //cout << dp[i] << " ";
        best = max(best, dp[i]);
    }
    cout << best;
    return 0;
}