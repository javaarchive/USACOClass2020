#include <iostream>
#include <set>
#include <algorithm>
#include <vector>
#define MAXN 1002

using namespace std;

void solve(){
    int n, m1, m2;
    cin >> n >> m1 >> m2;
    set<int> mocha[MAXN];
    set<int> diana[MAXN];
    for(int i = 0; i < m1; i ++){
        int a,b;
        cin >> a >> b;
        mocha[a].insert(b);
        mocha[b].insert(a);
    }
    for(int i = 0; i < m2; i ++){
        int a,b;
        cin >> a >> b;
        diana[a].insert(b);
        diana[b].insert(a);
    }
    vector<pair<int,int>> out;
    for(int i = 1; i <= n; i ++){
        for(int j = 1; j < i; j ++){
            // vector<int>::iterator it;
            vector<int> intersection;
            set_intersection(mocha[i].begin(), mocha[i].end(), mocha[j].begin(), mocha[j].end(), back_inserter(intersection));
            if(intersection.size() > 0){
                continue; // common so triangle
            }
            set_intersection(diana[i].begin(), diana[i].end(), diana[j].begin(), diana[j].end(), back_inserter(intersection));
            if(intersection.size() > 0){
                continue; // common so triangle
            }
            if(mocha[i].find(j) != mocha[i].end() || diana[i].find(j) != diana[i].end()){
                continue; // already exists
            }
            out.push_back(make_pair(i,j));
            diana[i].insert(j);
            diana[j].insert(i);

            mocha[i].insert(j);
            mocha[j].insert(i);
        }
    }
    cout << out.size() << endl;
    for(auto p: out){
        cout << p.first << " " << p.second << endl;
    }
}

int main(int argc, char const *argv[])
{
    solve();
    return 0;
}
