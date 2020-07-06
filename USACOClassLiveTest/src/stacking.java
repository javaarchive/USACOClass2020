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
		int[] bales = new int[N];
		for(int i = 0; i < K; i ++){
			st = new StringTokenizer(f.readLine());
			starts[i] = Integer.parseInt(st.nextToken()) - 1;
			ends[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		Arrays.sort(starts);
		Arrays.sort(ends);
		int startIndex = 0;
		int endIndex = 0;
		int hayAmount = 0;
		for(int i = 0; i < N; i ++){
			while(startIndex < K && starts[startIndex] == i){
				hayAmount ++;
				startIndex ++;
			}
			bales[i] = hayAmount;
			while(endIndex < K && ends[endIndex] == i){
				hayAmount --;
				endIndex ++;
			}
		}
		//System.out.println(Arrays.toString(bales));
		Arrays.sort(bales);
		//System.out.println(Arrays.toString(bales));
		pw.println(bales[(N-1)/2]);
		pw.close();
		f.close();
	}

}
