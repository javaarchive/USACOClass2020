import java.io.*;
import java.util.*;
public class scramble {
    public static int numberize(char c){
        return Character.getNumericValue(c) - 10;
    }
    public static void main(String[] args) throws IOException{
        // IO
        //BufferedReader f = new BufferedReader(new FileReader("reduce.in"));
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        //PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("reduce.out")));
        int N = Integer.parseInt(f.readLine());
        for(int i = 0; i < N; i ++){
            String line = f.readLine();
            
        }
    }
}
class ScrambledCow{
    String str;
    public int minpos = -1, maxpos = -1;
    public ScrambledCow(String s){
        this.str = s;
    }
}