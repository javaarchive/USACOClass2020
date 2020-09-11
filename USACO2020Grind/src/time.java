import java.io.*;
import java.util.*;

public class time {
    static final int MAXDAYS = 500;
    public static void main(String[] args) throws IOException{
        String inputfile = "time.in";
        if (args.length > 0) {
            inputfile = args[0];
        }
        BufferedReader f = new BufferedReader(new FileReader(inputfile));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("time.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N, M, C;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int[][] dp = new int[MAXDAYS + 1][N];
        List<List<Integer>> adjlist = new ArrayList<>(N); 
        
        for(int i = 0; i < N; i ++){
            adjlist.add(new ArrayList<Integer>());
        }
        st = new StringTokenizer(f.readLine());
        int[] moneyEarned = new int[N];
        for(int i = 0; i < N; i ++){
            moneyEarned[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0 ; i < M; i ++){
            st = new StringTokenizer(f.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            adjlist.get(a).add(b);
            //adjlist.get(b).add(a);
        }
        int[] visitedThisIter = new int[N];
        visitedThisIter[0] = 1;
        dp[0][0] = moneyEarned[0];
        Queue<Integer> toChange = new LinkedList<>();
        int moneyToSub = 0;
        for(int i = 0; i < MAXDAYS; i ++){
           // System.out.println(i);
            moneyToSub = (2*i + 1);
            for(int j = 0; j < visitedThisIter.length; j ++){
                if(visitedThisIter[j] == 0){
                    continue;
                }
                for(int nb: adjlist.get(j)){
                    int prev = (i > 0)?dp[i - 1][j]:0;
                    //System.out.println(i + " "+nb);
                    dp[i][nb] = Integer.max(prev + moneyEarned[nb], dp[i][nb]); //- moneyToSub;
                    toChange.add(nb);
                }
            }
            visitedThisIter = new int[N];
            while(!toChange.isEmpty()){
                visitedThisIter[toChange.poll()] = 1;
            }
        }
        /*for(int i = 0; i < MAXDAYS; i ++){
            System.out.println((dp[i][0]-(i + 1)*(i + 1)*C)+" ");
        }
        for(int i = 0 ; i < 100; i ++){
            for(int j = 0; j < N; j ++){
                System.out.print(String.format("%1$" + 2 + "s", Integer.toString(dp[i][j])).replace(' ', '0')+" ");
            }
            System.out.println();
        }*/
        int best = 0;
        for(int i = 0; i < MAXDAYS; i ++){
            best = Integer.max(best, dp[i][0] - (i + 1)*(i + 1)*C);
        }
        pw.println(best);
        pw.close();
    }
}
 