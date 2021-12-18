#include <iostream>
#include <bits/stdc++.h>

using namespace std;

int N;
string a;
string b;
map<string,vector<string>> family;
map<string,string> motherOf;

void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}

string repeat(const string& input, unsigned num)
{
    string ret;
    ret.reserve(input.size() * num);
    while (num--)
        ret += input;
    return ret;
}

string greatGrand(int n){
    if(n == 0){
        return "";
    }
    if(n == 1){
        return "grand-";
    }
    return repeat("great-",n - 1) + "grand-";
}

string great(int n){
    if(n == 0){
        return "";
    }
    if(n == 1){
        return "grand-";
    }
    return repeat("great-", n - 1); // + "-";
}

void solve(){
    setIO("family");
    cin >> N >> a >> b;
    for(int i = 0; i < N; i ++){
        string x,y;
        cin >> x >> y;
        motherOf[y] = x;
    }
    map<string,int> seen;
    string memberA = a;
    string memberB = b;
    // seen.insert(a);
    // seen.insert(b);
    seen[a] = 2;
    seen[b] = 2;
    int elapsed = 1;
    if(motherOf[a] == motherOf[b]){
        cout << "SIBLINGS" << endl;
        return;
    }
    while(motherOf[memberA].length() > 0 || motherOf[memberB].length() > 0){
        elapsed ++;
        if(motherOf[memberA].length() > 0){
            memberA = motherOf[memberA];
            if(memberA == b){
                cout << b << " is the " << greatGrand(elapsed - seen[memberA]) << "mother of " << a << endl;
                return;
            }else if(seen[memberA]  > 0 && elapsed - seen[memberA] == 2){
                cout << b << " is the " << great(elapsed - seen[memberA]) << "aunt of " << a << endl;
                return;
            }else if(seen[memberA] > 0){
                cout << "COUSINS " << seen[memberA] << " vs " << elapsed << endl;
                return;
            }
            seen[memberA] = elapsed;
        }
        if(motherOf[memberB].length() > 0){
            memberB = motherOf[memberB];
            if(memberB == a){
                cout << a << " is the " << greatGrand(elapsed - seen[memberB]) << "mother of " << b << endl;
                return;
            }else if(seen[memberB] > 0 && elapsed - seen[memberB] == 2){
                cout << b << " is the " << great(elapsed - seen[memberB]) << "aunt of " << a << endl;
                return;
            }else if(seen[memberB] > 0){
                cout << "COUSINS " << seen[memberB] << " vs " << elapsed << endl;
                return;
            }
            seen[memberB] = elapsed;
            
        }
        
    }
    cout << "NOT RELATED" << endl;
}

int main(int argc, char const *argv[])
{
    solve();
    return 0;
}
