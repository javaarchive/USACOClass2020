import java.io.*;
import java.util.*;
public class paintjob{
	public static int N, P;
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int BessieColor, DesiredColor; 
		StringTokenizer st = new StringTokenizer(f.readLine());
		BessieColor = Integer.parseInt(st.nextToken());
		DesiredColor = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		int[] origColors = new int[N];
		st = new StringTokenizer(f.readLine());;
		for(int i = 0; i < N; i ++) {
			origColors[i] = Integer.parseInt(st.nextToken());
		}
		Queue<MixColor> COLORZ = new LinkedList<>();
		MixColor mc = new MixColor(-1,-1);
		//System.out.println(BessieColor);
		boolean[] visitedcolors = new boolean[P+1];
		COLORZ.add(new MixColor(BessieColor, 0));
		while(!COLORZ.isEmpty()) {
			mc = COLORZ.poll();
			if(mc.color == DesiredColor) {
				break;
			}
			for(int i = 0; i < origColors.length; i ++) {
				int newColor = (mc.color * origColors[i]) % P;
				if(!visitedcolors[newColor]) {
					visitedcolors[newColor] = true;
					COLORZ.add(new MixColor(newColor, mc.depth + 1));
				}
			}
			//System.out.println(COLORZ);
		}
		//System.out.println(mc.color+" "+DesiredColor);
		if(mc.color == DesiredColor) {
			pw.println(mc.depth);
		}else {
			pw.println("-1");
		}
		pw.close();
		f.close();
	}
}
class MixColor{
	int color;
	int depth;
	public MixColor(int color, int depth) {
		this.color = color;
		this.depth = depth;
	}
	public String toString() {
		return "( " + this.color+" D: "+this.depth+")";
	}
}





