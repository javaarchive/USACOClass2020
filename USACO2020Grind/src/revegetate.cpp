#include <iostream>
#include <map>
#include <vector>
#define MAXN 100001
using namespace std;

int N,M;
bool visited[MAXN] = {false};
int seedType[MAXN] = {0};
const char SAME = 'S';
const char DIFFERENT = 'D';
map<int, vector<pair<int,char>>> requirements;
void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}
int recurSet(int pos, int newSeedType){
    if(visited[pos]){
        return 0; // graceful
    }
    visited[pos] = true;
    seedType[pos] = newSeedType;
    if(requirements.find(pos) != requirements.end()){
        if(requirements[pos].size() > 0){
            for(auto iter = requirements[pos].begin(); iter != requirements[pos].end(); iter ++ ){
                char requirement = iter->second;
                int nextPos = iter->first;
                if(!visited[nextPos]){
                    if(requirement == SAME){
                        if(recurSet(nextPos, seedType[pos]) == 1){
                            return 1;
                        }
                    }else if(requirement == DIFFERENT){
                        if(recurSet(nextPos, 1 - seedType[pos]) == 1){
                            return 1;
                        }
                    }
                }else{
                    // Verify
                    if(requirement == SAME){
                        if(seedType[pos] != seedType[nextPos]){
                            return 1; // :O something is defintley wrong
                        }
                    }else if(requirement == DIFFERENT){
                        if(seedType[pos] == seedType[nextPos]){
                            return 1; // :O something is defintley wrong
                        }
                    }
                }
            }
        }
    }
    return 0;
}

int main(int argc, const char** argv) {
    if(argc != 2){
        setIO("revegetate");
    }
    cin >> N >> M;
    int zeros = 0;
    for(int i = 0; i < M; i ++){
        char c; // Type
        cin >> c;
        int A,B;
        cin >> A >> B;
        A --;
        B --;
        requirements[A].push_back(make_pair(B,c));
        requirements[B].push_back(make_pair(A,c));
    }
    for(int i = 0; i < N; i ++){
        if(!visited[i]){
            zeros ++;
            if(recurSet(i, 0) == 1){
                // Contradictory statement
                cout << 0;
                return 0; // terminate
            }
        }
    }
    cout << 1;
    for(int i = 0; i < zeros; i ++){
        cout << 0;
    }
    cout << endl;

    return 0;
}