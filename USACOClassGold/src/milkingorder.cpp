#include <iostream>
#include <vector>
#include <cmath>
#include <set>
#include <queue>
using namespace std;

vector<vector<int>> chains;
int N, M;
vector<int> topSorted;

bool trySatsify(int firstK)
{
    topSorted.clear();
    topSorted.resize(0);
    vector<int> indeg;
    vector<int> outdeg;
    indeg.resize(N);
    outdeg.resize(N);
    vector<set<int>> graph;
    graph.resize(N);
    for (int i = 0; i < firstK; i++)
    {
        for (int j = 0; j < chains[i].size() - 1; j++)
        {
            int a = chains[i][j];
            int b = chains[i][j + 1];
            if (graph[a].empty() || graph[a].find(b) == graph[a].end())
            {
                outdeg[a]++;
                indeg[b]++;
            }
            graph[a].insert(b);
        }
    }
    vector<bool> removed;
    removed.resize(N);
    priority_queue<int, vector<int>, greater<int>> hasNoIndeg;
    for (int i = 0; i < N; i++)
    {
        if (indeg[i] == 0)
        {
            removed[i] = true;
            hasNoIndeg.push(i);
        }
    }
    
    while (!hasNoIndeg.empty())
    {
        int item = hasNoIndeg.top();
        hasNoIndeg.pop();
        topSorted.push_back(item);
        removed[item] = true;
        int index = item;
        if (!graph[index].empty())
        {
            for (auto iter2 = graph[index].begin(); iter2 != graph[index].end(); iter2++)
            {
                outdeg[index]--;
                indeg[*iter2]--;
                if(indeg[*iter2] == 0){
                    removed[*iter2] = true;
                    hasNoIndeg.push(*iter2);
                }
            }
        }

        /*for (int i = 0; i < N; i++)
        {
            if (removed[i])
            {
                continue;
            }
            if (indeg[i] == 0)
            {
                removed[i] = true;
                hasNoIndeg.push(i);
            }
        }*/
    }
    for (int i = 0; i < N; i++)
    {
        if (indeg[i] != 0 || outdeg[i] != 0)
        {
            return false;
        }
        //cout << indeg[i] << "," << outdeg[i] << " ";
    }
    return true;
}
int main()
{
    cin >> N >> M;
    chains.resize(M);
    for (int i = 0; i < M; i++)
    {
        int len;
        cin >> len;
        chains[i].resize(len);
        for (int j = 0; j < len; j++)
        {
            cin >> chains[i][j];
            chains[i][j]--;
        }
    }
    int low, high;
    low = 0;
    high = M;
    while (low < high)
    {
        int m = (low + high) / 2;
        //cout << low << "," << m << "," << high << endl;
        if (!trySatsify(m))
        {
            high = m;
        }
        else
        {
            low = m + 1;
        }
    }
    /*for (int i = 0; i < M; i++)
    {
        cout << i << ": ";
        for (int i = 0; i < topSorted.size(); i++)
        {
            cout << (topSorted[i] + 1) << " ";
        }
        cout << endl;
    }*/
    trySatsify(high - 1);
    /*(for(int j = 0; j <= M; j ++){
        bool out = trySatsify(j);
        cout << j << " has Cycle: " << out << " -> ";*/
    for (int i = 0; i < topSorted.size(); i++)
    {
        cout << (topSorted[i] + 1) << " ";
    }
    //cout << endl;
    //}
    // cout << "END" << endl;
    //cout << high << " " << low;
}