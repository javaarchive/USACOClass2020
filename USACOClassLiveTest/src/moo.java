import java.io.*;
import java.util.*;
public class moo {
	static int pos = 0;
	static int targetPos = -1;
	static int total;
	static void recur(){

	}
	static int terms(int N){
		return (int) Math.pow(2, N+3) - 5 - N;
	}
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                                    new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		targetPos = Integer.parseInt(f.readLine());
	}

}