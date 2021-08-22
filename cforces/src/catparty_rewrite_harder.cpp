#include <iostream>
#include <map>
#include <unordered_map>
#include <set>
#define MAXT 100002
using namespace std;

void solve(){
    int T;
    cin >> T;
    int arr[MAXT];
    for(int i = 0; i < T; i ++){
        cin >> arr[i];
    }

    int best = 0;

    int ribbonFreq[100001] = {0};
    unordered_map<int,int> freqToRibbonCount;
    freqToRibbonCount[0] = 100000;
    set<int> freqs;
    for(int i = 0; i < T; i ++){
        freqToRibbonCount[ribbonFreq[arr[i]]] --;
        if(freqToRibbonCount[ribbonFreq[arr[i]]] == 0){
            freqs.erase(ribbonFreq[arr[i]]);
        }
        ribbonFreq[arr[i]] ++;
        freqToRibbonCount[ribbonFreq[arr[i]]] ++;
        if(freqToRibbonCount[ribbonFreq[arr[i]]] == 1){
            freqs.insert(ribbonFreq[arr[i]]);
        }
        /*cout << i << ": ";
        for(int freq: freqs){
            cout << freq << " ";
        }
        cout << endl;*/
        if(freqs.size() == 2){
            bool hasFreqOne = false;
            bool hasMultipleFreqOne = false;
            for(int freq: ribbonFreq){
                if(freq == 1){
                    if(hasFreqOne){
                        hasMultipleFreqOne = true;
                        break;
                    }
                    hasFreqOne = true;
                }
            }

            if(hasFreqOne && !hasMultipleFreqOne){
                best = max(best,i);
            }else{
                int a,b;
                bool aDefined = false;
                for(int freq: freqs){
                    if(!aDefined){
                        a = freq;
                        aDefined = true;
                    }else{
                        b = freq;
                    }
                }
                if(abs(a-b) == 1){
                    if(freqToRibbonCount[max(a,b)] == 1){
                        best = max(best,i);
                    }
                }
            }
        }else if(freqs.size() == 1 && *freqs.begin() == 1){
            // all distinct types
             best = max(best,i);
        }else if(freqs.size() == 1 && freqToRibbonCount[*freqs.begin()] == 1){
            // all same type
            best = max(best,i);
        }
    }
    cout << best+1 << endl;
}

int main(int argc, char const *argv[])
{
    solve();
    return 0;
}