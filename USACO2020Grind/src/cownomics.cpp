#include <iostream>
#include <set>
#include <algorithm>
#include <vector>
#define MAXN 501
#define MAXM 51
using namespace std;

int N, M;
char spotty[MAXN][MAXM];
char plain[MAXN][MAXM];
int integerize(char a, char b, char c){
    // Turn 3 characters into an integer for use in sets
    return a*65536 + b*256 + c;
}
void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}
int main(int argc, const char** argv) {
    if(argc != 2){
        setIO("cownomics");
    }
    cin >> N >> M;
    for(int i = 0; i < N; i ++){
        cin >> spotty[i];
    }
    for(int i = 0; i < N; i ++){
        cin >> plain[i];
    }
    int ans = 0;
    for(int i = 0 ; i < M; i ++ ){
        for(int j = i + 1; j < M; j ++){
            for(int k = j + 1; k < M; k ++){
                // Check for common pattern 
                vector<int> spottyFingerprints(N);
                vector<int> plainFingerprints(N);
                for(int l = 0; l < N; l ++){
                    spottyFingerprints[l] = (integerize(spotty[l][i],spotty[l][j],spotty[l][k]));
                }
                for(int l = 0; l < N; l ++){
                    plainFingerprints[l] = (integerize(plain[l][i],plain[l][j],plain[l][k]));
                }
                sort(spottyFingerprints.begin(),spottyFingerprints.end());
                sort(plainFingerprints.begin(), plainFingerprints.end());
                set<int> intersect;
                set_intersection(spottyFingerprints.begin(), spottyFingerprints.end(), plainFingerprints.begin(), plainFingerprints.end(),
                std::inserter(intersect, intersect.begin()));
                //cout << i << " " << j << " " << k << " : " << intersect.size() << endl;
                //cout << *intersect.begin() << endl;
                if(intersect.size() == 0){
                    // No confusing
                    ans ++;
                }
            }
        }
    }
    cout << ans;
    return 0;
}