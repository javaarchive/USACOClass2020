import java.io.*;
import java.util.*;

public class covention2 {
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
		//Visitor currentCow = null;
		//int finishTime = -1;
		int most = 0;
		cows.add(new Visitor(-1, Integer.MAX_VALUE, 0));
		int i = 0;
		Visitor v = cows.get(i);
		int start = v.time;
        while(i < N){
            while(v.time <= start ){
				//System.out.println("Check: "+v);
				grazingAnimals.add(v);
				i ++;
				v = cows.get(i);
			}
			if(!grazingAnimals.isEmpty()){
				//System.out.println(grazingAnimals);
                Visitor v3 = grazingAnimals.poll();
				most = Integer.max(start - v3.time, most);
				//System.out.println("Compare "+start+" "+v.time);
				start += v3.consumptionTime;
            }else{
				start = cows.get(i).time;
			}
			
			//System.out.println("i: "+i);
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
		//int val = Integer.compare(this.time + this.consumptionTime, o.time + o.consumptionTime);
		int val = Integer.compare(this.senority, o.senority);
		return val;//Integer.compare(this.senority, o.senority);
	}

	@Override
	public String toString() {
		return "Visitor [consumptionTime=" + consumptionTime + ", senority=" + senority + ", time=" + time + "]";
	}
	
}
