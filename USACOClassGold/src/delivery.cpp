#include <iostream>
#include <queue>
#include <map>
#include <set>
#include <utility>

#define VLIMIT 31
#define GLIMIT 505

using namespace std;

int N;
int N2;
pair<int,int> points[101];
pair<int,int> graph[GLIMIT];
pair<int, int> dxdy[] = { make_pair(0, 1), make_pair(0,-1), make_pair(1,0),make_pair(-1,0) };
map<pair<int,int>, int> decoder;
map<int,int> IDTonode;

const int OFFSET = 100;
bool possible(pair<int,int> a, pair<int,int> b){
    bool firstWorks = true;
    int x1 = min(a.first, b.first),x2 = max(a.first,b.first);
    int y1 = min(a.second, b.second),y2 = max(a.second,b.second);
    for(int i = 0; i < N; i ++){
        pair<int,int> p = points[i];
        if(p.first == a.first && p.second == a.second){
            continue;
        }
        if(p.second == a.second && (x1 <= p.first && p.first <= x2)){
            firstWorks = false;
            break;
        }
        if(p.first == b.first && (y1 <= p.second && p.second <= y2)){
            firstWorks = false;
            break;
        }
    }
    if(firstWorks){
        return true;
    }
    bool secondWorks = true;
     for(int i = 0; i < N; i ++){
        pair<int,int> p = points[i];
        if(p.first == a.first && p.second == a.second){
            continue;
        }
        if(p.first == a.first && (y1 <= p.second && p.second <= y2)){
            secondWorks = false;
            break;
        }
        if(p.second == b.second && (x1 <= p.first && p.first <= x2)){
            secondWorks = false;
            break;
        }
    }


    return secondWorks;
}
int dijkstra(int node,pair<int,int> src, pair<int,int> to) {
    cout << "DIJ(" << node << ",("<<src.first<<","<<src.second<<"),("<<to.first<<","<<to.second<<"))" << endl;
    int minDist[GLIMIT];
    fill(minDist, minDist + GLIMIT,INT32_MAX);
    minDist[node] = 0;
    //              dist     point
    priority_queue<pair<int,pair<int,int>>> pq;
    pq.push(make_pair(0, src));
    while(!pq.empty()){
        int dist = pq.top().first;
        pair<int,int> coord = pq.top().second;
        cout << "PROC: " << dist << " x: " << coord.first << " y: " << coord.second << endl;
        if(coord.first == to.first && coord.second == to.second){
            return dist;
        }
        pq.pop();
        for(int i = 0; i < N2; i ++){
            if(graph[i].first = coord.first && graph[i].second == coord.second){
                //cout << "Condition 1 " << i << endl;
                continue;
            }
            if(decoder.find(coord) == decoder.end() && !(graph[i].first == to.first && graph[i].second == to.second)){
                //cout << "Condition 2 " << i << endl;
                continue;
            }
            int taxiDist = abs(graph[i].first - coord.first) + abs(graph[i].second - coord.second);
            if(taxiDist == 0){
                //cout << "Condition 3" << i << endl;
                continue;
            }
            int newDist = dist + taxiDist;
            if(possible(coord, graph[i]) && newDist < minDist[i]){
                minDist[i] = newDist;
                pq.push(make_pair(newDist,graph[i]));
            }
        }
    }
    for(int i = 0 ; i < N2; i ++){
        cout << "mindist ("<<i << ") : " << minDist[i] << endl;
    }
    return -1; // TODO: Change
}
int solve() {
    int output = 0;
    for (int i = 0; i < N; i++) {
        int nextNode = (i + 1) % N; // Loop back trick
        //cout << "DIJKSTRA";
        int dist = dijkstra(IDTonode[i], points[i], points[nextNode]);
        cout << i << " -> " << nextNode << " DIST: " << dist << endl;
        output += dist;

    }
    return output;
}


int main(int argc, const char** argv) {
    cin >> N;
    set<pair<int,int>> allPoints;
    for (int i = 0; i < N; i++) {
        cin >> points[i].first >> points[i].second;
        allPoints.insert(points[i]);
        for(int i = 0; i < 4; i ++){
            pair<int,int> newPair = make_pair(points[i].first + dxdy[i].first, points[i].second + dxdy[i].second);
            allPoints.insert(newPair);
        }
        decoder[points[i]] = i;
    }
    int count = 0;
    for(auto it = allPoints.begin(); it != allPoints.end(); it ++){
        pair<int,int> p = *it;
        if(decoder.find(p) != decoder.end()){
            IDTonode[decoder[p]] = count;
        }
        graph[count] = p;
        count ++;
    }
    N2 = count;
    for(int i = 0; i < N2; i ++){
        cout << "Point " << i << " is  (" << graph[i].first << ", " << graph[i].second << ")" << endl; 
    }
    cout << solve();
    return 0;
}