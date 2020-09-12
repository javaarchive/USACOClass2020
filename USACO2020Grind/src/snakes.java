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
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                if((i + j) >= N){
                    continue;
                }
                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;
                for(int k = i; k <= i + j; k ++){
                    max = Integer.max(max, snakes[k]);
                    min = Integer.min(min, snakes[k]);
                }
                dp[i][j] = max - min;
            }
        }
        for(int i = 0; i < N; i ++){
            System.out.println(Arrays.toString(dp[i]));
        }
        int best = Integer.MAX_VALUE;
        int[][] dp2 = new int[N][N];
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                dp2[i][j] = Integer.MAX_VALUE;
            }
        }
        dp2[0] = Arrays.copyOf(dp[0], dp.length);
        //System.out.println("Row 1: "+Arrays.toString(dp2[0]));
        
        for(int i = 0; i < N; i ++){
            System.out.println(Arrays.toString(dp2[i]));
        }
        System.out.println(best);

        //pw.println(dp[N - 1]);
        pw.close();
    }
}
