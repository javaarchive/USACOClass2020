import java.io.*;
import java.util.*;
public class reststops {
	static Point[][] ref = new Point[4][4];
	public static void main(String[] args) throws IOException{
		// IO
		BufferedReader f = new BufferedReader(new FileReader("reduce.in"));
		//BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out)));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("reduce.out")));
		int N = Integer.parseInt(f.readLine());
		List<RestStop> stops = new ArrayList<>(N);
		for(int i = 0; i < N; i ++){
			StringTokenizer st = new StringTokenizer(f.readLine());
			int pos = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			stops.add(new RestStop(pos, value));
		}
		f.close();
	}
}
class RestStop{
	int pos, value;
	public RestStop(int pos, int value){
		this.pos = pos;
		this.value = value;
	}
}