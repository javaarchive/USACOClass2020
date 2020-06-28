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
		return Integer.min(Integer.min(Math.abs(p.x - minx), Math.abs(p.x - maxx)) , Integer.min(Math.abs(p.y - miny), Math.abs(p.y - maxy)));
	}
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("reduce.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("reduce.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(f.readLine());
		int best = Integer.MAX_VALUE;
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
		int same = score(cows.get(0));
		int bound1 = 1, bound2=2, bound3=3;
		for(int i = 0; i < N; i ++) {
			if(score(cows.get(i)) != same) {
				bound1 = i;
				break;
			}
		}
		same = score(cows.get(bound1));
		
		for(int i = bound1; i < N; i ++) {
			if(score(cows.get(i)) != same) {
				bound2 = i;
				break;
			}
		}
		same = score(cows.get(bound2));
		for(int i = bound2; i < N; i ++) {
			if(score(cows.get(i)) != same) {
				//System.out.println("Set");
				bound3 = i;
				break;
			}
		}
		
		//System.out.println(bound1+" "+bound2+" "+bound3);
		for(int a = 0; a < bound1; a ++) {
			for(int b = bound1; b < bound2; b ++) {
				for(int c = bound2; c < bound3; c ++) {
		 minx = Integer.MAX_VALUE;
		 miny = Integer.MAX_VALUE;
		 maxx = Integer.MIN_VALUE;
		 maxy = Integer.MIN_VALUE;
		 cows.get(a).exists = false;
		 cows.get(b).exists = false;
		 cows.get(c).exists = false;
		for(int i = 0; i < cows.size(); i ++) {
			Point p = cows.get(i);
			if(!p.exists) {
				continue;
			}
			int x,y;
			x = p.x;
			y = p.y;
			minx = Integer.min(minx, x);
			miny = Integer.min(miny, y);
			maxx = Integer.max(maxx, x);
			maxy = Integer.max(maxy, y);
		}
		cows.get(a).exists = true;
		 cows.get(b).exists = true;
		 cows.get(c).exists = true;
		best = Integer.min(best, Math.abs((maxy - miny) * (minx-maxx)));
		}}}
		
		pw.println(best);
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
