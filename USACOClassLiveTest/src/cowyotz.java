import java.io.*;
import java.util.*;

public class cowyotz {
    static int[] freq;
    public static void recur(int side, int depth) {
        int leftToPlace = depth-1;
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
        int N = Integer.parseInt(st.nextToken()); // Dice Count
        int S = Integer.parseInt(st.nextToken()); // Dice sides
        int E = Integer.parseInt(st.nextToken()); // Expressions
        freq = new int[S]; // Side count
        int[] desired = new int[S];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(f.readLine(), "+");
            while (st.hasMoreTokens()) {
                String[] tokens = st.nextToken().split("x");
                int count = Integer.parseInt(tokens[0]);
                int side = Integer.parseInt(tokens[1]) - 1;
                desired[side] = Integer.max(desired[side], count);
            }
        }
        System.out.println(Arrays.toString(desired));
        for(int i = 0; i < S; i ++){
            recur(i, 1);
        }
    }
}