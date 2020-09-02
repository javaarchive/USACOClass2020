import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
public class paintbarn {
    public static void main(String[] args) throws IOException{
        // IO
        String inputfile = "paintbarn.in";
        if (args.length > 0) {
            inputfile = args[0];
        }
        BufferedReader f = new BufferedReader(new FileReader(inputfile));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("paintbarn.out")));
        // PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        //Map<Integer, List<Endpoint>> mapy = new TreeMap<>();
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 4 3
        // 1 2
        int[][] ps = new int[1002][1002];
        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(f.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            ps[x1][y1] ++;
            ps[x1][y2] --;
            ps[x2][y1] --;
            ps[x2][y2] ++;
        }
        int ans = 0;
        /*for(int i = 0; i < 13; i ++){
            for(int j = 0; j < 13; j ++){
                System.out.print(ps[i][j]+" ");
            }
            System.out.println();
        }*/
        for(int i = 0; i < 1001; i ++){
            for(int j = 0; j < 1001; j ++){
                int temp = ps[i][j];
                if(i > 0){
                    temp += ps[i - 1][j];
                }
                if(j > 0){
                    temp += ps[i][j - 1];
                }
                if(i > 0 && j > 0){
                    temp -= ps[i-1][j-1];
                }
                /*if(temp > 0){
                    System.out.println(temp);
                }*/
                    if(temp == K){
                    ans ++;
                }
                ps[i][j] = temp;
            }
        }
        pw.println(ans);
        pw.close();
    }
}
