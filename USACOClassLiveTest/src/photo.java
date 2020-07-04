import java.io.*;
import java.util.*;
public class photo {
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("reduce.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                                    new BufferedWriter(new FileWriter("reduce.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<Compliant> compliants = new ArrayList<>();
		for(int i = 0; i < M; i ++){
			int x,y;
			st = new StringTokenizer(f.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			compliants.add(new Compliant(x, y));
		}
		compliants.sort(new Comparator<Compliant>(){
			@Override
			public int compare(Compliant o1, Compliant o2) {
				int ycompare = Integer.compare(o1.y, o2.y);;
				if(ycompare == 0){
					return Integer.compare(o1.x, o2.x);
				}
				return ycompare;
			}
		});
		int photos = 1;
		int prevPos = 0;
		int currentPos = compliants.get(0).y - 1;
		//System.out.println(compliants);
		System.out.println("Set currentPos initially  to "+prevPos+" "+currentPos);
		for(int i = 0; i < M-1; i ++){
			Compliant c1 = compliants.get(i);
			Compliant c2 = compliants.get(i+1);
			System.out.println("COMPLIANT: "+c1);
			if(c1.y > currentPos && c1.x <= currentPos && prevPos <= c1.x){
				prevPos = currentPos+1;
				currentPos = c2.y;
				System.out.println("NEW: " + currentPos+" "+c1.x+" "+c1.y);
				photos ++;
			}else if(c1.y > currentPos && c1.x > currentPos){
				prevPos = currentPos+1;
				currentPos = c2.y;
				photos ++;
			}
			System.out.println("RANGE :"+prevPos+" "+currentPos);
		}
		pw.println(photos);
		pw.close();
	}
}
class Compliant{
	int x,y;
	public Compliant(int a, int b){
		this.x = Integer.min(a, b);
		this.y = Integer.max(a, b);
	}
	public String toString(){
		return "{"+this.x+","+this.y+"}";
	}
}