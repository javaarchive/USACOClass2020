import java.io.*;
import java.util.*;

public class blink {
    static int N;
    static int twonpower;

    static int setBit(int n, int k) {
        return ((1 << k) | n);
    }
    static int lastBit(int n){
        return n - 2 * (n >> 1);
    }

    public static int circleTheBits(int input) {
        int last = lastBit(input);
        int start = last * (twonpower);
        //System.out.println("Start: "+start);
        return (input >> 1) + start;
    }
    public static int circleTheBitsBad(int input){
        return Integer.rotateRight(input, 1);
    }


    public static void main(String[] args) throws IOException {
        // IO
        // new FileReader("milk3.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("milk3.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        int num = 0;
        twonpower = (1 << (N - 1));
        for (int i = 0; i < N; i++) {
            int digit = Integer.parseInt(f.readLine());
            num = num * 2 + digit;
        }
        int prevNum = num;
        int curNum = 0;
        long loop = B + 1;
        Set<Combin> seen = new HashSet<>();
        List<Combin> precalc = new ArrayList<>();
        boolean hasInfZeros = false;
        int loopStart = -1;
        for (long i = 0; i <= B; i++) {
            curNum = (circleTheBits(prevNum) ^ prevNum);// % (twonpower);
            //System.out.println("Circuled "+Integer.toString(circleTheBits(prevNum), 2));
            //System.out.println(Integer.toString(curNum, 2));
            Combin c = new Combin(prevNum, curNum);
            if(seen.contains(c)){
                //System.out.println("Found similar "+c);
                if(c.current == 0 && c.prev == 0){
                    hasInfZeros = true;
                }
                for(int j = 0; j < precalc.size(); j ++){
                    if(precalc.get(j).equals(c)){
                        loopStart = j;
                        break;
                    }
                }
                loop = i;
                break;
            }
            seen.add(c);
            precalc.add(c);
            prevNum = curNum;
        }
        if(loopStart != -1){
            for(int i = 0; i < loopStart; i ++){
                precalc.remove(0);
            }
        }else{
            loopStart = 0;
        }
        loop = precalc.size();
        //System.out.println(seen);
        //System.out.println("Loop: "+loop);
        String out = String.format("%"+N+"s", Integer.toBinaryString((precalc.get((int) ((B-1-loopStart) % loop))).current)).replace(' ', '0');
        if(hasInfZeros && B > loop){
            out = new String(new char[N]).replace("\0", "0");
        }
        for(int i = 0; i < out.length(); i ++){
            pw.println(out.charAt(i));
        }
        //System.out.println("Loop: "+loop);
        //System.out.println(precalc);
        pw.close();
    }
}

class Combin {
    int prev, current;

    public Combin(int a, int b) {
        this.prev = a;
        this.current = b;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + current;
        result = prime * result + prev;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Combin))
            return false;
        Combin other = (Combin) obj;
        if (current != other.current)
            return false;
        if (prev != other.prev)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Combin [current=" + current + ", prev=" + prev + "]";
    }

}