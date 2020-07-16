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
		boolean scFlag = false; // Special Case flag
		boolean scFlag2 = false;
		if((cows[N-2] - cows[0]) == (N-2) ){ // CONSECUTIVE CASE
			//best = Integer.min(2, best);
			best = 2;
			scFlag = true;
			//System.out.println("set flag");
		}
		if( (cows[N-1] - cows[1]) == (N-2)){
			scFlag2 = true;
			best = 2;
		}
		for (int i = 0; i < N; i++) {
			maxdist = cows[i] + N;
			while (top < N && cows[top] < maxdist) {
				top++;
			}
			if(scFlag && ((top == N-1 && i == 0))){
				//System.out.println("Trigger flag: "+i+" "+top);
				continue;
			}
			if(scFlag2 && ((top == N && i == 1))){
				//System.out.println("Trigger flag 2: "+i+" "+top);
				continue;
			}
			best = Integer.min(best, N - (top - i));
			//System.out.println(i+" "+top);
		}
		pw.println(best);
		// Find worst
		//pw.println(N-1);
		pw.println(Integer.max(cows[N-1] - cows[1], cows[N-2] - cows[0]) - (N-2));
		pw.close();
	}
}
