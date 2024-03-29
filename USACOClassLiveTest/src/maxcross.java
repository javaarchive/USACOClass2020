import java.io.*;
import java.util.*;
public class maxcross {
	static int[] ps;
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("reduce.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("reduce.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		List<Integer> broken = new ArrayList<Integer>();
		boolean[] crosswalks = new boolean[N];
		int needfixes = 0;
		ps = new int[N];
		int[] input = new int[B];
		for(int i = 0; i < B; i ++) {
			input[i] = Integer.parseInt(f.readLine());
		}
		Arrays.sort(input);
		int curIndex = 0;
		for(int i = 0;i < N; i ++) {
			if(i >= 1) {
				ps[i] = ps[i-1];
			}
			if(curIndex < B && input[curIndex] == i) {
				ps[i] = ps[i] + 1;
				curIndex ++;
			}
		}
		//System.out.println(Arrays.toString(ps));
		int best = Integer.MAX_VALUE;
		for(int i = K; i < N; i ++) {
			needfixes = ps[i] - ps[i - K];
			//System.out.println(needfixes);
			best = Math.min(needfixes, best);
		}
		
		pw.println(best);
		pw.close();
		f.close();
	}
}