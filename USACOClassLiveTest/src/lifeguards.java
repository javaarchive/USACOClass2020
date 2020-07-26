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
        Interval[][] lookup = new Interval[2][N];
        for(int i = 0 ; i < N; i ++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            int a = Integer.parseInt(st.nextToken()),b = Integer.parseInt(st.nextToken());
            Interval il1 = new Interval(a, b, 1, i);
            Interval il2 = new Interval(a, b, 2, i);
            lookup[0][i] = il1;
            lookup[1][i] = il2;
            inters.add(il1);
            inters.add(il2);
        }
        inters.sort(new Comparator<Interval>(){
            @Override
            public int compare(Interval o1, Interval o2) {
                // TODO Auto-generated method stub
                int a = (o1.type == 1) ? o1.start : o1.end;
                int b = (o2.type == 1) ? o2.start : o2.end;
                return Integer.compare(a, b);
            }
        });
        int curCount = 0;
        int lastTime = inters.get(0).start;
        int lastID = 0;
        Set<Integer> cows = new HashSet<>();
        int totalTime = 0;
        for(int i = 0; i < 2*N; i ++){
            Interval il = inters.get(i);
            int time = (il.type == 1) ? il.start: il.end;
            if(curCount == 1){
                //System.out.println("Last id"+lastID);
                int selectedID = cows.toArray(new Integer[1])[0];
                //System.out.println("Calc: "+time+" "+lastTime);
                //System.out.println(time- lastTime+" id: "+selectedID+" type: "+il.type);
                lookup[0][selectedID].aloneTime += time- lastTime;
                lookup[1][selectedID].aloneTime += time- lastTime;
            }
            if(il.type == 1){
                cows.add(il.id);
                curCount ++;
            }else{
                curCount --;
                cows.remove(il.id);
            }
            //System.out.println(curCount);
            lastTime = time;
            lastID = il.id;
        }
        //total = 0;
        Arrays.sort(lookup[0],new Comparator<Interval>(){

            @Override
            public int compare(Interval o1, Interval o2) {
                // TODO Auto-generated method stub
                return Integer.compare(o1.aloneTime, o2.aloneTime);
            }

        });
        Arrays.sort(lookup[1],new Comparator<Interval>(){

            @Override
            public int compare(Interval o1, Interval o2) {
                // TODO Auto-generated method stub
                return Integer.compare(o1.aloneTime, o2.aloneTime);
            }

        });
        //System.out.println(Arrays.toString(lookup[0]));
        //System.out.println("Removing "+lookup[1][0]);
        inters.remove(Collections.binarySearch(inters, lookup[0][0]));
        inters.remove(Collections.binarySearch(inters, lookup[1][0]));
        for(int i = 0; i < inters.size()-1; i ++){
            Interval i1 = inters.get(i);
            Interval i2 = inters.get(i + 1);
            if(i1.type == 1){
                curCount ++;
            }else{
                curCount --;
            }
            //System.out.println(i1.getTime()+" "+i2.getTime()+" "+curCount);
            if(curCount > 0){
                totalTime += i2.getTime() - i1.getTime();
            }
        }
        //int start = inters.get(0).start;
        //int end = inters.get(inters.size() - 1).end;
        //System.out.println(totalTime+","+lookup[0][0].aloneTime);
        pw.println(totalTime );
        pw.close();
        //System.out.println(inters);
    }
}

class Interval implements Comparable<Interval> {
    int start, end;
    int type = 0;
    int aloneTime = 0;
    int id;

    public Interval(int start, int end, int type, int id) {
        this.start = start;
        this.end = end;
        this.type = type;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Interval [aloneTime=" + aloneTime + ", end=" + end + ", start=" + start + ", type=" + type + "]";
    }

    public int getTime() {
        return (this.type == 1) ? this.start : this.end;
    }

    @Override
    public int compareTo(Interval o) {
        // TODO Auto-generated method stub
        return Integer.compare(this.getTime(), o.getTime());
    }

}