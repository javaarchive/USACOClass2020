#include <iostream>
#include <vector>
#include <algorithm>
#define MAXN 100001
using namespace std;
int N;
int scores[MAXN];
int lowest[MAXN];
vector<pair<pair<int,int>,int>> answers;
void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}
bool compare(long long aNum, long long aDen, long long bNum, long long bDen){
    return (aNum*bDen > bNum*aDen);
}
bool compareRev(long long aNum, long long aDen, long long bNum, long long bDen){
    return (aNum*bDen < bNum*aDen);
}
bool compareEq(long long aNum, long long aDen, long long bNum, long long bDen){
    return (aNum*bDen == bNum*aDen);
}
int main(int argc, const char** argv) {
    if(argc != 2){
        setIO("homework.5");
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
    int bestNumerator = 0;
    int bestDenominator = 1;
    for(int i = 1; i <= N - 2; i ++){
        sum -= scores[i - 1];
        // cout << "((" << sum << " - " << lowest[i] << ") / " << (N - i - 1) << endl;
        // 1000000
        // cout << "Average  " << avg << " at removal of " << i << endl;
        answers.push_back(make_pair(make_pair((sum - lowest[i]),(N - i - 1)),i));
    }
    sort(answers.begin(), answers.end(),[](pair<pair<int,int>,int> a, pair<pair<int,int>,int> b){
        if(compareEq(a.first.first, a.first.second, b.first.first, b.first.second)){
            return a.second < b.second;
        }
        return compare(a.first.first, a.first.second, b.first.first, b.first.second);
    });
    pair<int,int> bestVal = (*answers.begin()).first;
    // cout << "Best avg is " << bestVal.first/bestVal.second << endl;
    for(auto iter = answers.begin(); iter != answers.end(); iter ++){
        if(compareEq(iter->first.first,iter->first.second,bestVal.first,bestVal.second)){
            // cout << iter->first << " " << 
            cout << iter->second << endl;
        }else{
            break;
        }
    }
    return 0;
}