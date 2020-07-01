import java.io.*;
import java.util.*;
public class diamond {

	public static void main(String[] args) throws IOException{
		// IO
		//BufferedReader f = new BufferedReader(new FileReader("diamond.in"));
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("diamond.out")));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		List<Integer> diamonds = new ArrayList<>(N);
		for(int i = 0 ; i < N; i ++) {
			diamonds.add(Integer.parseInt(f.readLine()));
		}
		List<Result> results_S = new ArrayList<>(N);
		List<Result> results_E = new ArrayList<>(N);
		diamonds.sort(null);
		//int id = 0;
		int toprange = 0;
		for(int i =0 ; i < N; i ++) {
			int dia = diamonds.get(i);
			while(toprange < N && (diamonds.get(toprange) - dia <= K)) {
				toprange++;
			}
			Result r = new Result(i, toprange);
			results_S.add(r);	
		}
		int botrange = N-1;
		for(int i = N-1; i >= 0; i --) {
			int dia = diamonds.get(i);
			while(0 <= botrange && (dia - diamonds.get(botrange) <= K)) {
				botrange --;
			}
			Result r = new Result(botrange, i);
			results_E.add(r);
		}
		//System.out.println(results);
		//System.out.println(results.size());
		int best = 0;
		int[] ltor = new int[N];
		int max = -1;
		int[] rtol = new int[N];
		results_E.sort(new Comparator<Result>() {
			@Override
			public int compare(Result o1, Result o2) {
				return Integer.compare(o1.end, o2.end);
			}
		});
		for(int i =0 ;i < N; i ++) {
			Result r = results_E.get(i);
			int sel = r.end - r.start;
			max = Integer.max(max, sel);
			ltor[i] = max;
		}
		 max = -1;
		for(int i =N-1;i >= 0; i --) {
			Result r = results_S.get(i);
			int sel = r.end - r.start;
			max = Integer.max(max, sel);
			rtol[i] = max; 
		}
		for(int i =0 ;i < N-1; i ++) {
			int sum = ltor[i] + rtol[i+1];
			//System.out.println(max1 +"   "+ max2);
			if(sum > best) {
				best = sum;
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