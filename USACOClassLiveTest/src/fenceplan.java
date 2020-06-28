import java.io.*;
import java.util.*;
public class fenceplan {
	public static void main(String[] args) throws IOException{
		// new FileReader("farmoff.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		// new BufferedWriter(new FileWriter("farmoff.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Point2D[] cows = new Point2D[N];
		for(int i = 0; i < N; i ++) {
			st = new StringTokenizer(f.readLine());
			int x,y;
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			cows[i] = new Point2D(x,y);
		}
		List<List<Integer>> adjlist = new ArrayList<>(N);
		for(int i = 0; i < N; i ++) {
			adjlist.add(new ArrayList<>());
		}
		for(int i =0; i < M; i ++) {
			st = new StringTokenizer(f.readLine());
			int a,b;
			a = Integer.parseInt(st.nextToken())-1;
			b = Integer.parseInt(st.nextToken())-1;
			adjlist.get(a).add(b);
			adjlist.get(b).add(a);
		}
		boolean[] visited = new boolean[N];
		int bestperim = Integer.MAX_VALUE;
 		for(int i = 0; i < visited.length; i ++) {
 			if(visited[i]) {
 				continue;
 			}
 			Queue<Integer> choices = new LinkedList<>();
 			int maxx = Integer.MIN_VALUE, maxy = Integer.MIN_VALUE, minx = Integer.MAX_VALUE,  miny = Integer.MAX_VALUE;
 			choices.add(i);
 			while(!choices.isEmpty()) {
 				int cur = choices.poll();
 				Point2D p = cows[cur];
 				//System.out.println("Info: "+p+" cur x min/max "+minx+" "+maxx+" "+miny+" "+maxy);
 				if(p.x < minx) {
 					minx = p.x;
 				}
 				if(p.y < miny) {
 					miny = p.y;
 				}
 				if(p.x > maxx) {
 					maxx = p.x;
 				}
 				if(p.y > maxy) {
 					maxy = p.y;
 				}
 				for(int node: adjlist.get(cur)) {
 					if(!visited[node]) {
 						visited[node] = true;
 						choices.add(node);
 					}
 				}
 			}
 			int perim = 2 * ((maxx-minx) + (maxy - miny));
 			if(perim < bestperim) {
 				bestperim = perim;
 			}
 		}
 		pw.println(bestperim);
 		pw.close();
 		f.close();
	}
}
class Point2D{
	int x,y;
	public Point2D(int x,int y){
		this.x =x;
		this.y =y;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Point2D)) {
			return false;
		}
		Point2D other = (Point2D) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Point2D [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append("]");
		return builder.toString();
	}
	
}