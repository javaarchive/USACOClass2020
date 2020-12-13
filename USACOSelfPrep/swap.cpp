#include <iostream>
#include <vector>
#include <cmath>

using namespace std;
void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}
int N,M,K;
struct Move{
    vector<int> transform;
    Move(){
        //cout << "init";
        for(int i = 0; i < N; i ++){
            transform.push_back(i);
        }
    }
    void process(int a, int b){
        for(int i = a; i <= floor(a+(b-a)/2); i ++){
            int otherEnd = b - (i - a);
            int temp = transform[otherEnd];
            transform[otherEnd] = transform[i];
            transform[i] = temp;
        }
    }
    Move clone(){
        vector<int> newVect(transform);
        Move m;
        m.transform = newVect;
        return m;
    }
    void apply(Move m){
        vector<int> before(transform); // Copy before state
        for(int i = 0; i < N; i ++){
            transform[i] = before[m.transform[i]];
        }

    }
};
void solve(Move m1){
    Move pow2[32]; // 10^9 log_2 = approx 29
    int generateUpTo = log2(K) + 1;
    pow2[0] = m1; // 2^0 = 1
    for(int i = 1; i <= generateUpTo; i ++){
        Move newm;
        //newm.init(;
        /*cout << "I: " << i << " :";
        for(int i = 0 ;i < newm.transform.size(); i ++){
            cout << newm.transform[i] << " ";
        }*/
        newm.apply(pow2[i - 1]);
        /*cout << "I: " << i << " :";
        for(int i = 0 ;i < newm.transform.size(); i ++){
            cout << newm.transform[i] << " ";
        }*/
        newm.apply(pow2[i - 1]);
        /*cout << "I: " << i << " :";
        for(int i = 0 ;i < newm.transform.size(); i ++){
            cout << newm.transform[i] << " ";
        }
        cout << endl;*/
        pow2[i] = newm;
    }
    int curNum = K;
    Move final;
    for(int i = N; i >= 0; i --){
        int power = (i != 0)?(1 << i):1;
        if(curNum >= power){
            //cout << "Applying " << i << endl;
            final.apply(pow2[i]);
            curNum -= power;
        }
    }
    for(int i = 0; i < N; i ++){
        cout << (final.transform[i] + 1) << endl;
    }

}
int main(int argc, const char** argv) {
    setIO("swap");
    cin >> N >> M >> K;
    Move m;
    int a,b;
    for(int i =0 ; i < M; i ++){
        cin >> a >> b;
        a --; b --;
        m.process(a,b);
    }
     /*for(int i = 0 ;i < m.transform.size(); i ++){
            cout << m.transform[i] << " ";
        }
        cout << endl;*/
    solve(m);
    return 0;
}
