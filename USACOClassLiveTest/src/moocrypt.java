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
			for(int j = 0; j < rows; j ++) {
				map[i][j] = (int) line.charAt(j);
			}
		}
		int total = 0;
		int start = (int) 'a';
		int end = (int) 'z' + 1;
		for(int i = 0; i < dir.length; i ++) {
			for(int a = start; a < end; a ++) {
				for(int b = start; b < end; b ++) {
					if(a == b) {
						continue;
					}
					for(int row = 0; row < rows; row ++) {
						for(int col = 0; col < cols; col ++) {
							int curX = row;
							int curY = col;
							System.out.println(curX+" "+curY+" "+Arrays.toString(dir[i]));
							boolean works = false;
							
							works = (map[curX][curY] == a) && (map[curX+dir[i][0]][curY+dir[i][1]] == b) && (map[curX+dir[i][0]+dir[i][0]][curY+dir[i][1]+dir[i][1]] == b);
							if(0 < curX + dir[i][0] || ) {
								
							}
							if(works) {
								total++;
							}
						}
					}
				}
			}
		}
		pw.println(total);
		pw.close();
		f.close();
	}

}