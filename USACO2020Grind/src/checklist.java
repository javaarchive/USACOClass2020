import java.io.*;
import java.util.*;

public class checklist {
    static int[][] dp;
    static int[][] Hcows, Gcows;
    static int dist(int[] a, int[] b){
        int A = a[0] - b[0];
        int B = a[1] - b[1];
        return A * A + B * B;
    }
    static void recur(int Hpos,int Gpos, int[] point,boolean useH, int currentDist){
        currentDist += dist(point, (useH?Hcows:Gcows)[useH?Hpos:Gpos]);
        System.out.println(Hpos + " " + Gpos+" : "+currentDist);
        if(dp[Hpos][Gpos] > currentDist){
            dp[Hpos][Gpos] = currentDist;
            if(Hpos < Hcows.length-1){
                recur(Hpos + 1, Gpos, (useH?Hcows:Gcows)[useH?Hpos:Gpos], true, currentDist);
            }
            if(Gpos < Gcows.length-1){
                recur(Hpos, Gpos + 1, (useH?Hcows:Gcows)[useH?Hpos:Gpos], false, currentDist);
            }
        }else{
            return; // La prune
        }
    }
    public static void main(String[] args) throws IOException{
        String inputfile = "checklist.in";
        if (args.length > 0) {
            inputfile = args[0];
        }
        BufferedReader f = new BufferedReader(new FileReader(inputfile));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("checklist.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int H = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        Hcows = new int[H][2]; 
        Gcows = new int[G][2]; 
        for(int i = 0; i < H; i ++){
            st = new StringTokenizer(f.readLine());
            Hcows[i][0] = Integer.parseInt(st.nextToken());
            Hcows[i][1] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < G; i ++){
            st = new StringTokenizer(f.readLine());
            Gcows[i][0] = Integer.parseInt(st.nextToken());
            Gcows[i][1] = Integer.parseInt(st.nextToken());
        }
        dp = new int[H][G];
        for(int i = 0; i < dp.length; i ++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        recur(0, 0, Hcows[0], true, 0);
        recur(0, 0, Hcows[0], false, 0);
        System.out.println(dp[H - 1][G - 1]);
    }
}
