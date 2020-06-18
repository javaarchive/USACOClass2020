import java.io.*;
import java.util.*;
public class convention2 {
	public static void main(String[] args) throws IOException {
		// IO
		//                                    new FileReader("reduce.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("reduce.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(f.readLine());
		PriorityQueue<Visitor> grazingAnimals = new PriorityQueue<Visitor>();
		for(int i = 0; i < N; i ++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int time, eatingTime;
			time = Integer.parseInt(st.nextToken());
			eatingTime = Integer.parseInt(st.nextToken());
			grazingAnimals.add(new Visitor(i, time, eatingTime));
		}
		grazingAnimals.sort(new Comparator<Visitor>() {

			@Override
			public int compare(Visitor arg0, Visitor arg1) {
				return;
			}
		});
	}

}
class Visitor{
	int time;
	int senority;
	int consumptionTime;
	public Visitor(int s, int t, int ct) {
		this.senority = s;
		this.time = t;
		this.consumptionTime = ct;
	}
}
