#include <iostream>
#include <bits/stdc++.h>

using namespace std;

vector<int> heights;

void solve(){
    int N;
    cin >> N;
    auto cmp = [](pair<int,int> a, pair<int,int> b) { 
        if(a.first == b.first){
            return a.second < b.second;
        }
        return a.first < b.first;
    };
    //    height pos
    set<pair<int,int>, decltype(cmp)> leftSet(cmp);
    set<pair<int,int>, decltype(cmp)> rightSet(cmp);
    for(int i = 0; i < N; i ++){
        int val;
        cin >> val;
        heights.push_back(val);
        rightSet.insert(make_pair(heights[i], i));
    }
    long long ans = 0;
    for(int i = 0; i < N; i ++){
        pair<int,int> me = make_pair(heights[i], i);

        rightSet.erase(me);

        // retrieve
        auto leftIter = leftSet.upper_bound(me);
        auto rightIter = rightSet.upper_bound(me);

        cout << "Processing " << i << " height " << heights[i] << endl;
         if(leftIter != leftSet.end()){
            pair<int,int> val = *leftIter;
            // cout << "To the left of " << i << " is " << val.second << " with height " << val.first << endl;
            ans += abs(i - val.second) + 1;
        }
        if(rightIter != rightSet.end()){
            pair<int,int> val = *rightIter;
            // cout << "To the right of " << i << " is " << val.second << " with height " << val.first << endl;
            ans += abs(i - val.second) + 1;
        }
        leftSet.insert(me);
    }
    cout << ans << endl;
}

int main(int argc, char const *argv[])
{
    solve();
    return 0;
}
