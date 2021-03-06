// Imports
import java.io.*;
import java.util.*;
// It seems that sometimes things go wrong if the class
// isn't the same name as the problem name
public class lepr {
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("lepr.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("lepr.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		// Process
		StringTokenizer st;
		int N = Integer.parseInt(f.readLine());
		int[][] grid = new int[N][N];
		for(int i = 0; i < N; i ++) {
			st = new StringTokenizer(f.readLine());
			for(int j = 0; j < N; j ++) {
				int item = Integer.parseInt(st.nextToken());
				grid[i][j] = item;
			}
		}
		// Hardcoded dirs
		//System.out.println(Integer.MIN_VALUE);
		int[][] dir = {{1,0},{1,1},{0,1},{-1,1}};
		int best = Integer.MIN_VALUE;
		for(int i = 0; i < N; i ++) {
			for(int j = 0; j < N; j ++) {
				for(int[] direction: dir) {
					int posx= i;
					int posy = j;
					int curSum = 0;
					for(int k = 0; k < N; k ++) {
						curSum += grid[posx][posy];
						best = Math.max(best, curSum);
						// moooooove
						posx = (posx + N + direction[0]) % N;
						posy = (posy + N + direction[1]) % N;
					}
				}
			}
		}
		// Close da files
		pw.println(best);
		pw.close();
		f.close();
		
	}

}
