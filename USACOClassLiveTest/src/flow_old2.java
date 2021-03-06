import java.io.*;
import java.util.*;

public class flow {
    public static boolean checkNoDup(List<Connection> cList) {
        for(int i = 0; i < cList.size()-1; i ++){
            if(cList.get(i).node == cList.get(i + 1).node){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        // IO
        // new FileReader("cownomics.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("cownomics.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(f.readLine());
        int nextId = 0;
        List<List<Connection>> graph = new ArrayList<>(2 * N); // worst case
        // List<List<Connection>> rgraph = new ArrayList<>(2 * N); // worst case
        Map<String, Integer> stringtoid = new TreeMap<>();
        for (int i = 0; i < 2 * N; i++) {
            graph.add(new ArrayList<>());
        }
        /*
         * for (int i = 0; i < 2 * N; i++) { rgraph.add(new ArrayList<>()); }
         */
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            String a = st.nextToken();
            String b = st.nextToken();
            int length = Integer.parseInt(st.nextToken());
            if (!stringtoid.keySet().contains(a)) {
                stringtoid.put(a, nextId);
                nextId++;
            }
            if (!stringtoid.keySet().contains(b)) {
                stringtoid.put(b, nextId);
                nextId++;
            }
            int na = stringtoid.get(a);
            int nb = stringtoid.get(b);
            graph.get(na).add(new Connection(nb, length));
            // rgraph.get(nb).add(new Connection(na, length));
        }
        int start = stringtoid.get("A");
        int end = stringtoid.get("Z");
        boolean[] visited = new boolean[graph.size()];
        int[] flows = new int[graph.size()];
        // Reduction
        visited[start] = true;
        System.out.println(graph);
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < graph.size(); i++) {
                List<Connection> adj = graph.get(i);
                adj.sort(new Comparator<Connection>() {
                    @Override
                    public int compare(Connection o1, Connection o2) {
                        // TODO Auto-generated method stub
                        return Integer.compare(o1.node, o2.node);
                    }
                });
                int j = 0;
                if(checkNoDup(adj)){
                while (j < adj.size()) {
                    int node = adj.get(j).node;
                    if (graph.get(node).size() == 0 && !(node == end)) {
                        System.out.println("Removing " + i + " " + j);
                        adj.remove(j);
                    } else {
                        j++;
                    }
                }
            }
                j = 0;
                while (j < adj.size()) {
                    // Combine edges
                    List<Connection> c = graph.get(j);
                    if(c.size() > 0){
                    int middleNode = c.get(0).node;
                    List<Connection> c2 = graph.get(middleNode);
                    if (c2.size() == 1) {
                        int otherNode = c2.get(0).node;
                        int combinedFlow = Integer.min(c.get(0).flow, c2.get(0).flow);
                        c2.remove(0);
                        c.get(0).node = otherNode;
                        c.get(0).flow = combinedFlow;
                        System.out.println("Shortened " + j + " " + middleNode + " " + otherNode);
                    } else {
                        j++;
                    }
                }else{
                    j ++;
                }
                    // c2. = Integer.min(,c2.get(index))
                }
            }
            for (int i = 0; i < graph.size(); i++) {
                List<Connection> adj = graph.get(i);
                adj.sort(new Comparator<Connection>() {
                    @Override
                    public int compare(Connection o1, Connection o2) {
                        // TODO Auto-generated method stub
                        return Integer.compare(o1.node, o2.node);
                    }
                });
                int j = 0;
                while (j < adj.size() - 1) {
                    Connection a = adj.get(j);
                    Connection b = adj.get(j + 1);
                    if (a.node == b.node) {
                        adj.remove(j + 1);
                        a.flow = a.flow + b.flow;
                    } else {
                        j++;
                    }
                }
            }
        }
        System.out.println(graph);
        pw.println(graph.get(start).get(0).flow);
        pw.close();
    }
}

class MovementChoice {
    int node;
    int flow;

    public MovementChoice(int node, int curflow) {
        this.node = node;
        this.flow = curflow;
    }
}

class Connection {
    int node;
    int flow;

    public Connection(int nid, int flowVal) {
        this.node = nid;
        this.flow = flowVal;
    }

    @Override
    public String toString() {
        return "Connection [flow=" + flow + ", node=" + node + "]";
    }
}