import java.io.*;
import java.util.*;

public class cownomics {

	public static void main(String[] args) throws IOException {
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                                    new BufferedWriter(new FileWriter("cownomics.out"))
		int N, M; 
		StringTokenizer st =new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		String[] spotty = new String[N];
		String[] spotless = new String[N];
		for(int i = 0; i < N; i ++) {
			spotty[i] = f.readLine();
		}
		for(int i = 0; i < N; i ++) {
			spotless[i] = f.readLine();
		}
	}

}
