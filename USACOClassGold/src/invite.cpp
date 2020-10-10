#include <iostream>
#include <set>
#include <map>
#include <vector>
#include <queue>

using namespace std; // i will never do this in actually big programs
const int maxG = 1000001;
const int maxN = 1000001;
//                123456
int N, G;
set<int> sets[maxG];
map<int,vector<int>> inverseIndex;
bool invited[maxN];
queue<int> nextS;
int solve(){
    int answer = 1;
    nextS.push(1);
    invited[1] = true;
    while(!nextS.empty()){
        int target = nextS.front();
        nextS.pop();
        for(auto iter = inverseIndex[target].begin(); iter != inverseIndex[target].end(); ++ iter){
            //cout << *iter << endl;
            //cout << "Erasing: " << *iter << " because it contains " << target << endl; 
            sets[*iter].erase(target);
            if(sets[*iter].size() == 1){
                int leftout = *sets[*iter].begin();
                if(!invited[leftout]){
                    invited[leftout] = true;
                    nextS.push(leftout);
                    answer ++;
                }
            }
        } 

    }
    return answer;
}
int main(int argc, const char** argv) {
    cin >> N >> G;
    //cout << "Ok!";
    int lineCount;
    int cowNum;
    for(int i = 0; i < G; i ++){
        cin >> lineCount;
       // cout << "Readin " << i << endl;
        for(int j = 0; j < lineCount; j ++){
            cin >> cowNum;
            sets[i].insert(cowNum);
            //if(inverseIndex.find(cowNum) == inverseIndex.end()){
                inverseIndex[cowNum].push_back(i);
            //}
        }
    }
    cout << solve();
    return 0;
}