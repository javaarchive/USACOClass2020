import java.io.*;
import java.util.*;

public class cbarn2 {
    static int[] ps;
    static int K;
    static int N;
    //static int min = Integer.MAX_VALUE;
    static int[][] dp;
    static int recur(int pos, int seps, int curSum){
        
        if(seps >= K){
            if(pos > N-1){
                pos = N - 1;
                //return Integer.MAX_VALUE;
            }
            System.out.println("Part 1: "+(ps[N - 1] - ps[pos])+" "+((N - 1 - pos) * (N - 1 - pos)));
            curSum = (ps[N - 1] - ps[pos]) - ((N - 1 - pos) * (N - 1 - pos));
            dp[N - 1][K - 1] = curSum;
            System.out.println("recur("+pos+","+seps+","+curSum+") = "+curSum);
            return curSum;
        }
        int min = Integer.MAX_VALUE;
        for(int i = pos + 1; i < N; i ++){
            //System.out.println("iter: "+i);
            min = Integer.min(min, recur(i, seps + 1, curSum + (ps[i] - ps[pos] + (i - pos)*(i - pos))));
        }
        
        //System.out.println("Previous Value "+dp[pos][seps]);
        dp[pos][seps] = Integer.min(min, dp[pos][seps]);
        System.out.println("recur("+pos+","+seps+","+curSum+") = "+dp[pos][seps]);
        return dp[pos][seps];
    }
    public static void main(String[] args) throws IOException{
        String inputfile = "cbarn2.in";
        if (args.length > 0) {
            inputfile = args[0];
        }
        BufferedReader f = new BufferedReader(new FileReader(inputfile));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbarn2.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[] desired = new int[N]; // For looping
        for(int i = 0 ; i < N ; i ++){
            int v = Integer.parseInt(f.readLine());
            desired[i] = v;
            //desired[i + N] = v;
        }
        ps = new int [N + 1];
        for(int i = 0; i < N; i ++){
            ps[i + 1] = ps[i] + i * desired[i];
        }
        System.out.println(Arrays.toString(ps));
        dp = new int[N][K];
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < K; j ++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        int ans = recur(0, 1, 0);
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < K; j ++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        //Arrays.fill(dp, Integer.MAX_VALUE);
        //dp[0] = 0;
        
        System.out.println(ans);
    }
}
