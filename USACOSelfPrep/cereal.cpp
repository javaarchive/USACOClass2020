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
int out[100001];
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
            if(pickedNewCereal[takenCow] == 0){
                pickedNewCereal[takenCow] ++;
                int newTkCow = takenIDs[tcow.second];
               
                if(newTkCow < takenCow && newTkCow != -1){
                    hasCereal --;
                    break;
                }
                takenIDs[tcow.second] = takenCow;
                takenCow = newTkCow;
                 if(newTkCow == -1){
                    break;
                }
            }else{
                //pickedNewCereal[takenCow] ++;
                // Moo in disappointment
                //cout << takenCow << " moos in disappointment" << endl;
                if(takenCow == -1){
                    break;
                }
                hasCereal --;
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
    setIO("cereal");
    cin >> N >> M;
    for(int i = 0; i < N; i ++){
        pair<int,int> p;
        cin >> p.first >> p.second;
        cows[i] = p;
    }
    //vector<int> out;
    
    fill(takenIDs, takenIDs + 100001, -1);
    for(int i = 0; i < N; i ++){
        hasCereal ++;
        //cout << "add " << (N - i - 1) << endl;
        addCow(N - i - 1);
        out[i] = hasCereal;
       // out.insert(out.begin(), hasCereal);
    }
    for(int i = N - 1; i >= 0; i --){
        cout << out[i] << endl;
    }
}