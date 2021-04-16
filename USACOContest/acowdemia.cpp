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
    long long citationsUsed = 0;
    if(paperCitationsCount[newHIndex - 1] + K < newHIndex){
        return false;
    }
    for(int i = 0; i < min(N,newHIndex); i ++){
        if(paperCitationsCount[i] >= newHIndex){
            continue; // no additonial citations needed
        }
        long long needed = (long long) newHIndex - (long long) paperCitationsCount[i];
        if(needed > K){ // not enough survey papers to up this
            return false;
        }
        citationsUsed += needed;
        if(citationsUsed > (long long) K *  (long long) L){
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
        int mid = (h + l + 1)/2; // Floor of middle paper
        if(works(mid)){
            //cout << "works(" << mid << ") = " << "true" << endl;
            l = mid; // Move higher if this works
        }else{
            //cout << "works(" << mid << ") = " << "false" << endl;
            h = mid - 1;
        }
    }
    cout << l << endl;
    return 0;
}