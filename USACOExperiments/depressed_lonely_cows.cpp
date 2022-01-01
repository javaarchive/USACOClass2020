#include <bits/stdc++.h>

// Testing program to test annotations

using namespace std;

vector<int> Gs;
vector<int> Hs;

int calc(vector<int>& letterPoses){
    int out = 0;
    for(int i = 0; i < letterPoses.size(); i ++){
        int bottom = letterPoses[i] - 1;
        int top = letterPoses[i] + 1; 
        // out of bounds
        if(i > 0){
            bottom = letterPoses[i - 1];
        }
        if(i < letterPoses.size() - 1){
            top = letterPoses[i + 1];
        }
        bool bottomEnabled = (letterPoses[i] - bottom) > 1;
        bool topEnabled = (top - letterPoses[i]) > 1;

        cout << bottom << " < " << letterPoses[i] << " < " << top << endl;

        out += (letterPoses[i] - bottom) * (top - letterPoses[i]);
        cout << "+= " << (letterPoses[i] - bottom) << " * " << (top - letterPoses[i]) << endl;
        out -= 1; // single
        if(bottomEnabled){
            out -= 1;
        }
        if(topEnabled){
            out -= 1;
        }
    }
    return out;
}

void solve(){
    int N;
    cin >> N;
    string line = "";
    cin >> line;
    for(int i = 0; i < N; i ++){
        char c = line[i];
        if(c == 'G'){
            Gs.push_back(i);
        }else{
            Hs.push_back(i);
        }
    }
    int ans = 0;
    cout << "Gs" << endl;
    int gRes = calc(Gs);;
    ans += gRes;
    cout << "Hs" << endl;
    int hRes = calc(Hs);
    ans += hRes;
    cout << "G: " << gRes << " H: " << hRes << endl;
}

int main(int argc, char const *argv[])
{
    solve();    
    return 0;
}
