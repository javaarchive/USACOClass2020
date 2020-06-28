import java.io.*;
import java.util.*;
public class reduce {
	public static int check(List<Point> points) {
		return -1;
	}
	public static int getBit(int n, int k) {
	    return (n >> k) & 1;
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
		List<Point> pmaxx, pmaxy, pminx, pminy;
		pminx = new ArrayList<>();
		pminy = new ArrayList<>();
		pmaxx = new ArrayList<>();
		pmaxy = new ArrayList<>();
		for(Point p: cows) {
			if(p.x == maxx) {
				pmaxx.add(p);
			}
			if(p.x == minx) {
				pminx.add(p);
			}
			if(p.y == miny) {
				pmaxy.add(p);
			}
			if(p.y == maxy) {
				pmaxy.add(p);
			}
		}
		boolean useminx, usemaxx, useminy, usemaxy;
		List<Point> test = new ArrayList<>();
		//System.out.println(pminx);
		//System.out.println(pmaxx);
		//System.out.println(pminy);
		//System.out.println(pmaxy);
		int best = Integer.MAX_VALUE;
		for(int i = 0; i < 16; i ++) {
			test.clear();
			//System.out.println(getBit(i, 0));
			useminx = getBit(i, 0) == 1;
			usemaxx = getBit(i, 1) == 1;
			useminy = getBit(i, 2) == 1;
			usemaxy = getBit(i, 3) == 1;
			//System.out.println((useminx?1:0)+" "+(usemaxx?1:0)+" "+(useminy?1:0)+" "+(usemaxy?1:0));
			if(useminx) {
				test.addAll(pminx);
				if(test.size() > 3 || pminx.isEmpty()) {
					continue;
				}
			}
			if(useminy) {
				test.addAll(pminy);
				if(test.size() > 3 || pminy.isEmpty()) {
					continue;
				}
			}
			if(usemaxx) {
				test.addAll(pmaxx);
				if(test.size() > 3 || pmaxx.isEmpty()) {
					continue;
				}
			}
			if(usemaxy) {
				test.addAll(pmaxy);
				if(test.size() > 3 || pmaxy.isEmpty()) {
					continue;
				}
			}
			if(test.size() > 3) {
				continue;
			}
			for(Point p: test) {
				p.exists = false;
			}
			//System.out.println(test);
			minx = Integer.MAX_VALUE;
			miny = Integer.MAX_VALUE;
			maxx = Integer.MIN_VALUE;
			maxy = Integer.MIN_VALUE;
			for(Point p: cows) {
				int x,y;
				if(!p.exists) {
					//System.out.println("Cow marked as sold");
					continue;
				}
				x = p.x;
				y = p.y;
				minx = Integer.min(minx, x);
				miny = Integer.min(miny, y);
				maxx = Integer.max(maxx, x);
				maxy = Integer.max(maxy, y);
			}
			//System.out.println("Stats: "+minx+" "+miny+" "+maxx+" "+maxy);
			//System.out.println("Perim: "+(maxx-minx)*(maxy-miny));
			best = Integer.min(best, (maxx-minx)*(maxy-miny));
			for(Point p: test) {
				p.exists = true;
			}
		}
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
