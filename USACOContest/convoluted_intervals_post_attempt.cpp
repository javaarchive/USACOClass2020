#include <iostream>
#include <bits/stdc++.h>
#define PADDED_2MAXM 10010
#define PADDED_MAXM 5010

using namespace std;

int N, M;

long long output[PADDED_2MAXM] = {0};
long long padd[PADDED_2MAXM] = {0};
long long pminus[PADDED_2MAXM] = {0};
// unordered_set<int> starts_set;
// unordered_set<int> ends_set;
// unordered_map<int,long long> starts_freq;
// unordered_map<int,long long> ends_freq;

long long starts_freq[PADDED_MAXM];
long long ends_freq[PADDED_MAXM];


void solve(){
    cin >> N >> M;
    int start, end;
    for(int i = 0; i < N; i ++){
        cin >> start >> end;
        starts_freq[start] ++;
        ends_freq[end] ++;
        // starts_set.insert(start);
        // ends_set.insert(end);
    }
    /*vector<int> startsAsVec;
    vector<int> endsAsVec;
    for(auto it = starts_set.begin(); it != starts_set.end(); it ++){
        startsAsVec.push_back(*it);
    }
    for(auto it = ends_set.begin(); it != ends_set.end(); it ++){
        endsAsVec.push_back(*it);
    }
    int N1 = startsAsVec.size();
    int N2 = endsAsVec.size();*/
    for(int i = 0; i <= M; i ++){
        for(int j = 0; j <= M; j ++){
            int final_start = i + j;
            padd[final_start] += starts_freq[i] * starts_freq[j];
        }
    }

   for(int i = 0; i <= M; i ++){
        for(int j = 0; j <= M; j ++){
            int final_end = i + j;
            pminus[final_end] += ends_freq[i] * ends_freq[j];
        }
    }

    long long cur = 0;
    for(int i = 0; i <= (2 * M); i ++){
        cur += padd[i];
        cout << cur << endl;
        cur -= pminus[i];
    }
}

int main(int argc, char const *argv[])
{
    solve();
    return 0;
}
