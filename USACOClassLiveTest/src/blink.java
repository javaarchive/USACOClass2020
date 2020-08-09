import java.io.*;
import java.util.*;

public class blink {
    static int N;
    static int twonpower;

    static int setBit(int n, int k) {
        return ((1 << k) | n);
    }

    public static int circleTheBits(int input) {
        int last = ((input & 1 << N-1) != 0 ) ? 1:0;
        int start = last * twonpower;
        System.out.println("Last: "+last);
        return ((input >> 1) + start);
    }

    public static void main(String[] args) throws IOException {
        // IO
        // new FileReader("milk3.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("milk3.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int num = 0;
        twonpower = (1 << N);
        for (int i = 0; i < N; i++) {
            int digit = Integer.parseInt(f.readLine());
            num = num * 2 + digit;
        }
        int prevNum = num;
        int curNum = 0;
        for (int i = 0; i < B; i++) {
            curNum = (circleTheBits(prevNum) ^ prevNum);
            System.out.println(Integer.toString(curNum, 2));
            prevNum = curNum;
        }
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