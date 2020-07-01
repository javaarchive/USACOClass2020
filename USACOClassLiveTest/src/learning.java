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
		/*cows.sort(new Comparator<Cow>() {
			@Override
			public int compare(Cow arg0, Cow arg1) {
				// TODO Auto-generated method stub
				return Integer.compare(arg0.weight, arg1.weight);
			}
		});*/
		boolean inrange = false;
		int spotcount = 0;
		for(int i = 0; i < cows.size()-1; i ++) {
			//System.out.println(start.weight+" "+end.weight);
			Cow start = cows.get(i);
			Cow end = cows.get(i + 1);
			if(start.weight <= A && A <= end.weight) {
				//System.out.println("Encapsulates A");
				inrange = true;
				double mid = (start.weight + end.weight)/2;
				if(A < mid) {
					if(start.spotted) {
						spotcount += mid - start.weight;// System.out.println("Line 43");
					}
					if(end.spotted) {
						spotcount += end.weight - mid;  //System.out.println("Line 46");
					}
					if(A <= mid && mid == Math.floor(mid)) {
						if(start.spotted || end.spotted) {
							spotcount ++; // System.out.println("Line 50");
						}
					}else {
						spotcount ++;
					}
				}else if(A >= mid) {
					spotcount += end.weight - mid;   //System.out.println("Line 54");
				}
				//spotcount += (end.weight - A) ;
				//System.out.println("Current Count: "+spotcount);
				continue;
			}
			if(!inrange && start.weight >= A) {
				inrange = true;
				spotcount ++;
			}
			if(start.weight <= B && B <= end.weight) {
				//System.out.println("Encapsulates B");
				double mid = (start.weight + end.weight) / 2;
				if( mid <= B) {
					if(start.spotted) {
						spotcount += Math.floor(mid) - start.weight;  //System.out.println("Line 63");
					}
					if(end.spotted) {
						spotcount += B - Math.ceil(mid);  //System.out.println("Line 66");
					}
					if(B >= mid && mid == Math.floor(mid)) {
						if(start.spotted || end.spotted) {
							spotcount ++;
						}
					}
				}else if(mid >A)
				//spotcount += (B - start.weight);
				inrange = false;
				//System.out.println("Current Count: "+spotcount);
			}else if(inrange) {
				double mid = (start.weight + end.weight)/2;
				//System.out.println("In range!!! "+mid+" "+start.weight+" "+end.weight);
				if(start.spotted) {
					spotcount += (Math.floor(mid) - 1) - start.weight;  //System.out.println("Line 80");
					//System.out.println("+ "+((Math.floor(mid) - 1) - start.weight));
				}
				if(end.spotted) {
					spotcount += end.weight - (Math.ceil(mid)); // System.out.println("Line 83");
					//System.out.println("+ "+(end.weight - Math.ceil(mid)));
				}
				if(mid == Math.floor(mid)) {
					spotcount ++;  //System.out.println("Line 86");
				}
				//spotcount += (end.weight - start.weight);
				//System.out.println("Current Count: "+spotcount);
			}
		}
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