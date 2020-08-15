import java.io.*;
import java.util.*;
public class leftout {
    static boolean[][] field;
    static int N;
    public static void flipRow(int row){
        for(int i = 0; i < N; i ++){
            field[row][i] = !field[row][i];
        }
    }
    public static void flipCol(int col){
        for(int i = 0; i < N; i ++){
            field[i][col] = !field[i][col];
        }
    }
    public static boolean allOnes(int sx,int  sy){
        for(int i = sx; i < N; i ++){
            for(int j = sy; j < N; j ++){
                if(!field[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    public static int sumRow(int row){
        int out = 0;
        for(int i = 0; i < N; i ++){
            if(field[0][i]){
                out ++;
            }
        }
        return out;
    }
    public static int sumCol(int col){
        int out = 0;
        for(int i = 0; i < N; i ++){
            if(field[i][0]){
                out ++;
            }
        }
        return out;
    }
    public static void main(String[] args) throws IOException{
        // IO
		// new FileReader("reduce.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		// new BufferedWriter(new FileWriter("reduce.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(f.readLine());
        field = new boolean[N][N];
        for(int i = 0; i < N; i ++){
            String line = f.readLine();
            for(int j = 0; j < N; j ++){
                field[i][j] = line.charAt(j) == 'R';
            }
        }
        for(int i = 0; i < N; i ++){
            if(field[0][i]){
                flipCol(i);
            }
        }
        for(int i = 1; i < N; i ++){
            if(field[i][0]){
                flipRow(i);
            }
        }
        /*for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                System.out.print(field[i][j] ? 1:0);
            }
            System.out.println();
        }*/
        if(!field[0][0] && allOnes(1, 1)){
            pw.println("1 1");
            pw.close();
            System.exit(0);
        }
        int[] r = new int[N],c = new int[N];
        for(int i = 1; i < N; i ++){
            r[i] = sumRow(i);
            c[i] = sumCol(i);
        }
        int Nm1 = N-1;
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                if(c[i] == Nm1 || r[j] == Nm1){
                    pw.println((i + 1)+" "+(j + 1));
                    pw.close();
                    System.exit(0);
                }
            }
        }
        int fx = -1, fy = -1;
        int count = 0;
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                if(field[i][j]){
                    fx = i;
                    fy = j;
                    count ++;
                }
            }
        }
        //pw.println(count);
        //pw.println((fx + 1)+" "+(fy + 1));
        if(count == 1){
            pw.println((fx + 1)+" "+(fy + 1));
            pw.close();
            System.exit(0);
        }
        if(count == N-1){
            if(fx == N-1){
                pw.println(1+" "+(fy + 1));
                pw.close();;
                System.exit(0);
            }
            if(fy == N-1){
                pw.println((fx + 1)+" "+1);
                pw.close();
                System.exit(0);
            }
        }
        pw.println(-1);
        pw.close();
    }
}