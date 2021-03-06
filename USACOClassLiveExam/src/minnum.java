import java.io.*;
import java.util.*;
// Recursive Implementation
public class minnum {
	public static List<boolean[]> segements;
	public static short[] password;
	public static int placement(String cur) {
		////System.out.println("Testing "+cur);
		int minimum = Integer.MAX_VALUE;
		if(cur.length() > password.length) {
			//System.out.println("Too long");
			return minimum;
		}
		if(cur.length() == password.length) {
			//System.out.println("Match found");
			return Integer.parseInt(cur,2);
		}
		
		for(int i = 0 ; i < segements.size(); i ++) {
			boolean[] appendString = segements.get(i);
			boolean works = true;
			String s="";
			////System.out.println("Checking "+Arrays.toString(appendString)+" from "+cur);
			if((appendString.length + cur.length()) > password.length) {
				//System.out.println("Too long "+Arrays.toString(appendString));
				continue;
			}
			for(int j = 0; j < appendString.length; j ++) {
				s = s + ((appendString[j]) ? "1" : "0");
				if((((appendString[j]) ? 1 : -1) == password[cur.length()+j]) || (password[cur.length()+j] == 0)) {
					
				}else {
					////System.out.println((cur+s).length()+": "+cur+"-"+s+" is BAD");
					//System.out.println(((appendString[j]) ? 1 : -1)+" and "+password[cur.length()+j]);
					works = false;
					
				}
			}
			if(works) {
				//System.out.println((cur+s).length()+": "+cur+"-"+s+" is good");
				int newmin = placement(cur + s);
				if(newmin < minimum) {
					minimum = newmin;
				}
			}else {
				//System.out.println((cur+s).length()+": "+cur+"-"+s+" is BAD");
			}
		}
		return minimum;
	}
	public static String repeatStr(String s, int times) {
		String out = "";
		for(int i = 0; i < times; i ++) {
			out = out + s;
		}
		return out;
	}
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("minnum.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("minnum.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		String line = f.readLine();
		password= new short[N];
		for(int i = 0; i < N; i ++) {
			char c = line.charAt(i);
			if(c == '?') {
				password[i] = 0;
			}else if(c=='1') {
				password[i] = 1;
			}else if(c=='0') {
				password[i] = -1;
			}
		}
		segements = new ArrayList<>();
		for(int i =0 ; i < M; i ++) {
			line = f.readLine();
			boolean[] segement = new boolean[line.length()];
			for(int j = 0; j < line.length(); j ++) {
				char c = line.charAt(j);
				if(c=='1') {
					segement[j] = true;
				}else if(c=='0') {
					segement[j] = false;
				}
			}
			segements.add(segement);
		}
		for(boolean[] segement:segements) {
			//System.out.println(Arrays.toString(segement));
		}
		int ans = placement("");
		//pw.println("Answer: "+Integer.toString(ans));
		String ansstring = Integer.toString(ans, 2);
		//String ansstring = "123456789";
		if(ans!=Integer.MAX_VALUE) {pw.println(repeatStr("0",N-ansstring.length()) + ansstring);}else {pw.println("NO SOLUTION");}
		pw.close();
		f.close();
	}

}
