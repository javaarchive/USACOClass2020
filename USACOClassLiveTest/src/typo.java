import java.io.*;
import java.util.*;
public class typo {
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int t = 0;
		int out = 0;
		int pos = 0;
		while(true) {
			int chartype = f.read(); // One char at a time how magical!
			if(chartype == -1 || ((char) chartype == '\n') || chartype == 13) {
				break;
			}
			
			char c = (char) chartype;
			//System.out.println(c);
			if(c == '(') {
				t ++;
				if(t > 1) {
					//System.out.println("Reverse at "+pos);
					out++;
				}
			}else if(c == ')') {
				t --;
				if(t < 0) {
					System.out.println("Reverse at "+pos);
					out ++;
				}
			}else {
				throw new IOException("What the heck is this INPUT!");
			}
			pos++;
		}
		pw.println(out);
		pw.close();
		f.close();
	}

}
