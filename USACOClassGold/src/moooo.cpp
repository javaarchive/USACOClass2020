#include <string>
#include <fstream>
#include <vector>
#include <algorithm>
#include <iostream>
#include <stack>
#include <bits/stdc++.h>


using namespace std;
const int MAXN = 500001;
struct LoudCow{
    int height;
    int volume;
    int index;
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

int hearingLeft[MAXN] = {0};
int hearingRight[MAXN] = {0};
LoudCow cows[MAXN]; 
//LoudCow Scows[50001];

int solve(){
    //copy(cows, cows + N, Scows);
    //sort(Scows, Scows + N, &cmp);
    //cout << "Initial Cows in cows array" << endl;
    /*for(int i = 0; i < N; i ++){
        cout << cows[i].height << endl;
    }*/
    stack<LoudCow> stk;
    int volumes[MAXN] = {0};
    int maxVol = 0;
    for(int i = 0; i < N; i ++){
        while(!stk.empty() && stk.top().height < cows[i].height){
            volumes[i] += stk.top().volume; 
            stk.pop();
        }
        //volumes[stk.top().]
        stk.push(cows[i]);
    }
    stack<LoudCow> stk2;
    for(int i = N - 1; i >= 0; i --){
        while(!stk2.empty() && stk2.top().height < cows[i].height){
            volumes[i] += stk2.top().volume; 
            stk2.pop();
        }
        maxVol = max(volumes[i], maxVol);
        //volumes[stk.top().]
        stk2.push(cows[i]);
    }

    //sort(volumes, volumes + N);
    
   return maxVol;
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
        cows[i].index = i;
        //cout << cows[i].pos <<endl;
    }
    std::cout << solve();
    return 0;
}
