import java.io.*;
import java.util.*;

public class cowyotz {
    static int[] freq;
    static int[][] desired;
    static int S, N, E;
    static int ans = 0;

    public static void recur(int side, int depth) {
        if (depth > N) {
            boolean working = false;
            
            for (int j = 0; j < E; j++) {
                boolean failed = false;
                for (int i = 0; i < S; i++) {
                    if (freq[i] < desired[j][i]) {
                        failed = true;
                        break;
                    }
                }
                if (!failed) {
                    working = true;
                    break;
                }
            }
            if(working){
                //System.out.println(Arrays.toString(freq));
                ans ++;
            }
            return;
        }
        int leftToPlace = N - depth + 1;
        int nonworking = 0;
        freq[side]++;
        
        for (int j = 0; j < E; j++) {
            boolean nw = false;
            for (int i = 0; i < S; i++) {

                if (desired[j][i] - freq[i] > leftToPlace) {
                    //System.out.println("Prune "+desired[i] + " "+ freq[i]+" "+leftToPlace);
                    nw = true;
                    break;
                }
            }
            if (nw) {
                nonworking++;
            }
        }
        //System.out.println(nonworking);
        if (nonworking != E) {
            if(depth != N){
            for (int i = 0; i < S; i++) {
                recur(i, depth + 1);
            }
        }else{
            recur(-1, depth + 1);
        }
        }
        freq[side]--;
    }
    public static void main(String[] args) throws IOException {
        // new FileReader("pathfindin")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader f = new BufferedReader(new FileReader("wormsort.in"));
        // new BufferedWriter(new FileWriter("pathfind.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        // PrintWriter pw = new PrintWriter(new BufferedWriter(new
        // FileWriter("wormsort.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken()); // Dice Count
        S = Integer.parseInt(st.nextToken()); // Dice sides
        E = Integer.parseInt(st.nextToken()); // Expressions
        freq = new int[S]; // Side count
        desired = new int[E][S];
        //Arrays.fill(desired, Integer.MAX_VALUE);
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(f.readLine(), "+");
            while (st.hasMoreTokens()) {
                String[] tokens = st.nextToken().split("x");
                int count = Integer.parseInt(tokens[0]);
                int side = Integer.parseInt(tokens[1]) - 1;
                desired[i][side] = count;
            }
        }
        //System.out.println(Arrays.deepToString(desired));
        for (int i = 0; i < S; i++) {
            recur(i, 1);
        }
        pw.println(ans);
        pw.close();
    }
}