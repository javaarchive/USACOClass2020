#include <iostream>
#include <vector>
#define MAXLEN 200001
#define MAXW 201
using namespace std;

bool possible[MAXLEN] = {false};
int main(int argc, const char** argv) {
    vector<string> dictionary;
    while(true){
        string s;
        cin >> s;
        if(s == "."){
            break;
        }
        dictionary.push_back(s);
    }
    int W = dictionary.size();
    string target = "";
    // cin >> target;
    string line;
    while (getline(std::cin, line))
    {
        target += line;
    }
    int N = target.size();
    possible[0] = true;
    for(int i = 0; i < N; i ++){
        // cout << "Loop at " << i << endl;
        if(!possible[i]){
            continue;
        }
        for(int j = 0; j < W; j ++){
            if((dictionary[j].length() + i) > N){
                continue;
            }
            // cout << "Trying " << target.substr(0, i) << " " << dictionary[j] << endl;
            bool doesntwork = false;
            for(int k = 0; k < dictionary[j].length(); k ++){
                if(dictionary[j][k] != target[k + i]){
                    doesntwork = true;
                    break;
                }
            }
            if(doesntwork){
                continue;
            }
            // cout << " + " << (dictionary[j].length() + i) << endl;
            possible[(dictionary[j].length() + i)] = true;
        }
        /*for(int i = 0; i <= N; i ++){
            cout << possible[i] << " ";
        }
        cout << endl;*/
    }
    int best = 0;
    for(int i = 0; i <= N; i ++){
        if(possible[i]){
            // cout << i << " ";
            best = i;
        }
    }
    cout << best;
    return 0;
}