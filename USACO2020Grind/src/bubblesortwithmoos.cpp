#define MAXN 100002
#include <iostream>
#include <algorithm>
#include <map>
#include <unordered_map>
using namespace std;
void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}

int N;
int unsorted[MAXN];
int sorted[MAXN];
int ps[MAXN];
unordered_map<int,int> toSortedIndex;

int main(int argc, const char** argv) {
    if(argc != 2){
        setIO("sort");
    }

    cin >> N;
    
    for(int i = 0; i < N; i ++){
        cin >> unsorted[i];
        sorted[i] = unsorted[i];
    }
    sort(sorted, sorted + N);
    int farthestDist = 0;
    for(int i = 0; i < N; i ++){
        toSortedIndex[sorted[i]] = i;
    }
    for(int i = 0; i < N; i ++){
        farthestDist = max(farthestDist, abs(toSortedIndex[unsorted[i]] - i));
    }
    /*for(int i = 0; i < N; i ++){
        cout << sorted[i] << " ";
    }
    cout << endl;
     for(int i = 0; i < N; i ++){
        cout << unsorted[i] << " ";
    }
    cout << endl;*/
    cout << farthestDist+1 << endl;

    return 0;
}