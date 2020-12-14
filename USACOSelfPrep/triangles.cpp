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
map<int,int> X;
map<int,int> Y;
map<int,long long> sumsX;
map<int,long long> sumsY;
Point points[100001];
long long solve(){
    long long out = 0;
    for(int i = 0; i < N; i ++){
        Point p = points[i];
        
        long long a = 0;
        long long b = 0;
        long long Xsum = sumsX[p.y];
        long long Ysum = sumsY[p.x];
        a = (Xsum - (long long) ((X[p.x]) * (p.x)));
        b = (Ysum - (long long) ((Y[p.y]) * (p.y)));
        
        //cout << Xsum << " - (" << Y[i] << " * " << p.x << " ) " << endl;
        //cout << Ysum << " - (" << X[i] << " * " << p.y << " ) " << endl;
        cout << a << "*" << b << endl;
        /*if(X[p.y] <= 0 || Y[p.x] <= 0){
            continue;
        }*/
        
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
        sumsX[p.y] = 0;
        sumsY[p.x] = 0;
        X[p.x] = 0;
        Y[p.y] = 0;
        points[i] = p;
    }
    for(int i = 0; i < N; i ++){
        Point p = points[i];
        sumsX[p.y] += p.x;
        sumsY[p.x] += p.y;
        X[p.x] ++;
        Y[p.y] ++;
    }
    //sort(points, points + N);
    //for(int i = 0; i < N; i ++){
        //X[points[i].y] ++;
    //}
    //useX = false;
     //sort(points, points + N);
     //for(int i = 0; i < N; i ++){
       // Y[points[i].x] ++;
    //}
    
   
    cout << (long long) solve();
    return 0;
}