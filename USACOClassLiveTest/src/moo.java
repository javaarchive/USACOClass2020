import java.io.*;
import java.util.*;
public class moo {
	static int pos = 0;
	static int targetPos = -1;
	public static void S(int level){
		//System.out.println(level);
		if(level >= 1){
			S(level - 1);
			if((pos + level + 2) < targetPos){
				System.out.println("S("+level+") += C"+(level + 2));
				pos += level + 2;
			}else{
				return;
			}
			S(level - 1);
		}else{
			if((pos + level + 2) < targetPos){
				System.out.println("S("+level+") += C"+(level + 2));
				pos += level + 2;
			}
		}
		int add = (int) Math.pow(2,level + 1) - 1; // formula for terms
		//System.out.println("Add "+add+" "+pos);
		if((pos + add) > targetPos){
			//System.out.println("Exit S("+level+")");
			return;
		}
		System.out.println("S("+level+") += "+add);
		//pos += add;
	}
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                                    new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		targetPos = Integer.parseInt(f.readLine());
		S(4);
		System.out.println(pos);
	}

}