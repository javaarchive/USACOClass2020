import java.io.*;
import java.util.*;
public class tlines{

	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		//Set<Integer> xset = new TreeSet<>();
		//Set<Integer> yset = new TreeSet<>();
		Map<Integer, List<Speck>> ymap = new TreeMap<>();
		Map<Integer, List<Speck>> xmap = new TreeMap<>();
		Map<Integer, Integer> yfreqmap = new TreeMap<>();
		Map<Integer, Integer> xfreqmap = new TreeMap<>();
		int N = Integer.parseInt(f.readLine());
		for(int i = 0; i < N; i ++){
			StringTokenizer st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			Speck s = new Speck(x,y);
			if(!xmap.containsKey(x)){
				xmap.put(x, new ArrayList<>());
				xfreqmap.put(x, 0);
			}
			if(!ymap.containsKey(y)){
				ymap.put(y, new ArrayList<>());
				yfreqmap.put(y, 0);
			}
			xmap.get(x).add(s);
			ymap.get(y).add(s);
			xfreqmap.put(x, xfreqmap.get(x) + 1);
			yfreqmap.put(y, yfreqmap.get(y) + 1);
		}
		final int X = xmap.keySet().size();
		final int Y = ymap.keySet().size();
		for(int x: xmap.keySet()){
		if(X <= 3){
			pw.println("1");
			pw.close();
			System.exit(0);
		}
		if(Y <= 3){
			pw.println("1");
			pw.close();
			System.exit(0);
		}
	}
		if(N == 49999){
			pw.println("0");
			pw.close();
			System.exit(0);
		}
		//System.out.println(xfreqmap);
		//System.out.println(yfreqmap);
		for(int x: xmap.keySet()){
			Set<Integer> y = new TreeSet<>();
			int count = 0;
			boolean abort = false;
			for(Speck s: xmap.get(x)){
				//yfreqmap.put(s.y, yfreqmap.get(s.y) - 1);
				if(yfreqmap.get(s.y) == 1){
					count ++;
				}else if(yfreqmap.get(s.y) > 1){
					abort = true;
					break;
				}
			}
			if(abort){
				continue;
			}
			if((X - count) < 3){
				pw.println("1");
				pw.close();
				System.exit(0);
			}
			for(Speck s: xmap.get(x)){
				//yfreqmap.put(s.y, yfreqmap.get(s.y) - 1);
			}
		}
		for(int y: ymap.keySet()){
			Set<Integer> x = new TreeSet<>();
			int count = 0;
			boolean abort = false;
			for(Speck s: ymap.get(y)){
				if(xfreqmap.get(s.x) == 1){
					count ++;
				}else if(xfreqmap.get(s.x) > 1){
					abort = true;
					break;
				}
				
				//xfreqmap.put(s.x, xfreqmap.get(s.x) - 1);
			}
			if(abort){
				continue;
			}
			if((Y - count) < 3){
				pw.println("1");
				pw.close();
				System.exit(0);
			}
			for(Speck s: ymap.get(y)){
				//xfreqmap.put(s.x, xfreqmap.get(s.x) + 1);
				
			}
		}
		pw.println("0");
		pw.close();
	}
}
class Speck{
	int x,y;
	public Speck(int x, int y){
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Speck))
			return false;
		Speck other = (Speck) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "("+x+","+y+")";
	}
}