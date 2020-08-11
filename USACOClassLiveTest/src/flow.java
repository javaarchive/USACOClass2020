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
        int range = 'z' - 'A' + 1;
        int[][] matrix = new int[range][range];
        int[] neighborCount = new int[range];
        for(int i = 0; i < N; i ++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            int a = st.nextToken().charAt(0) - 'A';
            int b = st.nextToken().charAt(0) - 'A';
            int edge = Integer.parseInt(st.nextToken());
            matrix[a][b] += edge;
            neighborCount[a] ++;
        }
        for(int k = 0; k < N; k ++){
            if(k == 'Z'){
                continue;
            }
            if(neighborCount[k] == 0){

            }
        }
        for(int i = 0; i < range; i ++){
            for(int j = 0; j < range; j ++){
                if(matrix[i][j] == 0){
                    continue;
                }
                if(neighborCount[j] == 1){
                    // Combine nodes
                    int neighbor = -1;
                    for(int k = 0; k < matrix[j].length; k ++){
                        if(0 < matrix[j][k]){
                            neighbor = k;
                            break;
                        }
                    }
                    matrix[i][neighbor] += Integer.min(matrix[i][j], matrix[j][neighbor]);
                    matrix[j][neighbor] = 0;
                    matrix[i][j] = 0;
                }
            }
        }
        //pw.println(graph.get(start).get(0).flow);
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