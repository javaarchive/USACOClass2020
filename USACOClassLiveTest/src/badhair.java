import java.io.*;
import java.util.*;
public class badhair{

	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(f.readLine());
		int heightPos = -1;
		int bestHeight = -1;
		int ans = 0;
		for(int i = 0; i < N; i ++){
			int height = Integer.parseInt(f.readLine());
			if(height >= bestHeight){
				if(heightPos != -1){
					ans += i - heightPos - 1;
				}
				bestHeight = height;
				heightPos = i;
			}
		}
		ans += N - heightPos - 1;
		pw.println(ans);
		pw.close();
		
	}

}
