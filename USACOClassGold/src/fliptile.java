import java.io.*;
import java.util.*;
public class fliptile {
    static int N,M;
    public static char XOR(char a, char b){
        boolean A = (a == '1');
        boolean B = (b == '1');
        return (A != B) ? '1':'0';
    }
    public static char NOT(char c){
        return (c == 'a') ? 'b':'a';
    }
    public static void main(String[] args) throws IOException{
        // IO
        // new FileReader("tractor.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("tractor.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        char[][] target = new char[N][M];
        //String[] target = new String[N];
        for(int i = 0 ; i < N; i ++){
            st = new StringTokenizer(f.readLine());
            for(int j = 0; j < M; j ++){
                char c = st.nextToken().charAt(0);
                //int num = (c == '1') ? 1:0;
                target[i][j] = c;
            }
        }
        int[] preTable = new int[M];
        int cur = 1;
        for(int i = 0; i < M; i ++){
            preTable[M - i - 1] = cur;
            cur = cur * 2; 
        }
        System.out.println(Arrays.toString(preTable));
        int TwoPowM = (int) Math.pow(2, M);
        for(int i = 0 ;i < TwoPowM; i ++){
            System.out.println("Possible: "+i);
            int[] build = new int[M];
            build[0] = i;
            String iStr = String.format("%"+M+"s",Integer.toBinaryString(build[0])).replace(' ','0');
            char[][] grid = new char[N][M];
            for(int j = 0; j < M; j ++){
                grid[0][j] = iStr.charAt(j);
            }
            for(int j = 1; j < N; j ++){
                Arrays.fill(grid[j], '0');
            }
            for(int j = 1; j < N; j ++){
                for(int k = 0; k < M; k ++){
                    boolean works = true;
                    if(k > 0){
                        if(grid[j][k - 1] != 1){
                            works = false;
                        }
                    }
                    if(grid[j - 1][k] != 1){
                        works = false;
                    }
                    if(works){
                        if(k > 0){
                            grid[j][k - 1] = NOT(grid[j][k - 1]);
                        }
                        if(k < M - 1){
                            grid[j][k + 1] = NOT(grid[j][k + 1]);
                        }
                        if(j > 0){
                            grid[j - 1][k] = NOT(grid[j - 1][k]);
                        }
                        if(j < N - 1){
                            grid[j + 1][k] = NOT(grid[j + 1][k]);
                        }
                        build[j] = build[j] + preTable[k];
                    }
                }
                
                //System.out.println(String.format("%"+M+"s",Integer.toBinaryString(build[j])).replace(' ','0'));
            }
            for(int j = 0 ; j < N; j ++){
                    System.out.println(String.format("%"+M+"s",Integer.toBinaryString(build[j])).replace(' ','0'));
                    System.out.println(Arrays.toString(grid[j]));
            }
        }
    }
}