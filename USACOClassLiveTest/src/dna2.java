import java.io.*;
import java.util.*;
//moomooomoomoooomoomooomoomooooomoomooomoomoooomoomooomoomoooooomoomooomoomoooomoomooomoomooooomoomooomoomoooomoomooomoomooooooomoomooomoomoooomoomooomo
//01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678900123456789012345678901234567890123456789012345678901234567890
//          111111111122222222223333333333444444444455555555556
public class dna2 {
	public static String merge(String a, String b){
		boolean found = false;
		int cPos = 0;
		int finalPos = -1;
		int finalStart = 0;
		for(int i = 0; i < a.length(); i ++){
			char c = a.charAt(i);
			System.out.println(cPos);
			if(cPos < b.length() && c == b.charAt(cPos)){
				cPos ++;
			}else{
				cPos = 0;
			}
			if(i == a.length()){
				found = true;
				finalPos = cPos;
				finalStart = i - cPos;
			}
		}
		System.out.println(finalStart+" "+finalPos);
		if(!found){return null;}
		StringBuilder sb = new StringBuilder(a);
		sb.append(b.substring(finalStart, b.length()));
		return sb.toString();
	}
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                                    new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		System.out.println(merge("abcdef", "cdefghijklm"));
	}

}