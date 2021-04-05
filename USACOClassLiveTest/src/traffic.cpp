#include <iostream>
#include <vector>
#include <deque>
using namespace std;

int N,T;
deque<pair<int,int>> cows;
bool willCollide(pair<int,int> a, pair<int,int> b){
    if(a.second == b.second){
        return false;
    }else if(a.first == b.first){
        return true;
    }
    // Assume the cows keep going by themseleves without colliding
    long long endA = ((long long) a.first) + ((long long) T) *  ((long long) a.second);
    long long endB = ((long long) b.first) + ((long long) T) * ((long long) b.second);
    return (a.first < b.first && endA >= endB); // || (a.first > b.first && endB <= endA);
}
void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}
int main(int argc, const char** argv) {
    setIO("cowjog");
    cin >> N >> T;
    for(int i = 0; i < N; i ++){
        pair<int,int> p;
        cin >> p.first >> p.second;
        cows.push_back(p);
    }
    int clustersCount = 0;
    // vector<pair<int,int>> clusters;
    while(!cows.empty()){
        if(cows.size() == 1){
            clustersCount ++;
            // clusters.push_back(cows.front());
            cows.pop_front(); // Remove last item
            break;
        }
        pair<int,int> a = cows.front(); // remove left cow
        cows.pop_front();
        pair<int,int> b = cows.front(); // the one usually farther forward in the road
        cows.pop_front();
        if(willCollide(a,b)){
            // Merge
            // cout << "merging " << a.first << " " << a.second << " , " << b.first << " " << b.second << endl;
            pair<int,int> merged = make_pair(b.first, min(a.second,b.second)); // second position, SLOWEST speed
            cows.push_front(merged);
        }else{
            // cout << "dropping " << a.first << " " << a.second << " , " << b.first << " " << b.second << endl;
            clustersCount ++;
            // clusters.push_back(a);
            cows.push_front(b); // put back b
        }
    }
    cout << clustersCount << endl;
    return 0;
}