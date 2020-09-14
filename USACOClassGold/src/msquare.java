import java.io.*;
import java.util.*;

public class msquare {
    public static void main(String[] args) throws IOException {
        // IO
        // new FileReader("msquare.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("msquare.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int[] endState = new int[8];
        for (int i = 0; i < 4; i++) {
            endState[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 7; i >= 4; i--) {
            endState[i] = Integer.parseInt(st.nextToken());
        }
        int[] firstState = {1,2,3,4,8,7,6,5};
        Queue<PlanarSquareState> q = new LinkedList<PlanarSquareState>();
        PlanarSquareState pss = new PlanarSquareState(firstState);
        Map<Integer, String> visited = new TreeMap<>();
        q.add(pss);
        while(!q.isEmpty()){
            PlanarSquareState curPSS = q.poll();
            if(Arrays.equals(curPSS.nums, endState)){
                pw.println(curPSS.s.length());
                pw.println(curPSS.s);
                break;
            }
            //System.out.println(Arrays.toString(curPSS.nums)+" "+curPSS.s);
            PlanarSquareState a = new PlanarSquareState(curPSS.transformA());
            PlanarSquareState b = new PlanarSquareState(curPSS.transformB());
            PlanarSquareState c = new PlanarSquareState(curPSS.transformC());
            a.s = curPSS.s + "A";
            b.s = curPSS.s + "B";
            c.s = curPSS.s + "C";
            int ha = a.hashCode(), hb = b.hashCode(), hc = c.hashCode();
           // System.out.println(ha);
            if(!visited.keySet().contains(ha)){
                visited.put(ha, a.s);
                q.add(a);
            }else if(a.s.compareTo(visited.get(ha)) < 0){
                visited.put(ha, a.s);
                q.add(a);
            }
            if(!visited.keySet().contains(hb)){
                visited.put(hb, b.s);
                q.add(b);
            }else if(b.s.compareTo(visited.get(hb)) < 0){
                visited.put(hb, b.s);
                q.add(b);
            }
            if(!visited.keySet().contains(hc)){
                visited.put(hc, c.s);
                q.add(c);
            }else if(c.s.compareTo(visited.get(hc)) < 0){
                q.add(c);
                visited.put(hc, c.s);
            }

        }
        pw.close();
        f.close();
    }
}

class PlanarSquareState implements Comparable<PlanarSquareState> {
    int[] nums;
    String s = "";

    public PlanarSquareState(int[] data) {
        this.nums = data;
    }

    public int[] transformA() {
        int[] in = this.nums;
        int[] out = new int[8];
        out[0] = in[4];
        out[1] = in[5];
        out[2] = in[6];
        out[3] = in[7];
        out[4] = in[0];
        out[5] = in[1];
        out[6] = in[2];
        out[7] = in[3];
        return out;
    }

    public int[] transformB() {
        int[] in = this.nums;
        int[] out = new int[8];
        out[0] = in[3];
        out[1] = in[0];
        out[2] = in[1];
        out[3] = in[2];
        out[4] = in[7];
        out[5] = in[4];
        out[6] = in[5];
        out[7] = in[6];
        return out;
    }

    public int[] transformC() {
        int[] in = this.nums;
        int[] out = new int[8];
        out[0] = in[0];
        out[1] = in[5];
        out[2] = in[1];
        out[3] = in[3];
        out[4] = in[4];
        out[5] = in[6];
        out[6] = in[2];
        out[7] = in[7];
        return out;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(nums);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof PlanarSquareState))
            return false;
        PlanarSquareState other = (PlanarSquareState) obj;
        if (!Arrays.equals(nums, other.nums))
            return false;
        return true;
    }

    @Override
    public int compareTo(PlanarSquareState o) {
        // TODO Auto-generated method stub
        return Integer.compare(this.hashCode(), o.hashCode());
    }
}
