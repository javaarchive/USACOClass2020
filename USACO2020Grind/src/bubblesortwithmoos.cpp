#define MAXN 100002
#include <iostream>
#include <algorithm>
using namespace std;
void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}
struct Element{
    int index;
    int val = 0;
};

int N;
// int unsorted[MAXN];
Element sorted[MAXN];
// int ps[MAXN];

int main(int argc, const char** argv) {
    if(argc != 2){
        setIO("sort");
    }

    cin >> N;
    
    for(int i = 0; i < N; i ++){
        // cin >> unsorted[i];
        cin >> sorted[i].val;
        // sorted[i].val = unsorted[i];
        sorted[i].index = i; // original index
    }
    
    sort(sorted, sorted + N, [](Element a, Element b){
        return (a.val == b.val && a.index < b.index) || (a.val < b.val);
    });

    int farthestDist = 0;
    
    for(int i = 0; i < N; i ++){
        // cout << "i got " << (sorted[i].index - i) << " dist between " << sorted[i].index << " and " << i << endl;
        farthestDist = max(farthestDist, (sorted[i].index - i));
    }
    /*for(int i = 0; i < N; i ++){
        cout << sorted[i] << " ";
    }
    cout << endl;
     for(int i = 0; i < N; i ++){
        cout << unsorted[i] << " ";
    }
    cout << endl;*/
    cout << (farthestDist + 1) << endl;

    return 0;
}