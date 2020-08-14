import java.io.*;
import java.util.*;

// Generate Constants
// javscript code:
//let targetString = "EFHI";"{"+([!(targetString.indexOf("A") < 0),!(targetString.indexOf("B") < 0),!(targetString.indexOf("C") < 0),!(targetString.indexOf("D") < 0),!(targetString.indexOf("E") < 0),!(targetString.indexOf("F") < 0),!(targetString.indexOf("G") < 0),!(targetString.indexOf("H") < 0),! (targetString.indexOf("I") < 0)].join(","))+"}";
public class clocks {
	static boolean[][] ref = 
		  { { true, true, false, true, true, false, false, false, false }, // 1
			{ true, true, true, false, false, false, false, false, false }, //2
			{ false, true, true, false, true, true, false, false, false }, // 3
			{ true, false, false, true, false, false, true, false, false },// 4
			{ false, true, false, true, true, true, false, true, false },  // 5
			{ false, false, true, false, false, true, false, false, true },// 6
			{ false, false, false, true, true, false, true, true, false }, // 7
			{ false, false, false, false, false, false, true, true, true },// 8
			{ false, false, false, false, true, true, false, true, true } };//9
	static List<Integer> bestpath = new ArrayList<>();
	public static int sum(int[] arr) {
		int sum = 0;
		for(int i = 0; i < arr.length; i ++) {
			sum += arr[i];
		}
		return sum;
	}
	static int[][] map;
	public static void search(int ind, List<Integer> path) {
		boolean works = true;
		for(int i =0 ;i < map.length; i ++) {
			for(int j = 0; j < map[i].length; j ++) {
				if(map[i][j]!=3) {
					works = false;
					break;
				}
			}
			if(!works) {
				break;
			}
		}
		if(works) {
			path.sort(null);
			//System.out.println("Working");
			if(bestpath.size() > path.size() || bestpath.size() == 0) {
				bestpath = new ArrayList<>(path);
			}else if(bestpath.size() == path.size()) {
				for(int i = 0; i < path.size(); i ++) {
					if(bestpath.get(i) > path.size()) {
						bestpath = new ArrayList<>(path);
						break;
					}
				}
				if(path.size() == 0){

				}
			}
		}
		if(ind == ref.length*3 || path.size() == ref.length*3) {
			
			//System.out.println(path+": "+Arrays.deepToString(map));
			return;
		}
		path.add(-(ind%9+1));
		path.add(-(ind%9+1));
		path.add(-(ind%9+1));
		int row = (int) Math.floor(ind/3);
		int col = ind % 3;
		search(ind+1, path);
		path.remove(path.size() - 1);
		path.remove(path.size() - 1);
		path.remove(path.size() - 1);
		path.add(ind % 9 + 1);
		path.add(-(ind % 9 + 1));
		path.add(-(ind % 9 + 1));
		for(int i = 0; i < ref[ind].length; i ++) {
			int r,c;
			r = (int) Math.floor(i/ 3);
			c = i % 3;
			if(ref[ind][i]) {
				map[r][c] = (4 + map[r][c] + 1)%4;
			}
		}
		search(ind+1, path);
		path.remove(path.size() - 1);
		path.add(ind % 9 + 1);
		for(int i = 0; i < ref[ind].length; i ++) {
			int r,c;
			r = (int) Math.floor(i/ 3);
			c = i % 3;
			if(ref[ind][i]) {
				map[r][c] = (4 + map[r][c] + 1)%4;
			}
		}
		search(ind+1, path);
		path.remove(path.size() - 1);
		path.remove(path.size() - 1);
		path.add(ind % 9 + 1);
		path.add(ind % 9 + 1);
		for(int i = 0; i < ref[ind].length; i ++) {
			int r,c;
			r = (int) Math.floor(i/ 3);
			c = i % 3;
			if(ref[ind][i]) {
				map[r][c] = (4 + map[r][c] + 1)%4;
			}
		}
		search(ind+1, path);
		for(int i = 0; i < ref[ind].length; i ++) {
			int r,c;
			r = (int) Math.floor(i/ 3);
			c = i % 3;
			if(ref[ind][i]) {
				map[r][c] = (8 + map[r][c] - 3)%4;
			}
		}
		path.remove(path.size() - 1);
		path.remove(path.size() - 1);
		path.remove(path.size() - 1);
	}
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		map = new int[3][3];
		StringTokenizer st = new StringTokenizer(f.readLine());
		map[0][0] = Integer.parseInt(st.nextToken())/3 - 1;
		map[0][1] = Integer.parseInt(st.nextToken())/3 - 1;
		map[0][2] = Integer.parseInt(st.nextToken())/3 - 1;
		st = new StringTokenizer(f.readLine());
		map[1][0] = Integer.parseInt(st.nextToken())/3 - 1;
		map[1][1] = Integer.parseInt(st.nextToken())/3 - 1;
		map[1][2] = Integer.parseInt(st.nextToken())/3 - 1;
		st = new StringTokenizer(f.readLine());
		map[2][0] = Integer.parseInt(st.nextToken())/3 - 1;
		map[2][1] = Integer.parseInt(st.nextToken())/3 - 1;
		map[2][2] = Integer.parseInt(st.nextToken())/3 - 1;
		search(0, new ArrayList<>());
		//pw.println(bestpath);
		bestpath.sort(null);
		for(int move: bestpath){
			if(move > 0){
				pw.print(move+" ");
			}
		}
		pw.println();
		pw.close();
	}
}
