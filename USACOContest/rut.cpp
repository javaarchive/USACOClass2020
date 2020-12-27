#include <iostream>
#include <vector>
#include <string>
using namespace std;
struct Point{
    int x,y;
    bool equals(Point p2){
        return (x == p2.x) && (y == p2.y);
    }
};
struct DirVector{
    int x,y;
    char type;
    bool equals(DirVector dv){
        return (x == dv.x) && (y == dv.y);
    }
};

int N;
vector<int> table;
vector<DirVector> dirs;
vector<Point> points;
bool intersects(Point a, DirVector dv1, Point b, DirVector dv2){
    if(dv1.equals(dv2)){
        return (a.equals(b)); // Parellel so they never touch but if they are the same pos they instantly touch
    }
    // Most ugly code ever
    if(dv1.type == 'N' && dv2.type == 'S'){
        if(a.x != b.x){
            return false;
        }
        if(a.y <= b.y){
            return true;
        }
        return false;
    }
    
}
void solve(){

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
       dv.type = type;
       dirs[i] = dv;
   }
   solve();
   return 0;
}