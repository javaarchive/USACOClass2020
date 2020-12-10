#include <iostream>
#include <vector>
#include <iomanip>

using namespace std;
int N;
vector<pair<int,int>> coords;
double graph[151][151];
int main(int argc, const char** argv) {
    cin >> N;
    for(int i = 0; i < N; i ++){
        pair<int,int> p;
        cin >> p.first >> p.second;
        coords.push_back(p);
    }
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < N; j ++){
            graph[i][j] = INT32_MAX/2;
        }
    }
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < N; j ++){
            int state;
            cin >> state;
            
        }
    }

    return 0;
}