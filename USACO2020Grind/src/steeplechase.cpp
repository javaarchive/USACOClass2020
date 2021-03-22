#include <iostream>
#include <algorithm>
#include <set>
#include <iterator>
#include <vector>
#define MAXN 100001
using namespace std;
int N;
pair<pair<int, int>, pair<int, int>> points[MAXN];
struct Endpoint
{
    bool isStart;
    int x, y, index;
};
void setIO(string s)
{
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}
int curX;
int intersects[MAXN] = {0};
set<pair<int, int>> checkedIntersects;
double getYat(double x1, double y1, double x2, double y2, double X)
{
    if (x1 == x2)
    {
        return 0;
    }
    return y1 + (((X - x1) * (y2 - y1)) / (x2 - x1));
}

bool crosscmp(int a,
              int b)
{
    if (a == b)
    {
        return false;
    }
    return getYat(points[a].first.first, points[a].first.second, points[a].second.first, points[a].second.second, curX) < getYat(points[b].first.first, points[b].first.second, points[b].second.first, points[b].second.second, curX);
    //return a.y < b.y;
}
int orientation(pair<int, int> p, pair<int, int> q, pair<int, int> r)
{
    int orientationVal = (q.second - p.second) * (r.first - q.first) - (q.first - p.first) * (r.second - q.second);
    if (orientationVal == 0)
    {
        return 0;
    }
    else if (orientationVal > 0)
    {
        return 1;
    }
    else
    {
        return 2;
    }
}
bool betweenPointsOnLine(pair<int, int> p, pair<int, int> q, pair<int, int> r)
{
    return (q.first <= max(p.first, r.first) && q.first >= min(p.first, r.first) && q.second <= max(p.second, r.second) && q.second >= min(p.second, r.second));
}
bool checkIntersect(int a, int b)
{
    if (a == b)
    {
        return false;
    }
    int ori1 = orientation(points[a].first, points[a].second, points[b].first);
    int ori2 = orientation(points[a].first, points[a].second, points[b].second);
    int ori3 = orientation(points[b].first, points[b].second, points[a].first);
    int ori4 = orientation(points[b].first, points[b].second, points[a].second);
    if (ori1 != ori2 && ori3 != ori4)
    {
        return true;
    }
    return (ori1 == 0 && betweenPointsOnLine(points[a].first, points[a].second, points[b].first)) || (ori2 == 0 && betweenPointsOnLine(points[a].first, points[a].second, points[b].second)) || (ori3 == 0 && betweenPointsOnLine(points[b].first, points[b].second, points[a].first)) || (ori4 == 0 && betweenPointsOnLine(points[b].first, points[b].second, points[a].second));
}

bool checkIntersectOld(int a, int b)
{
    if (a == b)
    {
        return false;
    }
    int start = max(points[a].first.first, points[b].first.first);
    int end = min(points[a].second.first, points[b].second.first);
    if (points[a].second.first < points[b].first.first || points[b].second.first < points[a].first.first)
    {
        return false;
    }
    int startAY = getYat(points[a].first.first, points[a].first.second, points[a].second.first, points[a].second.second, start);
    int startBY = getYat(points[b].first.first, points[b].first.second, points[b].second.first, points[b].second.second, start);
    int endAY = getYat(points[a].first.first, points[a].first.second, points[a].second.first, points[a].second.second, end);
    int endBY = getYat(points[b].first.first, points[b].first.second, points[b].second.first, points[b].second.second, end);
    if (points[a].first.first == points[a].second.first)
    {
        int down = min(points[a].first.second, points[a].second.second);
        int up = max(points[a].first.second, points[a].second.second);
        int striaghtX = points[a].first.first;
        if (points[b].first.first == points[b].second.first)
        {
            return ((down <= points[a].first.second && points[a].first.second <= up) || (down <= points[a].second.second && points[a].second.second <= up));
        }
        if (!(points[b].first.first <= striaghtX && striaghtX <= points[b].second.first))
        {
            return false;
        }
        return ((down <= points[a].first.second && points[a].first.second <= up) || (down <= points[a].second.second && points[a].second.second <= up));
    }
    else if (points[b].first.first == points[b].second.first)
    {
        int down = min(points[b].first.second, points[b].second.second);
        int up = max(points[b].first.second, points[b].second.second);
        int striaghtX = points[b].first.first;
        if (!(points[a].first.first <= striaghtX && striaghtX <= points[a].second.first))
        {
            return false;
        }
        return ((down <= points[a].first.second && points[a].first.second <= up) || (down <= points[a].second.second && points[a].second.second <= up));
    }

    if (startAY > startBY && endAY < endBY)
    {
        return true;
    }
    if (startAY < startBY && endAY > endBY)
    {
        return true;
    }
    if (startAY == startBY || endAY == endBY)
    {
        return true;
    }
    return false;
}

