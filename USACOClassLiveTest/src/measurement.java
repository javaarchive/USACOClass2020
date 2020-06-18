import java.io.*;
import java.util.*;
public class measurement {

	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("measurement.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("measurement.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		TreeMap<Integer, Integer> cowToProd = new TreeMap<>();
		TreeMap<Integer, Integer> prodToAmount = new TreeMap<>();
		List<Cattle> days = new ArrayList<>();
		for(int i = 0; i < N; i ++) {
			int day, id, change;
			st = new StringTokenizer(f.readLine());
			day = Integer.parseInt(st.nextToken());
			id = Integer.parseInt(st.nextToken());
			change = Integer.parseInt(st.nextToken());
			cowToProd.put(id, G);
			days.add(new Cattle(day, id, change));
		}
		days.sort(null);
		prodToAmount.put(G, N);
		//System.out.println(prodToAmount.lastKey());
		int prevMax = -1, ans = -1;
		int lastProd = prodToAmount.get(prodToAmount.lastKey());
		for(int i = 0; i < N; i ++) {
			Cattle c = days.get(i);
			//System.err.println(c.day);
			//System.err.println(lastProd+" "+prevMax);
			int curProd = cowToProd.get(c.id);
			int newProd = curProd + c.change;
			int alsoProducingAmount = prodToAmount.get(curProd);
			if(alsoProducingAmount == 1) {
				prodToAmount.remove(curProd);
				int newprodamount = 0;
				if(prodToAmount.keySet().contains(newProd)) {
					newprodamount = prodToAmount.get(newProd);
				}
				prodToAmount.put(newProd, newprodamount + 1);
				prodToAmount.put(newProd, 1);
			}else {
				if(!prodToAmount.keySet().contains(newProd)) {
					prodToAmount.put(newProd, 0);
				}
				prodToAmount.put(curProd, prodToAmount.get(curProd) - 1);
				prodToAmount.put(newProd, prodToAmount.get(newProd) + 1);
			}
			cowToProd.put(c.id, newProd);
			//System.out.println(prodToAmount.lastKey());
			if(prodToAmount.lastKey() != prevMax) {
				//System.err.println("Best scorer prod is now "+prodToAmount.lastKey());
				ans ++;
				prevMax = prodToAmount.lastKey();
			}else if(prodToAmount.get(newProd) != lastProd) {
				//System.err.println("Amount is now "+prodToAmount.get(newProd));
				ans ++;
				lastProd = prodToAmount.get(newProd);
			}
		}
		pw.println(ans);
		pw.close();
	}

}
class Cattle implements Comparable<Cattle>{
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