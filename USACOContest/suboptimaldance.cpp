#include <iostream>
#include <vector>
#include <cmath>
#include <set>
#define MAXN 100001

using namespace std;
int N,M,K;
vector<vector<set<int>>> counts;
struct Move{
    vector<int> transform;
    Move(){
        //cout << "init";
        for(int i = 0; i < N; i ++){
            transform.push_back(i);
        }
    }
    void process(int a, int b){
        int temp = transform[a];
        transform[a] = transform[b];
        transform[b] = temp;
    }
    void process(int a, int b, int updateSection){
        int temp = transform[a];
        transform[a] = transform[b];
        transform[b] = temp;
        counts[updateSection][a].insert(b);
        counts[updateSection][b].insert(a);
    }
    Move clone(){
        vector<int> newVect(transform);
        Move m;
        m.transform = newVect;
        return m;
    }
    void apply(Move m){
        vector<int> before(transform); // Copy before state
        for(int i = 0; i < N; i ++){
            transform[i] = before[m.transform[i]];
        }

    }
};
void solve(Move m1){
    Move target;
    bool cycled[MAXN];
    for(int i = 0; i < K; i ++){
        target.apply(m1);
        for(int i = 0; i < N; i ++){
            /*if(target.transform[i] == i){
                cycled[i] = true;
            }else{*/
            counts[1][i].insert(counts[0][target.transform[i]].begin(),counts[0][target.transform[i]].end());
            //}
        }
    }

}
int main(int argc, const char** argv) {
    cin >> N >> K;
    M = K;
    Move m;
    int a,b;
    counts.resize(log2(K) + 1);
    for(int i = 0; i < counts.size(); i ++){
        counts[i].resize(N);
    }
    counts.resize(2);
    counts[0].resize(N);
    counts[1].resize(N);
    for(int i = 0; i < N; i ++){
        counts[0][i].insert(i); // include self
    }
    for(int i =0 ; i < M; i ++){
        cin >> a >> b;
        a --; b --;
        m.process(a,b,0);
    }
     /*for(int i = 0 ;i < m.transform.size(); i ++){
            cout << m.transform[i] << " ";
        }
        cout << endl;*/
    solve(m);
    for(int i = 0; i < N; i ++){
        cout << counts[1][i].size() << endl;
    }
    return 0;
}
