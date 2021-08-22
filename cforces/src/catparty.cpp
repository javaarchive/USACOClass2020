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

    int ribbonFreq[11] = {0};
    unordered_map<int,int> freqToRibbonCount;
    freqToRibbonCount[0] = 10;
    for(int i = 1; i <= 10; i ++){
        ribbonFreq[i] = 0;
    }

    if(T == 1){
        cout << 1 << endl;
        return;
    }

    int best = 1;
    bool weHaveAllOnOtherNunber = 0;
    set<int> freqs;

    for(int i = 0; i < T; i ++){
        // cout << i << ": " << arr[i] << endl;
        int ribbonType = arr[i];
        if(freqToRibbonCount[ribbonFreq[ribbonType]] == (10 - freqToRibbonCount[0])){

            weHaveAllOnOtherNunber = false;
        }
        freqToRibbonCount[ribbonFreq[ribbonType]] --;
        if(freqToRibbonCount[ribbonFreq[ribbonType]] == 0){
            // cout << "Erase freq " <<  ribbonFreq[ribbonType] << endl;
            freqs.erase(ribbonFreq[ribbonType]);
        }
        ribbonFreq[ribbonType] ++;
        freqToRibbonCount[ribbonFreq[ribbonType]] ++;
        if(freqToRibbonCount[ribbonFreq[ribbonType]] == 1){ // just got a new
        // cout << "Add freq " <<  ribbonFreq[ribbonType] << " reason:" << ribbonType << endl;
            freqs.insert(ribbonFreq[ribbonType]);
        }

        if(freqs.size() == 1){
            best = max(best,i - 1);
            /*if(i == T - 1){
                best = max(best,i + 1);
            }*/
        }
        
        // cout << i << ": Frequency of " << ribbonFreq[ribbonType] << " shared by " << freqToRibbonCount[ribbonFreq[ribbonType]] << " others " << endl;
        if(freqToRibbonCount[ribbonFreq[ribbonType]] == (10 - freqToRibbonCount[0])){
            weHaveAllOnOtherNunber = true;
            if(freqToRibbonCount[ribbonFreq[ribbonType] + 1] != 1 && freqToRibbonCount[1] == 0){
                // cout << "cancelled best" << endl;
                continue;
            }
            // cout << "new best" << endl;
            best = max(i,best);
        }else if(freqToRibbonCount[ribbonFreq[ribbonType]] == 1 && weHaveAllOnOtherNunber){
            best = max(i,best);
        }
        // no change
        if(freqs.size() != 2){
            continue;
        }
        bool firstDefined = false;
        bool secondDefined = false;
        int a,b;
        for(int setItem: freqs){
            if(!firstDefined){
                a = setItem;
                firstDefined = true;
            }else{
                b = setItem;
            }
            // cout << "freqs item " << setItem << endl;
        }
        if(abs(a - b) == 1){
          //  cout << i << ": ab trigger " << a << " AND " << b << " and "<< freqToRibbonCount[a] << " and " << freqToRibbonCount[b] << endl;
           if(freqToRibbonCount[max(a,b)] == 1){
               best = max(best,i);
           }
           if(a == 1 && freqToRibbonCount[a] == 1){
               best = max(best,i);
           }
           if(b == 1 && freqToRibbonCount[b] == 1){
               best = max(best,i);
           }
        }

    }
    /*if(freqs.size() == 1){
        cout << T << endl;
        return;
    }*/
    cout << best+1 << endl;
}

int main(int argc, char const *argv[])
{
    solve();
    return 0;
}
