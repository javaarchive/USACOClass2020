import java.io.*;
import java.util.*;
public class dna2 {
	static String[] seq;
	static int best = Integer.MAX_VALUE;
	static String beststr = "";
	static void permute(String str, String ans) {
		if(ans == null){return;}
		//System.out.println(str+" and "+ans);
		if (str.length() == 0) {
			//System.out.println("FINAL: "+ans);
			if(ans.length() < best){
				best = ans.length();
				beststr = ans;
			}
			return;
		}
		for (int i = 0; i < str.length(); i++) {
			String rest = str.substring(0, i) + str.substring(i + 1);
			char ch = str.charAt(i);
			String newAns = merge(ans,seq[Integer.parseInt(Character.toString(ch))]);
			System.out.println("Merge result of "+ans+" and "+seq[Integer.parseInt(Character.toString(ch))] + " is "+newAns);
			permute(rest, newAns);
		}
	}

	public static String merge(String a, String b) {
		if(a.equals("")){return b;}
		boolean found = false;
		int cPos = 0;
		int finalPos = -1;
		int finalStart = 0;

		for (int i = 0; i < a.length(); i++) {
			char c = a.charAt(i);
			//System.out.println("C:" + cPos);
			if (cPos < b.length() && c == b.charAt(cPos)) {
				cPos++;
			} else {
				cPos = 0;
				if(c == b.charAt(0)){cPos = 1;}
			}
		}
		//System.out.println(cPos);
		if (cPos != 0) {
			found = true;
			finalPos = cPos;
			finalStart = a.length() - cPos;
		}
		//System.out.println(finalStart+" "+finalPos);
		if (!found) {
			//return null;
			return a + b;
			//return (a.length() < b.length()) ? b:a;
		}
		StringBuilder sb = new StringBuilder(a);
		sb.append(b.substring(finalPos, b.length()));
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		// IO
		// new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		// new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		//System.out.println(merge("GATTA", "TAGG"));
		int N = Integer.parseInt(f.readLine());
		seq = new String[N];
		for (int i = 0; i < N; i++) {
			seq[i] = f.readLine();
		}
		//System.out.println("0123456789".substring(0, seq.length));
		permute("0123456789"
		.substring(0, seq.length),"");
		System.out.println(beststr.length());
	}

}