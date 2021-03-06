import java.io.*;
import java.util.*;
public class nqueen{
    static int depth = 0;
    static int[][] board;
    static int ans = 0;
    static int N;
    static int[] dx = {1,1,-1, -1};
    static int[] dy = {1,-1,-1,1};
    static Set<Integer> rows = new HashSet<>();
    static Set<Integer> cols = new HashSet<>();
    static List<Integer> qx,qy;
    public static boolean check(int x, int y){
        if(rows.contains(x) || cols.contains(y)){
            return false;
        }
        if(board[x][y] != 0){
            return false;
        }
        
       for(int i = 0; i < qx.size(); i ++){
           int ox = qx.get(i);
           int oy = qy.get(i);
           if(Math.abs(oy - y) == Math.abs(ox - x)){
                return false; // Diagonally
           }
       }
        return true;
    }
    public static void place(int x, int y, int inc){
        board[x][y] += inc;
        /*for(int i = 1; i < N+1; i ++){
            for(int j = 0; j < dx.length; j ++){
                int curX = x + dx[j] * i;
                int curY = y + dy[j] * i;
                if(0 <= curX && curX < N){
                    if(0 <= curY && curY < N){
                        board[curX][curY] += inc;
                    }
                }
            }
        }*/
        if(inc == 1){
            rows.add(x);
            cols.add(y);
        }else{
            rows.remove(x);
            cols.remove(y);
        }
    }
    public static void recur(int x, int y){
        qx.add(x);
        qy.add(y);
        /*for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println("x: "+x+" y: "+y+" enter depth "+depth);*/
        depth ++;
        if(depth == N){
            ans ++;
            depth --;
            qx.remove(qx.size() - 1);
            qy.remove(qy.size() - 1);
            return;
        }
        int nextx = x + 1;
        //int nexty = 0;
        if(nextx < N){
        for(int nexty = 0; nexty < N; y ++){
            if(check(nextx,nexty)){
                place(nextx, nexty, 1);
                recur(nextx, nexty);
                place(nextx, nexty, -1);
            }
            nexty ++;
        }
    }
        depth --;
        qx.remove(qx.size() - 1);
        qy.remove(qy.size() - 1);
    }
    public static void main(String[] args) throws IOException{
        // new FileReader("reduce.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("reduce.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(f.readLine());
        f.close();
        board = new int[N][N];
        qx = new ArrayList<>();;
        qy = new ArrayList<>();
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                place(i, j, 1);
                recur(i, j);
                place(i,j, -1);
            }
        }
        pw.println(ans);
        pw.close();
    }
}
