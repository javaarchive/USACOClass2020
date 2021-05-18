#include <iostream>
#include <vector>
#define MAXN 100001
using namespace std;

int N;
int incoming[MAXN];
vector<vector<int>> stacks;
bool viable(int position, int value){
    if(stacks[position].back() < value){
        // would be in wrong order
        // example:
        /*
        5
        2
        3
        */
        return false;
    }
    if(position > 0){
        if(value < stacks[position - 1].front()){
            return false;
        }
    }
    return true;
}


int main(int argc, char const *argv[])
{
    cin >> N;
    for (int i = 0; i < N; i++)
    {
        cin >> incoming[i];
    }
    int maxPopped = 0;
    for (int i = 0; i < N; i++)
    {
        // cout << "PROCESSING " << incoming[i] << endl;
        if(maxPopped > incoming[i]){
            // cout << "ENDING" << endl;
            cout << i << endl;
            break;
        }
        cout << "SUMMARY for " << i << endl;
        for (int i = 0; i < stacks.size(); i++)
        {
            cout << i << " : ";
            for (int j = 0; j < stacks[i].size(); j++)
            {
                cout << stacks[i][j] << " ";
            }
            cout << endl;
        }
        if (stacks.empty())
        {
            stacks.push_back(vector<int>());
            stacks[0].push_back(incoming[i]);
            //cout << "Trivial added " << incoming[i] << endl;
        }
        else
        {
            int L = 0, R = stacks.size() - 1;
            //cout << "INIT BS " << L << " and " << R << endl;
            while (L < R)
            {
                int middle = (L + R) / 2;
                //cout << L << " " << middle << " " << R << endl;
                if (viable(middle, incoming[i]))
                {
                    // get a larger number
                    R = middle;
                }
                else
                {
                    L = middle + 1;
                }
            }
            int targetStack = R;
            if(!viable(R,incoming[i])){
                // See what happens if we create a new stack
                // is it possible
                int curLargest = stacks.back().front();
                if(incoming[i] < curLargest){
                    // Dry dishes
                    while(!stacks.empty()){
                        if(stacks.front().empty()){
                            stacks.erase(stacks.begin()); // remove first
                            continue;
                        }
                        int topElem = stacks.front().back(); // get top
                        if(topElem < incoming[i]){
                            // Take away this element as we need to pop more off the stack to find a place to insert
                            maxPopped = max(maxPopped, topElem);
                            stacks.front().erase(prev(stacks.front().end()));
                        }else{
                            stacks.front().push_back(incoming[i]);
                            break;
                        }
                    }
                    //cout << "???" << endl;
                }else{
                    // New stack!
                    stacks.push_back(vector<int>());
                    stacks.back().push_back(incoming[i]);
                }
            }else if (targetStack < 0)
            {
                stacks.push_back(vector<int>());
                stacks[stacks.size() - 1].push_back(incoming[i]);
            }
            else
            {
                stacks[R].push_back(incoming[i]);
            }
            //cout << "Target stack: " << (R) << endl;
        }
    }
    /*cout << "END SUMMARY" << endl;
    for (int i = 0; i < stacks.size(); i++)
    {
        cout << i << " : ";
        for (int j = 0; j < stacks[i].size(); j++)
        {
            cout << stacks[i][j] << " ";
        }
        cout << endl;
    }*/

    return 0;
}
