import java.io.*;
import java.util.*;
public class fliptile {
    static int N,M;
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
        int TwoPowM = (int) Math.pow(2, M);
        for(int i = 0 ;i < TwoPowM; i ++){
            System.out.println("Possible: "+i);
            int[] build = new int[M];
            build[0] = i;
            for(int j = 1; j < N; j ++){
                char[] s1 = String.format("%"+M+"s",Integer.toBinaryString(build[j - 1])).replace(' ','0').toCharArray(); 
                char[] s2 = String.format("%"+M+"s",Integer.toBinaryString(build[j])).replace(' ','0').toCharArray(); 
                char[] s3 = target[j];
                for(int k = 0; k < N; k ++){
                    boolean changeThis = (s1[k]) == s3[k];
                    if(k != N - 1){
                        if(changeThis){
                            s2[k + 1] = (s2[k + 1] == '0')?'1':'0';
                        }
                    }
                    if(changeThis){
                        s2[k] = (changeThis == (s2[k] == '1')) ? '1':'0';
                    }

                }
                build[j] = Integer.parseInt(new String(s2),2);
                
            }
            for(int j = 0; j < N; j ++){
                System.out.println(String.format("%"+M+"s",Integer.toBinaryString(build[j])).replace(' ','0'));
            }
        }
    }
}