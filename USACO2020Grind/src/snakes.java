import java.io.*;
import java.util.*;
public class snakes {
    public static void main(String[] args) throws IOException{
        String inputfile = "snakes.in";
        if (args.length > 0) {
            inputfile = args[0];
        }
        BufferedReader f = new BufferedReader(new FileReader(inputfile));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("snakes.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] snakes = new int[N];
        int[][] dp = new int[N][N];
        st = new StringTokenizer(f.readLine());
        for(int i = 0; i < N; i ++){
            snakes[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 1; i < N; i ++){
            for(int j = 0; j + i < N; j ++){
                int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
                for(int k = j; k <= j + i; k ++){
                    min = Integer.min(min, snakes[k]);
                    max = Integer.max(max, snakes[k]);
                }
                dp[i][j] = (max - min);
                System.out.println(i+" "+j+" min: "+min+" max: "+max);
            }
        }
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                String s = Integer.toString(dp[i][j]);
                System.out.print(s+"  ".substring(s.length())+" ");
            }
            System.out.println();
        }
        //pw.println(dp[N - 1]);
        pw.close();
    }
}
