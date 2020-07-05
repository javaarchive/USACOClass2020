import java.io.*;
import java.util.*;
public class fairphoto{

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
		cows.sort(new Comparator<Livestock>() {
			@Override
			public int compare(Livestock arg0, Livestock arg1) {
				return Integer.compare(arg0.index, arg1.index);
			}
			
		});
		System.out.println(cows);
		Livestock cow;
		int[][] ps = new int[N][3];
		int bal = 0;
		int lastH = -1
		int lastG = -1;
		for(int i = 0; i < cows.size(); i ++) {
			cow = cows.get(i);
			bal += cow.type ? -1: 1;
			if(cow.type == true){
				lastG = i;
			}else{
				lastH = i;
			}
			ps[i][0] = bal;
			ps[i][1] = lastH;
			ps[i][2] = lastG;
		}
		pw.close();
		f.close();
		
	}
}
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