#include <iostream>
#include <set>
#include <unordered_set>
#include <unordered_map>
#include <map>
#include <vector>
#include <queue>
#define MAXN 200004

using namespace std;

void solve(){
    int N;
    cin >> N;

    bool visited[MAXN] = {false};
    vector<int> sharedLetters[28];
    bool used[28] = {false};
    // string history[MAXN] = {""};
    string compressed[MAXN] = {""};

    for(int i = 0; i < N; i ++){
        string s;
        cin >> s;
        bool seen[28] = {false};
        //history[i] = s;
        for(int j = 0; j < s.length(); j ++){
            char c = s[j];
            int val = c - 'a';
            if(!seen[val]){
                seen[val] = true;
                compressed[i] += c;
                sharedLetters[val].push_back(i);
            }
        }
    }

    int answer = 0;

    queue<int> nextUp;
    for(int i = 0; i < N; i ++){
        if(visited[i]){
            continue;
        }
        answer ++;
        nextUp.push(i);
        visited[i] = true;
        // cout << "BFSing from " << compressed[i] << endl;
        while(!nextUp.empty()){
            int current = nextUp.front();
            nextUp.pop();
            for(char c: compressed[current]){
               
                // cout << "branching out with " << c << endl;
                int num = c - 'a';
                if(used[num]){
                    continue;
                }else{
                    used[num] = true;
                }
                for(int j = 0; j < sharedLetters[num].size(); j ++){
                    int next = sharedLetters[num][j];
                    if(visited[next]){
                        continue;
                    }
                    // cout << compressed[next] << " reached " << endl;
                    visited[next] = true;
                    nextUp.push(next);
                }
            }
        }

    }
    cout << answer << endl;


}

int main(int argc, char const *argv[])
{
    solve();
    return 0;
}
