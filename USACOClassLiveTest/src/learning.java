import java.io.*;
import java.util.*;

public class learning {
	static List<Cow> cows;
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("reduce.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("reduce.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int N,A,B;
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		cows = new ArrayList<>();
		cows.add(new Cow(Integer.MIN_VALUE, false));
		for(int i = 0; i < N; i ++) {
			st = new StringTokenizer(f.readLine());
			boolean spots = st.nextToken().equals("S");
			int weight = Integer.parseInt(st.nextToken());
			cows.add(new Cow(weight, spots));
		}
		cows.add(new Cow(Integer.MAX_VALUE, false));
		cows.sort(new Comparator<Cow>() {
			@Override
			public int compare(Cow arg0, Cow arg1) {
				// TODO Auto-generated method stub
				return Integer.compare(arg0.weight, arg1.weight);
			}
		});
		List<Segement> newCows = new ArrayList<>();
		Cow prevCow = new Cow(-1,false), curCow = new Cow(-1, false);
		//                              Compiler Satsifcation
		int prevMid = Integer.MIN_VALUE,mid = 0;
		boolean prevIsInteger = false;
		for(int i = 0; i < N+2; i ++){
			//boolean isEdge = (i == 0) || (i == N+1);
			curCow = cows.get(i);
			if(i == 0){
				prevCow = curCow;
				continue; // SKIP!! We need a pair to process
			}
			//System.out.println("Processing "+i+" cow with weight "+curCow.weight);
			if(i == 1){
				newCows.add(new Segement(prevCow.weight, curCow.weight, curCow.spotted));
				prevMid = curCow.weight;
			}else if(i == N+1){
				Segement newSeg = new Segement(prevMid, curCow.weight, prevCow.spotted);
				if(prevIsInteger && prevCow.spotted == true){
					newSeg.MID = true;
				}
				newCows.add(newSeg);
			}else{
				double tempMid = (curCow.weight + prevCow.weight)/2.0;
				mid = (int) Math.floor(tempMid);
				Segement newSeg = new Segement(prevMid, mid, prevCow.spotted);
				//System.out.println("Conditions "+(prevIsInteger)+" "+(prevCow.spotted == false)+" "+(curCow.spotted == true));
				
				if(prevIsInteger && prevCow.spotted == true){
					newSeg.MID = true;
				}
				if(mid == tempMid && prevCow.spotted == false)
					prevIsInteger = true;
				else
				prevIsInteger = false;

				//System.out.println("mid "+(mid)+" "+"tempMid "+ tempMid);
				newCows.add(newSeg);
				prevMid = mid;
			}
			prevCow = curCow;
		}
		boolean inrange = false;
		int spotcount = 0;
		//System.out.println(newCows);
		for(Segement seg: newCows){
			int start = seg.start, end = seg.end;
			int calcStart = start + 1,calcEnd = end;
			if(B < start){
				break;
			}
			if(A < start && !inrange){
				inrange = true;
			}
			if(start <= A && A < end){
				calcStart = A;
				inrange = true;
			}
			if(start <= B && B <= end){
				calcEnd = B;
				if(calcStart > calcEnd){
				//	calcStart = B;
				}
				if(seg.spotted){
					if(seg.MID){
						spotcount ++;
					}
					spotcount += (calcEnd-calcStart) + 1;
				}
				inrange = false;
			}
			//System.out.println(start+" "+end);
			//System.out.println("Calculation: "+calcStart+" "+calcEnd+" spotted: "+seg.spotted+" MID: "+seg.MID);
			if(seg.spotted && inrange){
				if(seg.MID && start <= A){
					spotcount ++;
				}
				spotcount += (calcEnd-calcStart) + 1;
			}
			//System.out.println("Current spots count is "+spotcount);
		}
		//spotcount ++; // Add end
		////System.out.println(cows);
		pw.println(spotcount);
		pw.close();
	}
}
class Cow{
	int weight;
	boolean spotted;
	public Cow(int weight, boolean spots) {
		this.spotted = spots;
		this.weight = weight; 
	}
	public String toString() {
		return (this.spotted?"S":"P") + this.weight;
	}
}
class Segement{
	int start, end;
	boolean spotted;
	boolean MID;
	public Segement(int start, int end, boolean spots) {
		this.spotted = spots;
		this.start = start;
		this.end = end;
		this.MID = false;
	}
	public Segement(int start, int end, boolean spots, boolean mid) {
		this.spotted = spots;
		this.start = start;
		this.end = end;
		this.MID = mid;
	}
	public String toString() {
		return "Segement("+start+", "+end+" : SPOTTED = "+this.spotted+", MID = "+this.MID+")";
	}
}