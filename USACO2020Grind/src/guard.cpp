#include <iostream>
#include <algorithm>
#define MAXN 21
using namespace std;
void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}

struct FrisCow{
    int height;
    int weight;
    int strength;
};

FrisCow cows[MAXN];
int N, H;
int main(int argc, char const *argv[])
{
    setIO("guard");
    cin >> N >> H;
    for(int i = 0; i < N; i ++){
        cin >> cows[i].height >> cows[i].weight >> cows[i].strength;
    }
    
    // algo


    sort(cows, cows + N, [](FrisCow a, FrisCow b){
        if(a.strength == b.strength){
            if(a.weight == b.weight){
                return a.height > b.height;
            }
            return a.weight > b.weight;
        }
        return a.strength > b.strength;
    });
    int maxTotalWeight = INT32_MAX;
    int curTotalWeight = 0;
    int curHeight = 0;
    for(int i = 0; i < N; i ++){
        //cout << "Trying/Adding " << cows[i].height << " " << cows[i].weight << " " << cows[i].strength << endl;
        //cout << "Current max total weight " << maxTotalWeight << " compare (" << cows[i].weight << " + " << curTotalWeight << " ) " << endl; 
        if((cows[i].weight + curTotalWeight) > maxTotalWeight){
            break;
        }
        maxTotalWeight = min(maxTotalWeight, curTotalWeight + cows[i].weight + cows[i].strength);
        curHeight += cows[i].height;
        curTotalWeight += cows[i].weight;
    }
    if(curHeight < H){
        cout << "Mark is too tall" << endl;
    }
    // cout << maxTotalWeight << " - " << curTotalWeight << endl;
    cout << (maxTotalWeight - curTotalWeight) << endl;

    return 0;
}
