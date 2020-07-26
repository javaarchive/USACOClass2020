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
		int N = Integer.parseInt(f.readLine());
		for(int i = 0; i < N; i ++){
			StringTokenizer st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			Speck s = new Speck(x,y);
			if(!xmap.containsKey(x)){
				xmap.put(x, new ArrayList<>());
			}
			if(!ymap.containsKey(y)){
				ymap.put(y, new ArrayList<>());
			}
			xmap.get(x).add(s);
			ymap.get(y).add(s);
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
	Set<Integer> x = new TreeSet<>();
	Set<Integer> y = new TreeSet<>();
		for(int xx: xmap.keySet()){
			for(Speck s: xmap.get(xx)){
				y.add(s.y);
			}
		}
		System.out.println("Y: "+y.size());
		if((y.size()) < 3){
			pw.println("1");
			pw.close();
			System.exit(0);
		}
		for(int yy: ymap.keySet()){
			for(Speck s: ymap.get(yy)){
				x.add(s.x);
			}
		}
		System.out.println("X: "+x.size());
		if((x.size()) < 3){
			pw.println("1");
			pw.close();
			System.exit(0);
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