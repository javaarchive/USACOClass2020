import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class cereal {
    public static void main(String[] args) {
        // IO
        BufferedReader f = new BufferedReader(new FileReader("cownomics.in"));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
        // PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(f.readLine());
        int[] cerealTakeId = new int[M];
        Arrays.fill(a, val);
        List<CerealCow> cows = new ArrayList<>(M);
        for(int i = 0; i < M; i ++){
            st = new StringTokenizer(f.readLine());
            int a,b;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            CerealCow cc = new CerealCow(a,b);
            cows.add(cc);
        }
        int satsified = false;
        ArrayList<CerealCow> answers = new List<>();
        //Arrays.fill(cre, val);
        int happyCows = 0;
        for(int i  = N-1; i >= 0; i --){
            CerealCow cc = cows.get(i);
            happyCows ++;
            cc.taken = 1;
            if(cerealTakeId[cc.first] > 0){ // If a cow is occupying that cereal kick it off
                int id = cerealTakeId[i] - 1;
                CerealCow rcow = cows.get(id);
                if(rcow.taken == 2){ // Poor cow's second choice gets no cereal
                    happyCows ++;
                }else{
                    if(cerealTakeId[rcow.second] > 0){ // Kick another cow off

                    }
                }
            }
        }
        Collections.reverse(answers);
    }
}
class CerealCow{
    int first, second;
    int taken = 0;
    public CerealCow(int first, int second){
        this.first = first;
        this.second = second;
    }
}