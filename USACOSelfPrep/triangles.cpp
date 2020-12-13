// C++ Usaco triangles t
#include <vector>
#include <map>
#include <iostream>
#include <algorithm>

using namespace std;
int N;
bool useX = true;
struct Point{
    int x;
    int y;
    bool operator< (const Point &other) const {
        if(useX){
            return x < other.x;
        }else{
            return y < other.y;
        }
    }
};
void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}
map<int,vector<int>> X;
map<int,vector<int>> Y;
Point points[100001];
long long solve(){
    long long out = 0;
    for(int i = 0; i < N; i ++){
        Point p = points[i];
        
        long long a = 0;
        long long b = 0;
        vector<int> vx = X[p.y];
        vector<int> vy = Y[p.x];
        if(vx.size() <= 1 || vy.size() <= 1){
            continue;
        }
        for(auto j = vx.begin(); j != vx.end(); j ++){
            a += (long long) abs(*j - p.x);
        }
        for(auto j = vy.begin(); j != vy.end(); j ++){
            b += (long long) abs(*j - p.y);
        }//              123456789
        out += ((long long)(a * b))%(1000000000 + 7);
        //           123456789
        out = out %(1000000000 + 7);
    }
    return out;
}

int main(int argc, const char** argv) {
    setIO("triangles");
    cin >> N;
    for(int i = 0; i < N; i ++){
        Point p;
        cin >> p.x >> p.y;
        p.x += 10001;
        p.y += 10001;
        points[i] = p;
    }
    //sort(points, points + N);
    for(int i = 0; i < N; i ++){
        X[points[i].y].push_back(points[i].x);
    }
    useX = false;
     //sort(points, points + N);
     for(int i = 0; i < N; i ++){
        Y[points[i].x].push_back(points[i].y);
    }
    
   
    cout << (long long) solve();
    return 0;
}