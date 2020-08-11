import java.io.*;
import java.util.*;

public class flow {
    public static boolean checkNoDup(List<Connection> cList) {
        for (int i = 0; i < cList.size() - 1; i++) {
            if (cList.get(i).node == cList.get(i + 1).node) {
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
        int range = 'z' - 'A' + 1;
        int[][] matrix = new int[range][range];
        int[] out = new int[range];
        int[] in = new int[range];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int a = st.nextToken().charAt(0) - 'A';
            int b = st.nextToken().charAt(0) - 'A';
            int edge = Integer.parseInt(st.nextToken());
            if(matrix[a][b] == 0){
                out[a] = out[a] + 1;
                in[b] = in[b] + 1;
            }
            matrix[a][b] +=  edge;
            
        }
        /*for(int i = 0; i < matrix.length; i ++){
            String out = Arrays.toString(matrix[i]);
            System.out.println(out.substring(1, out.length() - 1));
            //System.out.println();
        }
        System.out.println();*/
        //System.out.println(Arrays.toString(neighborCount));
        /*for(int i = 0; i < matrix.length; i ++){
            for(int j = 0; j < matrix[i].length; j ++){
                System.out.print((matrix[i][j] == 0) ? 0:1);
            }
            System.out.println();
        }*/
        int Z = 'Z' - 'a';
        for (int ll = 0; ll < N; ll ++) {
            for (int k = 0; k < range; k++) {
                if (k == 25) {
                    continue;
                }
                if (out[k] == 0) {
                    for (int i = 0; i < range; i++) {
                        if (matrix[i][k] > 0) {
                            //System.out.println("Removed Connection "+i+" "+k);
                            matrix[i][k] = 0;
                            out[i]--;
                            in[k]--;
                        }
                    }
                }
            }
        }
        for (int ll = 0; ll < N; ll ++) {
            for (int i = 0; i < range; i++) {
                for (int j = 0; j < range; j++) {
                    if (matrix[i][j] == 0) {
                        continue; // not connected
                    }
                    if (out[j] == 1 && in[j] == 1) {
                        // Combine nodes
                        int neighbor = -1;
                        for (int k = 0; k < matrix[j].length; k++) {
                            //System.out.println("Neighbor has "+directionalNeighborCount[k]+" nodes connected");
                            if (0 < matrix[j][k]) {
                                neighbor = k; 
                                break;
                            }
                        }
                        if(neighbor == -1){
                            continue; // No neighbors
                        }
                        if(matrix[i][neighbor] != 0){
                            out[i] --;
                            in [neighbor] --;
                        }
                        matrix[i][neighbor] += Integer.min(matrix[i][j], matrix[j][neighbor]);
                        matrix[j][neighbor] = 0;
                        out[j] = 0;
                        in[j]  = 0;
                        in[neighbor] --;
                        out[i] --;
                        matrix[i][j] = 0;
                        //out[i] --;
                    }
                }
            }
            /*for(int i = 0; i < matrix.length; i ++){
                for(int j = 0; j < matrix[i].length; j ++){
                    System.out.print((matrix[i][j] == 0) ? 0:1);
                }
                System.out.println();
            }
            System.out.println();*/
        }
        //System.out.println(Arrays.toString(neighborCount));
        //System.out.println(Arrays.toString(matrix[0]));
        /*for(int i = 0; i < matrix.length; i ++){
            String out = Arrays.toString(matrix[i]);
            System.out.println(out.substring(1, out.length() - 1));
            //System.out.println();
        }*/
        // pw.println(graph.get(start).get(0).flow);
        pw.println(matrix[0][25]);
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