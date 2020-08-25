import java.io.*;
import java.util.*;

public class triangles {
    public static void main(String[] args) throws IOException{
        String inputfile = "triangles.in";
        if(args.length > 0){
            //System.out.println(Arrays.toString(args));
            inputfile = args[0];
        }
        BufferedReader f = new BufferedReader(new FileReader(inputfile));
        int N = Integer.parseInt(f.readLine());
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cereal.out")));
        TreeMap<Integer, List<Point>> mapx = new TreeMap<>();
        TreeMap<Integer, List<Point>> mapy = new TreeMap<>();
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
                    return Integer.compare(o1.x, o2.x);
                }

            });
        }
        //int range = Integer.min(mapx.keySet().size(), mapy.keySet().size());
        Integer[] xvals = mapx.keySet().toArray(new Integer[mapx.keySet().size()]);
        int count = 0;
        int firstx = xvals[0];
        int bsum = 0;
        for(int i = 1; i < xvals.length; i ++){
            bsum += xvals[i] - firstx;
        }
        for(int xval: xvals){
            //System.out.println(xval);
            
            int yaxissum = 0;
            int firsty = mapx.get(xval).get(0).y;
            for(Point yp: mapx.get(xval)){
                yaxissum += yp.y - firsty;
            }
            System.out.println(yaxissum + " "+ bsum);
            count ++;
            bsum -= xval - firstx;
        }
    }
}
class Point{
    int x, y;
    boolean disabled = false;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}