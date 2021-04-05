#include <iostream>
#include <algorithm>
#include <assert.h>

//            12345
#define MAXN 100001
using namespace std;
// Survey papers used for boosting a certain past papers
// int surveyPapersUsed[MAXN] = {0};
int paperCitationsCount[MAXN];
int N, K, L;
bool works(int newHIndex){
    int citationsUsed = 0;
    for(int i = 0; i < min(N,newHIndex); i ++){
        if(paperCitationsCount[i] >= newHIndex){
            continue; // no additonial citations needed
        }
        int needed = newHIndex - paperCitationsCount[i];
        if(needed > K){ // not enough survey papers to up this
            return false;
        }
        citationsUsed += needed;
        if(citationsUsed > K*L){
            return false;
        }
    }
    return true;
}
int main(int argc, const char** argv) {
    cin >> N >> K >> L;
    for(int i = 0; i < N; i ++){
        cin >> paperCitationsCount[i];
    }
    sort(paperCitationsCount, paperCitationsCount + N);
    int startingHIndex = 0;
    for(int i = 0; i < N; i ++){
        int count = N - i; // Number of papers with at least this values
        int val = paperCitationsCount[i];
        if(count >= val){
            startingHIndex = max(val,startingHIndex);
        }
    }
   // cout << "The current hindex is " << startingHIndex << endl;

    reverse(paperCitationsCount,paperCitationsCount + N); // Greatest first
    // Self-testing
    assert (works(startingHIndex)); // should work for current T
    int l = startingHIndex;
    int h = N;
    while(l < h){
        int mid = l + (h - l)/2; // Floor of middle paper
        if(works(mid)){
            //cout << "works(" << mid << ") = " << "true" << endl;
            l = mid + 1; // Move higher if this works
        }else{
            //cout << "works(" << mid << ") = " << "false" << endl;
            h = mid;
        }
    }
    cout << (l - 1) << endl;
    return 0;
}