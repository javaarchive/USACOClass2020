import java.io.*;
import java.util.*;

public class contest {
    public static void main(String[] args) throws IOException {
        // IO
        // new FileReader("milk3.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("milk3.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<List<Integer>> graph = new ArrayList<>(N);
        List<List<Integer>> reverseGraph = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(f.readLine());
            int winner = Integer.parseInt(st.nextToken()) - 1;
            int loser = Integer.parseInt(st.nextToken()) - 1;
            graph.get(winner).add(loser);
            reverseGraph.get(loser).add(winner);
        }
        boolean[] visited, visited2;
        int ans = 0;
        //System.out.println(graph);
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            visited2 = new boolean[N];
            Queue<SearchPlace> bfsqueue = new LinkedList<>();
            visited[i] = true;
            int losersCount = 0;
            bfsqueue.add(new SearchPlace(i));
            while (!bfsqueue.isEmpty()) {
                SearchPlace sp = bfsqueue.poll();
                //System.out.println("visit: "+sp.node);
                for (int nodeNum : graph.get(sp.node)) {
                    if (!visited[nodeNum]) {
                        visited[nodeNum] = true;
                        bfsqueue.add(new SearchPlace(nodeNum));
                        losersCount++;
                    }
                }
            }
            bfsqueue.add(new SearchPlace(i));
            int winnersCount = 0;
            while (!bfsqueue.isEmpty()) {
                SearchPlace sp = bfsqueue.poll();
                for (int nodeNum : reverseGraph.get(sp.node)) {
                    if (!visited2[nodeNum]) {
                        visited2[nodeNum] = true;
                        bfsqueue.add(new SearchPlace(nodeNum));
                        winnersCount++;
                    }
                }
            }
            //System.out.println(losersCount+" "+winnersCount);
            if((losersCount + winnersCount) == N-1){
                ans ++;
            }
        }
        pw.println(ans);
        pw.close();
    }
}

class SearchPlace {
    int node;

    public SearchPlace(int node) {
        this.node = node;
    }
}