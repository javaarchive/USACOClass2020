#include <iostream>
#include <map>
#include <set>
#include <vector>
using namespace std;

const int MAXN = 40000;
int N;
struct Endpoint
{
    bool startPoint;
    int height;
    int id;
    bool operator<(const Endpoint &e2) const
    {
        if (height == e2.height)
        {
            return id < e2.id;
        }
        return height < e2.height;
    }
};
map<int, set<Endpoint>> xtoys;

int solve()
{
    vector<int> heights;
    vector<int> widths;
    bool first = true;
    int lastX = -1;
    set<Endpoint> active;
    for (auto iter = xtoys.begin(); iter != xtoys.end(); iter++)
    {
        int curX = iter->first;
        set<Endpoint> se = iter->second;
        for (auto iter2 = se.begin(); iter2 != se.end(); iter2++)
        {
            if (iter2->startPoint)
            {
                active.insert(*iter2);
            }
            else
            {
                active.erase(*iter2);
            }
        }
        if (!first)
        {
            widths.push_back(curX - lastX);
        }
        else
        {
            first = false;
        }
        heights.push_back((*(active.rbegin())).height);
        lastX = curX;

        //return 0;
    }
    cout << "Widths";
    for (int i = 0; i < widths.size(); i++)
    {
        cout << " " << widths[i];
    }
    cout << endl;
    cout << "Heights";
    for (int i = 0; i < widths.size(); i++)
    {
        cout << " " << widths[i];
    }
    cout << endl;
    return 0;
}
int main(int argc, const char **argv)
{
    cin >> N;
    int x1, x2, height;
    for (int i = 0; i < N; i++)
    {
        cin >> x1 >> x2 >> height;
        Endpoint a, b;
        //xtoys[x1].insert(height);
        //xtoys[x2].insert(height);
        a.height = height;
        b.height = height;
        a.id = i;
        b.id = i;
        a.startPoint = true;
        b.startPoint = false;
        xtoys[x1].insert(a);
        xtoys[x2].insert(b);
    }
    cout << solve();
    return 0;
}