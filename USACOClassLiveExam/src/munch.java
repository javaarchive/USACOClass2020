import java.io.*;
import java.util.*;

public class munch {
	public static void main(String[] args) throws IOException{
		try {
		// IO
		//                                    new FileReader("munch.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("munch.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int R,C;
		StringTokenizer st = new StringTokenizer(f.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int[][] grid = new int[R][C];
		int cowx=-1,cowy=-1;
		//System.out.println(grid.length+" "+grid[0].length);
		for(int i = 0; i < R; i ++) {
			String line = f.readLine();
			for(int j= 0; j < C; j ++) {
				char c = line.charAt(j);
				if(c == '*') {
					grid[i][j] = Integer.MIN_VALUE;
				}else if(c=='C') {
					grid[i][j] = Integer.MAX_VALUE;
					cowx = i;
					cowy = j;
				}else {
					//System.out.println(i+" "+j);
					grid[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		Queue<Pos> nextPositions = new LinkedList<Pos>();
		nextPositions.add(new Pos(0,0, 0));
		int movex, movey, minvalue;
		int count=0;
		//Set<Pos> visited = new HashSet<>();
		while(!nextPositions.isEmpty()) {
			count++;
			Pos p = nextPositions.poll();
			/*if(visited.contains(p)) {
				continue;
			}
			visited.add(p);*/
			grid[p.x][p.y] = p.value;
			minvalue = p.value+1;
			movex = p.x+1;
			movey = p.y;
			if(0 <= movex && movex < R && 0 <= movey && movey < C && grid[movex][movey] > minvalue) {
				grid[movex][movey] = minvalue;
				nextPositions.add(new Pos(movex, movey, minvalue));
			}
			movex = p.x-1;
			movey = p.y;
			if(0 <= movex && movex < R && 0 <= movey && movey < C && grid[movex][movey] > minvalue) {
				grid[movex][movey] = minvalue;
				nextPositions.add(new Pos(movex, movey, minvalue));
			}
			movex = p.x;
			movey = p.y+1;
			if(0 <= movex && movex < R && 0 <= movey && movey < C && grid[movex][movey] > minvalue) {
				grid[movex][movey] = minvalue;
				nextPositions.add(new Pos(movex, movey, minvalue));
			}
			movex = p.x;
			movey = p.y-1;
			if(0 <= movex && movex < R && 0 <= movey && movey < C && grid[movex][movey] > minvalue) {
				grid[movex][movey] = minvalue;
				nextPositions.add(new Pos(movex, movey, minvalue));
			}
			if(count > 30000000) {
				//System.out.println(Arrays.deepToString(grid).replaceAll("]", "]\n").replaceAll(Integer.toString(Integer.MAX_VALUE), "#"));
				for(int i = 0 ; i < grid.length; i ++) {
					for(int j = 0; j < grid[i].length; j ++) {
						if(grid[i][j]==Integer.MAX_VALUE) {
							System.out.print("#");
						}else if(grid[i][j]==-1) {
							System.out.print("0");
						}else {
							System.out.print("+");
						}
					}
					System.out.println();
				}
				break;
			}
		}
		//System.out.println(Arrays.deepToString(grid).replaceAll("]", "]\n"));
		pw.println(grid[cowx][cowy]);
		pw.close();
		f.close();
		}catch(Exception ex) {
			System.out.println("code fault");
		}
	}

}
class Pos{
	int x,y,value;
	public Pos(int x,int y, int value) {
		this.value = value;
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "{"+this.x+", "+this.y+"}";
	}
	@Override
	public boolean equals(Object o) {
		if(o instanceof Pos) {
			Pos p = (Pos) o;
			return (p.x == this.x) && (p.y == this.y);
		}else {
			return false;
		}
	}
}
