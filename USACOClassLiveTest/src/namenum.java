import java.io.*;
import java.util.*;
public class namenum{
	public static int charToInt(char c) {
		int out = 0;
		switch(c) {
		case 'a':
		case 'b':
		case 'c':
			out = 2;
			break;
		case 'd':
		case 'e':
		case 'f':
			out = 3;
			break;
		case 'g':
		case 'h':
		case 'i':
			out = 4;
			break;
		case 'j':
		case 'k':
		case 'l':
			out = 5;
			break;
		case 'm':
		case 'n':
		case 'o':
			out=6;
			break;
		case 'p':
		case 'r':
		case 's':
			out=7;
			break;
		case 't':
		case 'u':
		case 'v':
			out=8;
			break;
		case 'w':
		case 'x':
		case 'y':
			out = 9;
			break;
	}
		return out;
	}
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("cownomics.out"))
		//PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		String l = f.readLine();
		String line = f.readLine();
		boolean hasFoundOne = false;
		while(line != null && !line.equals("finish")) {
			//System.out.println(line);
			if(line.length() == l.length()) {
				line = line.toLowerCase();
				boolean works = true;
				for(int i = 0; i < line.length(); i ++) {
					if(Integer.toString(charToInt(line.charAt(i))).charAt(0) != l.charAt(i))  {
						//System.out.println(Integer.toString(charToInt(line.charAt(i))).charAt(0)+" does not match "+l.charAt(i));
						works = false;
						break;
					}else {
						
					}
				}
				//System.out.println(works);
				if(works) {
					hasFoundOne = true;
					System.out.println(line.toUpperCase());
				}
			}else {
				//System.out.println("Length does not match");
			}
			line = f.readLine();
		}
		if(!hasFoundOne) {
			System.out.println("NONE");
		}
		f.close();
	}

}
