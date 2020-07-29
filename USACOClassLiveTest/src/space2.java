import java.util.*;

public class space2 {
	public static void main(String[] args){
		try{
		// IO
		// new FileReader("cownomics.in")
		java.io.BufferedReader f = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		// new BufferedWriter(new FileWriter("cownomics.out"))
		java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.OutputStreamWriter(System.out));
		int N = Integer.parseInt(f.readLine());
		byte[][] struct = new byte[N][N]; // Save memory
		for (int i = 0; i < N; i++) {
			String line = f.readLine();
			for (int j = 0; j < N; j++) {
				char c = line.charAt(j);
				byte type = (byte) ((c == '.') ? 0 : 1);
				struct[i][j] = type;
			}
		}
		Queue<Integer> moveqx = new LinkedList<>();
		Queue<Integer> moveqy = new LinkedList<>();
		int[] dx = { 0, 0, -1, 1 };
		int[] dy = { 1, -1, 0, 0 };
		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(struct[i][j] == 0){
					continue;
				}
				ans ++;
				moveqx.clear();
				moveqy.clear();
				struct[i][j] = 0;
				moveqx.add(i);
				moveqy.add(j);
				while (!moveqx.isEmpty()) {
					int x = moveqx.poll();
					int y = moveqy.poll();
					for (int k = 0; k < 4; k++) {
						int newx = x + dx[k], newy = y + dy[k];
						if(0 <= newx && newx < N && 0<= newy && newy < N){
							if(struct[newx][newy] == 1){
								struct[newx][newy] = 0;
								moveqx.add(newx);
								moveqy.add(newy);
							}
						}
					}
				}
			}
		}
		pw.println(ans);
		pw.close();
	}catch(java.io.IOException ex){
	
	}
	}
}

class Action {
	int x, y;

	public Action(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
