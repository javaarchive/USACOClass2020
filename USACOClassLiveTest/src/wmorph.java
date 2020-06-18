import java.io.*;
import java.util.*;
public class wmorph {
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("pathfindin")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("pathfind.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		Set<String> visited = new HashSet<>();
		Set<String> valid = new HashSet<>();
		String start = f.readLine();
		String end = f.readLine();
		String line = f.readLine();
		while(!(line == null || line.equals("stopitnow"))) {
			if(line.length() == end.length()) {
			valid.add(line.toLowerCase());
			}
			line = f.readLine();
		}
		valid.add(start);
		valid.add(end);
		Queue<Attempt> options = new LinkedList<>();
		options.add(new Attempt(start,0));
		/*
		 * S - String length
		 * 
		 */
		while(!options.isEmpty()) {
			Attempt a = options.poll();
			String seq = a.c;
			visited.add(seq.toString());
			//System.out.println(a.c);
			if(seq.equals(end)) {
				pw.println(a.depth);
				//System.out.println(a.depth);
				break;
			}
			// O(26s^2)
			for(char c = 'a'; c <= 'z'; c ++) {
				
				for(int j = 0; j < seq.length(); j ++) {
				StringBuilder testsubj = new StringBuilder(seq);
				testsubj.setCharAt(j, c);
				//                         O(S)
				if(valid.contains(testsubj.toString()) && !visited.contains(testsubj.toString())) {
					options.add(new Attempt(testsubj.toString(), a.depth + 1));
				}
				}
			}
		}
		pw.close();
		f.close();
	}
}
class Attempt{
	String c;
	int depth;
	public Attempt(String c, int depth) {
		this.c = c;
		this.depth = depth;
	}
}
