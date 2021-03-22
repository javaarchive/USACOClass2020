#include <iostream>
#include <set>
#include <vector>
#include <algorithm>
using namespace std;
int N,K;
set<int> blockPos;
int main(int argc, const char** argv) {
    cin >> N >> K;
    int output = 0;
    for(int i = 0; i < N; i ++){
        int year;
        cin >> year;
        blockPos.insert((year-1)/12 + 1);
        output = max(output,((year-1)/12 + 1) * 12);
    }
    int startpoint = 0;
    vector<int> gapLengths;
    for(auto iter = blockPos.begin(); iter != blockPos.end(); iter ++){
        gapLengths.push_back((*iter - startpoint - 1) * 12);
        startpoint = *iter;
    }
    sort(gapLengths.begin(), gapLengths.end());
    reverse(gapLengths.begin(), gapLengths.end());
    for(int i = 0; i < K - 1; i ++){
        output -= gapLengths[i];
    }
    cout << output;
    return 0;
}