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
        int comps = 0;
        boolean fail = false;
        //pw.print("1");
        for(int i = 0; i < N; i ++){
            if(nodeTypes[i] != 0){
                continue;
            }
            Queue<Integer> q = new LinkedList<>();
            q.add(i);
            nodeTypes[i] = 1;
            comps ++;
            while(!q.isEmpty()){
                int node = q.poll();
                byte nType = nodeTypes[i];
                for(FlavorEdge fe: graph.get(node)){
                    if(visited[fe.conn]){
                        continue;
                    }
                    if(nType == 0){
                        nType = fe.type;
                        nodeTypes[i] = nType;
                    }
                    if(nodeTypes[fe.conn] == 0 && nType != fe.type){
                        nodeTypes[fe.conn] = fe.type;
                    }
                    if(nodeTypes[fe.conn] != fe.type && nType != fe.type){
                        fail = true;
                    }
                    visited[fe.conn] = true;
                }
            }           
        }
        //ystem.out.println(comps);
        //System.out.println(Arrays.toString(nodeTypes));
        if(fail){
            pw.println("0");
        }
        pw.close();
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