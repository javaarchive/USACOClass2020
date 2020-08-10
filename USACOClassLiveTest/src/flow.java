import java.io.*;
import java.util.*;

public class flow {
    public static void main(String[] args) throws IOException{
        // IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("cownomics.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(f.readLine());
        int nextId = 0;
        List<List<Integer>> graph = new ArrayList<>(N);
        List<List<Integer>> rgraph = new ArrayList<>(N);
        Map<String, Integer> stringtoid = new TreeMap<>();
        for(int i = 0; i < N; i ++){
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < N; i ++){
            StringTokenizer st = new StringTokenizer(st.nextToken());
            String a = st.nextToken();
            String b = st.nextToken();
            if(!stringtoid.keySet().contains(a)){
                stringtoid.put(a, nextId);
                nextId ++;
            }
            if(!stringtoid.keySet().contains(b)){
                stringtoid.put(b, nextId);
                nextId ++;
            }
            int na = stringtoid.get(a);
            int nb = stringtoid.get(b);
            graph.get(na).add(nb);
            rgraph.get(nb).add(na);
        }
        int start = stringtoid.get("A");
        int end = stringtoid.get("Z");
        boolean[] visited = new boolean[N];
        // Find edges to delete
        visited[end] = true;
    }
}
class MovementChoice{
    public MovementChoice(int node){

    }
}