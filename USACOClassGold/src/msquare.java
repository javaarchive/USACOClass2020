import java.io.*;
import java.util.*;

public class msquare {
    public static void main(String[] args) throws IOExceptio{
         // IO
        // new FileReader("msquare.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("msquare.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(f.readLine());
        for(int i = 0; i < 4; i ++){

        }
    }
}
class PlanarSquareState{
    int[] nums;
    public PlanarSquareState(int[] data){
        this.nums = data;
    }
}
