#include <iostream>
#include <set>
#include <map>
#include <vector>
#include <string>
#include <unordered_set>

using namespace std;

void solve(){
    string s = "";
    cin >> s;
    int N = s.length();
    // set<char> chars;
    map<char,int> freq_history;
    map<char,int> freq;
    vector<char> removes;

    string revS = string(s.rbegin(),s.rend());

    for(int i = 0; i < N; i++){
        // chars.insert(revS[i]);
        if(freq_history[revS[i]] == 0){
            removes.push_back(revS[i]);
        }
        freq_history[revS[i]] ++;
    }
    int origLen = 0;

    for(int i = 0; i < removes.size(); i ++){
        origLen += freq_history[removes[i]]/(removes.size() - i);       
    }
    // cout << "determine orig length " << origLen << endl;

    for(int i = 0; i < origLen; i ++){
        freq[s[i]] ++;
    }

    set<char> expected;
    expected.insert(revS[0]);
    int secLength = freq[revS[0]];
    bool seenNew = false;
    int secAppend = -1;
    int secCounter = 0;
    vector<char> out;
    out.push_back(revS[0]);
    // cout << "initial " << secLength << endl;
    for(int i = 0; i < N; i++){
        if(expected.find(revS[i]) == expected.end()){
            if(seenNew){
                // too many new chars for this section
                // cout << "stop:" << revS[i] << " at " << i;
                cout << -1 << endl;
                return;
            }
            // cout << "SEEN ++ " << revS[i] << " ++ " << endl;
            seenNew = false;
            out.push_back(revS[i]);
            expected.insert(revS[i]);
            secAppend = freq[revS[i]];
            secLength += secAppend;
        }
        secCounter ++;
        if(secLength >= secCounter && seenNew){
            // cout << "section end " << secCounter << endl;
            /*if(!seenNew){
                cout << -2 << endl;
                return;
            }*/
            secLength += secAppend;
            secCounter = 0;
            seenNew = false;
        }        
    }
    string output = string(out.rbegin(),out.rend());
    // cout << string(removes.rbegin(), removes.rend()) << endl;
    if(output != string(removes.rbegin(), removes.rend())){
        cout << -1 << endl;
    }
    // attempt to go backwards
    string base = s.substr(0,origLen);
    string temp = base;
    unordered_set<char> filterer;
    for(int i = 0; i < output.length(); i ++){
        filterer.insert(output[i]);
        for(char c: base){
            if(filterer.find(c) == filterer.end()){
                temp += c;
            }
        }
    }
    if(temp != s){
        // cout << "using" << output << endl;
       // cout << temp << " vs " << s << " so " << -1 << endl;
        cout << -1 << endl;
        return;
    }

    cout << base << " " << output << endl;
}

int main(int argc, char const *argv[])
{
    int N;
    cin >> N;
    for(int i = 0; i < N; i ++){
        solve();
    }
    return 0;
}
