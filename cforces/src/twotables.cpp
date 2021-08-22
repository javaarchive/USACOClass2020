#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

/*
 *      N
 *     ---
 *  W |   |
 *    |   | E
 *     ---
 *      S
 */
void solve(){
    int W,H;
    cin >> W >> H;
    int x1,y1,x2,y2;
    cin >> x1 >> y1 >> x2 >> y2;

    int w,h;
    cin >> w >> h;

    // obviously
    if(w > W || h > H){
        // sanity
        // cout << "SANITY";
        cout << -1 << endl;
    }

    // Distances from walls
    int NORTHERN_DIST = H - y2;
    int SOUTHERN_DIST = y1;
    int EASTERN_DIST  = W - x2;
    int WESTERN_DIST  = x1;

    vector<pair<int,int>> choices;
    choices.push_back(make_pair(0, h - NORTHERN_DIST));
    choices.push_back(make_pair(1, h - SOUTHERN_DIST));
    choices.push_back(make_pair(2, w - EASTERN_DIST));
    choices.push_back(make_pair(3, w - WESTERN_DIST));
    // works anyway
    if(NORTHERN_DIST >= h || SOUTHERN_DIST >= h){
        cout << 0 << endl;
        return;
    }
    if(WESTERN_DIST >= w || EASTERN_DIST >= w){
        cout << 0 << endl;
        return;
    }
    


    sort(choices.begin(), choices.end(), [](const pair<int,int>& a, const pair<int,int>& b){
        /*int predictA = 0, predictB = 0;
        if(a.first == 0 || a.first == 1){
            predictA = h - a.second;
        }
        return predictA < predictB;*/
        return a.second < b.second;
        // return a.second > b.second;
    });
    // cout << "PRE: ";
    // cout << endl << "SORT: " << choices[0].second << " , " << choices[1].second << choices[2].second << choices[3].second << endl;
    
    for(int i = 0; i < 4; i ++){
        pair<int,int> choice = choices[i];
        int id = choice.first;
        // bool worked = false;
        switch(id){
            case 0:
                // headroom check
                if((h - NORTHERN_DIST) <= SOUTHERN_DIST){
                    cout << (h - NORTHERN_DIST) << endl;
                    return;
                }
                break;
            case 1:
                if((h - SOUTHERN_DIST) <= NORTHERN_DIST){
                    cout << (h - SOUTHERN_DIST) << endl;
                    return;
                }
                break;

            case 2:
                if((w - EASTERN_DIST) <= WESTERN_DIST){
                    cout << (w - EASTERN_DIST) << endl;
                    return;
                }
                break;

            case 3:
                if((w - WESTERN_DIST) <= EASTERN_DIST){
                    cout << (w - WESTERN_DIST) << endl;
                    return;
                }
                break;
        
        }
        /*if(worked){
            break;
        }*/
    }
    cout << -1 << endl;     
}

int main(int argc, char const *argv[])
{
    int T;
    cin >> T;
    for (int i = 0; i < T; i ++){
        solve();
    }
    return 0;
}
