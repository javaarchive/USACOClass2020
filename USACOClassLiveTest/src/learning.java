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
		for(int i = 0; i < N; i ++) {
			st = new StringTokenizer(f.readLine());
			boolean spots = st.nextToken().equals("S");
			int weight = Integer.parseInt(st.nextToken());
			cows.add(new Cow(weight, spots));
		}

			@Override
			public int compare(Cow arg0, Cow arg1) {
				// TODO Auto-generated method stub
				return Integer.compare(arg0.weight, arg1.weight);
			}
		});
		boolean inrange = false;
		int spotcount = 0;
		for(int i = 0; i < cows.size()-1; i ++) {
			System.out.println(cows.get(i).weight+" "+cows.get(i+1).weight);
			if(cows.get(i).weight <= A && A <= cows.get(i+1).weight) {
				inrange = true;
				double mid = (cows.get(i).weight + cows.get(i+1).weight)/2;
				if(mid == Math.floor(mid) && A < mid) {
					spotcount += (cows.get(i).spotted && cows.get(i+1).spotted) ? -1:0;
				}
				spotcount += (cows.get(i+1).weight - A) ;
			}else if(cows.get(i).weight <= B && B <= cows.get(i+1).weight) {
				double mid = (cows.get(i).weight + cows.get(i+1).weight)/2;
				if(mid == Math.floor(mid) && mid < B) {
					spotcount += (cows.get(i).spotted && cows.get(i+1).spotted) ? -1:0;
				}
				spotcount += (B - cows.get(i).weight);
				inrange = false;
			}else if(inrange) {
				double mid = (cows.get(i).weight + cows.get(i+1).weight)/2;
				if(mid == Math.floor(mid)) {
					spotcount += (cows.get(i).spotted && cows.get(i+1).spotted) ? -1:0;
				}
				spotcount += (cows.get(i+1).weight - cows.get(i).weight);
			}
		}
		System.out.println(cows);
		System.out.println(spotcount);
		
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