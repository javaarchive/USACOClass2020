#include <iostream>
#include <bits/stdc++.h>
#define PADDED_2MAXM 10010

using namespace std;

int N, M;

int output[PADDED_2MAXM] = {0};
int ps[PADDED_2MAXM] = {0};
set<int> starts_set;
set<int> ends_set;
map<int,int> starts_freq;
map<int,int> ends_freq;


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
    for(int i = 0; i < N1; i ++){
        for(int j = 0; j < N1; j ++){
            int final_start = startsAsVec[i] + startsAsVec[j];
            if(final_start > (2 * M + 1)){
                continue;
            }
            ps[final_start] += starts_freq[startsAsVec[i]] * starts_freq[startsAsVec[j]];
        }
    }

    for(int i = 0; i < N2; i ++){
        for(int j = 0; j < N2; j ++){
            int final_end = endsAsVec[i] + endsAsVec[j];
            if(final_end > (2 * M + 1)){
                continue;
            }
            ps[final_end + 1] -= ends_freq[endsAsVec[i]] * ends_freq[endsAsVec[j]];
        }
    }

    cout << ps[0] << endl;

    for(int i = 1; i <= (2 * M); i ++){
        output[i] = output[i - 1] + ps[i];
        cout << output[i] << endl;
    }
}

int main(int argc, char const *argv[])
{
    solve();
    return 0;
}
