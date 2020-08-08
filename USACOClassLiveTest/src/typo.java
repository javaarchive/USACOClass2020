import java.io.*;
import java.util.*;
public class typo {
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int t = 0;
		int out = 0;
		int pos = 0;
		char[] line = f.readLine().toCharArray();
		int N = line.length;
		int[] ps = new int[N + 1];
		for(int i = 0; i < N; i ++){
			ps[i + 1] = ps[i] + ((line[i] == '(') ? 1:-1);
		}
		int sol = 0;
		System.out.println(Arrays.toString(ps));
		for(int i = 0; i < N; i ++){
			//System.out.println(ps[i] + ((line[i] == '(') ? -1:1) + ps[N] - ps[i + 1]);
			if((ps[i] + ((line[i] == '(') ? -1:1) + ps[N] - ps[i + 1]) == 0){
				sol ++;
			}
		}
		pw.println(sol);
		pw.close();
		f.close();
	}

}
