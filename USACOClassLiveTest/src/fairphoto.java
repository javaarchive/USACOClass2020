import java.io.*;
import java.util.*;
public class fairphoto{
	static int[] ps;
	/*
	public static int size, HCowcount, GCowcount, balance;
	public static void query(int start, int end){
		if(start == 0){
			size = ps[end][3];
			HCowcount = ps[end][2];
			GCowcount = ps[end][1];
			balance = ps[end][0];
		}else{
			int startm1 = start;
			size = ps[end][3] - ps[startm1][3];
			HCowcount = ps[end][2] - ps[startm1][2];
			GCowcount = ps[end][1] - ps[startm1][1];
			balance = ps[end][0] - ps[startm1][0];
		}
	}
	public static boolean valid(){
		return (GCowcount == 0) || (HCowcount == 0) || (HCowcount == GCowcount);
	}*/
	static int seen[];
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int N; 
		StringTokenizer st;
		N = Integer.parseInt(f.readLine());
		List<Livestock> cows = new ArrayList<>();
		for(int i =0 ; i < N; i ++) {
			st = new StringTokenizer(f.readLine());
			cows.add(new Livestock(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)));
		}
		seen = new int[2*N + 1];
		cows.sort(new Comparator<Livestock>() {
			@Override
			public int compare(Livestock arg0, Livestock arg1) {
				return Integer.compare(arg0.index, arg1.index);
			}
			
		});
		//System.out.println(cows);
		Livestock cow;
		ps = new int[N];
		Arrays.fill(seen, -1);
		int bal = 0;
		//int Hcount = 0;
		//int Gcount = 0;
		for(int i = 0; i < cows.size(); i ++) {
			cow = cows.get(i);
			/*
			if(cow.type == true){
				//Gcount ++;
			}else{
				//Hcount ++;
			}*/
			bal += cow.type ? -1: 1;
			ps[i] = bal;
			seen[(bal+N)] = i;
		}
		System.out.println(Arrays.toString(ps));
		int ans = 0;
		for(int i = 0; i < N; i ++){
			int result = seen[ps[i] + N];
			//System.out.println("Q:"+ps[i]+" "+result);
			if(result != -1 && result > i){
				ans = Integer.max(ans, cows.get(result).index - cows.get(i).index);
			}
			System.out.println("P:"+ps[i]+" ::"+i+" "+result);
			System.out.println("I:"+cows.get(result).index+" "+cows.get(i).index);
		}
		pw.println(ans);
		pw.close();
		f.close();
		
	}
}
// G, H, G, G, H, G.  
//
class Livestock{
	boolean type;
	int index;
	public Livestock(int index, char type) {
		this.index = index;
		this.type = (type == 'G');
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ls(");
		builder.append(type);
		builder.append(", ");
		builder.append(index);
		builder.append(")");
		return builder.toString();
	}
	
}