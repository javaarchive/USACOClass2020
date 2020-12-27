#include <iostream>
#include <vector>
#include <string>
#include <set>
#include <map>
using namespace std;
struct Point{
    int x,y;
    bool equals(Point p2){
        return (x == p2.x) && (y == p2.y);
    }
};
struct DirVector{
    int x = 0,y = 0;
    char type;
    bool equals(DirVector dv){
        return (x == dv.x) && (y == dv.y);
    }
};

int N;
vector<int> table;
vector<DirVector> dirs;
vector<Point> points;
map<Point,set<int>> intersecting;
pair<bool,Point> intersects(Point a, DirVector dv1, Point b, DirVector dv2){
    cout << dv1.type << dv2.type << endl;
    //cout << dv1.x << " " << dv1.y << " " << dv2.x << " " << dv2.y << endl;
    Point p;
    if(dv1.equals(dv2)){
        //cout << "Parellel" << endl;
        return make_pair(a.equals(b),p); // Parellel so they never touch but if they are the same pos they instantly touch
    }
    // Hacky way to check if intersects
    int minx = -1, miny = -1;
    int maxx = INT32_MAX, maxy = INT32_MAX;
    int requiredX = -1, requiredY = -1;
    if(dv1.type == 'N'){
        miny = a.y;
        requiredX = a.x;
    }else if(dv1.type == 'S'){
        maxy = a.y;
        requiredX = a.x;
    }else if(dv1.type == 'E'){
        minx = a.x;
        requiredY = a.y;
    }else if(dv1.type == 'W'){
        maxx = a.x;
        requiredX = a.y;
    }
    cout << "mxy one : " << minx << " " << miny << " " << maxx << " " << maxy << endl;
    if(dv2.type == 'N'){
        miny = a.y;
        requiredX = b.x;
    }else if(dv2.type == 'S'){
        maxy = a.y;
        requiredX = b.x;
    }else if(dv2.type == 'E'){
        minx = a.x;
        requiredY = b.y;
    }else if(dv2.type == 'W'){
        maxx = a.x;
        requiredY = b.y;
    }
    cout << "mxy two: " << minx << " " << miny << " " << maxx << " " << maxy << endl;
    cout << "required X: " << requiredX << " required Y: " << requiredY << endl;
    p.x = requiredX;
    p.y = requiredY;
    return make_pair((minx <= maxx) && (miny <= maxy), p);
}
void solve(){
    for(int i = 0; i < N; i ++){
        for(int j = i + 1; j < N; j ++){
            //cout << i << " " << j << endl;
            //cout << dirs[i].x << " " << dirs[i].y << " " << dirs[j].x << " " << dirs[j].y << endl;
            pair<bool, Point> output = intersects(points[i], dirs[i], points[j], dirs[j]);
            if(output.first){
                Point intersection = output.second;
                intersecting[intersection].insert(i);
                intersecting[intersection].insert(j);
            }
        }
    }
    
}
int main(int argc, const char** argv) {
   cin >> N;
   table.resize(N);
   dirs.resize(N);
   points.resize(N);
   for(int i = 0; i < N; i ++){
       char type;
       Point p;
       cin >> type;
       cin >> p.x >> p.y;
       points[i] = p;
       DirVector dv;
       if(type == 'E'){
           dv.x = 1;
       }else if(type == 'N'){
           dv.y = 1;
       }else if(type == 'S'){
           dv.y = -1;
       }else if(type == 'W'){
           dv.x = -1;
       }
       //cout << i << " " << dv.x << "," << dv.y << endl;
       dv.type = type;
       dirs[i] = dv;
   }
   solve();
   return 0;
}