#include <iostream>
#include <map>
#include <utility>
#include <vector>
#include <algorithm>
#include <set>
#include <limits>

using namespace std;

int maxp = INT32_MIN;
int minp = INT32_MAX;
vector<pair<int,int>> cows;
int N;
int totalTypes;
set<int> typeset;
bool works(int range){
    int types = 0;
    map<int,int> freq;
    //freq[0] ++;
    int toprange = 0;
    //cout << "INIT " << freq[0] << endl; 
    for(int i = 0; i < cows.size(); i ++){
        int bottom = cows[i].first;
        if(i!=0){
            int cowRemove = cows[i - 1].second;
            if(freq[cowRemove] == 1){
                //cout << "Remove bottom out of bounds" << endl;
                types --;
            }
            freq[cowRemove] --;
        }
        for(;toprange < N; toprange ++){
            //cout << cows[toprange].first << "top > bottom "<< bottom << endl;
            if((cows[toprange].first - bottom) <= range){
                freq[cows[toprange].second] ++;
                if(freq[cows[toprange].second] == 1){
                    //cout << "Add new toprange" << endl;
                    types ++;
                }
                
            }else{
                break;
            }
        }
        //cout << types << " tr: "<< toprange <<  endl;
        if(totalTypes == types){
            return true;
        }
    }
    return false;
}
bool cmp(const pair<int, int>&i, const pair<int, int>&j) 
{ 
    return i.first< j.first; 
} 
int solve(){
    sort(cows.begin(), cows.end(), &cmp);
    int l = 1;
    int r = maxp - minp;
    int m;
    while(l <= r){
        m = l + (r - l)/2;
        //cout << l << " " << m << " " << r << endl;
        bool mWorks = works(m);
        bool bWorks = (m != 0) && works(m - 1);
        //cout << bWorks << endl;
        if(mWorks && !bWorks){
            return m;
        }else if(mWorks && bWorks){
            r = m + 1;
        }else if(mWorks){
            r = m + 1;
        }else{
            l = m - 1;
        }
    }
    return 50000;
}
int main(int argc, const char** argv) {
    cin >> N;
    int a,b;
    for(int i = 0; i < N; i ++){
        cin >> a >> b;
        maxp = max(maxp, a);
        minp = min(minp, a);
        cows.push_back(make_pair(a,b));
        typeset.insert(b);
    }
    totalTypes = typeset.size();
    cout << solve();
    return 0;
}