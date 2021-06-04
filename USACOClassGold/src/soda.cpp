#include <iostream>
#include <set>
#include <map>
#define MAXN 50001
#define VFIELD 100002
using namespace std;

int N;
set<int> endpoints;
int ranges[MAXN][2];
int pd[VFIELD] = {0};
int field[VFIELD] = {0};
map<int,int> realToCompressed;

int main(int argc, char const *argv[])
{
    cin >> N;
    for(int i = 0; i < N; i ++){
        cin >> ranges[i][0] >> ranges[i][1];
        endpoints.insert(ranges[i][0]);
        endpoints.insert(ranges[i][1]);
    }
    int nextIndex = 0;
    for(auto iter = endpoints.begin(); iter != endpoints.end(); iter ++){
        realToCompressed[*iter] = nextIndex;
        nextIndex ++;
    }
    for(int i = 0; i < N; i ++){
        pd[realToCompressed[ranges[i][0]]] ++; 
        pd[realToCompressed[ranges[i][1]] + 1] --; 
    }
    int best = 0;
    for(int i = 0; i < VFIELD; i ++){
        if(i > 0){
            field[i] = field[i - 1] + pd[i];
        }else{
            field[i] = pd[i];
        }
        best = max(field[i], best);
    }
    /*for(int i = 0; i < 2*N; i ++){
        cout << field[i] << " ";
    }
    cout << endl;*/
    cout << best << endl;
    return 0;
}
