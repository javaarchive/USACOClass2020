import java.io.*;
import java.util.*;

public class elect {
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("elect.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("elect.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(f.readLine());
		final int N = Integer.parseInt(st.nextToken());
		final int K = Integer.parseInt(st.nextToken());
		List<Cow> cows = new ArrayList<Cow>();
		for(int i =0 ; i < N; i ++) {
			st = new StringTokenizer(f.readLine());
			cows.add(new Cow(i+1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		cows.sort(new Comparator<Cow>() {

			@Override
			public int compare(Cow o1, Cow o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o2.a, o1.a);
			}
		});
		int bestCow = -1;
		int mostVotes = -1;
		for(int i = 0; i < K; i ++) {
			if(cows.get(i).b > mostVotes) {
				mostVotes = cows.get(i).b;
				bestCow = cows.get(i).id;
			}
		}
		System.out.println(bestCow);
		pw.close();
		f.close();
	}
}
class Cow{ // How many times have I typed this
	int id, a, b;
	public Cow(int id, int a, int b) {
		this.a =a;
		this.b =b;
		this.id=id;
	}
	public String toString() {
		return "{"+this.id+": "+this.a+", "+this.b+"}";
	}
}
// 101011101111001
// 101111101111001