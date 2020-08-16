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
            if(visited[i]){
                continue;
            }
            visited[i] = true;
            Queue<Integer> q = new LinkedList<>();
            q.add(i);
            nodeTypes[i] = 1;
            comps ++;
            while(!q.isEmpty()){
                int node = q.poll();
                byte nType = nodeTypes[node];
                //System.out.println("Picked node from queue "+node);
                for(FlavorEdge fe: graph.get(node)){
                    //nType = nodeTypes[i];
                    /*if(nodeTypes[fe.conn] != 0){
                        continue;
                    }*/
                    //System.out.println("Loop "+fe.conn+" : "+Arrays.toString(nodeTypes));
                    byte otherType = nodeTypes[fe.conn];
                    boolean updated = false;
                    if(fe.type == 1){
                        if(otherType == 0){
                            otherType = nType;
                            nodeTypes[fe.conn] = nType;
                            updated = true;
                        }
                        nType = nodeTypes[node];
                        if(nType != otherType){
                            //System.out.println("Fail S "+node+" "+fe.conn);
                            fail = true;
                            break;
                        }else{
                            if(updated){
                                visited[fe.conn] = true;
                                q.add(fe.conn);
                            }
                        }
                    }
                    updated = false;
                    //nType = nodeTypes[i];
                    if(fe.type == 2){
                        if(otherType == 0){
                            otherType = (byte) (3 - nType);
                            nodeTypes[fe.conn] = (byte) (3 - nType);
                            updated = true;
                        }
                        nType = nodeTypes[node];
                        if(nType == otherType){
                            
                            //System.out.println("Fail D "+node+" "+fe.conn+" "+Arrays.toString(nodeTypes)+" "+nType+" "+otherType+" "+updated);
                            fail = true;
                            break;
                        }else{
                            if(updated){
                                visited[fe.conn] = true;
                                q.add(fe.conn);
                            }
                        }
                    }
                }
                if(fail){
                    break;
                }
            }           
        }
       // System.out.println(comps+" "+fail);
        //System.out.println(Arrays.toString(visited));
        //System.out.println(Arrays.toString(nodeTypes));
        if(fail){
            pw.println("0");
        }else{
            pw.print("1");
            for(int i = 0; i < comps; i ++){
                pw.print("0");
            }
            pw.println();
            pw.close();
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