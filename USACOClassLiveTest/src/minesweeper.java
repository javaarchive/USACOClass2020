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
        System.out.println("recur("+x+","+y+","+K+") ");
        for(int px = 0; px < attempt.length; px ++){
            for(int py = 0; py < attempt[px].length; py ++){
                System.out.print(attempt[px][py] + " ");
            }
            System.out.println();
        }
        int addedMatch = 0;
        boolean failed = false;
        for (int i = 0; i < dx.length; i++) {
            int checkx = x + dx[i];
            int checky = y + dy[i];
            if (0 <= checkx && checkx < attempt.length) {
                if (0 <= checky && checky < attempt[checkx].length) {
                    attempt[checkx][checky]++;
                    if(attempt[checkx][checky] > count[checkx][checky]){
                        matched--;
                        addedMatch --;
                    }
                   if (attempt[checkx][checky] == count[checkx][checky]) {
                        matched++;
                        addedMatch++;
                    }
                }
            }
        }
        /*System.out.println("afterrecur("+x+","+y+","+K+") "+"tm: "+targetMatched+" matched: "+matched);
        for(int px = 0; px < attempt.length; px ++){
            for(int py = 0; py < attempt[px].length; py ++){
                System.out.print(attempt[px][py] + ",");
            }
            System.out.println();
        }*/
        path.add(new Location(x, y));
        if (matched == targetMatched && !failed) {
            return true;
        }
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[i].length; j++) {
                if (K > 0 && recur(i, j, K - 1)) {
                    /*System.out.println("Map: ");
                    for(int px = 0; px < attempt.length; px ++){
                        for(int py = 0; py < attempt[px].length; py ++){
                            System.out.print(attempt[px][py] + " ");
                        }
                        System.out.println();
                    }*/
                    return true;
                }else{
                    /*System.out.println("Recur "+i+" "+j+" failed");
                    for(int px = 0; px < attempt.length; px ++){
                        for(int py = 0; py < attempt[px].length; py ++){
                            System.out.print(attempt[px][py] + ",");
                        }
                        System.out.println();
                    }*/
                }
            }
        }

        path.remove(path.size() - 1);
        matched -= addedMatch;
        for (int i = 0; i < dx.length; i++) {
            int checkx = x + dx[i];
            int checky = y + dy[i];
            if (0 <= checkx && checkx < attempt.length) {
                if (0 <= checky && checky < attempt[checkx].length) {
                    attempt[checkx][checky] --;
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
                if(mineCount == 0){
                    targetMatched --;
                }
                // attempt[i][j] = mineCount;
            }
        }
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[i].length; j++) {
                if(recur(i, j, K - 1)){
                    matched = 0;
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