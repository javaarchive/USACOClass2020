#include <iostream>
#include <bits/stdc++.h>
#define PADDED_2MAXM 10010

using namespace std;

int N, M;

long long output[PADDED_2MAXM] = {0};
long long padd[PADDED_2MAXM] = {0};
long long pminus[PADDED_2MAXM] = {0};
unordered_set<int> starts_set;
unordered_set<int> ends_set;
unordered_map<int,long long> starts_freq;
unordered_map<int,long long> ends_freq;


void solve(){
    cin >> N >> M;
    int start, end;
    for(int i = 0; i < N; i ++){
        cin >> start >> end;
        starts_freq[start] ++;
        ends_freq[end] ++;
        starts_set.insert(start);
        ends_set.insert(end);
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
    for(auto it = starts_set.begin(); it != starts_set.end(); it ++){
        for(auto it2 = starts_set.begin(); it2 != starts_set.end(); it2 ++){
            int final_start = *it + *it2;
            if(final_start > (2 * M + 1)){
                continue;
            }
            padd[final_start] += starts_freq[*it] * starts_freq[*it2];
        }
    }

   for(auto it = ends_set.begin(); it != ends_set.end(); it ++){
        for(auto it2 = ends_set.begin(); it2 != ends_set.end(); it2 ++){
            int final_end = *it + *it2;
            if(final_end > (2 * M + 1)){
                continue;
            }
            pminus[final_end] += ends_freq[*it] * ends_freq[*it2];
        }
    }

    int cur = 0;
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