int main(int argc, const char **argv)
{
    bool debug = true; //false;
    if (argc != 2)
    {
        setIO("cowjump");
    }
    else
    {
        debug = true;
    }
    cin >> N;
    for (int i = 0; i < N; i++)
    {
        cin >> points[i].first.first;
        cin >> points[i].first.second;
        cin >> points[i].second.first;
        cin >> points[i].second.second;
        if (points[i].first.first > points[i].second.first)
        {
            // I like the smaller X first
            int secondx = points[i].second.first;
            int secondy = points[i].second.second;
            points[i].second.first = points[i].first.first;
            points[i].second.second = points[i].first.second;
            points[i].first.first = secondx;
            points[i].first.second = secondy;
        }
    }
    cout << "Verifying intersection function..."<<endl;
    for(int i = 0; i < N; i ++){
        for(int j = i + 1; j < N; j ++){
            if(checkIntersect(i,j)){
                cout << i << " and " << j << " intersect" << endl;
            }
        }
    }
    vector<Endpoint> endpoints;
    set<int, decltype(crosscmp) *> cur(crosscmp);
    for (int i = 0; i < N; i++)
    {
        Endpoint start;
        Endpoint end;
        start.isStart = true;
        end.isStart = false;
        start.x = points[i].first.first;
        start.y = points[i].first.second;
        end.x = points[i].second.first;
        end.y = points[i].second.second;
        start.index = i;
        end.index = i;
        endpoints.push_back(start);
        endpoints.push_back(end);
    }
    sort(endpoints.begin(), endpoints.end(), [](Endpoint &a, Endpoint &b) {
        if (a.x == b.x)
        {
            if (a.y == b.y)
            {
                return a.isStart && !b.isStart;
            }
            return a.y < b.y;
        }
        return a.x < b.x;
    });
    /*cout << "SORT:" << endl;
    sort(points, points + N, [](pair<pair<int,int>,pair<int,int>> a, pair<pair<int,int>,pair<int,int>> b){
        // cout << "s";
        // sORT BY X ONLY
        if(a.first.first == b.first.first){
            return a.second.first < b.second.first;
        }
        return a.first.first < b.first.first;
    });*/
    for (int i = 0; i < 2 * N; i++)
    {
        Endpoint e = endpoints[i];
        curX = e.x;
        if (e.isStart)
        {
            cur.insert(e.index);
            set<int>::iterator lowiter = cur.upper_bound(e.index - 1);
            set<int>::iterator highiter = cur.lower_bound(e.index + 1);
            if (lowiter != cur.end())
            {
                if (checkIntersect(e.index, *lowiter) && checkedIntersects.find(make_pair(*lowiter, e.index)) == checkedIntersects.end())
                {
                    if (debug)
                        cout << "1: " << e.index << " intersects with " << *lowiter << endl;
                    intersects[*lowiter]++;
                    intersects[e.index]++;
                    checkedIntersects.insert(make_pair(*lowiter, e.index));
                }
            }
            if (highiter != cur.end() && !(lowiter == highiter) && checkedIntersects.find(make_pair(min(e.index, *highiter), max(e.index, *highiter))) == checkedIntersects.end())
            {
                if (checkIntersect(e.index, *highiter))
                {
                    if (debug)
                        cout << "2: " << e.index << " intersects with " << *highiter << endl;
                    intersects[*highiter]++;
                    intersects[e.index]++;
                    checkedIntersects.insert(make_pair(e.index, *highiter));
                }
            }
        }
        else
        {
            cur.erase(e.index);
            set<int>::iterator highiter = cur.lower_bound(e.index + 1);
            set<int>::iterator lowiter = cur.upper_bound(e.index - 1);
            if (highiter != cur.end() && lowiter != cur.end())
            {
                if (checkIntersect(*lowiter, *highiter) && checkedIntersects.find(make_pair(*lowiter, *highiter)) == checkedIntersects.end())
                {
                    if (debug)
                        cout << "3: " << *highiter << " intersects with " << *lowiter << endl;
                    intersects[*lowiter]++;
                    intersects[*highiter]++;
                    checkedIntersects.insert(make_pair(*lowiter, *highiter));
                }
            }
        }
    }
    if (debug)
    {
        cout << "Intersects " << endl;
        for (int i = 0; i < N; i++)
        {
            cout << intersects[i] << " ";
        }
        cout << endl;
    }
    int maxVal = -1;
    int maxPos = 0;
    for (int i = 0; i < N; i++)
    {
        if (maxVal < intersects[i])
        {
            maxVal = intersects[i];
            maxPos = i;
        }
    }
    cout << maxPos + 1 << endl;
    /*for(int i = 0; i < N; i ++){
        cout << points[i].first.first << " " << points[i].first.second << " " << points[i].second.first << " " << points[i].second.second << endl;
    }*/

    return 0;
}
