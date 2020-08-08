import java.io.*;
import java.util.*;
public class cow {
    public static void main(String[] args) throws IOException {
        // IO
        // new FileReader("cow.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("cow.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(f.readLine());
        char[] line = f.readLine().toCharArray();
        long c = 0;
        long co = 0;
        long cow = 0;
        for(int i = 0; i < N; i ++){
            switch(line[i]){
                case 'C':
                    c ++;
                    break;
                case 'O':
                    co += c;
                    break;
                case 'W':
                    cow += co;
                    break;
                default:
                    break;
            }
            
        }
        pw.println(cow);
        pw.close();;
    }
}