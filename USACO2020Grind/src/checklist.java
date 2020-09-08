import java.io.*;
import java.util.*;

public class checklist {
    static int[][][] dp;
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
        dp = new int[H + 1][G + 1][2]; // 0 = now cows iterated yet
        for(int i = 0; i < dp.length; i ++){
            for(int j = 0; j < dp[i].length; j ++){
                dp[i][j][0] = Integer.MAX_VALUE;
                dp[i][j][1] = Integer.MAX_VALUE;
            }
        }
        dp[0][0][0] = 0;
        dp[0][0][1] = 0; // 1: H, 2: G

        for(int i = 0; i < H; i ++){
            dp[i + 1][0][0] = dist(Hcows[0], Hcows[i]);
            dp[i + 1][0][1] = 0;
        }
        for(int i = 0; i < G; i ++){
            dp[0][i + 1][0] = 0;
            dp[0][i + 1][1] = dist(Hcows[0], Gcows[i]);
        }
        for(int i = 1; i <= H; i ++){
            for(int j = 1; j <= G; j ++){
                int opt1 = dp[i - 1][j][0];
                int opt2 = dp[i][j - 1][1];
                if(i == 1){
                    // opt1 = dp[i - 1][j][0];
                }
                System.out.println(opt1+" "+opt2);
                dp[i][j][0] = Integer.min(opt1 + dist(Hcows[i - 1],Hcows[i]), opt2 + dist(Gcows[j - 1],Hcows[i]));
                dp[i][j][1] = Integer.min(opt1 + dist(Gcows[j - 1],Gcows[j]), opt2 + dist(Gcows[j - 1],Gcows[j]));
            }
        }
        for(int i = 0; i < H + 1; i ++){
            for(int j = 0; j < G + 1; j ++){
                System.out.print(dp[i][j][0]+"  ");
            }
            System.out.println();
        }
        for(int i = 0; i < H + 1; i ++){
            for(int j = 0; j < G + 1; j ++){
                System.out.print(dp[i][j][1]+"  ");
            }
            System.out.println();
        }
        /*for(int i = 0; i < H + 1; i ++){
            for(int j = 0; j < G + 1; j ++){
                System.out.print(dpIds[i][j]+"  ");
            }
            System.out.println();
        }*/
        pw.println(dp[H][G][0]);
        pw.close();
    }
}
