import java.io.*;
import java.util.*;
public class diamond {

	public static void main(String[] args) throws IOException{
		// IO
		BufferedReader f = new BufferedReader(new FileReader("diamond.in"));
		//BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("diamond.out")));
		//PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		List<Integer> diamonds = new ArrayList<>(N);
		for(int i = 0 ; i < N; i ++) {
			diamonds.add(Integer.parseInt(f.readLine()));
		}
		List<Result> results = new ArrayList<>(N);
		diamonds.sort(null);
		//int id = 0;
		int toprange = 0;
		for(int i =0 ; i < N; i ++) {
			int dia = diamonds.get(i);
			while(toprange < N && (diamonds.get(toprange) - dia <= K)) {
				toprange++;
			}
			results.add(new Result(i, toprange));
		}
		//System.out.println(results);
		//System.out.println(results.size());
		int best = 0;
		int Nm = N-1;
		for(int i =0 ;i < Nm; i ++) {
			int max1 = -1;
			int max2 = -1;
			Result r = new Result(-1,-1);// Make compiler happy
			for(int j =0 ;j <= i; j ++) {
				r = results.get(j);
				int selected = r.end - r.start;
				if(selected > max1) {
					max1 = selected;
				}
			}
			Result s;
			for(int j =i+1; j<N; j ++) {
				s = results.get(j);
				if(s.start < r.end) {
					continue;
				}
				int selected = s.end - s.start;
				//System.out.println(selected);
				if(selected > max2) {
					max2 = selected;
				}
			}
			//System.out.println(max1 +"   "+ max2);
			if((max1 + max2) > best) {
				best = max1 + max2;
			}
		}
		pw.println(best);
		pw.close();
		f.close();
	}

}
class Result{
	int start = 0, end = 0;// SATSIFY COMPILER
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Result [start=");
		builder.append(start);
		builder.append(", end=");
		builder.append(end);
		builder.append("]");
		return builder.toString();
	}
	public Result(int start, int end) {
		this.start = start;
		this.end = end;
	}
}