#include <tchar.h>
#include <windows.h>
#include <winuser.h>
#include <iostream>
using namespace std;
void runUnknownExp1(){
   
}
int main(int argc, const char** argv) {
    cout << "Run Experiment: ";
    int expid;
    cin >> expid;
    switch(expid){
        case 1:
            runUnknownExp1();
            break;
    }
    return 0;
}