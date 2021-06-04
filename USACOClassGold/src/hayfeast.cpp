#include <iostream>
#define MAXN 100001

using namespace std;

long long N, M;
long long spiceAmounts[MAXN];
long long flavorAmounts[MAXN];

int trySpice(int spiceLevel){
    long long curFlavor = 0;
    long long maxFlavorAchieved = 0;

    for(int i = 0; i < N; i ++){
        if(spiceAmounts[i] > spiceLevel){
            curFlavor = 0;
        }else{
            curFlavor += flavorAmounts[i];
        }
        maxFlavorAchieved = max(curFlavor, maxFlavorAchieved);
        if(curFlavor > M){
            return -1;
        }
    }
    if(M <= maxFlavorAchieved){
        return -1;
    }else{
        return 1;
    }
}

int main(int argc, const char** argv) {
    cin >> N >> M;
    for(int i = 0; i < N; i ++){
        cin >> flavorAmounts[i] >> spiceAmounts[i];
    }
    //                   123456789
    int low = 0, high = 1000000000;
    while(low < high){
        int mid = (low + high) / 2;
        //cout << low << " " << mid << " " << high << endl;
        //cout << "trySpice(" << mid << ") = " << trySpice(mid) << endl;
        if(trySpice(mid) == 1){
            low = mid + 1;
        }else{
            high = mid;
        }
    }
    cout << low << endl;
    return 0;
}