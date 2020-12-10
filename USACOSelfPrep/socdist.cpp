// Social Distancing C Plus Plus
#include <fstream> 
#include <iostream> 
#include <string> 
#include <vector>
#include <utility> 
#include <algorithm>

using namespace std;

int N, M;
void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}
vector<pair<int,int>> intervals;
bool tryPlaceWithD(int D){
    if(D == 0){
        return true;
    }
    int lastPlacedPos = 0;
    int cowsPlaced = 0;
    for(int i = 0; i < M; i ++){
        int startPoint = max(lastPlacedPos, intervals[i].first);
        int avalibleSection = intervals[i].second - startPoint;
        if(avalibleSection < 0){
            continue;
        }
        int cowsToPlace = avalibleSection/D + 1;
        lastPlacedPos = startPoint + ((cowsToPlace)  * D) ; // Set new placement pos
        //cout << "Placing " << cowsToPlace << " cows in interval " << intervals[i].first << " ---- " << intervals[i].second << " STP: " << startPoint << " new last placed pos: " << lastPlacedPos << endl;  
        cowsPlaced += cowsToPlace;
        if(cowsPlaced >= N){
            return true;
        }
    }
    //if(cowsPlaced < N){
        return false;
    //}
    //return true;
}
int solve(){
    int l,r,m;
    r = 100000;
    l = 0;
    while(l <= r){
        m = l + (r - l)/2;
        //cout << "BS: " << l << " " << m << " " << r << endl;
        if((r-l) == 1){
            if(tryPlaceWithD(r)){
                return r;
            }else if(tryPlaceWithD(l)){
                return l;
            }else{
                return -1;
            }
        }
        bool result = tryPlaceWithD(m);
        //cout << "Result: " << result << endl;
        if(result){
            l = m;
        }else{
            r = m;
        }
        if(l == r){
            return l;
        }
        
    }
    return m;
}

int main(int argc, const char** argv) {
    setIO("socdist");
    cin >> N >> M;

    
    for(int i = 0; i < M; i ++){
        pair<int,int> p;
        cin >> p.first >> p.second;
        intervals.push_back(p);
    }
    sort(intervals.begin(), intervals.end()); 
    cout << solve() << endl;
    
    
    return 0;
}