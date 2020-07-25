import java.io.*;
import java.util.*;
public class lifeguards {
    public static void main(String[] args) throws IOException {
		// IO
		// new FileReader("reduce.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		// new BufferedWriter(new FileWriter("reduce.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(f.readLine());
        List<Interval> inters = new ArrayList<>(N);
        for(int i = 0 ; i < N; i ++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            int a = Integer.parseInt(st.nextToken()),b = Integer.parseInt(st.nextToken());
            Interval il = new Interval(a, b);
            inters.add(il);
        }
        inters.sort(new Comparator<Interval>(){
            @Override
            public int compare(Interval o1, Interval o2) {
                // TODO Auto-generated method stub
                return Integer.compare(o1.start, o2.start);
            }
            
        });
    }
}
class Interval{
    int start, end;
    int coverTime;
    public Interval(int start, int end){
        this.start = start;
        this.end = end;
        this.coverTime = end - start;
    }
    public boolean intersectsWith(Interval il){
        if(this.start <= il.start && this.end <= il.end){
            return true;
        }
        return false;
    }
    public void recalculateCover(Interval il){
        if(this.intersectsWith(il)){
            
        }
    }
}