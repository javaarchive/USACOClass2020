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
            int[] build = new int[N];
            build[0] = i;
            String top = String.format("%"+M+"s",Integer.toBinaryString(build[j])).replace(' ','0');
            System.out.println(String.format("%"+M+"s",Integer.toBinaryString(build[j])).replace(' ','0'));
            char[][] grid = new char[N][M];
            for(int j = 0; j < M; j ++){
                grid[0][j] = top.charAt(j);
            }
            for(int j = 1; j < N; j ++){
                if(j >= 2){
                    if(!Arrays.equals(grid[j - 2], target[j - 2]){
                        break;
                    }
                }
                for(int k = 0; k < N; k ++){
                    
                }
            }
            
            for(int j = 0 ; j < N; j ++){
                    // System.out.println(String.format("%"+M+"s",Integer.toBinaryString(build[j])).replace(' ','0'));
                    System.out.println(Arrays.toString(grid[j]));
            }
        }
    }
}