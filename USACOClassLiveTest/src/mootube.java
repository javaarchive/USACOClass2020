import java.io.*;
import java.util.*;
public class mootube {
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                                    new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int N, Q;
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		List<List<AdjItem>> adjlist = new ArrayList<>(N);
		for(int i = 0; i < N; i ++) {
			adjlist.add(new ArrayList<>());
		}
		for(int i = 0; i < N-1; i ++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int rev = Integer.parseInt(st.nextToken())-1;
			adjlist.get(a).add(new AdjItem(b, rev));
			adjlist.get(b).add(new AdjItem(a, rev));
		}
		for(int i = 0; i < Q; i ++) {
			st = new StringTokenizer(f.readLine());
			int minrev = Integer.parseInt(st.nextToken()) - 1;
			int node = Integer.parseInt(st.nextToken());
			int ans = 0;
			for(AdjItem ai: adjlist.get(node)) {
				if(ai.weight >= minrev) {
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