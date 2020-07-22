import java.io.*;
import java.util.*;

public class measurement {

	public static void main(String[] args) throws IOException {
		// IO
		// new FileReader("measurement.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		// new BufferedWriter(new FileWriter("measurement.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		TreeMap<Integer, Integer> cowToProd = new TreeMap<>();
		TreeMap<Integer, Set<Integer>> prodToCows= new TreeMap<>();
		//TreeMap<Integer, Integer> prodToId = new TreeMap<>();
		List<Cattle> days = new ArrayList<>();
		Set<Integer> everything = new HashSet<>();
		for (int i = 0; i < N; i++) {
			int day, id, change;
			st = new StringTokenizer(f.readLine());
			day = Integer.parseInt(st.nextToken());
			id = Integer.parseInt(st.nextToken());
			change = Integer.parseInt(st.nextToken());
			cowToProd.put(id, G);
			days.add(new Cattle(day, id, change));
			everything.add(id);
			cowToProd.put(id, G);
		}
		days.sort(null);
		prodToCows.put(G, everything);
		// System.out.println(prodToAmount.lastKey());
		int prevMax = N, ans = 0;
		int bestProd = G;
		//int lastProd = N;
		//int lastid = -1;
		//int maxprod = ;
		// Set<Integer> cows = new HashSet<>();
		for (int i = 0; i < N; i++) {
			Cattle c = days.get(i);
			System.out.println(i+" of total "+N);
			//System.out.println(c.day+" "+c.id+" "+c.change);
			int prod = cowToProd.get(c.id);
			int newProd = prod + c.change;
			Set<Integer> oldSet = prodToCows.get(prod);
			if(oldSet.size() == 0){
				prodToCows.remove(prod);
			}
			if(!prodToCows.keySet().contains(newProd)){
				prodToCows.put(newProd,new HashSet<>());
			}
			Set<Integer> newSet = prodToCows.get(newProd);
			oldSet.remove(c.id);
			newSet.add(c.id);
			int lk = prodToCows.lastKey();
			Set<Integer> largestSet = prodToCows.get(lk);
			if(lk != bestProd){
				ans ++;
				prevMax = largestSet.size();
				bestProd = lk;
			}else if(largestSet.size() != prevMax){
				ans ++;
				prevMax = largestSet.size();
				bestProd = lk;
			}
			cowToProd.put(c.id, newProd); // Update Production
		}
		pw.println(ans);
		pw.close();
	}

}

class Cattle implements Comparable<Cattle> {
	int day;
	int id;
	int change;

	public Cattle(int day, int id, int change) {
		this.day = day;
		this.id = id;
		this.change = change;
	}

	@Override
	public int compareTo(Cattle arg0) {
		// TODO Auto-generated method stub
		return Integer.compare(this.day, arg0.day);
	}

}