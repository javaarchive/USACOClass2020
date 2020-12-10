#include <iostream>
#include <vector>

using namespace std;
int N,M;
void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}
int takenIDs[100001];
int pickedNewCereal[100001] = {0};
int hasCereal = 0;
pair<int,int> cows[100001];
void addCow(int i){
    pair<int,int> cow = cows[i];
    int oldCow = takenIDs[cow.first];
    if(oldCow != -1){
        int takenCow = oldCow;
        takenIDs[cow.first] = i;
        // pickedNewCereal[takenCow] ++;
        
        while(true){
            //cout << "Cow needs new pos: " << takenCow << endl;
            pair<int,int> tcow = cows[takenCow];
            /*for(int i = 0; i < 10; i ++){
                cout << takenIDs[i] << " ";
            }
            cout << endl;*/
            /*for(int i = 0; i < 10; i ++){
                cout << pickedNewCereal[i] << " ";
            }
            cout << endl;*/
            if(takenCow == -1){
                    break;
                }
            if(pickedNewCereal[takenCow] == 0){
                pickedNewCereal[takenCow] ++;
                int newTkCow = takenIDs[tcow.second];
                takenIDs[tcow.second] = takenCow;
                takenCow = newTkCow;
                if(takenCow == -1){
                    break;
                }
            }else{
                pickedNewCereal[takenCow] ++;
                // Moo in disappointment
                //cout << takenCow << " moos in disappointment" << endl;
                hasCereal --;
                if(takenCow == -1){
                    break;
                }
                
                break;
            }
            
        }

    }else{
        takenIDs[cow.first] = i;
    }
    /*for(int i = 0; i < 10; i ++){
                cout << takenIDs[i] << " ";
            }
            cout << endl;*/
    //takenIDs[cow.first] = i;
}
int main(){
    setIO("cereal2");
    cin >> N >> M;
    for(int i = 0; i < N; i ++){
        pair<int,int> p;
        cin >> p.first >> p.second;
        cows[i] = p;
    }
    vector<int> out;
    fill(pickedNewCereal, pickedNewCereal + 100001,0);
    fill(takenIDs, takenIDs + 100001, -1);
    
    for(int i = 0; i < N; i ++){
        hasCereal ++;
        //cout << "add " << (N - i - 1) << endl;
        addCow(N - i - 1); // Check if any cows get kicked off and if they do decrement
        out.insert(out.begin(), hasCereal);
    }
    for(int i = 0; i < out.size(); i ++){
        cout << out[i] << endl;
    }
}