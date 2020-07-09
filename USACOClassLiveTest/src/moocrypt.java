import java.io.*;
import java.util.*;
public class moocrypt {

	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                                    new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int[][] dir = {{0,1}, {1,1},{1,0}, {1,-1},{0,-1},{-1,-1}, {-1, 0}, {-1, 1}};
		int rows, cols;
		StringTokenizer st = new StringTokenizer(f.readLine());
		rows = Integer.parseInt(st.nextToken());
		cols = Integer.parseInt(st.nextToken());
		int[][] map;
		String line;
		map = new int[rows][cols];
		for(int i = 0; i < rows; i ++) {
			line = f.readLine();
			for(int j = 0; j < cols; j ++) {
				map[i][j] = (int) line.charAt(j);
			}
		}
		int total = 0;
		int start = (int) 'A';
		int end = (int) 'Z';
		int best = -1;
		//System.out.println(Arrays.deepToString(map));
			for(int a = start; a <= end; a ++) {
				if(a == 'M'){
					continue;
				}
				for(int b = start; b <= end; b ++) {
					if(a == b || b == 'O') {
						continue;
					}
					total = 0;
					//boolean[][] used = new boolean[rows][cols];
					for(int i = 0; i < dir.length; i ++) {
					for(int row = 0; row < rows; row ++) {
						for(int col = 0; col < cols; col ++) {
							int curX = row;
							int curY = col;
							//System.out.println(i+" "+a+" "+b+" "+curX+" "+curY+" "+Arrays.toString(dir[i]));
							boolean works = false;
							boolean inBounds = 0 <= (curX + 2 * dir[i][0]) && 0 <= (curY + 2 * dir[i][1]) && (curX + 2 * dir[i][0]) < rows && (curY + 2 * dir[i][1]) < cols;
							works = inBounds && (map[curX][curY] == a) 
							&& (map[curX+dir[i][0]][curY+dir[i][1]] == b) 
							&& (map[curX+dir[i][0]+dir[i][0]][curY+dir[i][1]+dir[i][1]] == b);
							
							if(works) {
								//used[curX][curY] = true;
								//System.out.println((char) a+" "+(char) b+" "+row+" "+col+" going "+dir[i][0]+" and "+dir[i][1]);
								total++;
							}
						}
					}
				}
					if(total > best){
						best = total;
					}
				}
			}
		
		pw.println(best);
		pw.close();
		f.close();
	}

}
