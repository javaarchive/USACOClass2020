#include <iostream>
#include <limits>
#include <utility>
#include <map>
#include <algorithm>
#include <set>

using namespace std;

bool comp(const pair<int, int> &i, const pair<int, int> &j)
{
    return i.first < j.first;
}
pair<int, int> cows[50001];
map<int, int> freqMap;
set<int> types;
int N, tt;
int solve()
{
    int types = 0, min = INT32_MAX;
    sort(cows, cows + N, &comp);
    int top = 0;
    int range = 0;
    for (int i = 0; i < N; i++)
    {
        freqMap[cows[i].second]++;
        if (freqMap.size() == tt)
        {
            for (; freqMap.count(cows[range].second) && freqMap[cows[range].second] > 1; range++)
                freqMap[cows[range].second]--;
            if (min > (cows[i].first - cows[range].first)){
                min = (cows[i].first - cows[range].first);
            }
        }
    }
    return min;
}
int main(int argc, const char **argv)
{
    cin >> N;
    for (int i = 0; i < N; i++)
    {
        int a, b;
        cin >> a >> b;
        cows[i] = make_pair(a, b);
        types.insert(b);
    }
    tt = types.size();
    cout << solve();
    return 0;
}