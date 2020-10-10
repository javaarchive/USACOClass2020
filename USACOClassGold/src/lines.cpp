#include <iostream>
#include <set>
using namespace std;
const int ZERODENOMINATOR = 54243223;
struct fraction{
    int a,b;
    bool operator ==(const fraction& f2) const{
        float sa,sb;
        if(b == 0){
            sa = ZERODENOMINATOR;
        }else{
            sa = (float) a/b;
        }
         if(f2.b == 0){
            sb = ZERODENOMINATOR;
        }else{
            sb = (float) f2.a/f2.b;
        }
        return sa == sb;
    }
    bool operator <(const fraction& f2) const{
        float sa,sb;
        if(b == 0){
            sa = ZERODENOMINATOR;
        }else{
            sa = (float) a/b;
        }
         if(f2.b == 0){
            sb = ZERODENOMINATOR;
        }else{
            sb = (float) f2.a/f2.b;
        }
        return sa < sb;
    }
    string stringify(){
        return a+"/"+b;
    }
};
int N;
const int MAXN = 201;
int pointsX[MAXN];
int pointsY[MAXN];
set<fraction, less<fraction> > slopes;
int solve(){
    for(int i = 0; i < N; i ++){
        for(int j = i + 1; j < N; j ++){
            fraction f;
            f.b = pointsX[i] - pointsX[j];
            f.a = pointsY[i] - pointsY[j];
            //cout << f.a << "/" << f.b << endl;
            slopes.insert(f);
        }
    }
     for (auto itr = slopes.begin(); itr != slopes.end(); ++itr) 
    { 
        //cout << "    " << (itr->a) << " / " << (itr->b) <<" SLOPE> " <<(float) (itr->a)/(itr->b);
    } 
    //cout << endl;
    return slopes.size();
}
int main(int argc, const char** argv) {
    cin >> N;
    for(int i = 0; i < N; i ++){
        cin >> pointsX[i] >> pointsY[i];
    }
    cout << solve();
    return 0;
}