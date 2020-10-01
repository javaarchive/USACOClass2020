#include <string>
#include <fstream>
#include <vector>
#include <algorithm>
#include <iostream>
#include <deque>
#include <bits/stdc++.h>
using namespace std;
const int MAXN = 500001;
struct LoudCow{
    int height;
    int volume;
    //bool operator() (LoudCow lc1, LoudCow lc2) {return lc1.pos < lc2.pos;}
};

int N;

bool cmp(LoudCow lc1, LoudCow lc2) 
{ 
    return (lc1.height < lc2.height);
} 
void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}

deque <LoudCow> lcs; 
int hearingLeft[50001] = {0};
int hearingRight[50001] = {0};
LoudCow cows[50001]; 
//LoudCow Scows[50001];

int solve(){
    //copy(cows, cows + N, Scows);
    //sort(Scows, Scows + N, &cmp);
    //cout << "Initial Cows in cows array" << endl;
    /*for(int i = 0; i < N; i ++){
        cout << cows[i].height << endl;
    }*/
    LoudCow maxcow;
    maxcow.height --;
    maxcow.volume --;
    int maxcowpos = -1;
    for(int i = 0; i < N; i ++){
        if(cows[i].height > maxcow.height){
            maxcow = cows[i];
            maxcowpos = i;
        }
        hearingRight[i] = maxcowpos;
    }
    maxcowpos = -1;
    maxcow = LoudCow();
    maxcow.volume --;
    maxcow.height --;
    for(int i = N - 1; i >= 0; i --){
        if(cows[i].height > maxcow.height){
            maxcow = cows[i];
            maxcowpos = i;
        }
        hearingLeft[i] = maxcowpos;
    }
    
    int volumes[MAXN] = {0};
    for(int i = 0; i < N; i ++){
        int curVol = cows[i].volume;
        int a = hearingLeft[i];
        int b = hearingRight[i];
        if(a != i){
            volumes[a] += curVol;
        }
        if(b != i && b != a){
            volumes[b] += curVol;
        }
    }
    sort(volumes, volumes + N);
    /*for(int i = 0; i < N; i ++){
        cout << volumes[i] << " ";
    }
    cout << endl;
    //std::cout << volumes[0] << " and " << volumes[N - 1];
    for(int i = 0; i < N; i ++){
        cout << volumes[i] << " ";
    }*/
    return volumes[N - 1];
}



int main() {
    // setIO("moooo")
    // Results of annoying debugging
    // v         v 
    //cout << "Test";
    cin >> N;
    //cout<<N<<endl;
    for(int i = 0; i < N; i ++){
        cin >> cows[i].height >> cows[i].volume;
        //cout << cows[i].pos <<endl;
    }
    std::cout << solve();
    return 0;
}
