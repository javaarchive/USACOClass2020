import java.io.*;
import java.util.*;

public class triangles {
    static final int MOD = 1000000000+9;
    public static void main(String[] args) throws IOException{
        String inputfile = "triangles.in";
        if(args.length > 0){
            //System.out.println(Arrays.toString(args));
            inputfile = args[0];
        }
        BufferedReader f = new BufferedReader(new FileReader(inputfile));
        int N = Integer.parseInt(f.readLine());
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));
        TreeMap<Integer, List<Point>> mapx = new TreeMap<>();
        TreeMap<Integer, List<Point>> mapy = new TreeMap<>();
        List<Point> points = new ArrayList<>();
        for(int i = 0; i < N; i ++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if(!mapx.keySet().contains(x)){
                mapx.put(x, new ArrayList<>());
            }
            if(!mapy.keySet().contains(y)){
                mapy.put(y, new ArrayList<>());
            }
            Point p = new Point(x,y);
            mapx.get(x).add(p);
            mapy.get(y).add(p);
            points.add(p);
        }
        // Sort all
        for(Integer key: mapx.keySet()){
            mapx.get(key).sort(new Comparator<Point>(){

                @Override
                public int compare(Point o1, Point o2) {
                    // TODO Auto-generated method stub
                    return Integer.compare(o1.y, o2.y);
                }

            });
        }
        for(Integer key: mapy.keySet()){
            mapy.get(key).sort(new Comparator<Point>(){
                @Override
                public int compare(Point o1, Point o2) {
                    // TODO Auto-generated method stub
                    int xcompare = Integer.compare(o1.x, o2.x);
                    if(xcompare == 0){
                        return Integer.compare(o1.y, o2.y);
                    }
                    return xcompare;
                }

            });
        }
        for(int i = 0; i < N; i ++){
            Point p = points.get(i);
            int xdist = 0;
            int ydist = 0;
            int xpos = Collections.binarySearch(mapx.get(p.x), p,new Comparator<Point>(){

                @Override
                public int compare(Point o1, Point o2) {
                    // TODO Auto-generated method stub
                    int ycompare = Integer.compare(o1.y, o2.y);
                    if(ycompare == 0){
                        return Integer.compare(o1.x, o2.x);
                    }
                    return ycompare;
                }

            });
            int ypos = Collections.binarySearch(mapy.get(p.y), p, new Comparator<Point>(){
                @Override
                public int compare(Point o1, Point o2) {
                    // TODO Auto-generated method stub
                    return Integer.compare(o1.x, o2.x);
                }

            });
            List<Point> xpoints = mapx.get(xpos);
            List<Point> ypoints = mapy.get(ypos);
            for(int j = xpos; j < xpoints.size(); j ++){
                ydist += xpoints.get(j).y - p.y;
            }
            for(int j = ypos; j < ypoints.size(); j ++){
                xdist += ypoints.get(j).x - p.x;
            }
            System.out.println(xdist + " "+ ydist);
        }
            pw.close();
    }
}
class Point implements Comparable<Point>{
    int x, y;
    boolean disabled = false;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point o) {
        // TODO Auto-generated method stub
        int xcompare = Integer.compare(this.x, o.x);
        if(xcompare == 0){
            return Integer.compare(this.y ,o.y);
        }
        return xcompare;
    }

}