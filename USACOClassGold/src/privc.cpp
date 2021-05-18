#include <iostream>
#include <vector>
#include <algorithm>
#include <utility>
#define MAXN 1001
#define PASSES_A 100
#define PASSES_B 4
#define PASSES_C 100
using namespace std;
int highCowCount = 0;
int midCowCount = 0;
int lowCowCount = 0;

int N;
int arr[MAXN];
int desired(int pos){
    if(0 <= pos && pos < highCowCount){
        return 1;
    }else if(highCowCount <= pos && pos < (highCowCount + midCowCount)){
        return 2;
    }else if((highCowCount + midCowCount) <= pos && pos < N){
        return 3;
    }
    return 0;
}
bool benifitsFromSwap(int a, int b){
    if(arr[a] == arr[b]){
        return false;
    }
    if(desired(a) == arr[a] || desired(b) == arr[b]){
        return false;
    }
    return desired(a) == arr[b] || desired(b) == arr[a];
}
bool doubleBenifitsFromSwap(int a, int b){
    return desired(a) == arr[b] && desired(b) == arr[a];
}
int main(int argc, const char** argv) {
    cin >> N;
    for (int i = 0; i < N; i ++){
        cin >> arr[i];
        switch(arr[i]){
            case 1:
                highCowCount++;
                break;
            case 2:
                midCowCount++;
                break;
            case 3:
                lowCowCount++;
                break;
            default:
                break;
        }
    }
    int swapCount = 0;
    //vector<pair<int,int>> doubleBenifitOppurtunities;
    for(int p = 0; p < PASSES_A; p ++){
        for(int i = 0; i < highCowCount; i ++){
            for(int j = highCowCount; j < N; j ++){
                if(benifitsFromSwap(i,j) && doubleBenifitsFromSwap(i,j)){
                    //doubleBenifitOppurtunities.push(make_pair(i,j));
                    swap(arr[i],arr[j]);
                    // cout << "Epic Swap " << i << " and " << j << endl;
                    swapCount++;
                }
            }
        }
    }
    for(int p = 0; p < PASSES_B; p ++){
         for(int i = 0; i < highCowCount; i ++){
            bool outerContinue = false;
            for(int j = highCowCount; j < N; j ++){
                if(!(arr[i] == 1 || arr[j] == 1)){
                    continue;
                }
                if(benifitsFromSwap(i,j)){
                    //doubleBenifitOppurtunities.push(make_pair(i,j));
                    swap(arr[i],arr[j]);
                    // cout << "Swap " << i << " and " << j << " pass: " << p << endl;
                    swapCount ++;
                    // outerContinue = true;
                    break;
                }
            }
            if(outerContinue){
                continue;
            }
        }
    }
    for(int p = 0; p < PASSES_C; p ++){
        for(int i = highCowCount; i < N; i ++){
            for(int j = i + 1; j < N; j ++){
                if(benifitsFromSwap(i,j)){
                    //doubleBenifitOppurtunities.push(make_pair(i,j));
                    swap(arr[i],arr[j]);
                    // cout << "Swap " << i << " and " << j << endl;
                    swapCount ++;
                }
            }
        }
    }
    /*cout << "FINAL: ";
    for(int i = 0; i < N; i ++){
        cout << arr[i] << " ";
    }
    cout << endl;*/
    //for(auto iter = doubleBenifitOppurtunities.begin(); iter != doubleBenifitOppurtunities.end(); iter ++){
        //swap(arr[iter->first])
    //}
    cout << swapCount << endl;
    return 0;
}