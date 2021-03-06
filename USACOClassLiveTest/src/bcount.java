import java.io.*;
import java.util.*;
public class bcount{
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int N, M; 
		StringTokenizer st =new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] ps = new int[3][N+1];
		for(int i =0; i < N; i ++) {
			int breed = Integer.parseInt(f.readLine()) - 1;
			ps[0][i + 1] = ps[0][i];
			ps[1][i + 1] = ps[1][i];
			ps[2][i + 1] = ps[2][i];
			ps[breed][i + 1] = ps[breed][i] + 1;
		}
		int a,b;
		for(int i = 0; i < M; i ++) {
			st = new StringTokenizer(f.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			pw.println((ps[0][b] - ps[0][a-1]) + " "+(ps[1][b] - ps[1][a-1])+" "+(ps[2][b] - ps[2][a-1]));
		}
		//System.out.println(Arrays.deepToString(ps).replaceAll("]", "]\n"));
		pw.close();
		f.close();	
	}
}
