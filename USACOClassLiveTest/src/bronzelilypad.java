import java.io.*;
import java.util.*;
public class bronzelilypad{

	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int N, M, px, py; 
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		px = Integer.parseInt(st.nextToken());
		py = Integer.parseInt(st.nextToken());
		int[] dx = new int[8];
		int[] dy = new int[8];
		int bx = 0;
		int by = 0;
		short[][] map = new short[N][M];
		for(int i = 0; i < N; i ++){
			st = new StringTokenizer(f.readLine());
			for(int j = 0; j < M; j ++){
				int num = Integer.parseInt(st.nextToken());
				if(num == 3){
					bx = i;
					by = j;
				}
				map[i][j] = (short) num;
			}
		}
		dx[0] = px;
		dy[0] = py;
		dx[1] = py;
		dy[1] = px;
		dx[2] = -px;
		dy[2] = -py;
		dx[3] = -py;
		dy[3] = -px;
		dx[4] = -px;
		dy[4] = py;
		dx[5] = -py;
		dy[5] = px;
		dx[6] = px;
		dy[6] = -py;
		dx[7] = py;
		dy[7] = -px;
		//System.out.println("Bessie is at "+bx+" "+by);
		Queue<Leap> leaps = new LinkedList<>();
		leaps.add(new Leap(bx, by, 0));
		int ans = 0;
		while(!leaps.isEmpty()){
			Leap l = leaps.poll();
			short value = map[l.x][l.y];
			if(value == 4){
				//System.out.println("GOT ANS");
				ans = l.steps;
				break;
			}
			//System.out.println(l.x+","+l.y);
			for(int i = 0; i < 8; i ++){
				int newx = dx[i] + l.x;
				int newy = dy[i] + l.y;
				//System.out.println("TRY: "+newx+" "+newy);
				//System.out.println(newx + " " + newy);
				if(0 <= newx && newx < N){
					if(0 <= newy && newy < M){
						int val = map[newx][newy];
						if(val == 1){
							map[newx][newy] = 2;

						}else if(val == 4){
							// Do nothing
						}else{
							continue;
						}
						//System.out.println("Add "+newx+" "+newy);
						leaps.add(new Leap(newx, newy, l.steps + 1));
					}else{
						continue;
					}
				}else{
					continue;
				}
			}
			//System.out.println(leaps);
		}
		pw.println(ans);
		pw.close();
	}
}
class Leap{
	int x,y, steps;
	public Leap(int x, int y, int steps){
		this.x = x;
		this.y = y;
		this.steps = steps;
	}

	@Override
	public String toString() {
		return "Leap [steps=" + steps + ", x=" + x + ", y=" + y + "]";
	}
	
}