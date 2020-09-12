import java.io.*;
import java.util.*;

public class snakes {
    public static void main(String[] args) throws IOException {
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
        int[][] diff = new int[N][N];
        st = new StringTokenizer(f.readLine());
        for (int i = 0; i < N; i++) {
            snakes[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) >= N) {
                    continue;
                }
                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;
                for (int k = i; k <= i + j; k++) {
                    max = Integer.max(max, snakes[k]);
                    min = Integer.min(min, snakes[k]);
                }
                diff[i][i + j] = max - min;
            }
        }
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(diff[i]));
        }
        int[][] dp = new int[K + 1][N + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE/2);
        }
        dp[0][0] = 0;
        // System.out.println("Row 1: "+Arrays.toString(dp2[0]));
        int mx0 = 0;
        for (int j = 1; j <= N; j++) {
            mx0 = Integer.max(mx0, snakes[j-1]);
            dp[0][j] =j*mx0;
            for (int i = 1; i <= K; i++) {
                
                int mx =snakes[j - 1];                   
                for (int k = j-1 ; k >= 0; k--) {
                    mx = Integer.max(mx, snakes[k]);
                    dp[i ][j] = Integer.min(dp[i][j], dp[i - 1][k] + mx * (j - k));
                    
                }
                
            }
        }
        for (int i = 0; i < K + 1; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }

        // pw.println(dp[N - 1]);
        pw.close();
    }
}
