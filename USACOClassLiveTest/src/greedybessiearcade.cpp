#include <iostream>
#include <algorithm>
#define MAXN 101 
using namespace std;

int N, T;
pair<int,int> games[MAXN]; // prize/cost

int main(int argc, const char** argv) {
    cin >> N >> T;
    for(int i = 0; i < N; i ++){
        cin >> games[i].first >> games[i].second;
    }
    sort(games, games + N, [](pair<int, int> a, pair<int,int> b){
        int ratioA = a.first/a.second;
        int ratioB = b.first/b.second;
        return ratioA > ratioB;
    });
    
    return 0;
}