import java.io.*;
import java.util.*;
public class hexagon {
	public static String padLeft(String s, int n) {
		return String.format("%" + n + "s", s);  
	}
	public static int K,H,L;
	public static int countRows(int cols) {
		return (cols <= K) ? (cols + K - 1) : (3 * K - cols - 1);
	}
	public static int getHexagonNumber(int r, int c){
		for(int i = 1; i < c; i ++){
			r += countRows(i);
		}
		return r;
	}
	public static HexyCoords getHexagonCoords(int num){
		int p =1;
		int col; // Searching Column

		// find the column the number resides in
		for(col = 1; col <= 2 * K; col ++){
			if((p + countRows(col)) > num){
				break; // Too much
			}else{
				p += countRows(col);
			}
		}
		return new HexyCoords(num - p + 1, col);
	}
	public static boolean inRange(int r, int c) {
		return (c >= 1) && c <= (2 * K - 1) && (r >= 1) && (r <= countRows (c));
	}
	static HexyCoords BAD = new HexyCoords(-1, -1);
	static final int UP = 1, DOWN = 2, LD = 3, LU = 4, RU = 5, RD = 6;
	public static HexyCoords getNewCoords(HexyCoords curDir, int newDir){
		curDir = new HexyCoords(curDir.r, curDir.c);
		int r = curDir.r, c = curDir.c;
		switch (newDir){
			case UP:
				c = curDir.c;
				r = curDir.r + 1;
				break;
			case DOWN:
				c = curDir.c;
				r = curDir.r - 1;
				break;
			case RU:
				c = curDir.c + 1;
				r = curDir.r + ((curDir.c >= K) ? 0 : 1);
				break;
			case RD:
				c = curDir.c + 1;
				r = curDir.r - ((curDir.c >= K) ? 1 : 0);
				break;
			case LU:
				c = curDir.c - 1;
				r = curDir.r + ((curDir.c > K) ? 1 : 0);
				break;
			case LD:
				c = curDir.c - 1;
				r = curDir.r - ((curDir.c > K) ? 0 : 1);
				break;
		}
		//System.out.println("? "+r+" "+c);
		curDir = new HexyCoords(r, c);
		return inRange(curDir.r, curDir.c) ? curDir : BAD;
	}
	static Set<Integer> hexagonNums = new TreeSet<>();
	static void walkToEdge(HexyCoords cur,int d1, int d2, int d3){
		//System.out.println("Dir: "+d1);
		for(int i = 0; i < L; i ++){
			if(cur.equals(BAD)){
				return;
			}else{
				cur = getNewCoords(cur, d1);
			}
		}
		HexyCoords orig = new HexyCoords(cur.r, cur.c);
		for(int j = 0; j < 2; j ++){
			//System.out.println("L: "+j);
			for(int i = 1; i <= L; i ++){
				//System.out.println(cur);
				if(cur.equals(BAD)){
					break; // Hit end
				}else{
					hexagonNums.add(getHexagonNumber(cur.r, cur.c));
					cur = getNewCoords(cur, (j == 1) ? d3 : d2);
					//System.out.println("c2"+cur);
				}
				
			}
			cur = orig;
		}
	}
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(f.readLine());
		K = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		//int[][] plane = new int[3*K][2*K];
		//int counter = 1;
		HexyCoords hc = getHexagonCoords(H);
		walkToEdge(hc, UP, RD, LD);
		walkToEdge(hc, DOWN, RU, LU);
 		walkToEdge(hc, RU, LU, DOWN);
 		walkToEdge(hc, LU, RU, DOWN);
 		walkToEdge(hc, RD, UP, LD);
 		walkToEdge(hc, LD, UP, RD);
		pw.println(hexagonNums.size());
		for(int num: hexagonNums){
			pw.println(num);
		}
		pw.close();
	}
}
class HexyCoords{
	int r,c;
	public HexyCoords(int r, int c){
		this.r = r;
		this.c = c;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + c;
		result = prime * result + r;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof HexyCoords))
			return false;
		HexyCoords other = (HexyCoords) obj;
		if (c != other.c)
			return false;
		if (r != other.r)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HexyCoords [r=" + r + ", c=" + c + "]";
	}
}