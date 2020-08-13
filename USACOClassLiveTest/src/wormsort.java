import java.io.*;
import java.util.*;

public class wormsort {
    public static List<Integer> orderOfCows;
    private static List<List<WeightedEdge>> adjList = new ArrayList<>();

    public static boolean works(int minCostRequirement) {
        int N = orderOfCows.size();
        //int found = 0;
        Stack<Integer> searchStack = new Stack<>();
        boolean[] visited = new boolean[N];
        int[] comp = new int[N];
        Arrays.fill(comp, -1);
        //int nc = 0;
        for (int i = 0; i < N; i++) {
            visited[i] = true;
            if(comp[i] != -1){
                continue;
            }
            comp[i] = i;
            
            searchStack.add(i); // does it really matter if it's 0
            //int edges = 0;
            while (!searchStack.isEmpty()) {
                int node = searchStack.pop();
                // found ++;
                for (WeightedEdge we : adjList.get(node)) {
                    if (comp[we.cid] == i || we.cost < minCostRequirement) {
                        continue;
                    }
                    //edges++;
                    /*if (we.cost < minCostRequirement) {
                        continue;
                    }*/
                    //visited[we.cid] = true;
                    comp[we.cid] = i;
                    searchStack.add(we.cid);
                }
            }
        }
        for(int i = 0; i < N; i ++){
            if(comp[i] != comp[orderOfCows.get(i)]){
                return false;
            }
        }
        // System.out.println("works("+maxCostRequirement+") = "+found);
        return true;
    }

    public static void main(String[] args) throws IOException {
        // IO
        // new FileReader("pathfindin")
        //BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader f = new BufferedReader(new FileReader("wormsort.in"));
        // new BufferedWriter(new FileWriter("pathfind.out"))
        //PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("wormsort.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        orderOfCows = new ArrayList<>();
        st = new StringTokenizer(f.readLine());
        int last = -2;
        boolean alreadySorted = true;
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
            int cowId = Integer.parseInt(st.nextToken()) - 1;
            orderOfCows.add(cowId);
            if (cowId < last) {
                alreadySorted = false;
            }
            last = cowId;
        }
        if (alreadySorted) {
            pw.println("-1");
            pw.close();
            System.exit(0);
        }
        if ((M + 1) < N) { // impossible to be at least a tree
            pw.println("0");
            pw.close();
            System.exit(0);
        }
        //int maxCost = Integer.MIN_VALUE;
        //int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(f.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            //minCost = Integer.min(minCost, cost);
            //maxCost = Integer.max(maxCost, cost);
            adjList.get(a).add(new WeightedEdge(cost, b));
            adjList.get(b).add(new WeightedEdge(cost, a));
        }
        int top =  1000000001, bottom = 0, mid = -1;
        while ((top - bottom) > 1) {
            mid = (int) Math.floor((bottom + top) / 2);
            //System.out.println(bottom+" "+mid+" "+top);
            boolean result = works(mid);
            if (result) {
                bottom = mid;
            } else {
                top = mid;
            }
        }
        // System.out.println("Final: "+bottom+" "+mid+" "+top);
        if (works(bottom)) {
            pw.println(bottom);
        } else {
            pw.println(top);
        }
        pw.close();
    }
}

class WeightedEdge {
    int cost;
    int cid;

    public WeightedEdge(int cost, int node) {
        this.cost = cost;
        this.cid = node;
    }
}