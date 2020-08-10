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
        List<List<Connection>> graph = new ArrayList<>(2*N); // worst case
        List<List<Connection>> rgraph = new ArrayList<>(2*N); // worst case
        Map<String, Integer> stringtoid = new TreeMap<>();
        for(int i = 0; i < N; i ++){
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < N; i ++){
            StringTokenizer st = new StringTokenizer(st.nextToken());
            String a = st.nextToken();
            String b = st.nextToken();
            int length = Integer.parseInt(st.nextToken());
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
            graph.get(na).add(new Connection(nb, length));
            rgraph.get(nb).add(new Connection(na, length));
        }
        int start = stringtoid.get("A");
        int end = stringtoid.get("Z");
        boolean[] visited = new boolean[graph.size()];
        // Find edges to delete
        visited[end] = true;
        Queue<MovementChoice> searchQ = new LinkedList<>();
        while(!searchQ.isEmpty()){
            MovementChoice mc = searchQ.poll();
            for(Connection c: graph.get(mc.node)){
                int node = c.node;
                if(!visited[node]){
                    visited[node] = true;
                    searchQ.add(new MovementChoice(node));
                }
            }
            for(Connection c: rgraph.get(mc.node)){
                int node = c.node;
                if(!visited[node]){
                    visited[node] = true;
                    searchQ.add(new MovementChoice(node));
                }
            }
        }
        
    }
}
class MovementChoice{
    int node;
    public MovementChoice(int node){
        this.node = node;
    }
}
class Connection{
    int node;
    int flow;
    public Connection(int nid, int flowVal){
        this.node = nid;
        this.flow = flowVal;
    }
}