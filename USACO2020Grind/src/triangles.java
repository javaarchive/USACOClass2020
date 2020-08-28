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
        int ans = 0;
        List<Integer> xvals = new ArrayList<>(mapx.keySet());
        List<Integer> yvals = new ArrayList<>(mapy.keySet());
        // Layers of PS
        List<int[]> ps1x = new ArrayList<>();
        List<int[]> ps1y = new ArrayList<>();
        List<int[]> ps2x = new ArrayList<>();
        List<int[]> ps2y = new ArrayList<>();
        int sum = 0;
        for(int i = 0; i < xvals.size(); i ++){
            int xval = xvals.get(i);
            List<Point> ylist = mapx.get(xval);
            int[] ps = new int[N];
            sum = 0;
            int initialY = ylist.get(0).y;
            for(int j = 0 ; j < ylist.size(); j ++){
                sum += ylist.get(j).y;
            }
        }
        pw.println(ans);
        pw.close();
    }
}
class Point{
    int x, y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}