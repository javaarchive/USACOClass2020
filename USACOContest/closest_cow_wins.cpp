#include <iostream>
#include <bits/stdc++.h>
using namespace std;
#define OFFSET 1000000001

vector<pair<int,int>> patches; // taste first for sorting
vector<pair<int,int>> patches_by_loc; 
vector<pair<int,int>> unfiltered_patches; // taste first for sorting
vector<int> oppositions;
set<int> opSet;
set<float> testedPositons;
int K, M, N;


int closestOpposition(int pos){
    set<int>::iterator lowIter = opSet.lower_bound(pos);
    lowIter --;
    set<int>::iterator highIter = opSet.upper_bound(pos);
    if(lowIter == opSet.end()){
        cout << "lowiter not def " << endl;
        return *highIter;
    }else if(highIter == opSet.end()){
        cout << "highiter not def " << endl;
        return *lowIter;
    }
    int low = *lowIter;
    int high = *highIter;
    return (pos - low < high - pos) ? low : high;
}

float mid(float a, float b){
    return (a + b) / 2;
}

int lessThan(int val){
    // Binary search rightmost
    int low = 0;
    int high = K;
    while(low < high){
        int mid = (low + high) / 2;
        if(patches_by_loc[mid].second > val){
            high = mid;
        }else{
            low = mid + 1;
        }
    }
    return low - 1;
}

int moreThan(int val){
    // Binary search leftmost
    int low = 0;
    int high = K;
    while(low < high){
        int mid = (low + high) / 2;
        if(patches_by_loc[mid].second < val){
            low = mid + 1;
        }else{
            high = mid;
        }
    }
    return high;
}

int withinRange(float min, float max){
    if(min == max){
        return 0;
    }
    return (lessThan() + 1);
}


void printpArr(vector<pair<int,int>> parr){
    for(int i = 0; i < parr.size(); i++){
        cout << "(" << parr[i].first << " p:" << parr[i].second << ") ";
    }
    cout << endl;
    return;
}

void solve(){
    cin >> K >> M >> N;
    
    for(int i = 0; i < K; i ++){
        int pos, taste;
        cin >> pos >> taste;
        unfiltered_patches.push_back(make_pair(taste,pos));
    }
    
    
    for(int i = 0; i < M; i ++){
        int pos;
        cin >> pos;
        oppositions.push_back(pos);
        opSet.insert(pos);
    }
    sort(oppositions.begin(),oppositions.end());

    for(int i = 0; i < unfiltered_patches.size(); i ++){
        if(opSet.find(unfiltered_patches[i].second) == opSet.end()){
            // if position is not directly occupied
            patches.push_back(unfiltered_patches[i]);
            patches_by_loc.push_back(unfiltered_patches[i]);
        }else{
            K --; // pretend patch does not exist
        }
    }

    sort(patches.begin(),patches.end());
    reverse(patches.begin(),patches.end());
    sort(patches_by_loc.begin(),patches_by_loc.end(),[](pair<int,int> a, pair<int,int> b){
        return a.second < b.second;
    });
    // pair<int,int> singleKnockoutBest = patches[0];
    // cout << "Promising patch taste " << patches[0].first << " at position " << patches[0].second << endl; 
    // vector<int> doubleKnockoutAttempts;
    printpArr(patches_by_loc);
    // cout << "Closest opposition to 14 " << closestOpposition(14) << endl; 
    // cout << "Closest opposition to 6 " << closestOpposition(6) << endl; 
}

int main(int argc, char const *argv[])
{
    solve();   
    return 0;
}
