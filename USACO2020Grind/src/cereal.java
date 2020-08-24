import java.io.*;
import java.util.*;

public class cereal {
    public static void main(String[] args) throws IOException {
        // IO
        BufferedReader f = new BufferedReader(new FileReader("cereal.in"));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cereal.out")));
        // PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] cerealTakeId = new int[M + 1];
        // Arrays.fill(a, val);
        List<CerealCow> cows = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(f.readLine());
            int a, b;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            CerealCow cc = new CerealCow(a, b);
            cows.add(cc);
        }
        // int satsified = 0;
        List<Integer> answers = new ArrayList<>();
        // Arrays.fill(cre, val);
        int happyCows = 0;
        for (int i = N - 1; i >= 0; i--) {
            System.out.println("Add "+i);
            CerealCow cc = cows.get(i);
            happyCows++;
            cc.taken = 1;
            int kickCow = cerealTakeId[cc.first] - 1;
            happyCows++;
            cerealTakeId[cc.first] = i + 1;
            cc.taken = 1;
            if (kickCow > -1) {
                while (true) {
                    System.out.println(kickCow + " Kicked "+Arrays.toString(cerealTakeId) );
                    CerealCow nextCow = cows.get(kickCow);
                    int cowToWrite = kickCow;
                    if (cerealTakeId[nextCow.second] == 0 && nextCow.taken == 1) {
                        cerealTakeId[nextCow.second] = kickCow + 1;
                        break;
                    }
                    if (nextCow.taken == 1) {
                        nextCow.taken = 2; // Switched to second cow
                        kickCow = cerealTakeId[nextCow.second] - 1;
                        cerealTakeId[nextCow.second] = cowToWrite + 1;
                    } else if (nextCow.taken == 2) {
                        happyCows--;
                        nextCow.taken = 0;
                        //cerealTakeId[nextCo]
                        break; // Yay! End of the kicking chain
                    }
                    if(cowToWrite == kickCow){
                        System.out.println("Self kick "+kickCow);
                        //break; // No self kicking
                    }
                }
            }
            answers.add(happyCows);
        }
        Collections.reverse(answers);
        System.out.println(happyCows+" "+answers);
    }
}

class CerealCow {
    int first, second;
    int taken = 0;

    public CerealCow(int first, int second) {
        this.first = first;
        this.second = second;
    }
}