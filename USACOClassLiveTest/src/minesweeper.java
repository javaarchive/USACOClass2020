import java.io.*;
import java.util.*;

public class minesweeper {
    public static int[][] count;
    public static int[][] attempt;
    static List<Location> path = new ArrayList<>();
    static int[] dx = { -1, -1, -1, 1, 1, 1, 0, 0, 0 };
    static int[] dy = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };

    public static boolean recur(int x, int y, int K) {
        
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
        // targetMatched = N * M;
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
                if(count[i][j] == 0){
                    continue; // PRUNE 1
                }
                if(recur(i, j, K - 1)){
                    // matched = 0;
                    for(Location l: path){
                        System.out.println((l.x + 1)+" "+(l.y + 1));
                    }
                    break;
                    //System.out.println(path);
                }
            }
        }
    }

}

class Location implements Comparable<Location>{
    int x, y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "("+ x + " , " + y + ")";
    }

    @Override
    public int compareTo(Location o) {
        // TODO Auto-generated method stub
        return (this.toString()).compareTo(o.toString());
    }
    
}