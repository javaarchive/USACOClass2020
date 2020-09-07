import java.io.*;
import java.util.*;

public class checklist {
    static int[][] dp;
    static int[][] dpIds;
    static int[][] Hcows, Gcows;
    static int dist(int[] a, int[] b){
        int A = a[0] - b[0];
        int B = a[1] - b[1];
        return A * A + B * B;
    }
    static int[] getPoint(int id){
        if(id > 0){
            return Hcows[id - 1];
        }else if(id < 0){
            return Gcows[-id - 1];
        }
        return new int[2];
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
        dp = new int[H + 1][G + 1]; // 0 = now cows iterated yet
        dpIds = new int[H + 1][G + 1];
        for(int i = 0; i < dp.length; i ++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        dpIds[0][0] = 1;
        for(int i = 0; i < H; i ++){
            dp[i + 1][0] = dist(Hcows[0], Hcows[i]);
            dpIds[i + 1][0] = i + 1;
        }
        for(int i = 0; i < G; i ++){
            dp[0][i + 1] = dist(Hcows[0], Gcows[i]);
            dpIds[0][i + 1] = -(i + 1);
        }
        for(int i = 1; i <= H; i ++){
            for(int j = 1; j <= G; j ++){
                int opt1 = dist(getPoint(dpIds[i - 1][j]),Hcows[i - 1]) + dp[i - 1][j];
                int opt2 = dist(getPoint(dpIds[i][j - 1]),Gcows[j - 1]) + dp[j][i - 1];
                if(opt1 < opt2){
                    dp[i][j] = opt1;
                    dpIds[i][j] = i;
                }else{
                    dp[i][j] = opt2;
                    dpIds[i][j] = -(j);
                }
            }
        }
        for(int i = 0; i < H + 1; i ++){
            for(int j = 0; j < G + 1; j ++){
                System.out.print(dp[i][j]+"  ");
            }
            System.out.println();
        }
        pw.println(dp[H][G]);
        pw.close();
    }
}
