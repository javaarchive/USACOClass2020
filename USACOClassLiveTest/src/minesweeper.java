import java.io.*;
import java.util.*;

public class minesweeper {
    public static int[][] count;
    public static int[][] attempt;
    static List<Location> path = new ArrayList<>();
    public static int targetMatched;
    static int[] dx = { -1, -1, -1, 1, 1, 1, 0, 0, 0 };
    static int[] dy = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };
    
    public static boolean checkBounds(int x, int y){
        return (0 <= x) && (x < attempt.length) && (0 <= y) && (y < attempt[x].length);
    }
    public static boolean valid(int x, int y){
        for(int i = 0; i < dx.length; i ++){
            int newx = x + dx[i];
            int newy = y + dy[i];
            if(!checkBounds(newx,newy)){
                continue;
            }
            if(attempt[newx][newy] >= count[newx][newy]){
                return false;
            }
        }
        return true;
    }

    public static int place(int x, int y, int inc){
        int match  =0;
        for(int i = 0; i < dx.length; i ++){
            int newx = x + dx[i];
            int newy = y + dy[i];
            if(!checkBounds(newx,newy)){
                continue;
            }
            attempt[newx][newy] += inc;
            if(attempt[newx][newy] == count[newx][newy]){
                match ++;
            }
        }
        return match;
    }
    static int Ktotal = 0;
    static long sTime = System.currentTimeMillis();
    static Set<Location> path2 = new HashSet<>();
    public static boolean recur(int K, int curMatch) {
        //System.out.println("recur("+K+","+curMatch+")");
        //System.out.println(K);
        //System.out.print("\rElapsed Time in ms: "+(System.currentTimeMillis() - sTime)+" "+K);
        //progressPercentage(K, Ktotal);
        if(K == 0){
            // return true;
            return false; 
         }
         if(K * 9 < (targetMatched - curMatch)){
            return false; // Give up
         }
        if(curMatch == targetMatched){
            return true;
        }else if(curMatch > targetMatched){
            return false; // No point adding more mines
        }
        for(int i = 0; i < attempt.length; i ++){
            for(int j = 0; j < attempt[i].length; j ++){
                Location l = new Location(i,j);
                if(valid(i,j) && !path2.contains(l)){
                    int newMatch = place(i,j, 1);
                    path.add(l);
                    path2.add(l);
                    if(recur(K - 1, curMatch + newMatch)){
                        return true;
                    }
                    path2.remove(l);
                    path.remove(path.size() - 1);
                    place(i,j, -1); // Undo Action
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
        Ktotal = K;
        targetMatched = N * M;
        count = new int[N][M];
        attempt = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(f.readLine());
            for (int j = 0; j < M; j++) {
                int mineCount = Integer.parseInt(st.nextToken());
                count[i][j] = mineCount;
                // attempt[i][j] = mineCount;
                if(mineCount == 0){
                    targetMatched --;
                }
            }
        }
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[i].length; j++) {
                if(count[i][j] == 0){
                    continue; // PRUNE 1
                }
                if(recur(K, 0)){
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Location))
            return false;
        Location other = (Location) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
    
}