import java.io.*;
import java.util.*;

public class loan {
	private static long N;
	private static long K;
	private static long M;
	public static long test(long X) {
        int iters = 0;
        long Y = N/X;
        long G = 0;
        while(Y > M){
            long L = ((long) Math.floor((N/Y) - X)) + 1L; // Magic Formula
            iters += L;
            G += L * Y;
            Y = (N - G)/X;
        }
        //System.out.println("N: "+N+" "+G);
        iters += (N-G)/M;
        System.out.println("X: "+X+" iters: "+iters);
        return iters;
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
		long upper = 1000000000000L; // Stress test
		long lower = 0L;
		long mid = -1L;
		while(true) {
			if(upper - lower <= 1) {
				if(test(lower) > K) {
					if(test(upper) > K) {
						pw.println(upper);
					}else {
						//pw.println("Unexpected case");
					}
				}else {
					
					pw.println(lower);
				}
				break;
			}
			mid = (long) (upper + lower)/2;
			long total = test(mid);
			if(total > K) {
				upper = mid;
			}else {
				lower = mid;
			}
		}
		//System.out.println(lower + " --- " + mid + " --- "+ upper);
		pw.close();
	}

}
