import java.io.*;
import java.util.*;
public class checkers {
	public static List<Pos> knights;
	private static int[][] map;
	private static final int[][] dir = new int[][] {{1,1}, {1,-1},{-1,-1}, {-1, 1}};
	public static int N;;
	public static List<Pos> traverse(List<Pos> locs, Pos curPos, int checkers) {
		for(int i = 0; i < dir.length; i ++) {
			if(0 <= (curPos.x + dir[i][0]) && 0 <= (curPos.y + dir[i][1]) && (curPos.y + dir[i][1]) < N &&  (curPos.x + dir[i][0]) < N && map[curPos.x + dir[i][0]][curPos.y + dir[i][1]] == 1) {
				Pos newpos = new Pos(curPos.x + dir[i][0] * 2, curPos.y + dir[i][1] * 2);
				map[curPos.x + dir[i][0]][curPos.y + dir[i][1]] = 5;
				locs.add(newpos);
				if(checkers == 1) {
					return locs;
				}
				locs = traverse(locs, newpos, checkers-1);
				break;
			}
		}
		return locs;
	}
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		knights = new ArrayList<Pos>();
		N = Integer.parseInt(f.readLine());
		map = new int[N][N];
		int totalcheckers = 0;
		for(int i = 0; i < N; i ++) {
			String line = f.readLine();
			for(int j = 0; j < N; j ++) {
				char type = line.charAt(j);
				if(type == '-') {
					map[i][j] = -1;
				}else if(type == '+') {
					map[i][j] = 0;
				}else if(type == 'K') {
					knights.add(new Pos(i,j));
					map[i][j] = 2;
				}else if(type == 'o') {
					map[i][j] = 1;
					totalcheckers++;
				}
			}
		}
		int[][] backuparr;
		backuparr = Arrays.copyOf(map, map.length);
		System.out.println(Arrays.deepToString(map).replaceAll("]", "]\n"));
		List<Pos> locs = new ArrayList<>();
		for(int i = 0; i < knights.size(); i ++) {
			locs.add(knights.get(i));
			map = Arrays.copyOf(backuparr, backuparr.length);
			traverse(locs, knights.get(i), totalcheckers);
			System.out.println(locs);
			if(locs.size() == (totalcheckers)) {
				break;
			}
			locs.clear();
		}
		pw.println(locs);
		pw.close();
	}

}
class Pos{
	int x,y;
	public Pos(int x, int y) {
		this.x =x ;
		this.y =y ;
	}
	public String toString() {
		return "("+this.x+","+this.y+")";
	}
}
