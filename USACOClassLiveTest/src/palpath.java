import java.io.*;
import java.util.*;
public class palpath {
    public static byte[][] map;
    public static Set<String> possible = new HashSet<>();
    public static int reflect(int a){
        return map.length - a - 1;
    }
    public static int xlimit, ylimit;
    public static void recur(int x, int y, String current){
        System.out.println(x+" "+y+" "+current+" "+(char) (map[x][y] + 65) );
        String newString = current + (char) (map[x][y] + 65) ;
        if(x == xlimit && y == ylimit){
            possible.add(newString);
            return;
        }
        if((x + 1) <= xlimit){
            if(map[reflect(x+1)][reflect(y)] == map[x+1][y]){
                recur(x+1,y, newString);
            }
        }
        if((y + 1) <= ylimit){
            if(map[reflect(x)][reflect(y+1)] == map[x][y+1]){
                recur(x,y+1, newString);
            }
        }
    }
    public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("palpath.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                                    new BufferedWriter(new FileWriter("palpath.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(f.readLine());
        //StringTokenizer st;
        map = new byte[N][N];
        for(int i = 0; i < N; i ++){
            String line = f.readLine();
            for(int j = 0; j < N; j ++){
                byte type = (byte) (((int) line.charAt(j)) - 65);
                map[i][j] = type;
            }
        }
        //int xlimit = N;
        for(int i = 0; i < N; i ++){
            xlimit = i;
            ylimit = N-i;
            recur(0,0, "");
        }
        pw.println(possible.size());
        pw.close();
    }
}