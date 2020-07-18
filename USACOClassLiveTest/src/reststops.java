import java.io.*;
import java.util.*;
public class reststops {
	public static void main(String[] args) throws IOException{
		// IO
		//BufferedReader f = new BufferedReader(new FileReader("reduce.in"));
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		//PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("reduce.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int L = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int r_F = Integer.parseInt(st.nextToken());
		int r_B = Integer.parseInt(st.nextToken());
		List<RestStop> stops = new ArrayList<>(N);
		for(int i = 0; i < N; i ++){
			st = new StringTokenizer(f.readLine());
			int pos = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			stops.add(new RestStop(pos, value));
		}
		int betweenRate = r_F - r_B;
		stops.sort(new Comparator<RestStop>(){

			@Override
			public int compare(RestStop o1, RestStop o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1.pos, o2.pos);
			}
		});
		//boolean isMax = true;
		int backMax = Integer.MIN_VALUE;
		for(int i = N-1; i >= 0; i --){
			RestStop rs = stops.get(i);
			if(backMax < rs.value){
				backMax = rs.value;
				rs.best = true;
			}
		}
		long ans = 0, max = -1, time = 0, lastPos = 0;
		for(int i = 0; i < N; i ++){
			RestStop rs = stops.get(i);
			if(rs.best){
				time += (rs.pos - lastPos) * betweenRate;
				ans += rs.value * time;
				//max = rs.value;
				time = 0;
				lastPos = rs.pos;
			}
		}
		//pw.println(stops);
		pw.println(ans);
		f.close();
		pw.close();
	}
}
class RestStop{
	int pos, value;
	boolean best = false;
	public RestStop(int pos, int value){
		this.pos = pos;
		this.value = value;
	}
	public String toString(){
		return "("+this.value+"@"+this.pos+")";
	}
}