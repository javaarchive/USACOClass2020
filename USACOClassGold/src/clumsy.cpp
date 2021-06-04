#include <iostream>
#include <iostream>
using namespace std;

int main(int argc, char const *argv[])
{
    string line;
    cin >> line;
    int N = line.size();
    int sum = 0;
    int flips = 0;
    for(int i = 0; i < N; i ++){
        char c = line[i];
        int charCode = c;
        switch(charCode){
            case '(':
                sum ++;
                break;
            case ')':
                sum --;
                if(sum < 0){
                    // FLIP!
                    flips ++;
                    sum += 2;
                }
                break;
            default:
                break;
        }
        
    }
    //cout << "Flips: " << flips << endl;
    flips += sum/2; // overshot
    cout << flips;
    return 0;
}
