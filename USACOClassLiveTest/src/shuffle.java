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
        List<List<Integer>> adjlist = new ArrayList<>();
        boolean[] looping = new boolean[N];
        for(int i = 0; i < N; i ++){
            adjlist.add(new ArrayList<>());
        }
        for(int i = 0; i < N; i ++){
            int destNode = Integer.parseInt(st.nextToken())-1;
            adjlist.get(destNode).add(i);
        }
        Set<Integer> gone = new HashSet<Integer>();
        for(int count = 0; count < 350; count ++){
        for(int i = 0; i < N; i ++){
            List<Integer> nodes = adjlist.get(i);
            for(int j = 0; j < nodes.size(); j ++){
                if(gone.contains(nodes.get(j))){
                    nodes.remove(j);
                    j --; // Obviously some issues if I didn't do that
                }
            }
           if(nodes.size() == 0){
               gone.add(i);
           }
        }
    }
        //System.out.println(adjlist);
        pw.println(N - gone.size());
        pw.close();
    }
}