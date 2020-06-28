import java.io.*;
import java.util.*;
public class lazy {
	public static void main(String[] args) throws IOException{
		// IO
		//new InputStreamReader(System.in)                                   
		BufferedReader f = new BufferedReader( new FileReader("lazy.in"));
		// new OutputStreamWriter(System.out)                             
		PrintWriter pw = new PrintWriter( new BufferedWriter(new FileWriter("lazy.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		PatchofGrass[] field = new PatchofGrass[N];
		for(int i = 0; i < N; i ++) {
			st = new StringTokenizer(f.readLine());
			int a,b;
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			field[i] = new PatchofGrass(b, a);
		}
		Arrays.sort(field, new Comparator<PatchofGrass>() {

			@Override
			public int compare(PatchofGrass arg0, PatchofGrass arg1) {
				// TODO Auto-generated method stub
				return Integer.compare(arg0.pos, arg1.pos);
			}
			
		});
		//System.out.println(Arrays.toString(field));
		int nextpos = 0;
		//int nextfieldpos = 0;
		int best = 0;
		int lm = (2*K + field[0].pos + 1);
		for(int i = 0; i < N; i ++) {
			if(field[i].pos > lm) {
				nextpos = i;
				//nextfieldpos = field[i].pos;
				break;
			}
			//System.out.println("Initial "+field[i].grazeAmount);
			best += field[i].grazeAmount;
		}
		int right = nextpos; // Range
		int curGrass = best;
		for(int i = 1; i < N; i ++) {
			//System.out.println("LOOP "+right);
			int extendnum = right;
			int maxrange = field[i].pos + 2 * K;
			while(extendnum < N && field[extendnum].pos <= maxrange) { // While grass patches can be still reached and we still have patches
				extendnum ++;
			}
			//System.out.println(extendnum);
			//System.out.println("Removed "+field[i-1].grazeAmount+" right: "+right);
			curGrass -= field[i-1].grazeAmount;
			for(int j = right; j < extendnum; j ++) {
				//System.out.println("Added "+field[j].grazeAmount);
				curGrass += field[j].grazeAmount;
			}
			right = extendnum;
			best = Integer.max(curGrass, best);
		}
		pw.println(best);
		pw.close();
	}

}
class PatchofGrass{
	int grazeAmount ;
	int pos;
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PatchofGrass [grazeAmount=");
		builder.append(grazeAmount);
		builder.append(", pos=");
		builder.append(pos);
		builder.append("]");
		return builder.toString();
	}
	public PatchofGrass(int pos, int amount) {
		this.grazeAmount = amount;
		this.pos = pos;
	}
}
