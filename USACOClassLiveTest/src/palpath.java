import java.io.*;
import java.util.*;
public class palpath {
    public static byte[][] map;
    public static Set<String> possible = new HashSet<>();
    public static int reflect(int a){
        return map.length - a - 1;
    }
    static Set<String> pos1;
    static Set<String> pos2;
    public static int xlimit, ylimit;
    public static void recur(int x, int y, String current){
        //System.out.println(x+" "+y+" "+current+" "+(char) (map[x][y] + 65) );
        String newString = current + (char) (map[x][y] + 65) ;
        if(x == xlimit && y == ylimit){
            pos1.add(newString);
            return;
        }
        if((x + 1) <= xlimit){
           recur(x+1,y, newString); 
        }
        if((y + 1) <= ylimit){
            recur(x,y+1, newString);
        }
    }
    public static void recur2(int x, int y, String current){
       // System.out.println(x+" "+y+" "+current+" "+(char) (map[x][y] + 65) );
        String newString = current + (char) (map[x][y] + 65) ;
        if(reflect(x) == xlimit && reflect(y) == ylimit){
           pos2.add(newString);
            return;
        }
        if(reflect(x-1) <= xlimit){
           recur2(x-1,y, newString); 
        }
        if(reflect(y-1) <= ylimit){
            recur2(x,y-1, newString);
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
        int ans = 0;
        for(int i = 0; i < N; i ++){
            xlimit = i;
            ylimit = N-i-1;
            pos1 = new TreeSet<>();
            pos2 = new TreeSet<>();
            recur(0,0, "");
            xlimit = N-i-1;
            ylimit = i;
            recur2(N-1,N-1, "");
            //System.out.println("Symmetry: "+i);
            //System.out.println(pos1);
            //System.out.println(pos2);
            pos1.retainAll(pos2);
            possible.addAll(pos1);
            //ans += pos1.size();
            pos1.clear();
            pos2.clear();
        }
        pw.println(possible.size());
        pw.close();
    }
}