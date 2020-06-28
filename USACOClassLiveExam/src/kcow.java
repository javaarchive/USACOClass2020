import java.io.*;
import java.util.*;
public class kcow {
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("kcow.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("kcow.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int[] dx = {1,2,-1,-2,-1,-2, 1, 2  };
		int[] dy = {2,1,-2,-1,2 ,1 ,-2,-1};
		Queue<Jump> jumps = new LinkedList<>();
		int X,Y;
		StringTokenizer st = new StringTokenizer(f.readLine());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		int cowx = -1, cowy = -1; // Computer doesn't like assuming I will define these in the loop
		int hayx = -1, hayy = -1; // Computer doesn't like assuming I will define these in the loop
		int[][] map = new int[Y][X];
		for(int i = 0; i < Y; i ++) {
			String line = f.readLine();
			for(int j = 0; j < X; j ++) {
				char c = line.charAt(j);
				if(c == '.') {
					map[i][j] = 0;
				}else if(c == '*') {
					map[i][j] = 1;
				}else if(c == 'K') {
					map[i][j] = 2;
					cowx = i;
					cowy = j;
				}else if(c == 'H') {
					hayx = i;
					hayy = j;
				}
			}
		}
		//System.out.println(Arrays.deepToString(map).replace("]", "]\n"));
		jumps.add(new Jump(cowx, cowy, 0));
		while(!jumps.isEmpty()) {
			Jump j = jumps.poll();
			//System.out.println(jumps+" "+j.x+" "+j.y);
			if(j.x == hayx && j.y == hayy) {
				pw.println(j.depth);
				break;
			}
			int tcx, tcy;
			for(int i = 0; i < dx.length; i ++){
				tcx = j.x+dx[i];
				tcy = j.y+dy[i];
				//System.out.println((j.x + tcx)+" "+(j.y + tcy));
				if(0 <= tcx && tcx < Y && 0 < tcy && tcy < X && map[tcx][tcy] == 0){
					map[tcx][tcy] = 2;
					jumps.add(new Jump(tcx, tcy, j.depth + 1));
				}
			}
		}
		pw.close();
	}
}
class Jump{
	int x,y, depth;
	public Jump(int x, int y, int depth) {
		this.x = x;
		this.y = y;
		this.depth = depth;
	}
}