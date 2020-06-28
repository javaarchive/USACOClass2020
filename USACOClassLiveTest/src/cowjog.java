import java.io.*;
import java.util.*;
public class cowjog {
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("cownomics.out"))b
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(f.readLine());
		List<Integer> rates = new ArrayList<>();
		for(int i = 0; i < N; i ++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			st.nextToken(); // "You can pretty much ignore the first value" - Bennett Liu
			rates.add(Integer.parseInt(st.nextToken()));
		}
		int slowestRate = Integer.MAX_VALUE;
		int groups = 0;
		for(int i = N-1; i >= 0; i --) {
			int rate = rates.get(i);
			if(slowestRate >= rate) {
				groups ++;
				slowestRate = rate;
			}
		}
		pw.println(groups);
		pw.close();
		f.close();
	}
}