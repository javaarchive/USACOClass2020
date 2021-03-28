#include <iostream>
#include <algorithm>
#include <queue>
#define MAXN 50001
using namespace std;
int N, K;
int diamonds[MAXN];
void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}
int main(int argc, const char** argv) {
    if(argc != 2){
        setIO("diamond"); 
    }
    cin >> N >> K;
    for(int i = 0; i < N; i ++){
        cin >> diamonds[i];
    }
    sort(diamonds, diamonds + N);
    // Scan for 2K connected solutions
    int end = 0;
    int best = 0;
    for(int start = 0; start < N; start ++){
        while(end < N && (diamonds[end] - diamonds[start]) <= 2*K){
            end ++;
        }
        int count = ((end - 1) - start + 1);
        best = max(best, count);
    }
    // seperate K solutions
    queue<pair<int,int>> overlapping;
    int bestPrevGroup = 0;
    // cout << "Inital best " << best << endl;
    for(int start = 0; start < N; start ++){
        end = start;
        while(end < N && (diamonds[end] - diamonds[start]) <= K){
            //cout << (diamonds[end] - diamonds[start]) << " <= " << K << endl;
            end ++;
        }
        end --; // get inclusive bound
        //cout << "Going from " << diamonds[start] << " to " << diamonds[end] << endl;
        int count = (end - start + 1);
        // best = max(best, count); // well I mean 2 cases are better than one so this really doesn't matter
        overlapping.push(make_pair(start,end)); // inclusive
        //cout << "In goes " << start << " and " << end << endl; 
        while(!overlapping.empty() && overlapping.front().second < start){
            pair<int,int> section = overlapping.front();
            overlapping.pop();
            //cout << "Out goes " << section.first << " and " << section.second << endl;
            bestPrevGroup = max(bestPrevGroup, section.second - section.first + 1);
        }
        best = max(best, count + bestPrevGroup);
        //cout << "Best group now: " << bestPrevGroup << " and best " << best << endl;
    }
    cout << best << endl;
    return 0;
}