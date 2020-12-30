#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>

using namespace std;
int N;
struct Point{
    int x,y,id;
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
int XYdist(Point p, Point p2){
    return abs(p.x - p2.x) + abs(p.y - p2.y);
}
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
    vector<int> stoppedByID;
    stoppedByID.resize(N);
    for(int i = 0; i < N; i ++){
        stoppedByID[i] = -1;
    }
    stopped.resize(N);
    
    for(int i = 0; i < east.size(); i ++){
        bool breakOuter = false;
        for(int j = 0; j < north.size(); j ++){ // Start with closest few
         // cout << "Checking: " << east[i].x << "," << east[i].y << " and " << north[j].x << "," << north[j].y << endl;
            Point intersection;
            if(stopped[east[i].id]){
                break; // PRUNE!
                breakOuter = true;
            }
            if(stopped[north[j].id]){
                break;; // They do not touch
            }
            if(east[i].y < north[j].y){
                continue; // They will not hit
            }
            if(north[j].x < east[i].x){
                continue; // They will also not hit
            }
            intersection.x = north[j].x;
            intersection.y = east[i].y;
            int iDist = XYdist(intersection, east[i]);
            int jDist = XYdist(intersection, north[j]);
            //cout << "east dist: " << iDist << endl;
            //cout << "north dist: " << jDist << endl;
            if(iDist == jDist){
                continue;
            }
            if(iDist < jDist){
                //cout << "Blaming east " << east[i].id << endl;
                //table[east[i].id] ++;
                stopped[north[j].id] = true;
                stoppedByID[north[j].id] = east[i].id;
                 
            }else{
                //cout << "Blaming north " << north[j].id << endl;
                //table[north[j].id] ++;
                stopped[east[i].id] = true;
                stoppedByID[east[i].id] = north[j].id;
                
            }
            //cout << east[i].x << "," << east[i].y << " intersecting with " << north[j].x << "," << north[j].y << endl;
            
           
        }
        if(breakOuter){
            break;
        }
    }
    //vector<bool> visited;
    //visited.resize(N);
    for(int i = 0; i < N; i ++){
        if(stoppedByID[i] < 0){
            continue;
        }
        stack<int> next;
        next.push(stoppedByID[i]);
        while(!next.empty()){
            int node = next.top();
            next.pop();
            table[node] ++;
            if(stoppedByID[node] >= 0){
                next.push(stoppedByID[node]);
            }
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
        p.id = i;
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