import java.io.*;
import java.util.*;
public class revegetate {
    public static void main(String[] args) throws IOException{
         // IO
        // new FileReader("reduce.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("reduce.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<List<FlavorEdge>> graph = new ArrayList<>(N);
        for(int i = 0; i < N; i ++){
            graph.add(new ArrayList<>());
        }
        byte[] nodeTypes = new byte[N];
        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(f.readLine());
            byte type = (byte) ((st.nextToken().equals("S"))?1:2);
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph.get(a).add(new FlavorEdge(b, type));
            graph.get(b).add(new FlavorEdge(a, type));
        }
        boolean[] visited = new boolean[N];
        int comps;
        for(int i = 0; i < N; i ++){
            Queue<Integer> q = new LinkedList<>();
            q.add(i);
            while(!q.isEmpty()){
                int node = q.poll();
                for(FlavorEdge fe: graph.get(node)){
                    
                }
            }           
        }
    }
}
class FlavorEdge{
    byte type;
    int conn;
    public FlavorEdge(int connectedNode, byte type){
        this.type = type;
        this.conn = connectedNode;
    }
}