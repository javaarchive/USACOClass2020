import java.util.*;
import java.io.*;

public class planting {
    public static void main(String[] args) throws IOException {
        // IO
        // new FileReader("minesweeper.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("minesweeper.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(f.readLine());
        List<Rect> rects = new ArrayList<>(N);
        List<Endpoint> eps = new ArrayList<Endpoint>(2 * N);
        Set<Integer> xset = new TreeSet<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(f.readLine());
            int x1 = Integer.parseInt(st.nextToken());// + 10001;
            int y1 = Integer.parseInt(st.nextToken());// + 10001;
            int x2 = Integer.parseInt(st.nextToken());// + 10001;
            int y2 = Integer.parseInt(st.nextToken());// + 10001;
            Rect r = new Rect(x1, y1, x2, y2);
            rects.add(r);
            xset.add(x1);
            xset.add(x2);
            Endpoint a = new Endpoint(x1, y1, (x1 < x2) ? -1 : 1, i);
            Endpoint b = new Endpoint(x2, y2, (x1 < x2) ? 1 : -1, i);
            eps.add(a);
            eps.add(b);
        }
        eps.sort(new Comparator<Endpoint>() {
            @Override
            public int compare(Endpoint o1, Endpoint o2) {
                // TODO Auto-generated method stub
                return Integer.compare(o1.x, o2.x);
            }
        });
        // System.out.println(eps);
        TreeSet<Endpoint> yvalues = new TreeSet<>();
        boolean first = true;
        Endpoint last = new Endpoint(-1, -1, -1, -1);
        int ans = 0;
        Map<Integer, List<Endpoint>> xtoendpoints = new HashMap<>(); 
        for (Endpoint ep : eps) {
            if (ep.type == -1) {
                yvalues.add(ep);
            } else {
                yvalues.remove(ep);
            }
            
            //List<Endpoint> myys = yvals.get(xassigns.get(ep.x));
            if (!first) {
                if (yvalues.size() > 0) {
                    xtoendpoints.putIfAbsent(last.x, new ArrayList<>());
                    xtoendpoints.putIfAbsent(ep.x, new ArrayList<>());
                    for(Endpoint ep2: yvalues){
                        xtoendpoints.get(last.x).add(ep);
                        xtoendpoints.get(ep2.x).add(ep);
                    }
                }
            }
            last = ep;
            first = false;
        }
        System.out.println(xtoendpoints);
        pw.println(ans);
        pw.close();
    }
}

class Rect {
    int x1, y1, x2, y2;

    public Rect(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}

class Endpoint implements Comparable<Endpoint> {
    int x, y, type, id;

    public Endpoint(int x, int y, int type, int id) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.id = id;
    }

    @Override
    public int compareTo(Endpoint o) {
        // TODO Auto-generated method stub
        int ycompare = Integer.compare(this.y, o.y);
        if(ycompare == 0){
            return Integer.compare(this.id, o.id);
        }
        return ycompare;
    }

    @Override
    public String toString() {
        return "Endpoint [id=" + id + ", type=" + type + ", x=" + x + ", y=" + y + "]";
    }
}