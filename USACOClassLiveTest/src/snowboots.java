import java.io.*;
import java.util.*;
public class snowboots {
    static List<Integer> snow;
    static List<Boot> boots;
    static boolean[][] visited = new boolean[250][250];
    static int best = Integer.MAX_VALUE;
    public static void solve(int curBootIndex, int curSnowIndex) {
        if(visited[curBootIndex][curSnowIndex]){
            return;
        }
        visited[curBootIndex][curSnowIndex] = true;
        int range = boots.get(curBootIndex).speed;
        int bootDepth = boots.get(curBootIndex).maxDepth;
        if(curSnowIndex == snow.size()-1){
            best = Integer.min(best, curBootIndex);
            return;
        }
        for(int j = curSnowIndex; j < snow.size() && (j - curSnowIndex) <= range; j ++){
            if(snow.get(j) <= bootDepth){
                solve(curBootIndex,j);
            }
        }
        for(int j = curBootIndex; j < boots.size(); j ++){
            if(snow.get(curSnowIndex) <= boots.get(j).maxDepth){
                solve(j, curSnowIndex);
            }
        }
        //return best;
    }
    public static void main(String[] args) throws IOException{
        // IO
        // new FileReader("cownomics.in")s
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("cownomics.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(f.readLine());
        snow = new ArrayList<>(N);
        for(int i = 0; i < N; i ++){
            int snowDepth = Integer.parseInt(st.nextToken());
            snow.add(snowDepth);
        }
        boots = new ArrayList<>(B);
        for(int i = 0; i < B; i ++){
            st = new StringTokenizer(f.readLine());
            int snowDepth = Integer.parseInt(st.nextToken());
            int snowSpeed = Integer.parseInt(st.nextToken());
            Boot b = new Boot(snowDepth, snowSpeed);
            boots.add(b);
        }
        solve(0, 0);
        pw.println(best);
        pw.close();
    }
}
class Boot{
    int maxDepth;
    int speed;
    public Boot(int maxDepth, int speed){
        this.maxDepth = maxDepth;
        this.speed = speed;
    }
}