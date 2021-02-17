#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N;
void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}
int main(int argc, char const *argv[])
{
    setIO("lemonade");
    cin >> N;
    vector<int> patience;
    patience.resize(N);
    for(int i = 0; i < N; i ++){
        cin >> patience[i];
    }
    sort(patience.begin(), patience.end(), [](int a, int b){return a > b;}); // reverse sort
    int inFront = 0;
    for(int i = 0; i < N; i ++){
        if(inFront <= patience[i]){
            inFront ++;
        }
    }
    cout << inFront;
    return 0;
}
