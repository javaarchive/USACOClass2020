// NOTE TO SELF: Greedy implementation of lower colors
#define MAXN 100002
#include <iostream>
#include <stack>
#include <algorithm>

using namespace std;
int fence[MAXN];
int forwardC[MAXN];
int backwardC[MAXN];
int N,Q;
int main(int argc, const char** argv) {
    char fenceStr[MAXN];
    cin >> N >> Q;
    cin >> fenceStr;
    for(int i = 0; i < N; i ++){
        fence[i] = (int) fenceStr[i];
    }
    int curStrokes= 1;
    stack<int> curPainting;
    forwardC[0] = 0;
    // forwardC[1] = 1;
    backwardC[0] = 0;
    // backwardC[1] = 1;
    for(int i = 0; i < N; i ++){
        int add = 0;
        while(!curPainting.empty() && curPainting.top() > fence[i]){
            curPainting.pop();
        }
        if(curPainting.empty() || curPainting.top() < fence[i]){
            curPainting.push(fence[i]);
            add += 1;
        }
        forwardC[i + 1] = forwardC[i] + add;
    }
    reverse(fence,fence + N);
    while(!curPainting.empty()){curPainting.pop();}
    curStrokes = 1;
    for(int i = 0; i < N; i ++){
        int add = 0;
        while(!curPainting.empty() && curPainting.top() > fence[i]){
            curPainting.pop();
        }
        if(curPainting.empty() || curPainting.top() < fence[i]){
            curPainting.push(fence[i]);
            add += 1;
        }
        backwardC[i + 1] = backwardC[i] + add;
    }
    /*for(int i = 0; i <= N + 1; i ++){
        cout << forwardC[i] << ",";
    }
    cout << endl;
    */
    // reverse(backwardC, backwardC + N + 1);
    /*
    for(int i = 0; i <= N + 1; i ++){
        cout << backwardC[i] << ",";
    }*/
    // cout << endl;
    for(int i = 0; i < Q; i ++){
        int a,b;
        cin >> a >> b;
        cout << (forwardC[a - 1] + backwardC[N - b]) << endl;
    }
   
}