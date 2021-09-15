#include <iostream>
using namespace std;

// failing
void solve(){
    int n;
    cin >> n;
    int arr[n];
    int odds = 0, evens = 0;
    for (int i = 0; i < n; i++) {
        int temp;
        cin >> temp;
        arr[i] = temp;
        if (temp % 2 == 0) {
            evens++;
        } else {
            odds++;
        }
    }
    if(abs(odds - evens) > 1){
        cout << -1 << endl;
        return;
    }else if(n == 1){
        cout << 0 << endl;
        return;
    }
    int oddIndex = 0;
    int evenIndex = 0;
    // cout << "MMM" << endl;
    while(arr[oddIndex] % 2 == 0 && oddIndex < n){
        oddIndex++;
    }

    while(arr[evenIndex] % 2 == 1 && evenIndex < n){
        evenIndex++;
    }

    bool boundOut = false;
    int ans = 0;
    // cout << "Main loop " << endl;
    for(int i = 0; i < n && !boundOut; i ++){
        // cout << oddIndex << " and " << evenIndex << " + " << abs(oddIndex - evenIndex) << endl;
       
        oddIndex ++;
        evenIndex ++;
        if(oddIndex >= n || evenIndex >= n){
            boundOut = true;
            continue;
        }

        if(i > 0 && arr[i] % 2 == arr[i - 1] % 2){
            if(i % 2 == 0 && arr[0] % 2 == 1 || i % 2 == 1 && arr[0] % 2 == 0){
                while(arr[oddIndex] % 2 == 0){
                    oddIndex++;
                    if(oddIndex >= n){
                        boundOut = true;
                        break;
                    }
                }
                if(boundOut){
                    break;
                }
                cout << oddIndex << " <-O-> " << i << endl;
                 ans += abs(oddIndex - i);
            }else{
                while(arr[evenIndex] % 2 == 1){
                    evenIndex++;
                    if(evenIndex >= n){
                        boundOut = true;
                        break;
                    }
                }
                if(boundOut){
                    break;
                }
                cout << evenIndex << " <-E-> " << i << endl;
                ans += abs(evenIndex - i);
            }
           
        }

        

        
    }
    cout << "Pog: " << ans << endl;
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
