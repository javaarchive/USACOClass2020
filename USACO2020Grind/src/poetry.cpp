#include <iostream>
#include <map>
#include <unordered_map>
#include <set>

#define answerMOD 1000000007LL
#define MAXLEN 5001
#define MAXN 5003

using namespace std;

int N, M, K;

unordered_map<char, int> counts;
//      class,length
// map<pair<int, int>, int> rhymeCountsStrict;
int rhymeCounts[MAXN] = {0};
pair<int, int> words[MAXN];
long long rhymeWays[MAXN] = {0LL};
set<int> rhymeIDs;
int maxWrite = 0; // Cool optimization I made

long long dp[MAXLEN] = {0LL};

long long powerMod(long long base, int power)
{
    if(power == 0LL){
        return 1LL;
    }else if(power == 1){
        return base % answerMOD;
    }else if(power % 2 == 1){
        long long sqrt = powerMod(base,power/2);
        return ((sqrt * sqrt) % answerMOD) * base % answerMOD;
    }else{
        long long sqrt = powerMod(base,power/2);
        return ((sqrt * sqrt) % answerMOD);
    }
    /*long long out = 1;
    for (int i = 0; i < power; i++)
    {
        out = (out * base ) % answerMOD;
    }*/
    // cout << base << ", " << power << " = " << out << endl;
    return -1LL;
}

// power = extra lines with same rhyme scheme
void genDP()
{
    for (int i = 0; i <= K; i++)
    {
        dp[i] = 0;
    }
    dp[0] = 1;
    for (int i = 0; i < K; i++)
    {
        if (dp[i] == 0)
        {
            continue;
        }
        for (int j = 0; j < N; j++)
        {
            if ((i + words[j].first) > K)
            {
                continue;
            }
            //long long mult = 1;
            /*if(power > 0 && (i + words[j].first) == K){
                mult = powerMod(rhymeCounts[make_pair(words[j].second, words[j].first)],power);
            }*/
            dp[(i + words[j].first)] += dp[i]; // * mult; // pick any word of the same rhyme class, to the power of how many extra lines
            dp[(i + words[j].first)] = (dp[(i + words[j].first)] ) % answerMOD;
        }
    }
    for (int i = 0; i <= K; i++)
    {
        dp[i] = (dp[i] ) % answerMOD;
    }

    /*for (int i = 0; i <= K; i++)
    {
        cout << dp[i] << " ";
    }
    cout << endl;*/
}
long long calcRhymes(int count)
{
    // cout << "CALCULATING " << count << endl;
    long long out = 0;
    // genDP();
    /*for(int i = 0; i < count; i ++){
        out = (out * dp[K]) % answerMOD;
    }*/
    // cout << "PASSING OUT " << out << endl;
    //bool seenRhymeType[MAXN] = {false};
    // for (int i = 1; i <= min(K,maxWrite); i++)
    for(auto rhymeIDIter = rhymeIDs.begin(); rhymeIDIter != rhymeIDs.end(); rhymeIDIter ++)
    {
        // int backLen = words[i].first;
        // int rhymeClass = words[i].second;
        /*if(seenRhymeType[rhymeClass]){
            continue;
        }else{
            seenRhymeType[rhymeClass] = true;
        }*/
        // * rhymeCounts[rhymeClass]
        // rhymeWays[i]
        long long plus = powerMod(rhymeWays[*rhymeIDIter], count);
        // cout << "+ " << plus << endl;
        out = (out + plus ) % answerMOD;
    }
    return out;
}

void setIO(string s) {
    ios_base::sync_with_stdio(0); cin.tie(0);
    freopen((s + ".in").c_str(), "r", stdin);
    freopen((s + ".out").c_str(), "w", stdout);
}

int main(int argc, char const *argv[])
{

    if(argc != 2){
        setIO("poetry");
    }

    // Self test

    if (powerMod(2, 10) != 1024LL)
    {
        cout << "Assertion Failed" << endl;
        return 1;
    }

    // Main

    cin >> N >> M >> K;

    int rhymeTypes = 0;

    for (int i = 0; i < N; i++)
    {
        cin >> words[i].first >> words[i].second;
        rhymeCounts[words[i].second] ++;
        rhymeIDs.insert(words[i].second);
        // rhymeCountsStrict[make_pair(words[i].second, words[i].first)] ++;
    }

    // dp[0] = 1;
    for (int i = 0; i < M; i++)
    {
        char c;
        cin >> c;
        counts[c] ++;
        if (counts[c] == 1)
        {
            rhymeTypes ++;
        }
    }

    genDP();

    for(int i = 0; i < N; i ++){
        rhymeWays[words[i].second] += dp[K - words[i].first];
        rhymeWays[words[i].second] = rhymeWays[words[i].second] % answerMOD;
        // maxWrite = max(maxWrite,words[i].second);
    }

    long long output = 1;
    for (int i = 0; i <= 26; i++)
    {
        if(counts[(int)(i + 65)] == 0){
            continue;
        }
        long long mult = calcRhymes(counts[(int)(i + 65)]);
        // cout << "Multiplying " << mult << endl;
        output = (output * mult ) % answerMOD;
    }
    cout << output << endl;

    return 0;
}
