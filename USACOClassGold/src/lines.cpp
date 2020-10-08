#include <iostream>
#include <set>
using namespace std;

struct fraction{
    int a,b;
    bool equals(fraction f2){
        return (this->a * f2.b) == (this->b * f2.a);
    }
    bool operator ==(const fraction& f2) const{
        return (a * f2.b) == (b * f2.a);
    }
    bool operator <(const fraction& f2) const{
        /*if(a == f2.a && a == 0){
            return false;
        }*/
        return (a * f2.b) < (b * f2.a);
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
            slopes.insert(f);
        }
    }
     for (auto itr = slopes.begin(); itr != slopes.end(); ++itr) 
    { 
        cout << " " << (float) (itr->a)/(itr->b);
    } 
    cout << endl;
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