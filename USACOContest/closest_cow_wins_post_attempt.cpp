#include <bits/stdc++.h>

using namespace std;

vector<int> nhojCows;
vector<pair<int,int>> patches; // pos, tastiness
vector<int> intervalEndings;

struct Option{
    bool isTwoCow = false;
    int oneCow = 0;
    int twoCow = 0;
    int getSortValue(){
        if(isTwoCow){
            return twoCow - oneCow; // ??? change?
        }else{
            return oneCow;
        }
    }
};

void solve(){
    int K, M, N;
    cin >> K >> M >> N;
    for(int i = 0; i < K; i ++){
        int pos, tastiness;
        cin >> pos >> tastiness;
        patches.push_back(make_pair(pos, tastiness));
    }
    sort(patches.begin(), patches.end());
    for(int i = 0; i < M; i ++){
        int pos;
        cin >> pos;
        nhojCows.push_back(pos);
    }
    sort(nhojCows.begin(), nhojCows.end());
    int lowest = min(patches[0].first, nhojCows[0]);
    int uppest = max(patches[K - 1].first, nhojCows[M - 1]);
    // Padding
    lowest -= 10000;
    uppest += 10000;
    intervalEndings.push_back(lowest);
    for(int i = 0; i < K - 1; i ++){
        intervalEndings.push_back((patches[i].first + patches[i + 1].first) / 2);
    }
    intervalEndings.push_back(uppest);
    sort(intervalEndings.begin(), intervalEndings.end());
    int positionInPatches = 0;
    vector<Option> choices;
    for(int i = 0; i < intervalEndings.size() - 1; i ++){
        int startPoint = intervalEndings[i];
        int endPoint = intervalEndings[i + 1];
        // cout << "RANGE: " << startPoint << " | ---- | " << endPoint << endl;
        pair<int,int> testingPatch = patches[positionInPatches];
        while(positionInPatches < K && testingPatch.first < startPoint ){
            positionInPatches ++;
            if(positionInPatches < K) testingPatch = patches[positionInPatches];
        }
        // positionInPatches --; // overshot
        if(positionInPatches >= K){
            // cout << "Aborted due to out of range " << endl; 
            continue;
        }
        if(testingPatch.first >= endPoint){
            continue; // agh we overshot the interval or an opposing cow is on top of the patch
        }
        // Sliding window
        int patchBestTastinessSingleCow = 0;
        int windowEndPos = positionInPatches;
        int windowSize = (endPoint - startPoint) / 2;
        int oldTastiness = patches[positionInPatches].second;
        int curTasteSum = patches[positionInPatches].second;
        // cout << "Initial pos " << positionInPatches << endl;
        int twoCowtaste = patches[positionInPatches].second;
        // cout << "Initial 2 cow taste " << twoCowtaste << endl;
        for(;positionInPatches < K && patches[positionInPatches].first < endPoint; positionInPatches ++){
            // positonInPatches is start
            windowEndPos = max(positionInPatches + 1, windowEndPos);
            for(;windowEndPos < K && (patches[windowEndPos].first - patches[positionInPatches].first) <= windowSize && patches[windowEndPos].first < endPoint; windowEndPos ++){
                // windowEndPos is end
                curTasteSum += patches[windowEndPos].second;
                // cout << "LOOP ++ " << windowEndPos << endl;
                twoCowtaste += patches[windowEndPos].second;
            }
            // cout << "Resized to " << positionInPatches << " < " << windowEndPos << endl;
            patchBestTastinessSingleCow = max(patchBestTastinessSingleCow, curTasteSum);
            curTasteSum -= oldTastiness;
            oldTastiness = patches[positionInPatches].second;
        }
        
        // cout << "Final Range " << (positionInPatches - 1) << " < -- inclusive -- > " << (windowEndPos - 1) << endl;
        // cout << "Section best taste sum: " << patchBestTastinessSingleCow << endl;
        // cout << "Two cow all " << twoCowtaste << endl;
        Option oneCow;
        oneCow.oneCow = patchBestTastinessSingleCow;
        oneCow.twoCow = twoCowtaste;
        oneCow.isTwoCow = false;
        Option twoCow;
        twoCow.oneCow = patchBestTastinessSingleCow;
        twoCow.twoCow = twoCowtaste;
        twoCow.isTwoCow = true;
        choices.push_back(oneCow);
        choices.push_back(twoCow);
    }
    sort(choices.begin(), choices.end(), [](Option a, Option b) {
        bool equal = a.getSortValue() == b.getSortValue();
        if(!equal){
            return a.getSortValue() > b.getSortValue();
        }else{
            if(a.isTwoCow && b.isTwoCow){
                return false; // abritrary
            }else if(a.isTwoCow){
                return false; // prefer b
            }else if(b.isTwoCow){
                return true; // prefer a
            }else{
                return false; // abritrary
            }
        }
    });
    /*cout << "Choices: " << endl;
    cout << "First up we have a 1 cow tastiness of " << choices[0].oneCow << " then a 2 cow tastiness of " << choices[0].twoCow << endl;
    cout << "Second up we have a 1 cow tastiness of " << choices[1].oneCow << " then a 2 cow tastiness of " << choices[1].twoCow << endl;
    cout << "Third up we have a 1 cow tastiness of " << choices[2].oneCow << " then a 2 cow tastiness of " << choices[2].twoCow << endl;
    cout << "Fourth up we have a 1 cow tastiness of " << choices[3].oneCow << " then a 2 cow tastiness of " << choices[3].twoCow << endl;
    cout << "Fifth up we have a 1 cow tastiness of " << choices[4].oneCow << " then a 2 cow tastiness of " << choices[4].twoCow << endl;*/
    int ans = 0;
    for(int i = 0; i < N; i ++){
        Option opt = choices[i];
        if(opt.isTwoCow){
            ans += opt.twoCow;
        }else{
            ans += opt.oneCow;
        }
    }
    cout << ans << endl;
}

int main(int argc, char const *argv[])
{
    solve();
    return 0;
}
