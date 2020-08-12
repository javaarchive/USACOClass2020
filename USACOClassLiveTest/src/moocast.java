import java.io.*;
import java.util.*;

public class moocast {
    public static int pythag(Cowphone cp1, Cowphone cp2) {
        int a = cp1.x - cp2.x;
        int b = cp1.y - cp2.y;
        return (int) Math.sqrt((a * a) + (b * b));
    }

    public static void main(String[] args) throws IOException {
        // IO
        // new FileReader("cownomics.in")s
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("cownomics.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(f.readLine());
        List<Cowphone> cows = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int radius = Integer.parseInt(st.nextToken());
            Cowphone cp = new Cowphone(i, x, y, radius);
            cows.add(cp);
        }
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Cowphone cp1 = cows.get(i);
            List<Integer> connected = new ArrayList<>();
            graph.add(connected);
            for (int j = 0; j < N; j++) {
                Cowphone cp2 = cows.get(j);
                if (cp1.canSendTo(cp2)) {
                    connected.add(cp2.id);
                }
            }
        }
        int best = 1;
        //System.out.println(graph);
        for (int i = 0; i < N; i++) {
            // Obtain starting cow
            Cowphone c = cows.get(i);
            int reachedCows = 1;
            boolean[] visited = new boolean[N];
            visited[i] = true;
            Queue<Integer> nextToVisit = new LinkedList<>();
            nextToVisit.add(i);
            //System.out.println("start"+i);
            while (!nextToVisit.isEmpty()) {
                int current = nextToVisit.poll();
                for (int neighbor : graph.get(current)) {
                    if(!visited[neighbor]){
                        //System.out.println("+"+neighbor);
                        visited[neighbor] = true;
                        reachedCows ++;
                        nextToVisit.add(neighbor);
                    }
                }
            }
            best = Integer.max(best, reachedCows);
        }
        pw.println(best);
        pw.close();
    }
}

class Cowphone {
    int radius;
    int x, y;
    int id;

    public Cowphone(int id, int x, int y, int radius) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public boolean canSendTo(Cowphone c2) {
        return moocast.pythag(this, c2) < radius;
    }
}