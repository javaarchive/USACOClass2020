import java.io.*;
import java.util.*;

public class milk3 {
    public static int limit(int initial, int newAmount, int overflow) {
        System.out.println("limit("+initial+","+newAmount+","+overflow+")");
        if ((initial + newAmount) > overflow) {
            return overflow - initial;
        }
        return newAmount;
    }

    public static void main(String[] args) throws IOException {
        // IO
        // new FileReader("milk3.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("milk3.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int A, B, C;
        StringTokenizer st = new StringTokenizer(f.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        boolean[][][] checked = new boolean[A + 1][B + 1][C + 1];
        Queue<State> states = new LinkedList<>();
        states.add(new State(0, 0, C));
        TreeSet<Integer> answers = new TreeSet<>();
        while (!states.isEmpty()) {
            State s = states.poll();
            System.out.println("("+s.A+","+s.B+","+s.C+")");
            if(s.A == 0){
                System.out.println("Added "+s.C);
                answers.add(s.C);
            }
            if (s.A != A) {
                int pourAmount = limit(s.A, s.C, A);
                int leftOver = s.C - pourAmount;
                if (pourAmount != 0) {
                    State newState = new State(pourAmount + s.A, s.B, s.C - pourAmount);
                    if (!checked[newState.A][newState.B][newState.C]) {
                        System.out.println("Pouring C to A, A would have " + (pourAmount + s.A));
                        checked[newState.A][newState.B][newState.C] = true;
                        states.add(newState);
                    }

                }
            }
            if (s.C != C) {
                int pourAmount = limit(s.C, s.A, C);
                int leftOver = s.A - pourAmount;
                if (pourAmount != 0) {
                    State newState = new State(s.A - pourAmount, s.B, s.C + pourAmount);
                    if (!checked[newState.A][newState.B][newState.C]) {
                        System.out.println("Pouring A to C, C would have " + (pourAmount + s.C));
                        checked[newState.A][newState.B][newState.C] = true;
                        states.add(newState);
                    }
                }
            }
            if (s.B != B) {
                int pourAmount = limit(s.B, s.A, B);
                int leftOver = s.A - pourAmount;
                if (pourAmount != 0) {
                    State newState = new State(s.A - pourAmount, s.B + pourAmount, s.C);
                    if (!checked[newState.A][newState.B][newState.C]) {
                        System.out.println("Pouring A to B, B would have " + (pourAmount + s.B));
                        checked[newState.A][newState.B][newState.C] = true;
                        states.add(newState);
                    }
                }
            }
            if (s.B != B) {
                int pourAmount = limit(s.B, s.C, B);
                int leftOver = s.C - pourAmount;
                if (pourAmount != 0) {
                    State newState = new State(s.A , s.B + pourAmount, s.C - pourAmount);
                    if (!checked[newState.A][newState.B][newState.C]) {
                        System.out.println("Pouring C to B, B would have " + (pourAmount + s.B));
                        checked[newState.A][newState.B][newState.C] = true;
                        states.add(newState);
                    }
                }
            }
        }
        for(int ans: answers){
            System.out.print(ans+" ");
        }
    }
}

class State {
    int A, B, C;

    public State(int A, int B, int C) {
        this.A = A;
        this.B = B;
        this.C = C;
    }
}