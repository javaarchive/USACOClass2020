import java.io.*;
import java.util.*;
public class numgrid {
    public static Set<Integer> possible;
    public static int[][] maps;
    public static int[] dx = {0,0,-1,1};
    public static int[] dy = {-1,1,0,0};
    public static void recur(int current, int x, int y, int left){
        if(left == 0){
            possible.add(current);
            return;
        }        
        int newval = current * 10 + maps[x][y];
        for(int i = 0; i < 4; i ++){
            int testx = x + dx[i];
            int testy = y + dy[i];
            if(0 <= testx && testx < 5 && 0 <= testy && testy < 5){
                recur(newval, testx, testy, left - 1);
            }
        }
        
    }
    public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                                    new BufferedWriter(new FileWriter("cownomics.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        maps = new int[5][5]; 
        for(int i = 0; i < 5; i ++){
            st = new StringTokenizer(f.readLine());
            for(int j = 0; j < 5; j ++){
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        possible = new HashSet<>();
        for(int i = 0; i < 5; i ++){
            for(int j = 0; j < 5; j ++){
                recur(0, i, j, 6);
            }
        }
        pw.println(possible.size());
        pw.close();
    }
}