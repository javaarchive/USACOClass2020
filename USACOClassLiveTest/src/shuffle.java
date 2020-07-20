import java.io.*;
import java.util.*;
public class shuffle {
    public static int hare, tortoise;
    public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("cownomics.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(f.readLine());
        StringTokenizer st = new StringTokenizer(f.readLine());
        int[] connections = new int[N];
        boolean[] looping = new boolean[N];
        for(int i = 0; i < N; i ++){
            int destNode = Integer.parseInt(st.nextToken())-1;
            connections[i] = destNode; 
        }
        for(int i = 0; i < N; i ++){
            if(looping[i]){
                continue; // Save calculation time
            }
            hare = i;
            tortoise = i;
            //System.out.println("Starting at "+i);
            while(true){
                hare = connections[hare];
                hare = connections[hare];
                tortoise = connections[tortoise];
                //System.out.println("Hare: "+hare+" Tortise: "+tortoise);
                if(hare == tortoise){
                    looping[hare] = true; 
                    break;
                }
                if(hare == i){
                    
                }
            }
        }
        int ans = 0;
        for(int i = 0; i < N; i ++){
            if(looping[i]){
                ans ++;
            }
        }
        pw.println(ans);
        pw.close();
    }
}