import java.io.*;
import java.util.*;

public class cowyotz {
    static int[] freq;
    static int[][] desired;
    static int S, N, E;
    static int ans = 0;

    public static void recur(int side, int depth) {
        if (depth > N) {
            boolean failed = false;
            // System.out.println(Arrays.toString(freq));
            for (int i = 0; i < N; i++) {
                if (freq[i] < desired[i]) {
                    failed = true;
                    break;
                }
            }
            if (!failed) {
                ans++;
            }
            return;
        }
        int leftToPlace = N - depth + 1;
        int nonworking = 0;
        freq[side]++;
        for (int j = 0; j < E; j++) {
            boolean nw = true;
            for (int i = 0; i < S; i++) {

                if (desired[i] - freq[j][i] > leftToPlace) {
                    // System.out.println("Prune "+desired[i] + " "+ freq[i]+" "+leftToPlace);
                    nw = true;
                    break;
                }
            }
            if(nw){
                nonworking ++;
            }
        }
        if (nonworking != E) {
            for (int i = 0; i < S; i++) {
                recur(i, depth + 1);
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
        Arrays.fill(desired, Integer.MAX_VALUE);
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(f.readLine(), "+");
            while (st.hasMoreTokens()) {
                String[] tokens = st.nextToken().split("x");
                int count = Integer.parseInt(tokens[0]);
                int side = Integer.parseInt(tokens[1]) - 1;
                desired[side] = Integer.min(desired[side], count);
            }
        }
        for (int i = 0; i < desired.length; i++) {
            if (desired[i] == Integer.MAX_VALUE) {
                desired[i] = 0;
            }
        }
        System.out.println(Arrays.toString(desired));
        for (int i = 0; i < S; i++) {
            recur(i, 1);
        }
        pw.println(ans);
        pw.close();
    }
}