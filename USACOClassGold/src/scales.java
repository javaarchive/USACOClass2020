import java.io.*;
import java.util.*;
public class scales {
    static int[] ps;
    static int C;
    public static int recur(int[] bps, int i){
        if(i <= 0){
            return bps[0];
        }
        int a = bps[i - 1];
        int b = bps[i];
        //int c = ps[i];
        if(a + b <= C){
            bps[i - 2] = b;
            return recur(bps, i - 2);
        }else if(b > C){
            bps[i - 2] = a;
            return recur(bps, i - 2);
        }else if(a + b > C){
            return Integer.max(recur(bps, i + 1),recur(bps, i));
        }else{
            return bps[i];
        }
    }
    public static void main(String[] args) throws IOException{
         // IO
        // new FileReader("tractor.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("tractor.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int[] bps = new int[N];
        ps = new int[N + 1];
        bps[N - 1] = arr[N - 1];
        for(int i = 0; i < N; i ++){
            arr[i] = Integer.parseInt(f.readLine());
            ps[i + 1] = ps[i] + arr[i];
        }
        System.out.println(recur(bps, N - 1));
    }
}
