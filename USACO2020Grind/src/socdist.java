import java.io.*;
import java.util.*;
public class socdist {
	public static void main(String[] args) throws IOException{
        String inputfile = "socdist.in";
        if(args.length > 0){
            //System.out.println(Arrays.toString(args));
            inputfile = args[0];
        }
		BufferedReader f = new BufferedReader(new FileReader(inputfile));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<GrassPatch> patchesOrig = new ArrayList<>(N); 
		for(int i = 0 ; i < M; i ++) {
			st = new StringTokenizer(f.readLine());
			patchesOrig.add(new GrassPatch(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		f.close();
		patchesOrig.sort(null);
		//System.out.println(patchesOrig);
		int low = 0; 
		//          12345
		int high = Integer.MAX_VALUE;
		int mid = -1;
		boolean best = false;
		boolean works = true;
		while(!best) {
			mid = Math.round((low+high)/2);
			// Try it
			works = true;
			Queue<GrassPatch> patches = new LinkedList<>(patchesOrig);
			int lastCowPos = -100000;
			//System.out.println("Trying "+mid);
			for(int i = 0 ; i < N; i ++) {
				int minPos;
				//int allsize = patches.size();
				int chosenPos = -1;
				minPos = lastCowPos + mid;
				while(!patches.isEmpty()) {
					GrassPatch patch = patches.peek();
					//System.out.println("Minimal Position "+minPos+" patch starts at "+patch.start);
					if(minPos <= patch.start) {
						chosenPos = patch.start;
						break;
					}else if(minPos <= patch.end) {
						chosenPos = minPos;
						break;
					}else {
						patches.poll();
						continue; // Not in this range
					}
				}
				if(chosenPos == -1) {
					works = false;
					//System.out.println("Doesn't work");
					break;
				}else {
					lastCowPos = chosenPos;
				}
				//System.out.println("Last Cow Pos: "+lastCowPos);
			}
			// BS Logic
			if((high - low) == 1) {
				break;
			}
			if(works) {
				low = mid;
			}else {
				high = mid;
			}
			
		}
		//System.out.println(low+" "+high);
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("socdist.out")));
		if(!works) {
			pw.println(high);
		}else {
			pw.println(low);
		}
		pw.close();
		System.out.println("Program ended ");
	}

}
class GrassPatch implements Comparable<GrassPatch>{
	int start, end;
	public GrassPatch(int start, int end) {
		this.start = start;
		this.end = end;
	}
	@Override
	public int compareTo(GrassPatch o) {
		return Integer.compare(this.start, o.start);
	}
	@Override
	public String toString() {
		return this.start+" -- "+this.end;
	}
}