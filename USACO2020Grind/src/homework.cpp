#include <iostream>
#include <vector>
#include <algorithm>
#define MAXN 10001
using namespace std;
int N;
int scores[MAXN];
int lowest[MAXN];
struct fraction{
    int a;
    int b;
    fraction(int numerator, int denominator){
        int common = __gcd(numerator, denominator);
        a = numerator/common;
        b = denominator/common;
    }
    bool operator < (const fraction &right)
    {
        if(right.b == b){
            return a < right.a;
        }
        return ((a/b) < (right.a/right.b));
    }
    bool operator ==(const fraction &right){
        return a == right.a && b == right.b;
    }
};
vector<pair<fraction,int>> answers;
void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}
bool dbleq(double a, double b, double epsilon = 0.01)
{
    return std::abs(a - b) < epsilon;
}

int main(int argc, const char** argv) {
    if(argc != 2){
        setIO("homework");
    }
    cin >> N;
    int sum = 0;
    for(int i = 0; i < N; i ++){
        cin >> scores[i];
        sum += scores[i];
    }
    int curMin = INT32_MAX;
    for(int i = N - 1; i >= 0; i --){
        curMin = min(curMin, scores[i]);
        lowest[i] = curMin;
    }

    for(int i = 1; i <= N - 2; i ++){
        sum -= scores[i - 1];
        // cout << "((" << sum << " - " << lowest[i] << ") / " << (N - i - 1) << endl;
        // 1000000
        fraction avg((((double) (sum - lowest[i]) * 1000000.0L)),((double) (N - i - 1)));
        // cout << "Average  " << avg << " at removal of " << i << endl;
        answers.push_back(make_pair(avg,i));
    }
    sort(answers.begin(), answers.end(),[](pair<fraction,int> a, pair<fraction,int> b){
        if(a.first == b.first){
            return a.second < b.second;
        }
        return b.first < a.first;
    });
    fraction bestVal = (*answers.begin()).first;
    for(auto iter = answers.begin(); iter != answers.end(); iter ++){
        if(iter->first == bestVal){
            // cout << iter->first << " " << 
            cout << iter->second << endl;
        }else{
            break;
        }
    }
    return 0;
}