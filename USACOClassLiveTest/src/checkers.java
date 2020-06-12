import java.io.*;
import java.util.*;
public class checkers {
	public static List<Pos> knights;
	private static int[][] map;
	private static final int[][] dir = new int[][] {{1,1}, {1,-1},{-1,-1}, {-1, 1}};
	public static int N;;
	public static List<Pos> traverse(List<Pos> using, List<List<Pos>> killingmethods, int ops) {
		for(int i = 0; i < killingmethods.size(); i ++) {
			for(int j = 0; j < killingmethods.get(i).size(); j ++) {
				Pos inc = killingmethods.get(i).get(j);
				Pos knight = knights.get(i);
				Pos finalPos = new Pos(inc.x + knight.x, inc.y + knight.y);
				if(map[finalPos.x][finalPos.y] == 1) {
					List<List<Pos>> modifiedkm = new ArrayList<List<Pos>>(killingmethods);
					List<Pos> km = new ArrayList<Pos>();
					modifiedkm.set(j, km);
					for(int k = 0; k < dir.length; k ++) {
						if(0 < (knight.x + dir[i][0]*2) && (knight.x + dir[i][0]*2) < N && 0 < (knight.y + dir[i][1]*2) &&  (knight.y + dir[i][1]*2) < N ) {
							if(map[knight.x  + dir[i][0]][knight.y  + dir[i][1]] == 1) {
								km.add(new Pos(dir[i][0], dir[i][1]));
							}
						}
					}
					List<Pos> newusing = new ArrayList<>(using);
					newusing.add(knight);
					if(ops == 0) {
						return newusing;
					}
					return traverse(newusing, modifiedkm, ops-1);
				}
			}
		}
		return using;
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
		
		List<List<Pos>> killingmethods = new ArrayList<>();
		for(int i = 0; i < killingmethods.size(); i ++) {
			List<Pos> km = new ArrayList<Pos>();
			killingmethods.add(km);
			Pos knightPos = knights.get(i);
			for(int j = 0; j < dir.length; j ++) {
				if(0 < (knightPos.x + dir[i][0]*2) && (knightPos.x + dir[i][0]*2) < N && 0 < (knightPos.y + dir[i][1]*2) &&  (knightPos.y + dir[i][1]*2) < N ) {
					if(map[knightPos.x  + dir[i][0]][knightPos.y  + dir[i][1]] == 1) {
						km.add(new Pos(dir[i][0], dir[i][1]));
					}
				}
			}
		}
		System.out.println(Arrays.deepToString(map).replaceAll("]", "]\n"));
		System.out.println(killingmethods.size());
		List<Pos> using = new ArrayList<>();
		pw.println(traverse(new ArrayList<>(using),new ArrayList<>(killingmethods), totalcheckers));
		pw.println(using);
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
