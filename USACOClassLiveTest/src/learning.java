import java.io.*;
import java.util.*;

public class learning {
	static List<Cow> cows;
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("reduce.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("reduce.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int N,A,B;
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		cows = new ArrayList<>();
		cows.add(new Cow(Integer.MIN_VALUE, false));
		for(int i = 0; i < N; i ++) {
			st = new StringTokenizer(f.readLine());
			boolean spots = st.nextToken().equals("S");
			int weight = Integer.parseInt(st.nextToken());
			cows.add(new Cow(weight, spots));
		}
		cows.add(new Cow(Integer.MAX_VALUE, false));
		cows.sort(new Comparator<Cow>() {
			@Override
			public int compare(Cow arg0, Cow arg1) {
				// TODO Auto-generated method stub
				return Integer.compare(arg0.weight, arg1.weight);
			}
		});
		List<Cow> newCows = new ArrayList<>();
		for(int i = 0; i < N+1; i ++){
			boolean isEdge = (i == 0) || (i == N+1);
			if(i == 0){
				continue; // SKIP!!
			}
		}
		boolean inrange = false;
		int spotcount = 0;

		//spotcount ++; // Add beggning
		//System.out.println(cows);
		pw.println(spotcount);
		pw.close();
	}
}
class Cow{
	int weight;
	boolean spotted;
	public Cow(int weight, boolean spots) {
		this.spotted = spots;
		this.weight = weight; 
	}
	public String toString() {
		return (this.spotted?"S":"P") + this.weight;
	}
}