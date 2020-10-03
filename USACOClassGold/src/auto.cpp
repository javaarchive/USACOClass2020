#include <iostream>
#include <string>
#include <algorithm>

using namespace std;
const int MAXW = 100002; // guessed
const int MAXN = 10002; 
int W;
int N;
struct Word{
    string str;
    int index;
};
bool compareWord(Word w1, Word w2){
    return w1.str < w2.str;
}
// Arrays
Word dictionary[MAXW];
string prefixes[MAXN];
int positions[MAXN];

void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}
bool startsWith(string target, string prefix){
    return target.rfind(prefix, 0) == 0;
}

int bsFindStart(string target){
    int left = 0, right = W - 1;
    int middle; //= left + (right - left)/2;
    while(left <= right){
       
        middle = left + (right - left)/2;
        // cout << "BS: " << left << " " << middle << " " << right << endl;
        bool middleWorks = startsWith(dictionary[middle].str, target);
        bool belowWorks = (middle != 0) && startsWith(dictionary[middle - 1].str, target);
        // cout << "BelowWorks " << belowWorks << " MiddleWorks " << middleWorks << endl;
        if(!belowWorks && middleWorks){
            return middle;
        }else if(belowWorks && middleWorks){
            right = middle - 1;
        }else{
            if(dictionary[middle].str < target){
                left = middle + 1;
            }else{
                right = middle - 1;
            }
        }
    }
    return -1;
}
int solve(int pos){
    int bsPos = bsFindStart(prefixes[pos]);
    //cout << "BSPOS : " << pos << " = " << bsPos << endl;
    if(bsPos < 0){
        return - 1;
    }
    if(startsWith(dictionary[bsPos + positions[pos] - 1].str, prefixes[pos])){
        return dictionary[bsPos + positions[pos] - 1].index + 1;
    }else{
        return -1;
    }
    return -1;
}

void solveAll(){
    sort(dictionary, dictionary + W, compareWord);
    /*cout << "DICTIONARY: " << endl;
    for(int i = 0; i < W; i ++){
        cout << i << ": " << dictionary[i].str << endl;
    }
    cout << "Solving: " << endl;*/
    for(int i = 0; i < N; i ++){
        cout << solve(i) << endl;
    }
}
int main(int argc, const char** argv) {
    cin >> W >> N;
    for(int i = 0; i < W; i ++){
        dictionary[i].index = i;
        cin >> dictionary[i].str;
    }
    for(int i = 0; i < N; i ++){
        cin >> positions[i] >> prefixes[i];
    }
    solveAll();
    return 0;
}
