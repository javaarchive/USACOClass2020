#include <iostream>
#include <vector>
#include <deque>
using namespace std;

int N,T;
deque<pair<int,int>> cows;
bool willCollide(pair<int,int> a, pair<int,int> b){
    int endA = a.first + T * a.second;
    int endB = b.first + T * b.second;
    return (a < b && endA >= endB) || (a > b && endB <= endA);
}
int main(int argc, const char** argv) {
    cin >> N >> T;
    for(int i = 0; i < N; i ++){
        pair<int,int> p;
        cin >> p.first >> p.second;
        cows.push_back(p);
    }
    vector<pair<int,int>> clusters;
    while(!cows.empty()){
        if(cows.size() == 1){
            clusters.push_back(cows.front());
            cows.pop_front();
            break;
        }
        pair<int,int> a = cows.front();
        cows.pop_front();
        pair<int,int> b = cows.front(); // the one usually farther forward in the road
        cows.pop_front();
        if(willCollide(a,b)){
            // Merge
            cout << "merging " << a.first << " " << a.second << " , " << b.first << " " << b.second << endl;
            pair<int,int> merged = make_pair(b.first, min(a.second,b.second)); // second position, SLOWEST speed
            cows.push_front(merged);
        }else{
            cout << "dropping " << a.first << " " << a.second << " , " << b.first << " " << b.second << endl;
            clusters.push_back(a);
            cows.push_front(b); // put back a
        }
    }
    cout << clusters.size() << endl;
    return 0;
}