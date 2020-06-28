import java.io.*;
import java.util.*;
public class cornmaze {

	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int N, M;
		int ax = -1,ay = -1;
		int bx = -1,by = -1;
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int cowx = -1, cowy = -1;
		Set<Character> alphabet = new TreeSet<Character>();
		String alphabetstr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for(int i = 0; i < alphabetstr.length(); i ++) {
			alphabet.add(alphabetstr.charAt(i));
		}
		Map<Integer, List<Pos2>> portals = new TreeMap<>();
		for(int i = 0; i < N; i ++) {
			String line = f.readLine();
			for(int j = 0; j < M; j ++) {
				char type = line.charAt(j);
				if(type == '#') {
					map[i][j] = 1;
				}else if(type == '.') {
					map[i][j] = 0;
				}else if(alphabet.contains(type)) {
					if(!portals.keySet().contains((int) type)) {
						portals.put((int) type, new ArrayList<>());
					}
					Pos2 newpos = new Pos2(i,j);
					portals.get((int) type).add(newpos);
				}else if(type == '@') {
					cowx = i;
					cowy = i;
				}else if(type == '=') {
					map[i][j] = 2;
				}
			}
		}
		Queue<BFSChoice> bfsoptions = new LinkedList<>();
		BFSChoice bc = new BFSChoice(-1,-1,-1);
		bfsoptions.add(new BFSChoice(cowx,cowy,0));
		while(!bfsoptions.isEmpty()) {
			bc = bfsoptions.poll();
			System.out.println(bc.x + " "+bc.y);
			int testx, testy, type;
			testx = bc.x + 1;
			testy = bc.y;
			if(map[bc.x][bc.y] == 2) {
				break;
			}
			if(testx < N && 0 <= testx && 0<=testy && testy<M){
				type = map[testx][testy];
				if(portals.keySet().contains(type)){
					List<Pos2> endpoints = portals.get(type);
					Pos2 e1 = endpoints.get(0);
					Pos2 e2 = endpoints.get(1);
					if(e1.x == testx && e1.y == testy){
						map[testx][testy] = 1;
						map[e2.x][e2.y] = 1;
						bfsoptions.add(new BFSChoice(e2.x, e2.y, bc.depth + 1));
					}else{
						map[e1.x][e1.y] = 1;
						map[e2.x][e2.y] = 1;
						bfsoptions.add(new BFSChoice(e1.x, e1.y, bc.depth + 1));
					}
				}
				if(type != 1 && type != 2){
					map[testx][testy] = 1;
				}
				bfsoptions.add(new BFSChoice(testx, testy, bc.depth + 1));
			}
			testx = bc.x - 1;
			if(testx < N && 0 <= testx && 0<=testy && testy<M){
				type = map[testx][testy];
				if(portals.keySet().contains(type)){
					List<Pos2> endpoints = portals.get(type);
					Pos2 e1 = endpoints.get(0);
					Pos2 e2 = endpoints.get(1);
					if(e1.x == testx && e1.y == testy){
						map[testx][testy] = 1;
						map[e2.x][e2.y] = 1;
						bfsoptions.add(new BFSChoice(e2.x, e2.y, bc.depth + 1));
					}else{
						map[e1.x][e1.y] = 1;
						map[e2.x][e2.y] = 1;
						bfsoptions.add(new BFSChoice(e1.x, e1.y, bc.depth + 1));
					}
				}
				if(type != 1 && type != 2){
						map[testx][testy] = 1;
				}
				bfsoptions.add(new BFSChoice(testx, testy, bc.depth + 1));
			}
			testx = bc.x;
			testy = bc.y - 1;
			if(testx < N && 0 <= testx && 0<=testy && testy<M){
				type = map[testx][testy];
				if(portals.keySet().contains(type)){
					List<Pos2> endpoints = portals.get(type);
					Pos2 e1 = endpoints.get(0);
					Pos2 e2 = endpoints.get(1);
					if(e1.x == testx && e1.y == testy){
						map[testx][testy] = 1;
						map[e2.x][e2.y] = 1;
						bfsoptions.add(new BFSChoice(e2.x, e2.y, bc.depth + 1));
					}else{
						map[e1.x][e1.y] = 1;
						map[e2.x][e2.y] = 1;
						bfsoptions.add(new BFSChoice(e1.x, e1.y, bc.depth + 1));
					}
					
				}
				if(type != 1 && type != 2){
						map[testx][testy] = 1;
					}
				bfsoptions.add(new BFSChoice(testx, testy, bc.depth + 1));
			}
			testy = bc.y + 1;
			if(testx < N && 0 <= testx && 0<=testy && testy<M){
				type = map[testx][testy];
				if(portals.keySet().contains(type)){
					List<Pos2> endpoints = portals.get(type);
					Pos2 e1 = endpoints.get(0);
					Pos2 e2 = endpoints.get(1);
					if(e1.x == testx && e1.y == testy){
						map[testx][testy] = 1;
						map[e2.x][e2.y] = 1;
						bfsoptions.add(new BFSChoice(e2.x, e2.y, bc.depth + 1));
					}else{
						map[e1.x][e1.y] = 1;
						map[e2.x][e2.y] = 1;
						bfsoptions.add(new BFSChoice(e1.x, e1.y, bc.depth + 1));
					}
					
				}
				if(type != 1 && type != 2){
						map[testx][testy] = 1;
					}
				bfsoptions.add(new BFSChoice(testx, testy, bc.depth + 1));
			}
		}
		pw.println(bc.depth);
		pw.close();
		f.close();
	}

}
class Pos2{
	int x,y;
	public Pos2(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Pos2)) {
			return false;
		}
		Pos2 other = (Pos2) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}
}
class BFSChoice{
	int x,y,depth;
	public BFSChoice(int x, int y, int depth) {
		this.x =x;
		this.y =y;
		this.depth = depth;
	}
}
