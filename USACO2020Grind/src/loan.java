import java.io.*;
import java.util.*;

public class loan {
    private static long N;
    private static long K;
    private static long M;

    public static boolean test(long X) {
        int days = 0;
        long Y = N / X;
        long G = 0;
        while (true) {
            long remainingMilk = N - G;
            if (Y < M) {
                Y = M;
                days += Math.ceil((double) (N - G) / M);
                System.out.println("A: test("+X+") = "+(days <= K));
                return (days <= K);
            }
            long L = (remainingMilk / Y - X) + 1L;
            days += L;
            G += L * Y;
            if (days >= K || G >= N) {
                break;
            }
            Y = remainingMilk / X;
        }
        // System.out.println("N: "+N+" "+G);

        System.out.println("X: " + X + " iters: " + days);
        System.out.println("B: test("+X+") = "+(N <= G));
        return (N <= G);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("loan.in"));
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Long.parseLong(st.nextToken());
        K = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("loan.out")));
        f.close();
        // long upper = N;
        // 123456789
        long upper = N;
        long lower = 0L;
        long mid = -1L;
        while (true) {
            mid = (long) (upper + lower + 1) / 2;
            if ((upper - lower) <= 0) {
                pw.println(mid);
                // pw.close();
                break;
            }
           
            if (test(mid)) {
                lower = mid;
            } else {
                upper = mid - 1;
            }
            System.out.println(lower + " --- " + mid + " --- " + upper);
        }
        System.out.println(lower + " --- " + mid + " --- " + upper);
        pw.close();
    }

}
