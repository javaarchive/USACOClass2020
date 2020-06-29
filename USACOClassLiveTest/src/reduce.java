import java.io.*;
import java.util.*;
public class reduce {
	public static int check(List<Point> points) {
		return -1;
	}
	public static int getBit(int n, int k) {
	    return (n >> k) & 1;
	}
	static int aminx = Integer.MAX_VALUE,aminy = Integer.MAX_VALUE,amaxx = Integer.MIN_VALUE,amaxy = Integer.MIN_VALUE;
	static int bminx = Integer.MAX_VALUE,bminy = Integer.MAX_VALUE,bmaxx = Integer.MIN_VALUE,bmaxy = Integer.MIN_VALUE;
	static int cminx = Integer.MAX_VALUE,cminy = Integer.MAX_VALUE,cmaxx = Integer.MIN_VALUE,cmaxy = Integer.MIN_VALUE;
	static int dminx = Integer.MAX_VALUE,dminy = Integer.MAX_VALUE,dmaxx = Integer.MIN_VALUE,dmaxy = Integer.MIN_VALUE;
	static Point aix = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE), aax = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE), aiy = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE), aay = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
	static Point bix = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE), bax = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE), biy = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE), bay = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
	static Point cix = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE), cax = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE), ciy = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE), cay = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
	static Point dix = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE), dax = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE), diy = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE), day = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
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
			if(!p.exists) {
				continue;
			}
			if(x < aminx) {
				aminx = x;
				aix = p;
			}
			if(y < aminy) {
				aminy = y;
				aiy = p;
			}
			if(y > aminy) {
				amaxy = y;
				aay = p;
			}
			if(x > aminy) {
				amaxx = x;
				aax = p;
			}
			cows.add(p);
		}
		Point[] selections1 = {aix, aiy, aax, aay};
		for(int i = 0; i < selections1.length; i ++) {
			selections1[i].exists = false;
			for(Point p: cows) {
				if(!p.exists) {
					continue;
				}
				int x = p.x;
				int y = p.y;
				if(x < bminx) {
					bminx = x;
					bix = p;
				}
				if(y < bminy) {
					bminy = y;
					biy = p;
				}
				if(y > bminy) {
					bmaxy = y;
					bay = p;
				}
				if(x > bminy) {
					bmaxx = x;
					bax = p;
				}
				cows.add(p);
			}
			selections1[i].exists = true;
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
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		this.index = -1;
	}
}
