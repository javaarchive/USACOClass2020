#include <iostream>
#include <algorithm>
#include <map>
#define MAXN 2550

using namespace std;
int N;
pair<int,int> points[MAXN];
pair<int,int> cpoints[MAXN];
map<int,int> compressedx;
map<int,int> compressedy;
long long ps[MAXN][MAXN] = {0};
long long regionCells(int x1, int y1, int x2, int y2){
    return ps[x2+1][y2+1] + ps[x1][y1] - ps[x2+1][y1] - ps[x1][y2+1];
}
long long solve(){
    long long ans = 1;
    
    //fill(ps, ps + MAXN*MAXN, 0);
    for(int i = 0; i <= N; i ++){
        for(int j = 0; j <= N; j ++){
            ps[i][j] = 0;
        }
    }
    for(int i = 0; i < N; i ++){
        cpoints[i].first ++;
        cpoints[i].second ++;
    }
    for(int i = 0; i < N; i ++){
        //cout << " + " << cpoints[i].first << " " << cpoints[i].second << endl;
        ps[cpoints[i].first][cpoints[i].second] = 1;
    }
    /*for(int i = 0; i <= N; i ++){
        for(int j = 0; j <= N; j ++){
            cout << ps[i][j] << " ";
        }
        cout << endl;
    }*/
    for(int i = 1; i <= N; i ++){
        for(int j = 1; j <= N; j ++){
            ps[i][j] += (ps[i -1 ][j] + ps[i][j - 1] - ps[i-1][j-1]);
        }
    }
    /*for(int i = 0; i <= N; i ++){
        for(int j = 0; j <= N; j ++){
            cout << ps[i][j] << " ";
        }
        cout << endl;
    }*/
    for(int i = 0; i < N; i ++){
        for(int j = i; j < N; j ++){ 
            int lowBound = min(cpoints[i].first, cpoints[j].first) - 1;
            int highBound = max(cpoints[i].first, cpoints[j].first) - 1;
            // cout << "Xs: " << lowBound << " " << highBound << endl;
            // cout << "ans += " << regionCells(0,i,lowBound,j) << " * " << regionCells(highBound,i,N-1,j) << endl;
            ans += (regionCells(0,i,lowBound,j)) * (regionCells(highBound,i,N-1,j));
        }
    }
    return ans;
}
int main(int argc, const char** argv) {
    cin >> N;
    int maxx = INT32_MIN, maxy = INT32_MIN;
    for(int i = 0; i < N; i ++){
        cin >> points[i].first >> points[i].second;
        //maxx = max(maxx, points[i].first);
        //maxy = max(maxy, points[i].second);
    }
    //cout << "MAX: " << maxx << " " << maxy << endl;
    sort(points, points + N, [](pair<int,int> a, pair<int,int> b){
        return a.first < b.first;
    });
    int nextX = 0, nextY = 0;
    for(int i = 0; i < N; i ++){
        if(i != 0 && points[i - 1].first != points[i].first){
             nextX ++;
        }
        //if(i != 0 && points[i].first != points[i - 1].first){
            compressedx[points[i].first] = nextX;
           // cout << "X: " << points[i].first << " -> "<< nextX << endl;
        //}
        
       
    }
    
    sort(points, points + N, [](pair<int,int> a, pair<int,int> b){
        return a.second < b.second;
    });
    for(int i = 0; i < N; i ++){
        if(i != 0 && points[i - 1].second != points[i].second){
             nextY ++;
        }
        //if(i != 0 && points[i].second != points[i - 1].second){
            compressedy[points[i].second] = nextY;
            // cout << "Y: " << points[i].second << " -> "<< nextY << endl;
        //}
        
       
    }
    for(int i = 0 ; i < N; i ++){
        pair<int,int> p = make_pair(compressedx[points[i].first],compressedy[points[i].second]);
        cpoints[i] = p;
    }
    cout << solve();
    return 0;
}