import java.io.*;
import java.util.*;
public class lphone{
		private static int M;
		private static int N;

		public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(f.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int ans = 0;
		int ax = 0, ay = 0, bx = 0, by = 0;
		boolean firstCowFound = false;
		String[] colorizer = {"\033[0;37m","\033[0;31m","\033[0;34m","\033[0;36m"};
		for(int i = 0; i < N; i ++){
			String line = f.readLine();
			for(int j = 0; j < M; j ++){
				char c = line.charAt(j);
				int num = 0;
				if(c == '*'){
					num = 1;
				}
				if(c == 'C'){
					num = 2;
					if(!firstCowFound){
						bx = i;
						by = j;
						//System.out.println("Set bx,by "+bx+" "+by);
						firstCowFound = true;
					}else{
						ax = i;
						ay = j;
						//System.out.println("Set ax,ay "+ax+" "+ay);
					}
				}
				map[i][j] = num;
			}
		}
		Queue<ReflectOpt> movingQueue = new LinkedList<>();
		movingQueue.add(new ReflectOpt(ax, ay, 0, -1));
		//int[] dx = {0,0,-1,1};
		//int[] dy = {-1,1,0,0};
		//int[] dx = {0,1,0,-1};
		//int[] dy = {-1,0,1,0};
		int[] dx = {-1,0,1,0};
		int[] dy = {0,-1,0,1};
		while(!movingQueue.isEmpty()){
			ReflectOpt ro = movingQueue.poll();
			if(ro.x == bx && ro.y == by){
				ans = ro.turns;
				break;
			}
			//System.out.println("We're at "+ro.x+","+ro.y);
			
			for(int i = 0; i < 4; i ++){
				int curX = ro.x;
				int curY = ro.y;
				while(true){
					curX += dx[i];
					curY += dy[i];
					if(!inBounds(curX, curY)){
						break; // You've hit the edge of the map
					}
					if(map[curX][curY] == 1 || map[curX][curY] == 3){
						break; // You've hit a rock
					}
					//System.out.println("ADD: "+curX+" "+curY);
					if(ro.dir != -1){
						if(map[curX][curY] != 2){
							map[curX][curY] = 3;
						}
						movingQueue.add(new ReflectOpt(curX, curY, ro.turns + 1, i));
					}else{
						if(map[curX][curY] != 2){
							map[curX][curY] = 3;
						}
						movingQueue.add(new ReflectOpt(curX, curY, ro.turns, i));
					}
				}
				//System.out.println();
		}
		/*System.out.println();
		for(int i = 0; i < N; i ++){
			for(int j = 0; j < M; j ++){
				System.out.print(colorizer[map[i][j]] + Integer.toString(map[i][j]) + "\033[0m");
			}
			System.out.println();
		}*/
	}
		pw.println(ans);
		pw.close();
		f.close();
		
	}
	public static boolean inBounds(int x, int y){
		return (0 <= x) && (x < N) && (0 <= y) && (y < M); 
	}
}

class ReflectOpt{
	int x,y, turns, dir;
	public ReflectOpt(int x, int y, int turns, int dir){
		this.x = x;
		this.y = y;
		this.turns = turns;
		this.dir = dir;
	}
}