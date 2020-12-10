#include <iostream>
#include <queue>
using namespace std;
int P,C;
double F;
bool isFavorite[500] = {false};
int matrix[500][500];
vector<int> favorited;
int solve(){
    for(int k = 0; k < 500; k ++){
        for(int i = 0; i < 500; i ++){
            for(int j = 0; j < 500; j ++){
                if(matrix[i][j] > (matrix[i][k] + matrix[k][j])){
                    matrix[i][j] = matrix[i][k] + matrix[k][j];
                }
            }
        }
    }
    int bestPos = -1;
    double best = 9999999;
    for(int i = 0; i < P; i ++){
        double avg = 0;
        for(int j = 0; j < favorited.size(); j ++){
            cout << i << " , " << favorited[j] << " : "<<matrix[i][favorited[j]] << endl;
            avg += matrix[i][favorited[j]];
            //cout << "New avg: " << avg << endl;
        }
        cout << i << " : " << avg << endl;
        avg = avg/F;
        cout << i << " : " << avg << endl;
        if(avg < best){
            bestPos = i;
            best = avg;
        }
        //double result = (double)(sum/favorite.size());
    }
    return bestPos;
}
int main(int argc, const char** argv) {
    cin >> P >> F >> C;
    for(int i = 0; i < F; i ++){
        int fav;
        cin >> fav;
        fav --;
        isFavorite[fav] = true;
        favorited.push_back(fav);
    }
    for(int i = 0; i < 500; i ++){
        for(int j = 0; j < 500; j ++){
            matrix[i][j] = INT32_MAX/2;
        }
    }
    for(int i = 0; i < C; i ++){
        int A,B,dist;
        cin >> A >> B >> dist;
        A --;
        B --;
        matrix[A][B] = dist;
        matrix[B][A] = dist;
    }
    cout << (solve());

    return 0;
}