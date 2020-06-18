import java.io.*;
import java.util.*;
public class pathfind {
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("pathfindin")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("pathfind.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean[][] matrix = new boolean[N][N];
		for(int i = 0; i < N; i ++) {
			st = new StringTokenizer(f.readLine());
			for(int j = 0; j < N; j ++) {
				matrix[i][j] = st.nextToken().charAt(0) == '1';
			}
		}
		f.close();
		Queue<BFSOption> bfsq = new LinkedList<>();
		bfsq.add(new BFSOption(1, M-1));
		int lastDepth = 1;
		boolean[] visited = new boolean[N];
		visited[M-1] = true;
		List<Integer> connected = new ArrayList<>();
		while(!bfsq.isEmpty()) {
			BFSOption bo = bfsq.poll();
			for(int i = 0; i < N; i ++) {
				if(matrix[bo.node][i] && !visited[i]) {
					visited[i] = true;
					bfsq.add(new BFSOption(bo.depth + 1, i));
				}
			}
			if(lastDepth != bo.depth) {
				//System.out.println("DEPTH CHANGE");
				connected.sort(new Comparator<Integer>() {

					@Override
					public int compare(Integer arg0, Integer arg1) {
						// TODO Auto-generated method stub
						return Integer.compare(arg0, arg1);
					}});
				for(int node: connected) {pw.print((node+1)+" ");};
				pw.println();
				connected.clear();
				lastDepth = bo.depth;
			}
			connected.add(bo.node);
			
			//System.out.println(bfsq);
			//System.out.println((bo.node+1)+" ");
		}
		connected.sort(new Comparator<Integer>() {

			@Override
			public int compare(Integer arg0, Integer arg1) {
				// TODO Auto-generated method stub
				return Integer.compare(arg0, arg1);
			}});
		for(int node: connected) {pw.print((node+1)+" ");};
		pw.close();
		
	}	
	

}
class BFSOption{
	int depth;
	int node;
	public BFSOption(int depth, int node) {
		this.depth = depth;
		this.node = node;
	}
	public String toString() {
		return "(n: "+(node+1)+", depth: "+depth+" )";
	}
}
