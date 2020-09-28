#include <string>
#include <fstream>
#include <vector>
#include <algorithm>
#include <iostream>
#include <deque>
#include <bits/stdc++.h>
using namespace std;
const int MAXN = 50001;
struct LoudCow{
    int volume;
    int pos;
    //bool operator() (LoudCow lc1, LoudCow lc2) {return lc1.pos < lc2.pos;}
};

int N;

bool cmp(LoudCow lc1, LoudCow lc2) 
{ 
    return (lc1.pos < lc2.pos);
} 
void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}

deque <LoudCow> lcs; 
int hearingLeft[50001];
int hearingRight[50001];
LoudCow cows[50001];
void solve(){
    sort(cows, cows + N, &cmp);
    cout << "Initial Cows in cows array" << endl;
    for(int i = 0; i < N; i ++){
        cout << cows[i].pos << endl;
    }
    lcs.push_front({-1,-1}); // Cow to be removed
    for(int i = 0 ; i < N; i ++){
        lcs.pop_front();
        for(int j = i;  j < N; j ++){
            if((cows[j].pos - cows[i].pos) <= cows[i].volume){
                lcs.push_back(lcs[j]);
            }else{
                break;
            }
        }
        cout << "ITER: " << i  << endl;
        for(auto it = lcs.begin(); it != lcs.end(); ++it){
            cout << "Pos: " << it->pos << " Vol: "<< it->volume << endl;
        }
    }
}



int main() {
    // setIO("moooo")
    // Results of annoying debugging
    // v         v 
    //cout << "Test";
    cin >> N;
    //cout<<N<<endl;
    for(int i = 0; i < N; i ++){
        cin >> cows[i].pos >> cows[i].volume;
        //cout << cows[i].pos <<endl;
    }
    solve();
    return 0;
}
