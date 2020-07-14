import java.io.*;
import java.util.*;
public class badhair{

	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("cownomics.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(f.readLine());
		int heightPos = -1;
		int bestHeight = -1;
		long ans = 0;
		Stack<HairyCow> cows = new Stack<HairyCow>();
		for(int i = 0; i < N; i ++){
			int height = Integer.parseInt(f.readLine());
			while(!cows.isEmpty()){
				HairyCow c = cows.peek();
				if(c.height <= height){
					cows.pop();
				}else{
					break;
				}
			}
			//System.out.println(cows);
			ans += cows.size();
			cows.push(new HairyCow(i, height));
		}
		//ans += N - heightPos - 1;
		pw.println(ans);
		pw.close();
		
	}

}
class HairyCow{
	int pos, height;
	public HairyCow(int pos, int height){
		this.pos = pos;
		this.height = height;
	}
	public String toString(){
		return "Cow(^"+this.height+" pos: "+this.pos+");";
	}
}