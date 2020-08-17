import java.io.*;
import java.util.*;
public class nqueen{
    public static void main(String[] args) throws IOException{
        // new FileReader("reduce.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("reduce.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(f.readLine());
        f.close();
        int[][] board = new int[N][N];

        pw.close();
    }
}
class DepthChoice{
    int x,y;
    public DepthChoice(int x, int y){
        this.x = x;
        this.y = y;
    }
}