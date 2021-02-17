#include <iostream>
#include <vector>
#include <string>
using namespace std;
vector<string> split(string cur,string delim){
    int dLength = delim.length();
    vector<string> out;
    while(!cur.find(delim) == string::npos){
        int pos = cur.find(delim);
        cout << pos;
        out.push_back(cur.substr(0,pos));
        cur = cur.substr(pos + dLength, cur.length() - 1);
    }
    return out;
}
int main(){
    vector<string> spl = split("a big rock"," ");
    for(int i = 0; i < spl.size(); i ++){
        cout << spl[i] << endl;
    }
}