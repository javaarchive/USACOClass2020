import java.io.*;
import java.util.*;

public class hideseek2 {
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("flyingcow.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("flyingcow.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		@SuppressWarnings("unchecked")
		List<Integer>[] adjlist = new ArrayList[N];
		for(int i = 0; i < N; i ++) {
			adjlist[i] = new ArrayList<Integer>();
		}
		for(int i = 0; i < M; i ++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			adjlist[a].add(b);
			adjlist[b].add(a);
		}
		Queue<Move> options = new LinkedList<>();
		int[] bestpaths = new int[N];
		Arrays.fill(bestpaths, Integer.MAX_VALUE);
		bestpaths[0] = 0;
		options.add(new Move(0, 0));
		while(!options.isEmpty()) {
			Move m = options.poll();
			for(int node: adjlist[m.node]) {
				if((m.depth + 1) < bestpaths[node]) {
					//System.out.println("Better path found for "+node+" "+(m.depth+1)+" better than "+bestpaths[node]);
					bestpaths[node] = m.depth+1;
					options.add(new Move(node, m.depth + 1));
				}
			}
		}
		int best = Integer.MIN_VALUE;
		int bestIndex = -1;
		int bestcount = 0;
		for(int i = 0; i < N; i ++) {
			if(bestpaths[i] > best) {
				bestcount = 1;
				best = bestpaths[i];
				bestIndex = i;
				//System.out.println("Best "+i);
			}else if(bestpaths[i] == best) {
				bestcount ++;
			}
		}
		
		//System.out.println(Arrays.toString(bestpaths));
		pw.println((bestIndex+1)+" "+best+" "+bestcount);
		pw.close();
	}
}
class Move{
	int node,depth;
	public Move(int node,int depth) {
		this.node = node;
		this.depth = depth;
	}
}
