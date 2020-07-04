import java.io.*;
import java.util.*;

public class cownomics {
	public static int NUMERIZE(char c){
		switch(c){
			case 'G':
				return 0;
			case 'A':
				return 1;
			case 'C':
				return 2;
			case 'T':
				return 3;
		}
		return -1;
	}
	public static void main(String[] args) throws IOException {
		// IO
		// new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		// new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int N, M;
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// String[] spotty = new String[N];
		// String[] spotless = new String[N];
		int seen[][][] = new int[64];
		for (int i = 0; i < N; i++) {
			String line = f.readLine();
			for (int j = 0; j < M; j++) {
				for(int k = j+1; k < M; k ++){
					for(int l = k+1; l < M; l ++){
						seen[NUMERIZE(line.charAt(j))*16 + NUMERIZE(line.charAt(k))*4 + NUMERIZE(line.charAt(l))][i][j][k]];
						
					}
				}
			}
		}
		for (int i = 0; i < N; i++) {
			String line = f.readLine();
			for (int j = 0; j < M; j++) {
				for(int k = j+1; k < M; k ++){
					for(int l = k+1; l < M; l ++){
						seen[NUMERIZE(line.charAt(j))][NUMERIZE(line.charAt(k))][NUMERIZE(line.charAt(l))] = 0;
					}
				}
			}
		}
	
	int count = 0;
	for(int[][] dim2: seen){
		for(int[] dim3: dim2){
			for(int state: dim3){
				if(state != 0){
					count += state;
				}
			}
		}
	}
	//System.out.println(Arrays.deepToString(seen).replace("]", "]\n"));
	pw.println(count);
	pw.close();
	}
}
