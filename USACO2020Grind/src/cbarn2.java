import java.io.*;
import java.util.*;

public class cbarn2 {
    public static void main(String[] args) throws IOException{
        String inputfile = "cbarn2.in";
        if (args.length > 0) {
            inputfile = args[0];
        }
        BufferedReader f = new BufferedReader(new FileReader(inputfile));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbarn2.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] desired = new int[2*N]; // For looping
        for(int i = 0 ; i < N ; i ++){
            int v = Integer.parseInt(f.readLine());
            desired[i] = v;
            desired[i + N] = v;
        }
        int[] ps = new int [2 * N + 1];
        for(int i = 0; i < 2*N; i ++){
            ps[i + 1] = ps[i] + i * desired[i];
        }
        System.out.println(Arrays.toString(ps));
    }
}
