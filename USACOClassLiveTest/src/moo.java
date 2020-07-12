import java.io.*;
import java.util.*;
//moomooomoomoooomoomooomoomooooomoomooomoomoooomoomooomoomoooooomoomooomoomoooomoomooomoomooooomoomooomoomoooomoomooomoomooooooomoomooomoomoooomoomooomo
//01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678900123456789012345678901234567890123456789012345678901234567890
//          111111111122222222223333333333444444444455555555556
public class moo {
	static int pos = 0;
	static int targetPos = -1;
	static int total;
	static int[] table = new int[250];
	static boolean isM = false;
	static void recur(int start,int N){
		if(N < 0){return;}
		//System.out.println("Recur("+start+","+N+")");
		int a,b,c;
		// Ranges start--a,a--b.b--c
		a = start + terms(N);
		b = a + N + 4;
		c = b + terms(N);
		//System.out.println(start+" "+a+" "+b+" "+c);
		if(start < targetPos && targetPos < a){
			recur(start, N-1);
		}else if(a < targetPos && targetPos < b){
			//recur(a, N-1);
		}else if(b < targetPos && targetPos < c){
			recur(b, N-1);
		}else{
			isM = true;
		}
	}
	static int terms(int N){
		// will not do without caching table
		if(N < 0){
			return 0;
		}
		if(table[N] > 0){
			return table[N];
		}else{
			int ans = (int) Math.pow(2, N+3) - 5 - N;
			table[N] = ans;
			return ans;
		}
	}
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                                    new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		targetPos = Integer.parseInt(f.readLine()) - 1;
		int i;
		for(i = 0; i < 250; i ++){
			if(terms(i) > targetPos){
				break;
			}
		}
		recur(0, i-1);
		System.out.println(isM?"m":"o");
	}

}