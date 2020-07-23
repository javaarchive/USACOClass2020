import java.io.*;
import java.util.*;

public class convention2 {
	public static void main(String[] args) throws IOException {
		// IO
		// new FileReader("reduce.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		// new BufferedWriter(new FileWriter("reduce.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(f.readLine());
		PriorityQueue<Visitor> grazingAnimals = new PriorityQueue<Visitor>();
		List<Visitor> cows = new ArrayList<>(N);
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int time, eatingTime;
			time = Integer.parseInt(st.nextToken());
			eatingTime = Integer.parseInt(st.nextToken());
			Visitor v = new Visitor(i, time, eatingTime);
			cows.add(v);
		}
		cows.sort(new Comparator<Visitor>() {
			@Override
			public int compare(Visitor o1, Visitor o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1.time, o2.time);
			}
		});
		Visitor currentCow = null;
		int finishTime = -1;
		int most = 0;
		cows.add(new Visitor(-1, Integer.MAX_VALUE, 0));
		for (Visitor v : cows) {
			if (v.time >= finishTime) {
				int curTime = finishTime;
				//System.out.println(v.time+" "+finishTime);
				//System.out.println(grazingAnimals);
				while (!grazingAnimals.isEmpty()) {
					Visitor v2 = grazingAnimals.peek();
					if (v2.time > finishTime) {
						//System.out.println("Should this condition even fire?");
						break;
					}
					//if(v2.time > curTime){System.out.println("ERROR");}
					System.out.println("Calculating time between "+curTime+" and "+v2.time);
					most = Integer.max(most, curTime - v2.time);
					curTime += v2.consumptionTime;
					grazingAnimals.poll();
				}
				currentCow = null;
			}
			if (currentCow == null) {
				currentCow = v;
				finishTime = v.time + v.consumptionTime;
			} else {
				grazingAnimals.add(v);
			}
		}
		pw.println(most);
		pw.close();
	}

}

class Visitor implements Comparable<Visitor> {
	int time;
	int senority;
	int consumptionTime;

	public Visitor(int s, int t, int ct) {
		this.senority = s;
		this.time = t;
		this.consumptionTime = ct;
	}

	@Override
	public int compareTo(Visitor o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.senority, o.senority);
	}

	@Override
	public String toString() {
		return "Visitor [consumptionTime=" + consumptionTime + ", senority=" + senority + ", time=" + time + "]";
	}
	
}
