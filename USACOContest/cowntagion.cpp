#include <iostream>
#include <vector>
#include <queue>

using namespace std;
vector<vector<int>> graph;
int N;
struct BFSState{
    int days = 0;
    vector<int> state;
    BFSState(){
        state.resize(N + 1);
    }
    int covered = 1;
};
int solve(){
    queue<BFSState> q;
    BFSState ibs; // Initial
    ibs.state[1] = 1;
    q.push(ibs);
    while(!q.empty()){
        BFSState bs = q.front();

        /*cout << "Days: " << bs.days << " Covered: " << bs.covered << endl;
        for(int i = 0; i <= N; i ++){
            cout << bs.state[i] << " ";
        }
        cout << endl;*/

        q.pop();
        if(bs.covered >= N){
            return bs.days;
        }
        // Superspreading event
        for(int i = 1; i <= N; i ++){
            if(bs.state[i] > N || bs.state[i] == 0){ // No point increasing this now
                continue;
            }
            BFSState newBs;
            newBs.state = bs.state;
            newBs.state[i] = 2 * bs.state[i];
            newBs.days = bs.days + 1;
            q.push(newBs);
        }
        // Cow moves to farm
        for(int a = 1; a <= N; a ++){
            if(bs.state[a] == 0){
                continue;
            }
            for(auto pB = graph[a].begin(); pB != graph[a].end(); pB ++){
                int b = *pB;
                if(!(bs.state[b] == 0 && bs.state[a] > 1)){
                    continue; // Pruning???
                }
                if(bs.state[b] >= N){
                    continue; // no effect
                }
                BFSState newBs; 
                newBs.state = bs.state;
                newBs.covered = bs.covered;
                newBs.days = bs.days + 1;
                if(bs.state[b] == 0 && bs.state[a] > 1){
                    newBs.covered ++;
                }
                newBs.state[a] --;
                newBs.state[b] ++;
                q.push(newBs);
            }
        }
    }
    return -1;
}
int main(int argc, const char** argv) {
    cin >> N;
    graph.resize(N + 1);
    for(int i = 0; i < N-1; i ++){
        int a,b;
        cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }
    cout << solve();
    return 0;  
}