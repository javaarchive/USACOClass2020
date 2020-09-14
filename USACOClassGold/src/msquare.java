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
        int[] firstState = new int[8];
        for (int i = 0; i < 8; i++) {
            firstState[i] = Integer.parseInt(st.nextToken());
        }
        Queue<PlanarSquareState> q = new LinkedList<PlanarSquareState>();
        PlanarSquareState pss = new PlanarSquareState(firstState);
        Map<Integer, String> visited = new TreeMap<>();
        q.add(pss);
        while(!q.isEmpty()){
            PlanarSquareState curPSS = q.poll();
            PlanarSquareState a = new PlanarSquareState(curPSS.transformA());
            PlanarSquareState b = new PlanarSquareState(curPSS.transformB());
            PlanarSquareState c = new PlanarSquareState(curPSS.transformC());
            a.s += "A";
            b.s += "B";
            c.s += "C";
            int ha = a.hashCode(), hb = b.hashCode(), hc = c.hashCode();
           // System.out.println(ha);
            if(!visited.keySet().contains(ha)){
                if(a.s.compareTo(visited.get(ha)) < 0){
                    visited.put(ha, a.s);
                }
            }else if(a.s.compareTo(visited.get(ha)) < 0){
                visited.put(ha, a.s);
            }
            if(!visited.keySet().contains(hb)){
                if(b.s.compareTo(visited.get(hb)) < 0){
                    visited.put(hb, b.s);
                }
            }else if(b.s.compareTo(visited.get(hb)) < 0){
                visited.put(hb, b.s);
            }
            if(!visited.keySet().contains(hc)){
                if(c.s.compareTo(visited.get(hc)) < 0){
                    visited.put(hc, c.s);
                }
            }else if(c.s.compareTo(visited.get(hc)) < 0){
                visited.put(hc, c.s);
            }

        }

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
        out[0] = in[7];
        out[1] = in[6];
        out[2] = in[5];
        out[3] = in[4];
        out[4] = in[0];
        out[5] = in[1];
        out[6] = in[2];
        out[7] = in[3];
        return out;
    }

    public int[] transformB() {
        int[] in = this.nums;
        int[] out = new int[8];
        out[0] = in[1];
        out[1] = in[2];
        out[2] = in[3];
        out[3] = in[0];
        out[4] = in[5];
        out[5] = in[6];
        out[6] = in[7];
        out[7] = in[4];
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
        out[6] = in[3];
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
