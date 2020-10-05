#include <iostream>
#include <algorithm>
#include <vector>


using namespace std;

const int MAXR = 10000;
struct Interval{
    int start;
    int end;
};
vector<Interval> ranges;
int N, R, I, H;
int newR;
//int ;
bool cmp(Interval a, Interval b){
    return min(a.start,a.end) < min(b.start,b.end);
}
bool works(int ri, int ci){
    return (ci < max(ranges[ri].end,ranges[ri].start) && min(ranges[ri].end, ranges[ri].start) < ci);
}
int bsBottom(int ci){
    int l = 0;
    int r = newR - 1;
    while(l <= r){
        int m = l +  (r - 1) / 2;
        cout << l << m << r << endl;
        bool mWorks = works(m,ci);
        bool bWorks = (m != 0) && works(m - 1,ci);
        if(mWorks && !bWorks){
            return m;
        }else if(mWorks && bWorks){
            l = m + 1;
        }else if(ranges[m].start > ci){
            l = m + 1;
        }else{
            r = m - 1;
        }

    }
    return -1;
}
int bsTop(int ci){
    int l = 0;
    int r = newR - 1;
    while(l <= r){
        int m = l +  (r - 1) / 2;
        bool mWorks = works(m,ci);
        bool tWorks = (m != (newR - 1)) && works(m + 1,ci);
        if(mWorks && !tWorks){
            return m;
        }else if(mWorks && tWorks){
            l = m + 1;
        }else if(ranges[m].start > ci){
            l = m + 1;
        }else{
            r = m - 1;
        }

    }
    return -1;
}
void solve(){
    sort(ranges.begin(), ranges.end(), &cmp);
    for(int i = 0; i < ranges.size(); i ++){
        if(ranges[i].start == ranges[i + 1].start){
            if(ranges[i].end == ranges[i + 1].end){
                ranges.erase(ranges.begin() + i + 1);
            }
        }
    }
    
    newR = ranges.size();
    for(int i = 0; i < newR; i ++){
        cout << "Debug ranges[" << i << "] : " << ranges[i].start << ", " << ranges[i].end << endl;
    }
    //int startRangeIndex = 0;
    //int endRangeIndex = 0;
    cout << newR << endl;
    for(int i = 0; i < N; i ++){
        int bsBottomIndex = bsBottom(i);
        int bsTopIndex = bsTop(i);
        cout << bsTopIndex << " " << bsTopIndex << endl;
    }
}
int main(int argc, const char** argv) {
    cin >> N >> I >> H >> R;
    for(int i = 0; i < R; i ++){
        Interval temp;
        cin >> temp.start >> temp.end;
        ranges.push_back(temp);
    }
    solve();
    return 0;
}