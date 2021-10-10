#include <iostream>
#include <vector>
#include <algorithm>
#define MAXNM 301
using namespace std;

void solve(){
    vector<pair<int,int>> v;
    int N,M;
    cin >> N >> M;
    for(int i = 0; i < N*M; i ++){
        int eyes;
        cin >> eyes;
        v.push_back(make_pair(eyes,i + 1));
    }
    sort(v.begin(),v.end());
    /*sort(v.begin(),v.end(),[](pair<int,int> a, pair<int,int> b){
        if(a.second == b.second){
            return a.first < b.first;
        }
        return a.second < b.second;
    });*/
    int ans = 0; // , lastAns = 0;
    int count = 0;
    /*for(int i = 0; i < N*M; i ++){
        if(i > 0 && (v[i].first - v[i - 1].first) == 0){
            // ans += i;
        }else{
            ans += count;
            count ++;
        }
        lastAns = ans;
    }*/
    int startWindow = 0;
    
    int R = N * M;

    // pair (eyerange, position)

    int writingX = 0;
    int writingY = 0;

    int final[MAXNM][MAXNM];

    
    for(int i = startWindow; startWindow < R && i < R; i ++){
        if(i == R - 1 || (v[i].first != v[i + 1].first)){
            // end section
            int windowEnd = i;
            int eyeLevel = v[startWindow].first;
            // cout << "Acquire Window " << startWindow << " to " << windowEnd << " intial etcher position " << writingY << "," << writingX << endl;
            int writesLeft = windowEnd - startWindow + 1; // inclusive
            //cout << "Performing " << writesLeft << " writes" << endl;
            int copyPointer = startWindow;
            while(writesLeft > 0){
                //cout << "Writes left " << writesLeft << " etcher pos " << writingY << " and " << writingX << " pointer " << copyPointer << endl;
                int spaceLeft = M - writingX;
                if(writesLeft >= spaceLeft && writingX == 0){
                    // Overflowing Writes
                    writingX = M - 1;
                    writesLeft -= spaceLeft; // consume
                    for(int idk = 0; idk < spaceLeft; idk ++){
                        // cout << "Bulk Writing to " << writingY << "," << writingX <<  " pos: " << copyPointer << " ";
                        //cout << " content: " << v[copyPointer].second << endl;
                        final[writingY][writingX] = v[copyPointer].second;
                        copyPointer ++;
                        writingX --;
                    }
                    writingY ++;
                    writingX = 0;
                }else{
                    // Perform some writes on some positions but not all
                    // writes left or 
                    int range = min(writesLeft,M - writingX);
                    writingX += range - 1;
                    bool skippedToNextLine = false;
                    for(int writesToPerform = range; writingX >= 0 && writesToPerform > 0; writesToPerform --){
                        // cout << "Tiny Writing to " << writingY << "," << writingX << " content: " << v[copyPointer].second << " from " << copyPointer << endl;
                        final[writingY][writingX] = v[copyPointer].second; // write index
                        copyPointer ++;
                        writingX --;
                        /*if(writingX < 0){
                            writingY ++;
                            writingX = 0;
                            cout << "NEXT LINE SKIP ??? " << endl;
                            skippedToNextLine = true;
                        }*/
                        writesLeft --;
                    }
                    
                    // if(!skippedToNextLine){
                        writingX += range + 1; // bounce back
                    // }
                    if(writingX >= M){
                        writingY ++;
                        writingX = 0;
                    }
                    

                }
            }
            // cout << "New start window at " << (i + 1) << endl;
            startWindow = i + 1;
        }


    }
    /*cout << "FINAL: " << endl;
    for(int i = 0; i < N; i ++){
        for(int j = 0; j < M; j ++){
            cout << final[i][j] << " ";
        }
        cout << endl;
    }*/

    for(int i = 0; i < N; i ++){
        for(int j = 0; j < M; j ++){
            int order = final[i][j];
            for(int k = j + 1; k < M; k ++){
                if(order < final[i][k]){
                    ans ++;
                }
            }
        }
    }

    cout << "" << ans << endl;
}

int main(int argc, char const *argv[])
{
    int T;
    cin >> T;
    for(int i = 0; i < T; i ++){
        solve();
    }
    return 0;
}
