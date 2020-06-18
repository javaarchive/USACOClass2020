import java.io.*;
import java.util.*;

//It seems that sometimes things go wrong if the class
//isn't the same name as the problem name
public class farmoff {
	public static long a, b, c, d, e, f, g, M;

	public static long W_(long i) {
		return (((((a * i % M) * i % M) * i % M) * i % M) * i % M) + ((b * i % M) * i % M) + c;
	}

	public static long U_(long i) {
		return (((((e * i % M) * i % M) * i % M) * i % M) * i % M) + (((f * i % M) * i % M) * i % M) + g;
	}

	public static void main(String[] args) throws IOException {
		// IO
		// new FileReader("farmoff.in")
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// new BufferedWriter(new FileWriter("farmoff.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long N = Long.parseLong(st.nextToken());
		a = Long.parseLong(st.nextToken());
		b = Long.parseLong(st.nextToken());
		c = Long.parseLong(st.nextToken());
		d = Long.parseLong(st.nextToken());
		e = Long.parseLong(st.nextToken());
		f = Long.parseLong(st.nextToken());
		g = Long.parseLong(st.nextToken());
		M = Long.parseLong(st.nextToken());
		List<BigCow> cows = new ArrayList<BigCow>();
		for (long i = 0; i < (3 * N); i++) {
			cows.add(new BigCow(W_(i), U_(i)));
		}
		cows.sort(new Comparator<BigCow>() {

			@Override
			public int compare(BigCow arg0, BigCow arg1) {
				int utilcompare = -Long.compare(arg0.utility, arg1.utility);
				if (utilcompare == 0) {
					return Long.compare(arg0.weight, arg1.weight);
				}
				return utilcompare;
			}

		});
		br.close();
		//System.out.println(cows);
		long sum = 0;
		for(int i = 0; i < N; i ++) {
			sum = sum + (cows.get(i).weight) % M; 
		}
		pw.println((sum + M) % M);
		pw.close();
	}

}

class BigCow {
	public long weight, utility;

	public BigCow(long weight, long utility) {
		this.weight = weight;
		this.utility = utility;
	}
	public String toString() {
		return "(" + this.weight + ", "+this.utility + ")";
	}
}