import java.io.*;
import java.util.*;
public class mootube {
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		//BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader f = new BufferedReader(new FileReader("mootube.in"));
		//                                    new BufferedWriter(new FileWriter("cownomics.out"))
		//PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
		int N, Q;
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		List<List<AdjItem>> adjlist = new ArrayList<>(N);
		for(int i = 0; i < N; i ++) {
			adjlist.add(new ArrayList<>());
		}
		int a,b,rev;
		int maxrev = 0;
		for(int i = 0; i < N-1; i ++) {
			st = new StringTokenizer(f.readLine());
			a = Integer.parseInt(st.nextToken())-1;
			b = Integer.parseInt(st.nextToken())-1;
			rev = Integer.parseInt(st.nextToken());
			adjlist.get(a).add(new AdjItem(b, rev));
			adjlist.get(b).add(new AdjItem(a, rev));
			maxrev = Integer.max(maxrev, rev);
		}
		int minrev,node,ans, nextNode;
		boolean[] visited;
		Stack<Integer> toVisit;
		for(int i = 0; i < Q; i ++) {
			st = new StringTokenizer(f.readLine());
			minrev = Integer.parseInt(st.nextToken());
			node = Integer.parseInt(st.nextToken())-1;
			if(minrev > maxrev){
				pw.println(N);
				continue;
			}
			ans = 0;
			visited = new boolean[N];
			toVisit = new Stack<Integer>();
			toVisit.add(node);
			visited[node] = true;
			//System.out.println("Processing: "+node);
			while(!toVisit.isEmpty()){
				int nodeId = toVisit.pop();
				//System.out.println("Reached "+nodeId);
				for(AdjItem adji: adjlist.get(nodeId)){
					nextNode = adji.connNode;
					if(adji.weight < minrev || visited[nextNode]){
						continue;
					}
					visited[nextNode] = true;
					toVisit.add(nextNode);
					ans ++;
				}
			}
			pw.println(ans);
		}
		pw.close();
	}

}
class AdjItem{
	int weight, connNode;
	public AdjItem(int connNode, int weight) {
		this.connNode = connNode;
		this.weight = weight;
	}
}