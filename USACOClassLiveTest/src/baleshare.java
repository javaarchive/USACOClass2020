import java.io.*;
import java.util.*;
public class baleshare {
	public static int N;
	private static int[] haybales;
	public static int maxAll(int... stuff) {
		// A max function that takes any amount of arguments...MAGICAL
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < stuff.length; i ++) {
			max = Integer.max(stuff[i], max);
		}
		return max;
	}
	public static int ans = Integer.MAX_VALUE;
	public static void Collectionsdotsolve(int b1, int b2, int b3, int index){
		//System.out.println(index);
		int maxBarn = Integer.max(b1, Integer.max(b2, b3));
		if(maxBarn >= ans) {
			//System.out.println("A");
			return;
		}else if(index == N) {
			ans = maxBarn;
			//System.out.println("B: "+maxBarn+" "+ans);
			return;
		}
		int h =  haybales[index];
		int ip1 = index + 1;
		Collectionsdotsolve(b1 + h, b2, b3, ip1);
		Collectionsdotsolve(b1, b2 + h, b3, ip1);
		Collectionsdotsolve(b1, b2, b3 + h, ip1);
	}
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(f.readLine());
		haybales = new int[N];
		for(int i = 0; i < N; i ++) {
			haybales[i] = Integer.parseInt(f.readLine());
		}
		//int b1, b2, b3;
		Collectionsdotsolve(0, 0, 0, 0);
		pw.println(ans);
		pw.close();
		f.close();
	}
}