#include <iostream>
#include <set>
using namespace std;

struct fraction{
    int a,b;
    bool equals(fraction f2){
        return (this->a * f2.b) == (this->b * f2.a);
    }
    bool operator==(const fraction& f2) const{
        return (this->a * f2.b) == (this->b * f2.a);
    }
    bool operator<(const fraction& f2) const{
        return (this->a * f2.b) < (this->b * f2.a);
    }
};
int N;
const int MAXN = 201;
int pointsX[MAXN];
int pointsY[MAXN];
set<fraction, greater<fraction> > slopes;
int solve(){
    for(int i = 0; i < N; i ++){
        for(int j = i + 1; j < N; j ++){
            fraction f;
            f.a = pointsX[i] - pointsX[j];
            f.b = pointsY[i] - pointsY[j];
            slopes.insert(f);
        }
    }
    return slopes.size();
}
int main(int argc, const char** argv) {
    cin >> N;
    for(int i = 0; i < N; i ++){
        cin >> pointsX[i] >> pointsY[i];
    }
    return 0;
}