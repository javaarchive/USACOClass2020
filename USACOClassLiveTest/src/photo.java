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
				// TODO Auto-generated method stub
				int ycompare = Integer.compare(o1.y, o2.y);;
				if(ycompare == 0){
					return Integer.compare(o1.x, o2.x);
				}
				return ycompare;
			}
		});
		int photos = 0;
		int prevPos = -1;
		int currentPos = N;
		for(Compliant c: compliants){
			if(c.y > currentPos && c.x < currentPos && prevPos < c.x){
				prevPos = currentPos;
				currentPos = c.y;
				photos++;
			}
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
}