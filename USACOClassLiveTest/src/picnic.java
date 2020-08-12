import java.io.*;
import java.util.*;
public class picnic {
	public static void main(String[] args) throws IOException {
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(f.readLine());
		Map<Integer, List<Integer>> travelguide = new TreeMap<>();
		Map<Integer, Integer> visitcount = new TreeMap<>();
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<Integer> pastures = new ArrayList<>();
		for(int i = 0; i < K; i ++) {
			int node = Integer.parseInt(f.readLine());
			pastures.add(node);
		}
		for(int i = 0 ;i < N; i ++) {
			travelguide.put(i + 1, new ArrayList<>());
			visitcount.put(i + 1, 0);
		}
		for(int i = 0; i < M; i ++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			travelguide.get(a).add(b);
		}
		//System.out.println("Main loop");
		for(int key: pastures) {
			//System.out.println("Main loop 2");
			//System.out.println("BFSing for cow at "+key);
			Map<Integer, Boolean> visited = new TreeMap<>();
			for(int i = 0; i < N; i ++) {
				visited.put(i+1, false);
			}
			//System.out.println("Main loop 3");
			Queue<Integer> placestogo = new LinkedList<>();
			placestogo.add(key);
			visited.put(key, true);
			visitcount.put(key, visitcount.get(key) + 1);
			//System.out.println("Looping");
			while(!placestogo.isEmpty()) {
				int node = placestogo.poll();
				for(int neighbor: travelguide.get(node)) {
					if(visited.get(neighbor)) {
						continue;
					}
					//System.out.println("Reached "+neighbor);
					visitcount.put(neighbor, visitcount.get(neighbor) + 1);
					visited.put(neighbor, true);
					placestogo.add(neighbor);
				}
			}
		}
		//System.out.println(visitcount);
		int answer =0 ;
		//System.out.println(visitcount);
		for(int i = 0; i < N; i ++) {
			if(visitcount.get(i+1) == K) {
				answer++;
			}
		}
		pw.println(answer);
		pw.close();
	}

}
