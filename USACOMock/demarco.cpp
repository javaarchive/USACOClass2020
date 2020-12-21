#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

typedef long long ll;
typedef vector<int> vi;
typedef vector<pair<int,int>> vpi;
# 20 "tmp.cpp"
void setIO(string name) {
 ios_base::sync_with_stdio(0); cin.tie(0);
 freopen((name+".in").c_str(),"r",stdin);
 freopen((name+".out").c_str(),"w",stdout);
}

int N,L;
vi w,x,d;

void init() {
 setIO("meetings");
 cin >> N >> L;
 w.resize(N), x.resize(N), d.resize(N);
 for (int i = (0); i < (N); ++i) cin >> w[i] >> x[i] >> d[i];
 vi inds(N); iota(begin(inds), end(inds),0);
 sort(begin(inds), end(inds),[](int a, int b) { return x[a] < x[b]; });
 vi W,X,D;
 for (auto& t: inds) {
  W.push_back(w[t]);
  X.push_back(x[t]);
  D.push_back(d[t]);
 }
 swap(w,W), swap(x,X), swap(d,D);
}

int getTime() {
 vi lef, rig;
 for (int i = (0); i < (N); ++i) {
  if (d[i] == -1) lef.push_back(x[i]);
  else rig.push_back(x[i]);
 }
 vpi v;
 for (int i = (0); i < (int(lef.size())); ++i) v.push_back({lef[i],w[i]});
 for (int i = (0); i < (int(rig.size())); ++i) v.push_back({L-rig[i],w[int(lef.size())+i]});
 sort(begin(v), end(v));
 int tot = 0; for (auto& t: v) tot += t.second;
 for (auto& t: v) {
  tot -= 2*t.second;
  if (tot <= 0) return t.first;
 }
}

int main() {
 init();
 int t = getTime();
 queue<int> rig;
 int ans = 0;
 for (int i = (0); i < (N); ++i) {
  if (d[i] == -1) {
   while (int(rig.size()) && rig.front()+2*t < x[i]) rig.pop();
   ans += int(rig.size());
  } else rig.push(x[i]);
 }
 cout << ans << "\n";
}
