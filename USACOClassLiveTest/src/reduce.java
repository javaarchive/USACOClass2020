import java.io.*;
import java.util.*;
public class reduce {
	public static int check(List<Point> points) {
		return -1;
	}
	public static int getBit(int n, int k) {
	    return (n >> k) & 1;
	}
	//static Point aix = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE), aax = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE), aiy = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE), aay = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
	//static Point bix = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE), bax = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE), biy = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE), bay = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
	//static Point cix = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE), cax = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE), ciy = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE), cay = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
	//static Point dix = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE), dax = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE), diy = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE), day = new Point(Integer.MIN_VALUE, Integer.MIN_VALUE);
	static Point[][] ref = new Point[4][4];
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
			Point p = new Point(x,y, -i, -i);
			if(!p.exists) {
				continue;
			}
			cows.add(p);
		}
		cows.sort(new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				int c1 = Integer.compare(o1.x, o2.x);
				if(c1 == 0) {
					return Integer.compare(o1.y, o2.y);
				}
				return c1;
			}
			
		});
		ref[0][0] = cows.get(0).setRef(0, 0);
		ref[1][0] = cows.get(1).setRef(1, 0);;
		ref[2][0] = cows.get(2).setRef(2, 0);;
		ref[3][0] = cows.get(3).setRef(3, 0);;
		ref[0][1] = cows.get(N-1).setRef(0, 1);;
		ref[1][1] = cows.get(N-2).setRef(1, 1);;
		ref[2][1] = cows.get(N-3).setRef(2, 1);;
		ref[3][1] = cows.get(N-4).setRef(3, 1);;
		cows.sort(new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				int c1 = Integer.compare(o1.y, o2.y);
				if(c1 == 0) {
					return Integer.compare(o1.x, o2.x);
				}
				return c1;
			}
			
		});
		ref[0][2] = cows.get(0).setRef(0, 2);
		ref[1][2] = cows.get(1).setRef(1, 2);
		ref[2][2] = cows.get(2).setRef(2, 2);
		ref[3][2] = cows.get(3).setRef(3, 2);
		ref[0][3] = cows.get(N-1).setRef(0, 3);
		ref[1][3] = cows.get(N-2).setRef(1, 3);
		ref[2][3] = cows.get(N-3).setRef(2, 3);
		ref[3][3] = cows.get(N-4).setRef(3, 3);
		//System.out.println(cows);
		//Point[] selections1 = {aix, aiy, aax, aay};
		//Point[] selections2 = {bix, biy, bax, bay};
		//Point[] selections3 = {cix, ciy, cax, cay};
		//Point[] selections4 = {dix, diy, dax, day};
		
		//System.out.println(Arrays.toString(selections1));
		//System.out.println(Arrays.toString(selections2));
		//System.out.println(Arrays.toString(selections3));
		//System.out.println(Arrays.toString(selections4));
		int a = 0,b = 0,c = 0,d = 0;
		for(int i = 0; i < 4; i ++) {
			switch(i) {
			case 0:
				a++;
				break;
			case 1:
				b++;
				break;
			case 2:
				c++;
				break;
			case 3:
				d++;
				break;
			}
			for(int j = 0; j < 4; j ++) {
				switch(j) {
				case 0:
					a++;
					break;
				case 1:
					b++;
					break;
				case 2:
					c++;
					break;
				case 3:
					d++;
					break;
				}
				for(int k = 0; k < 4; k ++) {
					switch(k) {
					case 0:
						a++;
						break;
					case 1:
						b++;
						break;
					case 2:
						c++;
						break;
					case 3:
						d++;
						break;
					}
					for(int m = 0; m < 4; m ++) {
						switch(m) {
						case 0:
							a++;
							break;
						case 1:
							b++;
							break;
						case 2:
							c++;
							break;
						case 3:
							d++;
							break;
						}
					for(int l = 0; l < 4; l ++) {
					if(l!=0) {ref[a][0].exists = false;}
					if(l!=1) {ref[b][1].exists = false;}
					if(l!=2) {ref[c][2].exists = false;}
					if(l!=3) {ref[d][3].exists = false;}
					//for(int l = 0; l < 4; l ++) {
						//selections4[l].exists = false;
						//System.out.println(selections1[i]+" "+selections2[j]+" "+selections3[k]+" "+selections4[l]);
						int minx = Integer.MAX_VALUE, miny = Integer.MAX_VALUE, maxx = Integer.MIN_VALUE, maxy = Integer.MIN_VALUE; 
						for(Point p: cows) {
							if(!p.exists) {
								continue;
							}
							minx = Integer.min(p.x, minx);
							miny = Integer.min(p.y, miny);
							maxx = Integer.max(p.x, maxx);
							maxy = Integer.max(p.y, maxy);
						}
						int count = 0;
						for(Point p: cows) {
							if(p.x < minx && maxx < p.x && p.y < miny && maxy < p.y) {
								count ++;
							}
						}
						if(count <= 3) {
							//if(((maxx - minx) * (maxy - miny)) < best) {
								//System.out.println("BETTER! "+minx+" "+maxx+" "+miny+" "+maxy);
							//}
							best = Integer.min(best, (maxx - minx) * (maxy - miny));
						}
						//System.out.println(cows);
						//selections4[l].exists = true;
					//}
						if(l!=0) {ref[a][0].exists = true;}
						if(l!=1) {ref[b][1].exists = true;}
						if(l!=2) {ref[c][2].exists = true;}
						if(l!=3) {ref[d][3].exists = true;}
					}
					switch(m) {
					case 0:
						a--;
						break;
					case 1:
						b--;
						break;
					case 2:
						c--;
						break;
					case 3:
						d--;
						break;
					}
					}
						switch(k) {
						case 0:
							a--;
							break;
						case 1:
							b--;
							break;
						case 2:
							c--;
							break;
						case 3:
							d--;
							break;
						}
				}
				switch(j) {
				case 0:
					a--;
					break;
				case 1:
					b--;
					break;
				case 2:
					c--;
					break;
				case 3:
					d--;
					break;
				}
			}
			switch(i) {
			case 0:
				a--;
				break;
			case 1:
				b--;
				break;
			case 2:
				c--;
				break;
			case 3:
				d--;
				break;
			}
		}
		
		pw.println(best);
		pw.close();
	}
}
class Point{
	int x;
	int y;
	int ax, ay;
	public boolean exists = true;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if(!this.exists) {
			builder.append("!");
		}
		builder.append("(");
		builder.append(x);
		builder.append(", ");
		builder.append(y);
		builder.append(")");
		return builder.toString();
	}

	public Point(int x, int y, int ax, int ay) {
		this.x = x;
		this.y = y;
		this.ax = ax;
		this.ay = ay;
	}
	public Point setRef(int ax, int ay) {
		this.ax = ax;
		this.ay = ay;
		return this;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (exists ? 1231 : 1237);
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Point)) {
			return false;
		}
		Point other = (Point) obj;
		if (exists != other.exists) {
			return false;
		}
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		this.ax = -1;
		this.ay = -1;
	}
}
