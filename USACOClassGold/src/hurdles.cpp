#include <iostream>
#include <map>
#include <vector>
#include <queue>
#define MAXN 301

using namespace std;
int N, M, T;
//                   height
//map<int, vector<pair<int, int>>> graph;
int graph[MAXN][MAXN]; 

int main(int argc, const char **argv)
{
    cin >> N >> M >> T;
    for(int i = 0; i <= N; i ++){
        for(int j = 0; j <= N; j ++){
            graph[i][j] = INT32_MAX/2;
        }
    }
    for (int i = 0; i < M; i++)
    {
        int a, b, height;
        cin >> a >> b >> height;
        graph[a][b] = height;
        //graph[a].push_back(make_pair(b, height));
    }
    
    for (int k = 1; k <= N; k++)
    {
        for (int i = 1; i <= N; i++)
        {
            for (int j = 1; j <= N; j++)
            {
                if(graph[i][j] == 0 && i != j){
                    graph[i][j] = max(graph[i][k],graph[k][j]);
                }else{
                    graph[i][j] = min(graph[i][j],max(graph[i][k],graph[k][j]));
                }
            }
        }
    }
    for(int i = 0; i < T; i ++){
        int a,b;
        cin >> a >> b;
        cout <<  ((graph[a][b]>=(INT32_MAX/2))?-1:graph[a][b]) << endl;
    }
    /*for(int i = 0; i <= N; i ++){
        for(int j = 0; j <= N; j ++){
            cout << (graph[i][j]) << " ";
        }
        cout << endl;
    }*/
    return 0;
}