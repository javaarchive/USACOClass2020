import java.util.*;
import java.io.*;

public class planting {
    public static int min(TreeSet<Endpoint> endpoints) {
        try{
        return endpoints.first().y;
        }catch(NoSuchElementException nsee){
            return Integer.MAX_VALUE;
        }
    }    
    public static int max(TreeSet<Endpoint> endpoints){
    try{
        return endpoints.last().y;
    }catch(NoSuchElementException nsee){
        return Integer.MIN_VALUE;
    }
    }
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
            int xx1 = Integer.parseInt(st.nextToken());// + 10001;
            int yy1 = Integer.parseInt(st.nextToken());// + 10001;
            int xx2 = Integer.parseInt(st.nextToken());// + 10001;
            int yy2 = Integer.parseInt(st.nextToken());// + 10001;
            int x1 = Integer.min(xx1, xx2);
            int x2 = Integer.max(xx1, xx2);
            int y1 = Integer.min(yy1, yy2);
            int y2 = Integer.max(yy1, yy2);
            Rect r = new Rect(x1, y1, x2, y2);
            rects.add(r);
            xset.add(x1);
            xset.add(x2);
            Endpoint a = new Endpoint(x1, y1, (x1 < x2) ? -1 : 1, i);
            Endpoint b = new Endpoint(x2, y2, (x1 < x2) ? 1 : -1, i);
            b.other = a;
            a.other = b;
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
        Map<Integer, TreeSet<Endpoint>> xtoendpoints = new TreeMap<>(); 
        for (Endpoint ep : eps) {
            if (ep.type == -1) {
                yvalues.add(ep);
            } else {
                yvalues.remove(ep);
            }
            
            //List<Endpoint> myys = yvals.get(xassigns.get(ep.x));
            if (!first) {
                if (yvalues.size() > 0) {
                    xtoendpoints.putIfAbsent(last.x, new TreeSet<>());
                    xtoendpoints.putIfAbsent(ep.x, new TreeSet<>());
                    for(Endpoint ep2: yvalues){
                        xtoendpoints.get(last.x).add(ep2);
                        xtoendpoints.get(ep2.x).add(ep2);
                        xtoendpoints.get(last.x).add(ep2.other);
                        xtoendpoints.get(ep2.x).add(ep2.other);
                    }
                }
            }
            last = ep;
            first = false;
        }
        //System.out.println(xtoendpoints);
        for(int i =0 ; i < eps.size()-1; i ++){
            Endpoint e1 = eps.get(i);
            Endpoint e2 = eps.get(i + 1);
            while(e2.x == e1.x && i < eps.size()-1){
                i ++;
                e2 = eps.get(i + 1);
            }
            int distance = e2.x - e1.x;
            //System.out.println(distance);
            int vertical = 0;
            int coveringCount = 0;
            int lasty = xtoendpoints.get(e1.x).first().y;
            //ystem.out.println( xtoendpoints.get(e1.x));
            TreeSet<Endpoint> a = xtoendpoints.get(e1.x);
            TreeSet<Endpoint> b = xtoendpoints.get(e2.x);
            
            int topbound = Integer.min(max(a), max(b));
            int bottombound = Integer.max(min(a), min(b));
            System.out.println("Round: ");
            for(Endpoint ep: a){
                /*if(bottombound > ep.y || topbound < ep.y ){
                    continue;
                }*/
                System.out.println(ep);
                if(ep.type == -1){
                    coveringCount ++;
                }
                if(coveringCount > 0){
                    vertical += ep.y - lasty;
                }
                if(ep.type == 1){
                    coveringCount --;
                }
                lasty = ep.y;
            }
            //System.out.println("V: "+vertical);
            ans += vertical * distance;
        }
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
    Endpoint other;
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