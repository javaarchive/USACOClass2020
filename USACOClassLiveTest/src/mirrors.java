import java.io.*;
import java.util.*;

public class mirrors {
    public static void main(String[] args) throws IOException {
        // IO
        // new FileReader("cownomics.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("cownomics.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        List<Mirror> mirrors = new ArrayList<>(N);
        Map<Integer, List<Mirror>> mapx = new TreeMap<>();
        Map<Integer, List<Mirror>> mapy = new TreeMap<>();
        mirrors.add(new Mirror(Integer.MIN_VALUE, Integer.MIN_VALUE, 1, -1));
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            int type = (st.nextToken().charAt(0) == '/') ? 1 : 2;
            Mirror m = new Mirror(x, y, type, i);
            mirrors.add(m);
            if (!mapx.keySet().contains(x)) {
                mapx.put(x, new ArrayList<>());
            }
            if (!mapy.keySet().contains(y)) {
                mapy.put(y, new ArrayList<>());
            }
            mapx.get(x).add(m);
            mapy.get(y).add(m);
        }
        for (int v : mapx.keySet()) {
            mapx.get(v).sort(new Comparator<Mirror>() {

                @Override
                public int compare(Mirror o1, Mirror o2) {
                     // TODO Auto-generated method stub
                     int xcompare = Integer.compare(o1.x, o2.x);
                     if(xcompare == 0){
                         return Integer.compare(o1.y, o2.y);
                     }
                     return xcompare;
                }

            });
        }
        for (int v : mapy.keySet()) {
            mapy.get(v).sort(new Comparator<Mirror>() {

                @Override
                public int compare(Mirror o1, Mirror o2) {
                     // TODO Auto-generated method stub
                     int ycompare = Integer.compare(o1.y, o2.y);
                     if(ycompare == 0){
                         return Integer.compare(o1.x, o2.x);
                     }
                     return ycompare;
                }

            });
        }
        /*
               1
              ^^^
               |
               |
        4 < -- O -- > 2
               |
               |
              ↓ ↓ 
               3
        */
        //          1,2,3,4
        int[] t1 = {2,1,4,3}; // /
        int[] t2 = {4,3,2,1}; // \
        //Mirror first = new Mirror(0,0, -1);
        Mirror first = new Mirror(-1,-1,-1,-1);
        try{
            for(Mirror m : mapy.get(0)){
                if(m.x >= 0){
                    first = m;
                    break;
                }
            }
            if(first.index == -1){
                throw new NullPointerException();
            }
        }catch(NullPointerException npe){
            pw.println("-1"); // farmer john can't see a mirror
            pw.close();
            System.exit(0);
        }
        
        //System.out.println("Selected first mirror "+first);
        boolean foundAns = false;
        for(int i = 0; i < N; i ++){
            Mirror cm = mirrors.get(i);
            System.out.println("Changed mirror "+i+" from "+cm.type+" to "+(3 - cm.type));
            cm.type = 3 - cm.type;
            int dir = 2;
            int pos = -1; 
            Mirror curMirror = first;
            boolean seen[] = new boolean[N];
            boolean works = false;
            if(curMirror.type == 1){
                dir = t1[dir-1];
            }else{
                dir = t2[dir-1];
            }

            while(true){
                System.out.println(curMirror+" direction "+dir);
                if(seen[curMirror.index]){
                    //System.out.println("Already Seen");
                    break;
                }
                seen[curMirror.index] = true;
                //break;
                if(dir % 2 == 0){
                    pos = Collections.binarySearch(mapy.get(curMirror.y), curMirror, new Comparator<Mirror>(){
                        @Override
                        public int compare(Mirror o1, Mirror o2) {
                            // TODO Auto-generated method stub
                            int ycompare = Integer.compare(o1.y, o2.y);
                            if(ycompare == 0){
                                return Integer.compare(o1.x, o2.x);
                            }
                            return ycompare;
                        }
                    });

                }else if(dir % 2 == 1){
                    pos = Collections.binarySearch(mapx.get(curMirror.x), curMirror, new Comparator<Mirror>(){
                        @Override
                        public int compare(Mirror o1, Mirror o2) {
                            // TODO Auto-generated method stub
                            int xcompare = Integer.compare(o1.x, o2.x);
                            if(xcompare == 0){
                                return Integer.compare(o1.y, o2.y);
                            }
                            return xcompare;
                        }
                    });
                }
                int inc = 0;
                if(dir <= 2){
                    inc = 1;
                }else{ 
                    inc = -1;
                }
                boolean useX = !(dir % 2 == 0);
                Mirror nm; // Filler value so compiler doesn't get mad
                //System.out.println(curMirror+ " " + ((dir % 2 == 0) ? mapx.get(curMirror.x).size():mapy.get(curMirror.y).size()));
               // System.out.println(inc);
               //System.out.println("Pos: "+pos+" "+mapx.get(curMirror.x)+" "+mapy.get(curMirror.y));
                if(dir <= 2){
                    if(pos+1 >= ((useX) ? mapx.get(curMirror.x).size():mapy.get(curMirror.y).size())){
                        break;
                    }
                }else{
                    if(pos >= 0){
                        break;
                    }
                }
                if(useX){
                    //System.out.println("X: "+mapx.get(curMirror.x));
                    nm = mapx.get(curMirror.x).get(pos + inc);
                }else{
                    //System.out.println("Y: "+mapy.get(curMirror.y));
                    //System.out.println(curMirror.y+" "+mapy.get(curMirror.y));
                    nm = mapy.get(curMirror.y).get(pos + inc);
                }
                //System.out.println("Posinc: "+(pos+inc));
                if(curMirror.x <= a && nm.x > a && nm.y == b && dir == 2){
                    works = true;
                    break;
                }
                if(curMirror.x >= a && a > nm.x && nm.y == b && dir == 4){
                    works = true;
                    break;
                }
                if(curMirror.y <= b && nm.y > b && nm.x == a && dir == 1){
                    works = true;
                    break;
                }
                if(curMirror.y >= b && b > nm.y && nm.x == a && dir == 3){
                    works = true;
                    break;
                }
                //System.out.println("curm type: "+cm.type);
                if(nm.type == 1){
                    System.out.println("Mapping 1: "+dir+" to "+t1[dir-1] + " new mirror: "+nm);
                    dir = t1[dir - 1];
                }else{
                    System.out.println("Mapping 2: "+dir+" to "+t2[dir-1] + " new mirror: "+nm);
                    dir = t2[dir - 1];
                }
                curMirror = nm;
                //System.out.println("New Mirror "+nm+" "+inc);
            }
            //System.out.println("Before works "+works);
            if(curMirror.x <= a && dir == 2){
                works = true;
            }
            if(curMirror.x >= a && dir == 4){
                works = true;
            }
            if(curMirror.y <= b  && dir == 1){
                works = true;
            }
            if(curMirror.y >= b  && dir == 3){
                works = true;
            }
           // System.out.println(works);
            cm.type = 3 - cm.type;
            if(works){
                pw.println(i);
                foundAns = true;
                break;
            }
        }
        if(!foundAns){
            pw.println("-1"); // Not found
        }
        pw.close();
        
    }
}

class Mirror {
    int x, y, type, index;

    public Mirror(int x, int y, int type, int index) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.index = index;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + index;
        result = prime * result + type;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Mirror))
            return false;
        Mirror other = (Mirror) obj;
        if (index != other.index)
            return false;
        if (type != other.type)
            return false;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Mirror [index=" + index + ", type=" + type + ", x=" + x + ", y=" + y + "]";
    }
}