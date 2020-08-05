import java.io.*;
import java.util.*;

public class minesweeper {
    public static int N, M, K;
    public static int[][] count;
    public static int[][] test;
    public static int goal;

    public static boolean inBounds(int x, int y) {
        return (0 <= x) && (x < N) && (0 <= y) && (y < M);
    }

    public static int[] dx = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };
    public static int[] dy = { -1, -1, -1, 0, 0, 0, 1, 1, 1 };
    public static final int DIRS = 9;

    public static int place(int x, int y, int inc) {
        int matches = 0;
        for (int i = 0; i < DIRS; i++) {
            int newx = x + dx[i];
            int newy = y + dy[i];
            if (inBounds(newx, newy)) {
                test[newx][newy] += inc;
                if (test[newx][newy] == count[newx][newy]) {
                    matches++;
                }
            }
        }
        return matches;
    }

    public static boolean valid(int x, int y) {
        for (int i = 0; i < DIRS; i++) {
            int newx = x + dx[i];
            int newy = y + dy[i];
            if (inBounds(newx, newy)) {
                if (count[newx][newy]  <= test[newx][newy]) {
                    return false;
                }
            }
        }
        return true;
    }

    static List<Loc> path = new ArrayList<>();

    public static boolean recur(int x, int y, int k, int curMatched) {
        //System.out.println("recur("+x+","+y+","+k+","+curMatched+");");
        if (curMatched == goal) {
            return true;
        }
        if (k == 0) {
            return false;
        }
        if (x == N) {
            return false;
        }
        
        if(y == 0 && x > 1){
            int row = x - 2;
            for(int i = 0; i < M; i ++){
                if(count[row][i] != test[row][i]){
                    return false;
                }
            }
        }
        // CASE 1
        if (valid(x, y)) {
            int newMatched = place(x, y, 1); // Place mine
            path.add(new Loc(x,y));
            if (y == M - 1) {
                if (recur(x + 1, 0, k - 1, curMatched + newMatched)) {
                    return true;
                }
            } else {
                if (recur(x, y + 1, k - 1, curMatched + newMatched)) {
                    return true;
                }
            }
            path.remove(path.size() - 1);
            place(x, y, -1); // Undo changes
        }
        // CASE 2
        if (y == M - 1) {
            if (recur(x + 1, 0, k, curMatched)) {
                return true;
            }
        } else {
            if (recur(x, y + 1, k, curMatched)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        // new FileReader("minesweeper.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("minesweeper.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        goal = N * M;
        count = new int[N][M];
        test = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(f.readLine());
            for (int j = 0; j < M; j++) {
                int type = Integer.parseInt(st.nextToken());
                count[i][j] = type;
                if (type == 0) {
                    goal --;
                }
            }
        }

        if (recur(0, 0, K, 0)) {
            for (Loc p : path) {
                pw.println(p);
            }
        }else{
            pw.println("NO SOLUTION");
        }
        pw.close();
    }
}
class Loc {
    int x, y;

    public Loc(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return (x + 1) + " " + (y + 1);
    }
    
}