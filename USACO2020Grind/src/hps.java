import java.io.*;
import java.util.*;
public class hps {
    public static void main(String[] args) throws IOException{
        // IO
        String inputfile = "hps.in";
        if (args.length > 0) {
            inputfile = args[0];
        }
        BufferedReader f = new BufferedReader(new FileReader(inputfile));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] moves = new int[N];
        for (int i = 0; i < N; i++) {
            int num = 0;
            switch(f.readLine().charAt(0)){
                case 'H':
                    num = 0;
                    break;
                case 'P':
                    num = 1;
                    break;
                case 'S':
                    num = 2;
                    break;
            }
            moves[i] = num;
        }
        int[][][] states = new int[N][K][3];
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < K; j ++){
                for(int k = 0; k < 3; k ++){
                    if(i == 0){
                        states[i][j][k] = 0;
                    }else{
                        if(j == 0){
                            states[i][j][k] = states[i - 1][j][k] + ((moves[i - 1] == k)?1:0);
                        }else{
                            int a = (j + 1) % 3;
                            int b = (j + 2) % 3;
                            states[i][j][k] = Integer.max(states[i - 1][j - 1][a], states[i - 1][j - 1][b]) + ((moves[i - 1] == k)?1:0);
                        }
                    }
                }
            }
        }
        pw.println(Integer.max(states[N - 1][K - 1][0], Integer.max(states[N - 1][K - 1][1], states[N - 1][K - 1][2])));
        pw.close();
    }
}
