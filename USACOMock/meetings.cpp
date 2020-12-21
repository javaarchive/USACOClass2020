#include <iostream>
#include <set>
#include <algorithm>

using namespace std;
int N,L;
void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}
bool willMeetAt(int T, int x1, int x2){
    return ((x1 + 2*T) >= x2);
}
struct Cow{
    bool movingLeft;
    int pos;
    int weight;
};
Cow cows[50001];
Cow nCows[50001];
int main(int argc, const char** argv) {
    setIO("meetings");
    cin >> N >> L;
    int z = 0;
    for(int i = 0; i < N; i ++){
        int dir;
        cin >> cows[i].weight >> cows[i].pos >> dir;
        cows[i].movingLeft = (dir == -1);
        if(cows[i].movingLeft){
            z ++;
        }
        //completionTimes.insert(cows[i].movingLeft?cows[i].pos:L-cows[i].pos);
    }
    sort(cows,cows + N, [](Cow a, Cow b){
        return a.pos < b.pos;
    });
    int leftCursor = 0;
    for(int i = 0; i < N; i ++){
        if(cows[i].movingLeft){
            nCows[i].movingLeft = cows[i].movingLeft;
            nCows[i].weight = cows[i].weight;
            nCows[i].pos = cows[leftCursor].pos;
            leftCursor ++;
        }
    }
    int rightCursor = N - 1;
    for(int i = 0; i < N; i ++){
        if(!cows[i].movingLeft){
            nCows[i].movingLeft = cows[i].movingLeft;
            nCows[i].weight = cows[i].weight;
            nCows[i].pos = cows[rightCursor].pos;
            rightCursor --;
        }
    }
    sort(nCows, nCows + N, [](Cow a,Cow b){
        int na = a.movingLeft?a.pos:L-a.pos;
        int nb = b.movingLeft?b.pos:L-b.pos;
        return na < nb;
    })
    return 0;
}