#include <bits/stdc++.h>

using namespace std;

vector<int> nhojCows;
vector<pair<int,int>> patches; // pos, tastiness
vector<int> intervalEndings;

struct Option{
    bool isTwoCow = false;
    int accumulatedTastiness = 0;
    int customSortValue; // use ?
    int getSortValue(){
        if(isTwoCow){
            return accumulatedTastiness; // ??? change?
        }else{
            return accumulatedTastiness;
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
    lowest -= 10;
    uppest += 10;
    intervalEndings.push_back(lowest);
    for(int i = 0; i < K - 1; i ++){
        intervalEndings.push_back((patches[i].first + patches[i + 1].first) / 2);
    }
    intervalEndings.push_back(uppest);
    sort(intervalEndings.begin(), intervalEndings.end());
    int positionInPatches = 0;
    for(int i = 0; i < intervalEndings.size() - 1; i ++){
        int startPoint = intervalEndings[i];
        int endPoint = intervalEndings[i + 1];
        pair<int,int> testingPatch = patches[positionInPatches];
        while(testingPatch.first <= startPoint){
            positionInPatches ++;
        }
        if(testingPatch.first > endPoint){
            continue; // agh we overshot the interval
        }
        // Sliding window
        int patchBestTastinessSingleCow = 0;
        int windowEndPos = positionInPatches + 1;
        int windowSize = (endPoint - startPoint) / 2;
        int oldTastiness = patches[positionInPatches].second;
        int curTasteSum = patches[positionInPatches].second;
        for(;positionInPatches < K && patches[positionInPatches].first <= endPoint; positionInPatches ++){
            // positonInPatches is start

            windowEndPos = max(positionInPatches + 1, windowEndPos);
            for(;windowEndPos < K && (patches[windowEndPos].first - patches[positionInPatches].first) <= windowSize && patches[windowEndPos].first < endPoint; windowEndPos ++){
                // windowEndPos is end
                curTasteSum += patches[windowEndPos].second;
            }
            cout << "Resized to " << positionInPatches << " < " << windowEndPos << endl;
            patchBestTastinessSingleCow = max(patchBestTastinessSingleCow, curTasteSum);
            curTasteSum -= oldTastiness;
            oldTastiness = patches[positionInPatches].second;
        }
        
        cout << "Final Range " << (positionInPatches - 1) << " < -- inclusive -- > " << (windowEndPos - 1) << endl;
        cout << "Section best taste sum: " << patchBestTastinessSingleCow << endl;
    }
}

int main(int argc, char const *argv[])
{
    solve();
    return 0;
}
