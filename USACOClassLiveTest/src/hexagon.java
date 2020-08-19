import java.io.*;
import java.util.*;
public class hexagon {
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int K = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[][] plane = new int[3*K][2*K];
		int counter = 1;
		int mid = (int) Math.floor((3*K - 1)/2);
		for(int i = 0; i < 2*K-1; i ++){
			int start = mid - 1 - Integer.min(i,K-i);
			int end = mid + 1 + Integer.min(i,K-i);
			System.out.println(start+" "+end);
			for(int j = end; j >= start; j -= 2){
				System.out.println("P "+j+" "+i);
				plane[j][i] = counter;
				counter ++;
			}
		}
		for(int i = 0; i < 3*K; i ++){
			System.out.println(Arrays.toString(plane[i]));
		}
	}
}
