import java.io.*;
import java.util.*;

public class lifeguards {
    public static void main(String[] args) throws IOException {
        // IO
        // new FileReader("reduce.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("reduce.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(f.readLine());
        List<Interval> inters = new ArrayList<>(N);
        Interval[][] lookup = new Interval[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            Interval il1 = new Interval(a, b, 1, i);
            Interval il2 = new Interval(a, b, 2, i);
            lookup[i][0] = il1;
            lookup[i][1] = il2;
            inters.add(il1);
            inters.add(il2);
        }
        inters.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                // TODO Auto-generated method stub
                int a = (o1.type == 1) ? o1.start : o1.end;
                int b = (o2.type == 1) ? o2.start : o2.end;
                return Integer.compare(a, b);
            }
        });
        int curCount = 0;
        int lastTime = 0;
        int lastID = 0;
        for (int i = 0; i < 2 * N; i++) {
            Interval il = inters.get(i);
            int time = (il.type == 1) ? il.start : il.end;
            if (curCount == 1) {
                System.out.println(time - lastTime + " id: " + lastID);
                lookup[lastID][0].aloneTime += time - lastTime;
                lookup[lastID][1].aloneTime += time - lastTime;
            }
            if (il.type == 1) {
                curCount++;
            } else {
                curCount--;
            }
            // System.out.println(curCount);
            if (il.type == 1) {
                // Only update last on start events
                lastTime = time;
                lastID = il.id;
            }
        }
        System.out.println(inters);
    }
}

class Interval {
    int start, end;
    int type = 0;
    int aloneTime = 0;
    int id;

    public Interval(int start, int end, int type, int id) {
        this.start = start;
        this.end = end;
        this.type = type;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Interval [aloneTime=" + aloneTime + ", end=" + end + ", start=" + start + ", type=" + type + "]";
    }

}