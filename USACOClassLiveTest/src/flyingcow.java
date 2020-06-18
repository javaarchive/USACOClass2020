import java.io.*;
import java.util.*;
public class flyingcow {
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("flyingcow.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("flyingcow.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int target = Integer.parseInt(f.readLine());
		Queue<Position> nextPos = new LinkedList<>();
		nextPos.add(new Position(0,1));
		boolean[] visited = new boolean[target+2];
		int best = Integer.MAX_VALUE;
		while(!nextPos.isEmpty()) {
			Position p = nextPos.poll();

			//System.out.println(p.pos);
			if(p.pos * 3 <= target && !visited[p.pos*3]) {
				visited[p.pos*3] = true;
				nextPos.add(new Position(p.moves+1,p.pos*3));
			}
			if(p.pos+1 <= target && !visited[p.pos+1]) {
				visited[p.pos+1] = true;
				nextPos.add(new Position(p.moves+1,p.pos+1));
			}
			if(p.pos-1 > 0&& p.pos-1 <= target && !visited[p.pos-1]) {
				visited[p.pos-1] = true;
				nextPos.add(new Position(p.moves+1,p.pos-1));
			}
			if(p.pos == target) {
				if(p.moves < best) {
					best = p.moves;
				}
			}
		}
		pw.println(best);
		pw.close();
		f.close();
	}
}
class Position{
	int moves;
	int pos;
	public Position(int moves, int pos) {
		this.moves = moves;
		this.pos = pos;
	}
}