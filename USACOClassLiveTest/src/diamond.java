import java.io.*;
import java.util.*;
public class diamond {

	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("diamond.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("diamond.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		List<Integer> diamonds = new ArrayList<>();
		for(int i = 0 ; i < N; i ++) {
			diamonds.add(Integer.parseInt(st.nextToken()));
		}
		int idx = 0;
		for(int i =0 ; i < N-1; i ++) {
			//.while((diamonds.get(i) - )) {
				
			//}
		}
		diamonds.sort(null);
		
	}

}