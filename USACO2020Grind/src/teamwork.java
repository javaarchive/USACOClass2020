import java.io.*;
import java.util.*;
public class teamwork {
    public static void main(String[] args) throws IOException{
        String inputfile = "teamwork.in";
        if (args.length > 0) {
            inputfile = args[0];
        }
        BufferedReader f = new BufferedReader(new FileReader(inputfile));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("teamwork.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] cows = new int[N];
        int[] dp = new int[N + 1];
        for(int i = 0; i < N; i ++){
            cows[i] = Integer.parseInt(f.readLine());
        }
        
        for(int i = 0; i < N; i ++){
            int max = cows[i];
            for(int j = i; j <= Integer.min(N - 1, K + i); j ++){
                System.out.println("i: "+i + " j: "+j);
                max = Integer.max(cows[j], max);
                dp[Integer.min(N, j + i)] 
                = Integer.max(dp[Integer.min(N, j + i)],dp[i] + max);
            }
        }
        System.out.println(Arrays.toString(dp));
    }
}
