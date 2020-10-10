#include <iostream>
#include <algorithm>
#include <vector>

/*
#define RESET   "\033[0m"
#define endl   "\033[0m\n"
#define BLACK   "\033[30m"      
#define RED     "\033[31m"      
#define GREEN   "\033[32m"
#define YELLOW  "\033[33m"     
#define BLUE    "\033[34m"      
#define MAGENTA "\033[35m"      
#define CYAN    "\033[36m"     
#define WHITE   "\033[37m"     
#define BOLDBLACK   "\033[1m\033[30m"      
#define BOLDRED     "\033[1m\033[31m"      
#define BOLDGREEN   "\033[1m\033[32m"      
#define BOLDYELLOW  "\033[1m\033[33m"      
#define BOLDBLUE    "\033[1m\033[34m"      
#define BOLDMAGENTA "\033[1m\033[35m"      
#define BOLDCYAN    "\033[1m\033[36m"     
#define BOLDWHITE   "\033[1m\033[37m"     
*/
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
    bool out = (a.start < b.start);
    if(a.start == b.start){
        out = a.end < b.end;
    }
    return out;
}
bool works(int ri, int ci){
    //cout << CYAN << "Checking if " << ci << " is within " << "r["<<ri<<"]: "<<ranges[ri].start << " and " << ranges[ri].end << endl;
    return (ranges[ri].start < ci) && (ci < ranges[ri].end);
}
int bsBottom(int ci){
    int l = 0;
    int r = newR - 1;
    while(l <= r){
        int m = l +  (r - l) / 2;
        //cout << l << " " << m << " " << r << endl;
        bool mWorks = works(m,ci);
        bool bWorks = (m != 0) && works(m - 1,ci);
        //cout << bWorks << endl;
        if(mWorks && !bWorks){
            return m;
        }else if(mWorks && bWorks){
            r = m;
        }else if(ranges[m].start < ci){
            l = m + 1;
        }else{
            r = m;
        }

    }
    return -1;
}
int bsTop(int ci){
    int l = 0;
    int r = newR - 1;
    while(l <= r){
        int m = l +  (r - l) / 2;
        //cout << GREEN << l << " " << m << " " << r << endl;
        bool mWorks = works(m,ci);
        bool tWorks = (m != (newR - 1)) && works(m + 1,ci);
        if(mWorks && !tWorks){
            return m;
        }else if(mWorks && tWorks){
            l = m + 1;
        }else if(ranges[m].start < ci){
            l = m + 1;
        }else{
            r = m;
        }

    }
    return -1;
}
void solve(){
    sort(ranges.begin(), ranges.end(), &cmp);
    for(int i = 0; i < ranges.size() - 1; i ++){
        if(ranges[i].start == ranges[i + 1].start){
            if(ranges[i].end == ranges[i + 1].end){
                ranges.erase(ranges.begin() + i + 1);
                //i --;
            }
        }
    }
    for(int i = 0; i < ranges.size(); i ++){
        if(ranges[i].end - ranges[i].start == 1){
            ranges.erase(ranges.begin() + i);
            i --;
        }
    }
    
    newR = ranges.size();
    for(int i = 0; i < newR; i ++){
        cout << "Debug ranges[" << i << "] : " << ranges[i].start << ", " << ranges[i].end << endl;
    }
    //int startRangeIndex = 0;
    //int endRangeIndex = 0;
    //cout << newR << endl;
    for(int i = 0; i < N; i ++){
        int bsBottomIndex = bsBottom(i);
        //std::cout << "BS TOP: " << endl;
        int bsTopIndex = bsTop(i);
        cout << "Hits for " << i << " range " << bsBottomIndex << " " << bsTopIndex << endl;
        if(bsBottomIndex == -1 || bsTopIndex == -1){
            cout << H << endl;
        }else{
            cout << (H - (bsTopIndex - bsBottomIndex + 1)) << endl;
        }
    }
}
int main(int argc, const char** argv) {
    cin >> N >> I >> H >> R;
    for(int i = 0; i < R; i ++){
        Interval temp;
        cin >> temp.start >> temp.end;
        if(temp.end < temp.start){
            int tempNum = temp.start;
            temp.start = temp.end;
            temp.end = tempNum;
        }
        temp.start --; // Offset
        temp.end --; // More offset
        ranges.push_back(temp);
    }
    solve();
    return 0;
}