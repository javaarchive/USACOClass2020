import java.io.*;
import java.util.*;

public class wrongdir {

	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		String data = f.readLine();
		int[][] ps = new int[data.length()+2][3];
		int x,y,dir;
		x = 0;
		y = 0;
		dir = 0;
		int[][] dxdy = {
				{0,1},
				{1,0},
				{0,-1},
				{-1, 0}
		};
		ps[0][0] = 0;
		ps[0][1] = 0;
		ps[0][2] = 0;
		for(int i = 0; i <= data.length(); i ++) {
			if(data.length() > i) {
			char c = data.charAt(i);
				if(c == 'F') {
					x = x + dxdy[dir][0];
					y = y + dxdy[dir][1];
				}else if(c == 'R') {
					dir = (dir + 1) % 4;
				}else if(c == 'L') {
					dir = (dir + 3) % 4;
				}
			}
			ps[i+1][0] = x;
			ps[i+1][1] = y;
			ps[i+1][2] = dir;
			//System.out.println(Arrays.toString(ps[i+1]));
		}
		System.out.println(Arrays.deepToString(ps).replaceAll("]", "]\n"));
		Set<Pos2D> fails = new HashSet<>();
		for(int i = 1; i <= data.length(); i ++) {
			System.out.println(i+"th !!!!");
			int bx = ps[i][0];
			int by = ps[i][1];
			int bd = ps[i][2];
			if(data.charAt(i-1) == 'F') {
				int fx, fy, fd;
				int tx, ty, td;
				tx = ps[ps.length-1][0] - ps[i-1][0];
				ty = ps[ps.length-1][1] - ps[i-1][1];
				td = ps[ps.length-1][2] - ps[i-1][2];
				fx = bx - ty;
				fy = by + tx;
				fd = (bd + td + 3) % 4;
				System.out.println(fx+" "+fy);
				fails.add(new Pos2D(fx,fy));
				fx = bx + ty;
				fy = by - tx;
				System.out.println(fx+" "+fy);
				fails.add(new Pos2D(fx,fy));
			}else if(data.charAt(i-1) == 'R') {
				int fx, fy, fd;
				int tx, ty, td;
				tx = ps[ps.length-1][0] - ps[i-1][0];
				ty = ps[ps.length-1][1] - ps[i-1][1];
				td = ps[ps.length-1][2] - ps[i-1][2];
				fx = bx + tx + dxdy[bd][0];
				fy = by + ty + dxdy[bd][1];
				fd = (bd + td + 3) % 4;
				System.out.println(fx+" "+fy);
				fails.add(new Pos2D(fx,fy));
				fx = bx - ty;
				fy = by + tx;
				System.out.println(fx+" "+fy);
				fails.add(new Pos2D(fx,fy));
			}else if(data.charAt(i-1) == 'L') {
				int fx, fy, fd;
				int tx, ty, td;
				tx = ps[ps.length-1][0] - ps[i-1][0];
				ty = ps[ps.length-1][1] - ps[i-1][1];
				td = ps[ps.length-1][2] - ps[i-1][2];
				fx = bx + tx + dxdy[bd][0];
				fy = by + ty + dxdy[bd][1];
				fd = (bd + td + 3) % 4;
				System.out.println(fx+" "+fy);
				fails.add(new Pos2D(fx,fy));
				fx = bx + ty;
				fy = by - tx;
				System.out.println(fx+" "+fy);
				fails.add(new Pos2D(fx,fy));
			}
		}
		pw.println(fails.size());
		System.out.println(fails.size()+" "+fails);
		pw.close();
		f.close();
	}
}
class Pos2D{
	int x,y;
	public Pos2D(int x,int y){
		this.x =x;
		this.y =y;
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
		if (!(obj instanceof Pos2D)) {
			return false;
		}
		Pos2D other = (Pos2D) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pos2D [x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append("]");
		return builder.toString();
	}
	
}