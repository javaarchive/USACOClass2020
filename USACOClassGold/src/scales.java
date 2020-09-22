import java.io.*;
import java.util.*;
public class scales {
    static int[] ps;
    static int[] arr;
    static int C;
    public static int recur(int curSum, int i){
        //System.out.println("RECUR("+curSum+","+i+");");
        if(i < 0){
            if(curSum <= C){
                return curSum;
            }else{
                return -1;
            }
        }

        int a = 0;
        if(i > 0){
            a = arr[i - 1];
        }
        int b = arr[i];
        //System.out.println(a + " " + b);
        //int c = ps[i];
        int curAns;
        if(i == 0){
            if((curSum + arr[0]) > C){
                if(curSum <= C){
                    return curSum;
                }else{
                    return -1;
                }
            }else{
                //System.out.println("R: "+(curSum + arr[0]));
                return curSum + arr[0];
            }
        }else if(a + b <= C){
            int r = Integer.max(recur(curSum + b, i - 2),recur(curSum + b + a, i - 2));
            //System.out.println("recur("+(i-2)+") = "+r);
            return r;
        }else if(b > C){
            int r = Integer.max(recur(curSum + a, i - 2),recur(curSum, i - 2));
            //System.out.println("recur("+(i - 2)+") = "+r);
            return r;
        }else if(a + b > C){
            return Integer.max(recur(curSum + a, i - 2),recur(curSum + b, i - 2));
        }else{
            return 0;
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
        arr = new int[N];
        //int[] bps = new int[N];
        ps = new int[N + 1];
        //bps[N - 1] = arr[N - 1];
        for(int i = 0; i < N; i ++){
            arr[i] = Integer.parseInt(f.readLine());
        }
        System.out.println(recur(0, N - 1));
    }
}
