import java.io.*;
import java.util.*;
public class palpath {
    public static byte[][] map;
    public static Set<String> possible = new HashSet<>();
    public static int reflect(int a){
        return map.length - a - 1;
    }
    static Map<String, Integer> freq1; 
    static Map<String, Integer> freq2; 
    public static int xlimit, ylimit;
    public static void recur(int x, int y, String current){
        //System.out.println(x+" "+y+" "+current+" "+(char) (map[x][y] + 65) );
        String newString = current + (char) (map[x][y] + 65) ;
        if(x == xlimit && y == ylimit){
            if(!freq1.keySet().contains(newString)){
                freq1.put(newString, 0);
            }
            freq1.put(newString, freq1.get(newString) + 1);
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
            if(!freq2.keySet().contains(newString)){
                freq2.put(newString, 0);
            }
            freq2.put(newString, freq2.get(newString) + 1);
            return;
        }
        if(reflect(x - 1) <= xlimit){
           recur2(x-1,y, newString); 
        }
        if(reflect(y - 1) <= ylimit){
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
        for(int i = 0; i < N; i ++){
            xlimit = i;
            ylimit = N-i-1;
            freq1 = new HashMap<>();
            freq2 = new HashMap<>();
            recur(0,0, "");
            recur2(N-1,N-1, "");
            System.out.println(freq1);
            System.out.println(freq2);
            freq1.clear();
            freq2.clear();
        }
        pw.println(possible.size());
        pw.close();
    }
}