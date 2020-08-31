import java.io.*;
import java.util.*;

public class loan {
	private static long N;
	private static long K;
	private static long M;
	public static boolean test(long X){
        System.out.println("test("+X+")");
        int iters = 0;
        long Y = N/X;
        long G = 0;
        while(true){
            long remainingMilk = N - G;
            if(Y < M){
                Y = M;
                break;
            }
            long L = (remainingMilk/Y - X) + 1L;
            iters += L;
            
            G += L * Y;
            if(iters >= K || G >= N){
                break;
            }
            Y = remainingMilk/X;
        }
        //System.out.println("N: "+N+" "+G);
        if(G < N){
            iters += (N-G)/M;
        }
        System.out.println("X: "+X+" iters: "+iters);
        return (N <= G);
	}
	public static void main(String[] args) throws IOException{
		BufferedReader f = new BufferedReader(new FileReader("loan.in"));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Long.parseLong(st.nextToken());
		K = Long.parseLong(st.nextToken());
		M = Long.parseLong(st.nextToken());
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("loan.out")));
		f.close();
        //long upper = N;
        //            123456789
		long upper = 1000000000000L; 
		long lower = 0L;
		long mid = -1L;
		while(true) {
			if((upper - lower) <= 1){
                pw.println(mid);
                //pw.close();
                break;
            }
			mid = (long) (upper + lower)/2;
			if(test(mid)) {
				lower = mid;
			}else {
				upper = mid;
            }
            System.out.println(lower + " --- " + mid + " --- "+ upper);
		}
		System.out.println(lower + " --- " + mid + " --- "+ upper);
		pw.close();
	}

}
