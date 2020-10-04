#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<int> currentlyDancin;
int times[10000];
int N, T_MAX;
void simulateToTime(int endTime, int& i){
    while(!currentlyDancin.empty() && currentlyDancin.front() <= endTime){
        currentlyDancin.erase(currentlyDancin.begin());
        if(i < N){
            int newTime = endTime + times[i];
            currentlyDancin.insert(lower_bound(currentlyDancin.begin(), currentlyDancin.end(), newTime), newTime);
            //currentlyDancin.push_back(endTime + times[i]);
            i ++;
        }
    }
    //return i;
}
int calcTimeForSize(int size){
    int totalTime = 0;
    for(int i = 0; i < size; i ++){
        currentlyDancin.push_back(times[i]);
    }
    sort(currentlyDancin.begin(), currentlyDancin.end());
    int cowIndex = size;
    //int sims = 0;
    int et;
    while(!currentlyDancin.empty()){
        //cout << "Sim length: " << currentlyDancin.size() << "ET: " << currentlyDancin.front() << endl;
        et = currentlyDancin.front();
        simulateToTime(et, cowIndex);
        //sort(currentlyDancin.begin(), currentlyDancin.end());
        //sims ++;
    }
    return et;
}
int solve(){
    /*for(int i = 0; i < 10; i ++){
        cout << "Testing " << i << " result " << calcTimeForSize(i) << endl;
    }*/
    int low = 1;
    int high = N;
    int middle;
    bool middleWorks, belowWorks;
    while(low <= high){
       
        middle = low + (high - low)/2;
        // cout << "BS: " << left << " " << middle << " " << right << endl;
        middleWorks = calcTimeForSize(middle) <= T_MAX;
        belowWorks = (middle != 0) && (calcTimeForSize(middle - 1) <= T_MAX);
        // cout << "BelowWorks " << belowWorks << " MiddleWorks " << middleWorks << endl;
        if(!belowWorks && middleWorks){
            return middle;
        }else if(belowWorks && middleWorks){
            high = middle - 1;
        }else{
            low = middle + 1;
        }
    }
    return 0;
}

int main(int argc, const char** argv) {
    cin >> N >> T_MAX;
    for(int i = 0; i < N; i ++){
        cin >> times[i];
    }
    cout << solve();
    return 0;
}