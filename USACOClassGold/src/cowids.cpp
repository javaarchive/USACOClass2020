#include <iostream>
using namespace std;
int K;
long long N;
int main(int argc, const char** argv) {
    cin >> N >> K;
    string firstString = string(100000, '0');
    for(int i = 0; i < K; i ++){
        firstString += "1";
    }
    // cout << firstString << endl;
    // cout << N << endl;
    if(K == 1){
        cout << "1" << string(N - 1, '0') << endl;
        return 0;
    }
    for(int iter = 1; iter < N; iter ++){
        bool didMove = false;
        int ones = 0;
        for(int i = firstString.length() - 1; i > 0; i --){
            if(firstString[i] == '1' && firstString[i - 1] == '0'){ // can move
                didMove = true;
                //cout << firstString << endl;
                // cout << "moving " << i << " of " << firstString.length() << endl;
                firstString.replace(i - 1,1,"1");
                // cout << firstString.length() << endl;
                firstString.replace(i,firstString.length() - i,string(firstString.length() - i, '0'));
                // cout << firstString.length() << endl;
                firstString.replace(firstString.length() - ones,ones,string(ones, '1'));
                //firstString[i] == 'a';
                //firstString[i - 1] == 'a';
                // cout << iter << ": " << firstString << endl;
                break;
            }
            if(firstString[i] == '1'){
                ones ++;
            }
        }
        /*if(!didMove){
            //cout << "IN: " << firstString << endl;
            int oldLen = firstString.length();
            firstString = firstString.insert(0, "1");
            // cout << "I: " << firstString << endl;
            firstString.replace(1,oldLen, string(oldLen,'0'));
            firstString.replace(firstString.length() - (K - 1),K - 1, string(K-1,'1'));
            // cout << "NEW!!! " << firstString << " at: " << i << endl;
        }*/
        // cout << firstString << endl;
    }
    //cout << firstString << endl;
    bool sawFirst1 = false;
    for(int i = 0; i < firstString.length(); i ++){
        if(firstString[i] == '1') sawFirst1 = true;
        if(sawFirst1){
            cout << firstString[i];
        }
    }
    cout << endl;
    return 0;
}