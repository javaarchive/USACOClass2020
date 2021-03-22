import java.io.*;
import java.util.*;

public class teamwork {
    public static void main(String[] args) throws IOException {
        String inputfile = "teamwork.in";
        if (args.length > 0) {
            inputfile = args[0];
        }
        // BufferedReader f = new BufferedReader(new FileReader(inputfile));
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // PrintWriter pw = new PrintWriter(new BufferedWriter(new
        // FileWriter("teamwork.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] cows = new int[N];
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            cows[i] = Integer.parseInt(f.readLine());
        }
        // dp[0] = cows[0];
        for (int i = 0; i < N - 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < (K + i); j++) {
                // System.out.println("i: "+i + " j: "+j+" MAX: "+max);
                if (j >= N) {
                    break;
                }
                max = Integer.max(max, cows[j]);
                int prev = 0;
                if (i > 0) {
                    prev = dp[i - 1];
                }
                dp[j] = Integer.max(dp[j], prev + max * (j - i + 1));

            }
        }
        System.out.println(dp[N - 1]);
        // pw.close();
    }
}
