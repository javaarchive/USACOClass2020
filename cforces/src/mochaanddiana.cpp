#include <iostream>
#include <set>
#include <unordered_set>
#include <algorithm>
#include <queue>
#include <stack>
#include <map>
#include <vector>
#define MAXN 100002

using namespace std;

struct hash_pair {
    template <class T1, class T2>
    size_t operator()(const pair<T1, T2>& p) const
    {
        auto hash1 = hash<T1>{}(p.first);
        auto hash2 = hash<T2>{}(p.second);
        return hash1 ^ hash2;
    }
};


pair<int,int> normalize(int a,int b){
    return make_pair(min(a,b),max(a,b));
}

void solve(){
    int n, m1, m2;
    cin >> n >> m1 >> m2;
    map<int,vector<int>> mocha;
    map<int,vector<int>> diana;
    int mappingsM[MAXN];
    int mappingsD[MAXN];
    for(int i = 0; i <= n; i ++){
        mappingsM[i] = i;
        mappingsD[i] = i;
    }
    // bool existing[MAXN][MAXN];
    unordered_set<pair<int,int>, hash_pair> existing;
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
            if(mappingsM[srcm[i]] == mappingsM[srcm[j]] || mappingsD[srcd[i]] == mappingsD[srcd[j]] || existing.find(normalize(i,j)) != existing.end()){
                continue;
            }
            out.push_back(make_pair(i,j));
            existing.insert(normalize(i,j));
            int srcA = srcm[i];
            mappingsM[srcm[j]] = mappingsM[srcA];
            int srcB = srcd[i];
            mappingsD[srcd[j]] = mappingsD[srcB];
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
