#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
int N;
struct Point{
    int x,y;
    bool equals(Point p2){
        return (x == p2.x) && (y == p2.y);
    }
    bool operator<( const Point& pointB){
        if (x < pointB.x) return true;
        if (x > pointB.x) return false;
        if (y < pointB.y) return true;
        return false;
    }
    bool operator()(const Point& pointA, const Point& pointB) const{
        if (pointA.x < pointB.x) return true;
        if (pointA.x > pointB.x) return false;
        if (pointA.y < pointB.y) return true;
        return false;
    }
};
struct DirVector{
    int x = 0,y = 0;
    char type;
    bool equals(DirVector dv){
        return (x == dv.x) && (y == dv.y);
    }
};
vector<Point> east;
vector<Point> north;
vector<int> table;
bool sortX(Point p1, Point p2){
    return p1.x < p2.x;
}
bool sortY(Point p1, Point p2){
    return p1.y < p2.y;
}
void solve(){
    sort(east.begin(),east.end(), sortY);
    sort(north.begin(),north.end(), sortX);
    vector<bool> stopped;
    stopped.resize(N);
    for(int i = 0; i < east.size(); i ++){
        for(int j = 0; j < north.size(); j ++){ // Start with closest few
            Point intersection;
            if(stopped[i] || stopped[j]){
                continue; // They do not touch
            }
            intersection.x = east[i].x;
            intersection.y = north[i].y;
        }
    }
}
int main(int argc, const char** argv) {
    cin >> N;
    table.resize(N);
    for(int i = 0; i < N; i ++){
        char dtype;
        Point p;
        cin >> dtype;
        cin >> p.x >> p.y;
        if(dtype == 'E'){
            east.push_back(p);
        }else{
            north.push_back(p);
        }
    }
    solve();
    
    for(int i = 0; i < N; i ++){
       cout << table[i] << endl;
   }

    return 0;
}