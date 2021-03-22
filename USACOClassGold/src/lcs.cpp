#include <iostream>
#include <vector>
using namespace std;
int main(int argc, const char** argv) {
    string s1,s2;
    cin >> s1;
    cin >> s2;
    int N = s1.size();
    int M = s2.size();
    
    vector<pair<int,int>> lastRow; // (index, value) of last row used for dp appending
    for(int i = 0; i < M; i ++){
        int posInLastRow = -1;
        for(int j = 0; j < N; j ++){
            
        }
    }
    return 0;
}