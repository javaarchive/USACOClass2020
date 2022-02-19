#include <bits/stdc++.h>
#define MAXN 60001
#define NULLVAL -33333

using namespace std;

int N;

int starts[MAXN];
int speeds[MAXN];

bool isNull(pair<double,double> possibleNull ){
    return (possibleNull.first == NULLVAL) && (possibleNull.second == NULLVAL);
}

pair<double,double> normalize(pair<double,double> p){
    return make_pair(min(p.first,p.second),max(p.first,p.second));
}

pair<double,double> merge(pair<double,double> a, pair<double,double> b){
    // cout << "Merging " << a.first << " " << a.second << " and " << b.first << " " << b.second << endl;
    // Casework ouchie
    if(b.second == a.first){
        // cout << "E" << endl;
        // bbbbb
        //     aaaa
        return make_pair(b.second,a.first);
    }else if(a.second == b.first){
        // cout << "F" << endl;
        // aaaaaa
        //      bbb
        return make_pair(a.second,b.first);
    }else if(a.first < b.first && b.first < b.second && b.second < a.second ){
        // cout << "A" << endl;
        // aaaaaaaaaaaaaaaaaa
        //       bbbbbb
        return make_pair(b.first,b.second);
    }else if(a.first <= b.first && b.first <= a.second && a.second <= b.second){
        // cout << "B" << endl;
        //      bbbbbbbb
        // aaaaaaaaa
        return make_pair(b.first,a.second);
    }else if(b.first < a.first && a.first < a.second && a.second < b.second){
        // bbbbbbbbbb
        //    aaaaa
        // cout << "C" << endl;
        return make_pair(a.first,a.second);
    }else if(b.first <= a.first && a.first <= b.second && b.second <= a.second){
        // cout << "D" << endl;
        // bbbbbbbbbbb
        //        aaaaaaaaaaa
        return make_pair(a.first,b.second);
    }else{
        return make_pair(NULLVAL,NULLVAL); // nothing
    }
}

bool bs(double range){
    double start = 0;
    double end = INT32_MAX;

    // Loop through each and narrow down the range
    pair<double,double> interval = make_pair(0, end);

    for(int i = 0; i < N; i ++){
        double mid = starts[i];
        double startPoint = mid - range * speeds[i];
        double endPoint = mid + range * speeds[i];

        // cout << "Merging " << startPoint << " < " << endPoint << " with cur interval " << interval.first << " < " << interval.second << endl;
        interval = merge(normalize(make_pair(startPoint, endPoint)),normalize(interval));
        // cout << "New " << interval.first << " < " << interval.second << endl;

        if(isNull(interval)){
            // cout << " is null " << interval.first << " " << interval.second << endl;
            return false;
        }
    }
    // COMMON INTERVAL YAY!!!
    return true;
}

void solve(){
    cin >> N;
    for(int i = 0; i < N; i ++){
        cin >> starts[i];
    }

    for(int i = 0; i < N; i ++){
        cin >> speeds[i];
    }

    int tries = 1000;//00; // percision :D

    double low = 0;
    double high = INT32_MAX;

    cout.precision(20);

    // cout << "ITEST" << endl;
    if(bs(0)){
        // Insta-works case
        cout << 0.000 << endl;
        return;
    }
    // cout << "BS TEST" << endl;

    while(low < high){
        double mid = (low + high)/2;


        // cout << " ==== BS " << mid << " START ====" << endl;
        bool works = bs(mid);

        // cout << low << " < " << mid << " < " << high << endl;
        // cout << mid << " works " << works << endl;

        tries --;

        if(works){
            high = mid;
            /*if(low == high){
                high = low + (mid-low)/2;
            }*/
        }else{
            low = mid; //+ 1;
            /*if(low == high){
                low = mid;
            }*/
        }

        if(tries <= 0){
            break;
        }
    }

    cout << low << endl;

}

int main(int argc, char const *argv[])
{
    solve();   
    return 0;
}
