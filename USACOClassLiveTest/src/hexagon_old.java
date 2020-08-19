import java.io.*;
import java.util.*;
public class hexagon {
	public static String padLeft(String s, int n) {
		return String.format("%" + n + "s", s);  
	}
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
		int pastureTotalGuess = K * K * 3;
		int[] dx = {0,0};
		int[] dy = {2,-2};
		int[] px = new int[pastureTotalGuess], py = new int[pastureTotalGuess]; // Pasture id to pos
		int mid = (int) Math.floor((3*K - 1)/2);
		for(int i = 0; i < 2*K-1; i ++){
			int start = mid - 1 - Integer.min(i,K-i + K-2);
			int end = mid + 1 + Integer.min(i,K-i + K-2);
			if(i == (K-1)){
				start = mid - 1 - Integer.max(i, K-i);
				end = mid + 1 + Integer.max(i, K-i);
			}
			System.out.println(start+" "+end);
			if((end-start) <= 1){
				continue;
			}
			for(int j = end; j >= start; j -= 2){
				System.out.println("P "+j+" "+i);
				plane[j][i] = counter;
				px[counter] = j;
				py[counter] = i;
				counter ++;
			}
		}
		for(int i = 0; i < 3*K; i ++){
			for(int j = 0; j < 2*K; j ++){
				System.out.print(padLeft(Integer.toString(plane[i][j]), 2) + " ");
			}
			System.out.println();
		}
	}
}
