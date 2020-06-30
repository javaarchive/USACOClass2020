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
		System.out.println(diamonds);
		//System.out.println(results.size());
		int best = 0;
		int[] ltor = new int[N];
		int[] maxEnds = new int[N];
		boolean[] works = new boolean[N];
		int max = -1;
		int[] rtol = new int[N];
		results.sort(new Comparator<Result>() {

			@Override
			public int compare(Result arg0, Result arg1) {
				// TODO Auto-generated method stub
				return Integer.compare(arg0.start, arg1.start);
			}
			
		});
		//int maxEnd = -1;
		for(int i =0 ;i < N; i ++) {
			Result r = results.get(i);
			int sel = r.end - r.start;
			max = Integer.max(max, sel);
			//maxEnd = Integer.max(maxEnd, r.end);
			ltor[i] = max;
			maxEnds[i] = r.end;
		}
		 max = -1;
		 System.out.println("ME: "+Arrays.toString(maxEnds));
		for(int i =N-1;i >= 0; i --) {
			Result r = results.get(i);
			int sel = r.end - r.start;
			max = Integer.max(max, sel);
			rtol[i] = max;
			if(i!=0) {System.out.println("RS "+maxEnds[i-1]+"<"+r.start);}
			if(i!=0 && maxEnds[i-1] >= r.start) {
				works[i] = false;
			}else {
				works[i] = true;
			}
		}
		for(int i =1 ;i < N-1; i ++) {
			int sum = ltor[i] + rtol[i-1];
			System.out.println(sum);
			if(sum > best && works[i]) {
				best = sum;
			}
		}
		System.out.println(Arrays.toString(works));
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