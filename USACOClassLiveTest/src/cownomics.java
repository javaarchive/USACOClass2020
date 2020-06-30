import java.io.*;
import java.util.*;

public class cownomics {

	public static void main(String[] args) throws IOException {
		// IO
		// new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		// new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int N, M;
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// String[] spotty = new String[N];
		// String[] spotless = new String[N];
		boolean[][] spottySeen = new boolean[M][4];
		boolean[][] spotlessSeen = new boolean[M][4];
		for (int i = 0; i < N; i++) {
			String line = f.readLine();
			for (int j = 0; j < M; j++) {
				char c = line.charAt(j);
				switch (c) {
				case 'G':
					spottySeen[j][0] = true;
					break;
				case 'A':
					spottySeen[j][1] = true;
					break;

				case 'C':
					spottySeen[j][2] = true;
					break;

				case 'T':
					spottySeen[j][3] = true;
					break;

				}
			}
		}
		for (int i = 0; i < N; i++) {
			String line = f.readLine();
			for (int j = 0; j < M; j++) {
				char c = line.charAt(j);
				switch (c) {
				case 'G':
					spotlessSeen[j][0] = true;
					break;
				case 'A':
					spotlessSeen[j][1] = true;
					break;

				case 'C':
					spotlessSeen[j][2] = true;
					break;

				case 'T':
					spotlessSeen[j][3] = true;
					break;

				}
			}
		}
		
	}

}
