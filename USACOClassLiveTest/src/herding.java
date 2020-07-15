import java.io.*;
import java.util.*;

public class herding {
	public static void main(String[] args) throws IOException {
		// IO
		// new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		// new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(f.readLine());
		int[] cows = new int[N];
		for (int i = 0; i < N; i++) {
			cows[i] = Integer.parseInt(f.readLine());
		}
		Arrays.sort(cows);
		// Find best
		int best = Integer.MAX_VALUE;
		int top = 0;
		int maxdist;
		for (int i = 0; i < N; i++) {
			maxdist = cows[i] + N;
			while (top < N && cows[top] < maxdist) {
				top++;
			}
			//if ((top - i) == N - 1) {
				//best = Integer.min(best,2);
			//} else {
				best = Integer.min(best, N - (top - i));
			//}
			//System.out.println(i+" "+top);
		}
		pw.println(best);
		// Find worst
		//pw.println(N-1);
		pw.close();
	}
}
