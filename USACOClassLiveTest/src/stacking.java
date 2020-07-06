import java.io.*;
import java.util.*;
public class stacking{

	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int N, K; 
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[] starts = new int[K];
		int[] ends = new int[K];
		for(int i = 0; i < N; i ++){
			st = new StringTokenizer(f.readLine());
			starts[i] = Integer.parseInt(st.nextToken());
			ends[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(starts);
		Arrays.sort(ends);
	}

}
