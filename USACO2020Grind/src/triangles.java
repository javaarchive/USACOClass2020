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
        TreeMap<Integer, List<TrianglePoint>> mapx = new TreeMap<>();
        TreeMap<Integer, List<TrianglePoint>> mapy = new TreeMap<>();
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
        Answer side1 = new Answer(0);
        Answer side2 = new Answer(0);
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
            System.out.println(curLenY.getValue());
            side1.addValue(curLenY);
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
            System.out.println(curLenX.getValue());
            side1.addValue(curLenX);
        }
        pw.println(Answer.fromMultiply(side1.value, side2.value));
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
}

class TrianglePoint {
    int x, y;

    public TrianglePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}