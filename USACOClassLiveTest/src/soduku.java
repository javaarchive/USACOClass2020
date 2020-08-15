import java.io.*;
import java.util.*;

public class soduku {
    static int[][] board;
    static int N;

    static boolean checkCol(int col, int num) {
        if(col > N){
            return true;
        }
        for (int i = 0; i < N; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }
        return true;
    }

    static boolean checkRow(int row, int num) {
        if(row >= N){
            return true;
        }
        //System.out.println("R: "+row);
        for (int i = 0; i < N; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }
        return true;
    }

    static boolean checkSquare(int x, int y, int num) {
        if(x >= N || y > N){
            return true;
        }
        int startX = (int) Math.floor(x / 3) * 3;
        int startY = (int) Math.floor(y / 3) * 3;
        for (int i = startX; i < startX + 3; i++) {
            for (int j = startY; j < startY + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean recur(int pos, int num) {
        int x = (int) Math.floor(pos / N);
        int y = pos % N;
        System.out.println(x + " " + y);
        if (x >= N && y >= N) {
            return true;
        }

        board[x][y] = num;
        int nextPlace = -1;
        for (int p = pos + 1; p <= N * N; p++) {
            if (p == N * N) {
                nextPlace = N * N;
                break;
            }
            int newx = (int) Math.floor(p / N);
            int newy = p % N;
            if (board[newx][newy] == 0) {
                nextPlace = p;
                break;
            }
        }
        if (nextPlace != -1) {
            int newx = (int) Math.floor(nextPlace / N);
            int newy = nextPlace % N;
            
            for (int i = 1; i <= N; i++) {
                if (checkRow(newx, i)) {
                    if (checkCol(newy, i)) {
                        if (checkSquare(newx, newy, i)) {
                            if (recur(nextPlace, i)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        board[x][y] = 0;

        return false;
    }

    public static void main(String[] args) throws IOException {
        // IO
        // new FileReader("reduce.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("reduce.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        N = 9;
        StringTokenizer st;
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(f.readLine());
            for (int j = 0; j < N; j++) {
                // System.out.println(i + " "+j);
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int nextPlace = 0;
        int newx = (int) Math.floor(nextPlace / N);
        int newy = nextPlace % N;
        boolean found = false;
        for (int n = 1; n <= N; n++) {
            if (checkRow(newx, n)) {
                if (checkCol(newy, n)) {
                    if (checkSquare(newx, newy, n)) {
                        if (recur(nextPlace, n)) {
                            found  = true;
                            for (int i = 0; i < N; i++) {
                                for (int j = 0; j < N; j++) {
                                    pw.print(board[i][j] + " ");
                                }
                                pw.println();
                            }
                            break;
                        }
                    }
                }
            }
        }
        if(!found){
            pw.println("NO SOLUTION");
        }
        pw.close();
    }
}