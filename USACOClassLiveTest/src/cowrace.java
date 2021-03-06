import java.io.*;
import java.util.*;

public class cowrace {

	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                                    new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int N, M;
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Seg[] timeline1 = new Seg[N];
		Seg[] timeline2 = new Seg[M];
		int curTime =0,x,y;
		for(int i = 0; i < N; i ++) {
			st = new StringTokenizer(f.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			curTime += y;
			timeline1[i] = new Seg(curTime,x);
		}
		curTime =0;
		for(int i = 0; i < M; i ++) {
			st = new StringTokenizer(f.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			curTime += y;
			timeline2[i] = new Seg(curTime,x);
		}
		Arrays.sort(timeline1, new Comparator<Seg>() {

			@Override
			public int compare(Seg o1, Seg o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1.time, o2.time);
			}
			
		});
		Arrays.sort(timeline2, new Comparator<Seg>() {

			@Override
			public int compare(Seg o1, Seg o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1.time, o2.time);
			}
			
		});
		int a = 0, b = 0;
		int best;
		double B = 0, E = 0; // Bessie and Elsie's pos
		int lastTime = 0;
		best = -2;
		int ans = -1;
		while(a < N && b < M) {
			double newB, newE;
			Seg Bessie = timeline1[a];
			Seg Elsie = timeline2[b];
			//System.out.println(Bessie+" "+Elsie);
			int tB, tE; // Time advanced since last time
			tB = Bessie.time - lastTime;
			tE = Elsie.time - lastTime;
			//System.out.println("Pos: "+a+" "+b);
			//System.out.println(tB+" and "+tE+" last: "+lastTime+" current: "+Integer.min(Elsie.time, Bessie.time));
			if(Bessie.time < Elsie.time) {
				newB = B + Bessie.speed * tB;
				newE = E + Elsie.speed * tB;
				a ++;
			}else {
				newB = B + Bessie.speed * tE;
				newE = E + Elsie.speed * tE;
				b ++;
			}
			B = newB;
			E = newE;
			if(best !=  ((B < E) ? -1:1)) {
				//System.out.println("Diff: "+best+" "+ ((B < E) ? -1:1));
				best = (B < E) ? -1:1;
				ans ++;
			}
			lastTime = Integer.min(Elsie.time, Bessie.time);
			//System.out.println(B+" "+E);
		}
		pw.println(ans);
		pw.close();
	}
	

}
class Seg{
	int time, speed;
	public Seg(int time, int speed) {
		this.time = time;
		this.speed = speed;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Seg [time=");
		builder.append(time);
		builder.append(", speed=");
		builder.append(speed);
		builder.append("]");
		return builder.toString();
	}
}
