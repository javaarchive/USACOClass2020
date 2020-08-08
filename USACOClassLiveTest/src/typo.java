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
		char[] line = f.readLine().toCharArray();
		int N = line.length;
		int left = 0;
		int right = 0;
		int goodLeft = 0;
		int goodRight = 0;
		boolean curNegative = false;
		boolean useLeft = false;
		if(N % 2 == 0){
		for(int i = 0; i < N; i ++){
			t += ((line[i] == '(') ? 1:-1);
		}
		int mode = t;
		t = 0;
		for(int i = 0; i < N; i ++){
			t += ((line[i] == '(') ? 1:-1);
			if(((line[i] == '(') ? 1:-1) == 1){
				left ++;
			}else{
				right ++;
			}
			if(t >= 2 && mode > 0){
				useLeft = true;
				goodLeft = left;
				//break;
				mode = 0;
			}
			if(t < 0 && mode < 0){
				goodRight = right;
				//curNegative = true;
				break;
			}else{
				//curNegative = false;
			}
			//System.out.println(t);
		}
		//System.out.println(right+" "+left);
		//System.out.println(goodRight + " " + goodLeft);
		out = (goodRight);
		if(useLeft){
			out += (left - goodLeft) + 1;
		}
	}
		//int sol = 0;
		
		pw.println(out);
		pw.close();
		f.close();
	}

}
