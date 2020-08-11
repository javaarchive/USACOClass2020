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
        for(int i = 0; i < 2*N; i ++){
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < 2*N; i ++){
            rgraph.add(new ArrayList<>());
        }
        for(int i = 0; i < N; i ++){
            StringTokenizer st = new StringTokenizer(f.readLine());
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
        int[] flows = new int[graph.size()];
        // BFS
        visited[start] = true;
        Queue<MovementChoice> searchQueue = new LinkedList<>();
        searchQueue.add(new MovementChoice(start, Integer.MAX_VALUE));
        for(int i = 0; i < graph.size(); i ++){
            graph.get(i).sort(new Comparator<Connection>(){

                @Override
                public int compare(Connection o1, Connection o2) {
                    // TODO Auto-generated method stub
                    return Integer.compare(o2.flow, o1.flow);
                }

            });
        }
        while(!searchQueue.isEmpty()){
            MovementChoice mc = searchQueue.poll();
            flows[mc.node] += mc.flow;
            for(Connection c: graph.get(mc.node)){
                if(!visited[c.node]){ 
                    visited[c.node] = true;
                    searchQueue.add(new MovementChoice(c.node, Integer.min(c.flow, flows[mc.node])));
                }
            }
        }
        //pw.println(Arrays.toString(flows));
        pw.println(flows[end]);
        pw.close();
    }
}
/*class MovementChoice{
    int node;
    int flow;
    public MovementChoice(int node, int curflow){
        this.node = node;
        this.flow = curflow;
    }
}
class Connection{
    int node;
    int flow;
    public Connection(int nid, int flowVal){
        this.node = nid;
        this.flow = flowVal;
    }
}*/