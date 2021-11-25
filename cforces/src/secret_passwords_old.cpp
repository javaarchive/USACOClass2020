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
    vector<int> compressedPasswords;
    set<int> existenceSet;
    map<int,int> existenceMapping;
    map<int,int> freqMapping;
    vector<string> originals;

    for(int i = 0; i < N; i ++){
        string s;
        cin >> s;
        int num = 0;
        for(int j = 0; j < s.length(); j ++){
            int slot = s[j] - 'a';
            if(num % (1 << (slot + 1)) < (1 << slot)){
                num += (1 << slot);
            }
            // num += slot;
        }
        // cout << "translated " << s << " to " << num << endl;
        existenceSet.insert(num);
        compressedPasswords.push_back(num);
        existenceMapping[num] = i;
        originals.push_back(s);
    }

    int answer = 0;

    for(int i = 0; i < N; i ++){
        if(visited[i]){
            continue;
        }
        // cout << "Branching from " << i << endl;
        answer ++;
        int startPoint = compressedPasswords[i];
        freqMapping[startPoint] ++;
        if(freqMapping[startPoint] > 1){
            answer --; // undo
        }
        queue<int> nextUp;
        nextUp.push(startPoint);
        while(!nextUp.empty()){
            int current = nextUp.front();
            nextUp.pop();
            visited[existenceMapping[current]] = true; // ensure
            cout << "Marked " << existenceMapping[current] << endl;
            for(int i = 0; i < 26; i ++){
                if(current % (1 << (i + 1)) < (1 << i)){
                    // add letter
                    int next = current + (1 << i);
                    if(existenceSet.find(next) != existenceSet.end() && !visited[existenceMapping[next]]){
                        // explore!
                        cout << "Marked + " << existenceMapping[next] << endl;
                        visited[existenceMapping[next]] = true;
                        nextUp.push(next);
                    }else{
                        cout << originals[existenceMapping[current]] << " + " << (char) ('a' + i) << " failed " << next << endl;
                    }
                }
                if(current % (1 << (i + 1)) >= (1 << i)){
                    // remove letter
                    int next = current - (1 << i);
                    if(existenceSet.find(next) != existenceSet.end() && !visited[existenceMapping[next]]){
                        // explore!
                        cout << "Marked - " << existenceMapping[next] << endl;
                        visited[existenceMapping[next]] = true;
                        nextUp.push(next);
                    }else{
                        cout << originals[existenceMapping[current]] << " - " << (char) ('a' + i) << " failed " << next << endl;
                    }
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
