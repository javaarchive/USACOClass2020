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
    stack<LoudCow> stk;
    for(int i = 0; i < N; i ++){
        if(stk.empty() || stk.top().height > cows[i].height){
            stk.push(cows[i]);
        }else{
            while(stk.top().height < cows[i].height){
                stk.pop();
            }
        }
    }
   return 0;
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
