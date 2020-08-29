import java.io.*;
import java.util.*;

public class triangles {
    public static void main(String[] args) throws IOException {
        // IO
        String inputfile = "triangles.in";
        if (args.length > 0) {
            inputfile = args[0];
        }
        BufferedReader f = new BufferedReader(new FileReader(inputfile));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));
        // PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(f.readLine());
        List<TrianglePoint> points = new ArrayList<>(N);
        Set<TrianglePoint> allPoints = new TreeSet<TrianglePoint>();
        TreeMap<Integer, List<TrianglePoint>> mapx = new TreeMap<>();
        TreeMap<Integer, List<TrianglePoint>> mapy = new TreeMap<>();
        Map<Integer, Answer> Xans = new TreeMap<>();
        Map<Integer, Answer> Yans = new TreeMap<>();
        Answer ans = new Answer(0);
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (!mapx.keySet().contains(x)) {
                mapx.put(x, new ArrayList<>());
            }
            if (!mapy.keySet().contains(y)) {
                mapy.put(y, new ArrayList<>());
            }
            TrianglePoint p = new TrianglePoint(x, y);
            points.add(p);
            allPoints.add(p);
            mapx.get(x).add(p);
            mapy.get(y).add(p);
        }
        // Sort all
        for (Integer key : mapx.keySet()) {
            mapx.get(key).sort(new Comparator<TrianglePoint>() {
                @Override
                public int compare(TrianglePoint o1, TrianglePoint o2) {
                    // TODO Auto-generated method stub
                    return Integer.compare(o1.y, o2.y);
                }

            });
        }
        for (Integer key : mapy.keySet()) {
            mapy.get(key).sort(new Comparator<TrianglePoint>() {
                @Override
                public int compare(TrianglePoint o1, TrianglePoint o2) {
                    // TODO Auto-generated method stub
                    return Integer.compare(o1.x, o2.x);
                }

            });
        }
        for (Integer x : mapx.keySet()) {
            Answer curLenY = new Answer(0);
            List<TrianglePoint> tpx = mapx.get(x);
            TrianglePoint fp = tpx.get(0);
            // System.out.println(tpx);
            for (int i = 0; i < tpx.size(); i++) {
                curLenY.addValue(tpx.get(i).y - fp.y);
            }
            for (int i = 0; i < tpx.size(); i++) {
                TrianglePoint tp = tpx.get(i);
                if (i > 0) {
                    TrianglePoint tp2 = tpx.get(i - 1);
                    //System.out.println("")
                    curLenY.addValue(Answer.fromMultiply(tp.y - tp2.y, 2 * (i) - tpx.size()));
                }
                // System.out.println("C: "+curLenY.getValue()+" "+(tp.y - fp.y)+" "+(2 * i -
                // tpx.size()));
            }
            Xans.put(x, curLenY);
            System.out.println(curLenY.getValue());
        }
        System.out.println("SEP");
        
        for (Integer y : mapy.keySet()) {
            Answer curLenX = new Answer(0);
            List<TrianglePoint> tpy = mapy.get(y);
            TrianglePoint fp = tpy.get(0);
            for (int i = 0; i < tpy.size(); i++) {
                curLenX.addValue(tpy.get(i).x - fp.x);
            }
            System.out.println("Initial value for y = "+y+" is "+curLenX.getValue());
            for (int i = 0; i < tpy.size(); i++) {
                TrianglePoint tp = tpy.get(i);
                if (i > 0) {
                    TrianglePoint tp2 = tpy.get(i - 1);
                    curLenX.addValue(Answer.fromMultiply(tp.x - tp2.x, 2 * (i) - tpy.size()));
                    //System.out.println((tp.x - tp2.x)+" "+(2 * i - tpy.size()));
                }
            }
            Yans.put(y, curLenX);
            System.out.println(curLenX.getValue());
        }
        
        for(TrianglePoint p: points){
            ans.addValue(Answer.fromMultiply(Xans.get(p.x).value, Yans.get(p.y).value));
        }
       // System.out.println("yans: "+Yans);
        //System.out.println("xans: "+Xans);
        pw.println(ans.value);
        pw.close();
        //Answer[] xLenSum, yLenSum;

    }
}

class Answer {
    static long MOD = 1000000000 + 9;
    long value;

    public Answer(long value) {
        this.value = value % MOD;
    }

    public void addValue(Answer other) {
        this.value = (this.value + other.value) % MOD;
    }

    public void addValue(int value) {
        this.value = (this.value + value) % MOD;
    }

    public long getValue() {
        return this.value % MOD;
    }

    static Answer fromMultiply(long a, long b) {
        return new Answer((a % MOD) * (b % MOD));
    }

    static Answer fromMultiply(int a, int b) {
        return fromMultiply((long) a, (long) b);
    }

    @Override
    public String toString() {
        return "Answer [value=" + value + "]";
    }
}

class TrianglePoint implements Comparable<TrianglePoint>{
    int x, y;

    public TrianglePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public int compareTo(TrianglePoint o) {
        // TODO Auto-generated method stub
        int comparex = Integer.compare(this.x, o.x);
        if(comparex == 0){
            return Integer.compare(this.y, o.y);
        }
        return comparex;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof TrianglePoint))
            return false;
        TrianglePoint other = (TrianglePoint) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }

    
}