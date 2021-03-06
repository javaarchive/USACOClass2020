import java.io.*;
import java.util.*;

//It seems that sometimes things go wrong if the class
//isn't the same name as the problem name
public class farmoff {
	public static long a, b, c, d, e, f, g, h, M;

	public static long W_(int i) {
		return (((((((((((a * i % d) * i) % d) * i) % d) * i) % d) * i) % d) + (((b * i) % d) * i) + c)) % d;
	}
	public static long U_(int i) {
		return (((((((((((e * i % h) * i) % h) * i) % h) * i) % h) * i) % h) + (((f * i % h) * i % h) * i) + g)) % h;
	}

	public static void main(String[] args) throws IOException {
		// IO
		// new FileReader("farmoff.in")
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// new BufferedWriter(new FileWriter("farmoff.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long s1 = System.currentTimeMillis();
		int N = Integer.parseInt(st.nextToken());
		a = Long.parseLong(st.nextToken());
		b = Long.parseLong(st.nextToken());
		c = Long.parseLong(st.nextToken());
		d = Long.parseLong(st.nextToken());
		e = Long.parseLong(st.nextToken());
		f = Long.parseLong(st.nextToken());
		g = Long.parseLong(st.nextToken());
		h = Long.parseLong(st.nextToken());
		M = Long.parseLong(st.nextToken());
		//System.out.println("part 1");
		List<BigCow> cows = new ArrayList<BigCow>();
		int Ntimes3 = N * 3;
		for (int i = 0; i < Ntimes3; i++) {
			cows.add(new BigCow(W_(i), U_(i)));
		}
		//System.out.println("part 1 sort");
		cows.sort(new Comparator<BigCow>() {
			@Override
			public int compare(BigCow arg0, BigCow arg1) {
				int utilcompare = Long.compare(arg1.utility, arg0.utility);
				if (utilcompare == 0) {
					return Long.compare(arg0.weight, arg1.weight);
				}
				return utilcompare;
			}

		});
		//System.out.println("part 2");
		//System.out.println(cows);
		long sum = 0;
		for(int i = 0; i < N; i ++) {
			sum = (sum + cows.get(i).weight) % M; 
		}
		//System.out.println("part 3");
		pw.println(sum);
		long s2 = System.currentTimeMillis();
		//System.out.println(s2 - s1);
		pw.close();
		br.close();
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
