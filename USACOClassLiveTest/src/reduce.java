import java.io.*;
import java.util.*;
public class reduce {
	public static int check(List<Point> points) {
		return -1;
	}
	public static int getBit(int n, int k) {
	    return (n >> k) & 1;
	}
	static int minx = Integer.MAX_VALUE,miny = Integer.MAX_VALUE,maxx = Integer.MIN_VALUE,maxy = Integer.MIN_VALUE;
	public static int score(Point p) {
		return Integer.min(Math.abs(p.x - minx), Math.abs(p.x - maxx)) + Integer.min(Math.abs(p.y - miny), Math.abs(p.y - maxy));
	}
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("reduce.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("reduce.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(f.readLine());
		StringTokenizer st;
		List<Point> cows = new ArrayList<>(N);
		for(int i = 0; i < N; i ++) {
			st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			Point p = new Point(x,y, i);
			minx = Integer.min(minx, x);
			miny = Integer.min(miny, y);
			maxx = Integer.max(maxx, x);
			maxy = Integer.max(maxy, y);
			cows.add(p);
		}
		cows.sort(new Comparator<Point>() {
			@Override
			public int compare(Point arg0, Point arg1) {
				return Integer.compare(score(arg0), score(arg1));
			}
		});
		//System.out.println(cows);
		cows.remove(0);
		cows.remove(0);
		cows.remove(0);
		minx = Integer.MAX_VALUE;
		miny = Integer.MAX_VALUE;
		maxx = Integer.MIN_VALUE;
		maxy = Integer.MIN_VALUE;
		for(int i = 0; i < cows.size(); i ++) {
			Point p = cows.get(i);
			int x,y;
			x = p.x;
			y = p.y;
			minx = Integer.min(minx, x);
			miny = Integer.min(miny, y);
			maxx = Integer.max(maxx, x);
			maxy = Integer.max(maxy, y);
		}
		pw.println(Math.abs((minx - maxx)*(maxy-miny)));
		pw.close();
	}
}
class Point{
	int x;
	int y;
	int index;
	public boolean exists = true;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("(");
		builder.append(x);
		builder.append(", ");
		builder.append(y);
		builder.append(")");
		return builder.toString();
	}

	public Point(int x, int y, int index) {
		this.x = x;
		this.y = y;
		this.index = index;
	}
}
