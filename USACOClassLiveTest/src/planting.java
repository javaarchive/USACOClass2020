import java.util.*;
import java.io.*;
public class planting {
    public static void main(String[] args) throws IOException {
        // IO
        // new FileReader("minesweeper.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("minesweeper.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int N = Integer.parseInt(f.readLine());
        List<Rect> rects = new ArrayList<>(N);
        List<Endpoint> eps = new ArrayList<Endpoint>(2*N);
        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(f.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            Rect r = new Rect(x1, y1, x2, y2);
            rects.add(r);
            Endpoint a = new Endpoint(x1, y1, (x1 < x2) ? -1:1, i);
            Endpoint b = new Endpoint(x2, y2, (x1 < x2) ? 1:-1, i);
            eps.add(a);
            eps.add(b);
        }
        eps.sort(new Comparator<Endpoint>(){

            @Override
            public int compare(Endpoint o1, Endpoint o2) {
                // TODO Auto-generated method stub
                return Integer.compare(o1.x, o2.x);
            }
        });
        //System.out.println(eps);
        TreeSet<Endpoint> yvalues = new TreeSet<>();
        boolean first = true;
        Endpoint last = new Endpoint(-1,-1,-1,-1);
        int ans = 0;
        for(Endpoint ep: eps){
            /*System.out.println(yvalues);
            for(Endpoint covering: yvalues){
                Rect r = rects.get(covering.id);
                System.out.println(r.x1 + " " + r.y1 + " " + r.x2 + " " + r.y2);
            }*/
            if(yvalues.size() > 0){
                if(!first){
                    int distance = ep.x - last.x;
                    //System.out.println("xvalues " + last.x + " "+ep.x);
                    int miny = Integer.MAX_VALUE, maxy = Integer.MIN_VALUE;
                    List<Integer> endpoints2 = new ArrayList<>();
                    for(Endpoint covering: yvalues){
                        Rect r = rects.get(covering.id);
                        
                        int y1 = Integer.min(r.y1, r.y2) + 1; // Anti -0
                        endpoints2.add(-y1);
                        
                        
                        int y2 = Integer.max(r.y1, r.y2) + 1; // Anti -0
                        endpoints2.add(y2);
                        
                        
                       
                    }
                    endpoints2.sort(new Comparator<Integer>(){
                        @Override
                        public int compare(Integer o1, Integer o2) {
                            // TODO Auto-generated method stub
                            return Integer.compare((o1 > 0) ? o1:-o1, (o2 > 0) ? o2:-o2);
                        }
                    });
                    int covercount = 0;
                    if(endpoints2.size() <= 0){
                        continue;
                    }
                    int last2 = Math.abs(endpoints2.get(0));
                    int ysum = 0;
                    //System.out.println("Calculating y sum for "+ep.x);
                    //System.out.println(endpoints2);
                    for(Integer yvalue: endpoints2){
                        if(yvalue < 0){
                            covercount ++;
                        }
                       
                        int absyvalue = Math.abs(yvalue);
                        //System.out.println(absyvalue+" "+covercount);
                        if(covercount >= 1){
                            //System.out.println("S: "+(absyvalue - last2));
                            ysum += absyvalue - last2;
                        }
                        last2 = absyvalue;
                         if(yvalue > 0){
                            covercount --;
                        }
                    }
                    //System.out.println("Range " + miny + " " + maxy + " adding "+((maxy - miny) * distance));
                    ans += ysum * distance;
                }
            }
            if(ep.type == -1){
                yvalues.add(ep);
            }else{
                yvalues.remove(ep);
            }
            last = ep;
            first = false;
        }
        pw.println(ans);
        pw.close();
    }
}
class Rect{
    int x1, y1, x2, y2;
    public Rect(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}
class Endpoint implements Comparable<Endpoint>{
    int x, y, type, id;
    public Endpoint(int x, int y, int type, int id){
        this.x = x;
        this.y = y;
        this.type = type;
        this.id = id;
    }

    @Override
    public int compareTo(Endpoint o) {
        // TODO Auto-generated method stub
        return Integer.compare(this.id, o.id);
    }

    @Override
    public String toString() {
        return "Endpoint [id=" + id + ", type=" + type + ", x=" + x + ", y=" + y + "]";
    }
}