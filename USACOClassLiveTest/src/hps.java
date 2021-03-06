import java.io.*;
import java.util.*;
public class hps{

	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int N; 
		StringTokenizer st;
		N = Integer.parseInt(f.readLine());
		int[][] ps = new int[3][N + 1];
		ps[0][0] = 0;
		ps[1][0] = 0;
		ps[2][0] = 0;
		for(int i = 1; i < N+1; i ++) {
			String line = f.readLine();
			char move = line.charAt(0);
			ps[0][i] = ps[0][i-1]; 
			ps[1][i] = ps[1][i-1]; 
			ps[2][i] = ps[2][i-1]; 
			if(move == 'H') {
				ps[0][i] = ps[0][i-1] + 1; 
			}else if(move == 'P') {
				ps[1][i] = ps[1][i-1] + 1;
			}else if(move == 'S') {
				ps[2][i] = ps[2][i-1] + 1;
			}
		}
		//System.out.println(Arrays.deepToString(ps));
		int out = 0;
		for(int i = 1; i < N+1; i ++) {
			int left = Math.max(Math.max(ps[0][i] - ps[0][0], ps[1][i] - ps[1][0]), ps[2][i] - ps[2][0]);
			int right = Math.max(Math.max(ps[0][N] - ps[0][i], ps[1][N] - ps[1][i]), ps[2][N] - ps[2][i]);
			out = Math.max(out, left+right);
		}
		pw.println(out);
		pw.close();
		f.close();
		
	}

}
