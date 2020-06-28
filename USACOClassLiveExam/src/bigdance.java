import java.io.*;
import java.util.*;
public class bigdance {
	static int daproduct = 0;
	public static void solve(int lower, int upper) {
		//System.out.println("CALL: "+lower+" "+upper);
		if(upper - lower == 1) {
			//System.out.println(lower+" "+upper);
			daproduct = daproduct + upper * lower;
			return;
		}else if(upper == lower) {
			return; // Rose!
		}
		int mid = (int) Math.ceil((lower + upper)/2);
		solve(lower, mid);
		solve(mid + 1, upper);
	}
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("bigdance.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("bigdance.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(f.readLine());
		f.close();
		solve(1, N);
		pw.println(daproduct);
		pw.close();
	}
}