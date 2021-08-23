#include <iostream>
#include <set>
#include <algorithm>
#include <queue>
#include <stack>
#include <map>
#include <vector>
#define MAXN 100002

using namespace std;

pair<int,int> normalize(int a,int b){
    return make_pair(min(a,b),max(a,b));
}

void solve(){
    int n, m1, m2;
    cin >> n >> m1 >> m2;
    map<int,vector<int>> mocha;
    map<int,vector<int>> diana;
    // bool existing[MAXN][MAXN];
    set<pair<int,int>> existing;
    for(int i = 0; i < m1; i ++){
        int a,b;
        cin >> a >> b;
        mocha[a].push_back(b);
        mocha[b].push_back(a);
        existing.insert(normalize(a,b));
    }
    for(int i = 0; i < m2; i ++){
        int a,b;
        cin >> a >> b;
        diana[a].push_back(b);
        diana[b].push_back(a);
        existing.insert(normalize(a,b));
    }

    int srcm[MAXN] = {0};
    int srcd[MAXN] = {0};
    fill(srcm, srcm + MAXN,0);
    fill(srcd, srcd + MAXN,0);
    for(int i = 1; i <= n; i ++){
        if(srcm[i] == 0){
            // dfs
            stack<int> next;
            next.push(i);
            srcm[i] = i;
            while(!next.empty()){
                int cur = next.top();
                next.pop();
                for(int j = 0; j < mocha[cur].size(); j ++){
                    int nextNode = mocha[cur][j];
                    if(srcm[nextNode] == 0){
                        srcm[nextNode] = i; // master node
                        next.push(nextNode);
                    }
                }
            }
        }
    }
    
    for(int i = 1; i <= n; i ++){
        if(srcd[i] == 0){
            // dfs
            stack<int> next;
            next.push(i);
            srcd[i] = i;
            while(!next.empty()){
                int cur = next.top();
                next.pop();
                for(int j = 0; j < diana[cur].size(); j ++){
                    int nextNode = diana[cur][j];
                    if(srcd[nextNode] == 0){
                        srcd[nextNode] = i; // master node
                        next.push(nextNode);
                    }
                }
            }
        }
    }
    vector<pair<int,int>> out;
    int ans = 0;
    for(int i = 1; i <= n; i ++){
        for(int j = i + 1; j <= n; j ++){
            if(srcm[i] == srcm[j] || srcd[i] == srcd[j] || existing.find(normalize(i,j)) != existing.end()){
                continue;
            }
            out.push_back(make_pair(i,j));
            existing.insert(normalize(i,j));
            int srcA = srcm[i];
            stack<int> next;
            next.push(j);
            srcm[j] = srcA;
            // cout << "dfsing" << endl;
            while(!next.empty()){
                int cur = next.top();
                next.pop();
                for(int k = 0; k < mocha[cur].size(); k ++){
                    int nextNode = mocha[cur][k];
                    if(srcm[nextNode] != srcA){
                        srcm[nextNode] = srcA;
                        next.push(nextNode);
                    }
                }
            }
            int srcB = srcd[i];
            next.push(j);
            srcd[j] = srcB;
            while(!next.empty()){
                int cur = next.top();
                next.pop();
                for(int k = 0; k < diana[cur].size(); k ++){
                    int nextNode = diana[cur][k];
                    if(srcd[nextNode] != srcB){
                        srcd[nextNode] = srcB;
                        next.push(nextNode);
                    }
                }
            }
            mocha[i].push_back(j);
            mocha[j].push_back(i);
            diana[i].push_back(j);
            diana[j].push_back(i);

            ans ++;
        }
    }
    // ans -= m1 + m2;
    cout << ans << endl;
    for(auto pairing: out){
        cout << pairing.first << " " << pairing.second << endl;
    }
}

int main(int argc, char const *argv[])
{
    solve();
    return 0;
}
