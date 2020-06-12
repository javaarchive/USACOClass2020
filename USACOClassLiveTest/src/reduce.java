import java.io.*;
import java.util.*;
public class reduce {
	public static int check(List<Point> points) {
		return -1;
	}
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("reduce.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("reduce.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(f.readLine());
		StringTokenizer st;
		int minx = Integer.MAX_VALUE,miny = Integer.MAX_VALUE,maxx = Integer.MIN_VALUE,maxy = Integer.MIN_VALUE;
		for(int i = 0; i < N; i ++) {
			st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			minx = Math.min(minx, x);
			miny = Math.min(miny, y);
			maxx = Math.max(maxx, x);
			maxy = Math.max(maxy, y);
		}
	}
}
class Point{
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
