#include <iostream>
#include <map>
#include <vector>
#define MAXSESSIONS 1001

using namespace std;

int N, M, C;
int earliest[MAXSESSIONS] = {0};
int inwards[MAXSESSIONS] = {0};
int outwards[MAXSESSIONS] = {0};
map<int, vector<pair<int, int>>> dGraph;
vector<int> topSort() {
    vector<int> indegreesStart;
    vector<int> newIndegrees;
    vector<int> ans;
    for (int i = 0; i < N; i++) {
        if (inwards[i] == 0) {
            indegreesStart.push_back(i); // To process
        }
    }
    int iter = 0;
    while (!indegreesStart.empty()) {
        //cout << indegreesStart.size() << endl;
        for(int i = 0; i < N; i ++){
            cout << i << " IN: " << inwards[i] << " and OUT:" << outwards[i] << endl;  
        }
        for (auto iter = indegreesStart.begin(); iter != indegreesStart.end(); iter++) {
            int pos = *iter;
            //cout << pos<<":"<<dGraph[pos].size() << endl;
            ans.push_back(pos);
            if(!dGraph[pos].empty())
                for (auto iter2 = dGraph[pos].begin(); iter2 != dGraph[pos].end(); iter2++) {
                    int dest = iter2->second;
                    int len = iter2->first;
                    outwards[pos] --;
                    inwards[dest] --;
                    if (inwards[dest] == 0) {
                        newIndegrees.push_back(dest);
                    }
                }
            
        }
        indegreesStart = newIndegrees;
        newIndegrees = {}; // Reset vector
        cout << iter << endl;
    }
    return ans;
}

int main(int argc, char const* argv[])
{
    /* code */
    cin >> N >> M >> C;
    for (int i = 0; i < N; i++) {
        cin >> earliest[i];
    }
    for (int i = 0; i < C; i++) {
        int a, b, len;
        cin >> a >> b >> len;
        //                            days session
        dGraph[a].push_back(make_pair(len, b));
        inwards[b] ++;
        outwards[a] ++;
    }
    vector<int> ans = topSort();
    cout << "Result: " << endl;
    for (int i = 0; i < ans.size(); i++) {
        cout << i << ": " << ans[i] << endl;
    }
    return 0;
}
