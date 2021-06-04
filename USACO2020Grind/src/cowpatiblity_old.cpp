#include <iostream>
#define MAXID 1000002
#define MAXN 50001
int overlaps[MAXID] = {0};

using namespace std;

int N;

int main(int argc, char const *argv[])
{
    cin >> N;
    long long totalEdges = (N * (N - 1))/2;
    long long compatibleCows = 0;
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < 5; j ++){
            int newID;
            cin >> newID;
            int potientialFriends = overlaps[newID];
            overlaps[newID] ++;
            compatibleCows += potientialFriends;
        }
    }
    for(int i = 0; i < 90; i ++){
        cout << overlaps[i] << " ";
    }
    cout << endl;
    cout << totalEdges << " - " << compatibleCows << endl;
    cout << totalEdges-compatibleCows << endl;
    return 0;
}
