import java.io.*;
import java.util.*;

public class minesweeper {
    public static int[][] count;
    public static int[][] attempt;
    static List<Location> path = new ArrayList<>();
    static int[] dx = { -1, -1, -1, 1, 1, 1, 0, 0, 0 };
    static int[] dy = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };
    static int matched = 0;
    static int targetMatched;

    public static boolean recur(int x, int y, int K) {
        int addedMatch = 0;
        boolean failed = false;
        for (int i = 0; i < dx.length; i++) {
            int checkx = x + dx[i];
            int checky = y + dx[i];
            if (0 <= checkx && checkx < attempt.length) {
                if (0 <= checky && checky < attempt[checkx].length) {

                    attempt[checkx][checky]++;
                    if (attempt[checkx][checky] > count[checkx][checky]) {
                        failed = true;
                        break;
                    } else if (attempt[checkx][checky] == count[checkx][checky]) {
                        matched++;
                        addedMatch++;
                    }
                }
            }
        }

        path.add(new Location(x, y));
        if (matched == targetMatched && !failed) {
            return true;
        }
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[i].length; j++) {
                if (K > 0 && recur(i, j, K - 1)) {
                    return true;
                }else{
                    System.out.println("Recur "+i+" "+j+" failed");
                    for(int px = 0; px < attempt.length; px ++){
                        for(int py = 0; py < attempt[i].length; py ++){
                            System.out.print(attempt[px][py] + ",");
                        }
                        System.out.println();
                    }
                }
            }
        }

        path.remove(path.size() - 1);
        matched -= addedMatch;
        for (int i = 0; i < dx.length; i++) {
            int checkx = x + dx[i];
            int checky = y + dx[i];
            if (0 <= checkx && checkx < attempt.length) {
                if (0 <= checky && checky < attempt[checkx].length) {
                    if (attempt[checkx][checky] > count[checkx][checky]) {
                        break;
                    }
                    attempt[checkx][checky] -= 54;
                }
            }

        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        // IO
        // new FileReader("minesweeper.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("minesweeper.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        targetMatched = N * M;
        count = new int[N][M];
        attempt = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(f.readLine());
            for (int j = 0; j < M; j++) {
                int mineCount = Integer.parseInt(st.nextToken());
                count[i][j] = mineCount;
                // attempt[i][j] = mineCount;
            }
        }
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[i].length; j++) {
                System.out.println(recur(i, j, K - 1));
            }
        }
    }

}

class Location {
    int x, y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

}