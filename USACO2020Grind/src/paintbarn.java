import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
public class paintbarn {
    public static void main(String[] args) throws IOException{
        // IO
        String inputfile = "paintbarn.in";
        if (args.length > 0) {
            inputfile = args[0];
        }
        BufferedReader f = new BufferedReader(new FileReader(inputfile));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("paintbarn.out")));
        // PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        Map<Integer, List<Endpoint>> mapy = new TreeMap<>();
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 4 3
        // 1 2
        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(f.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            Endpoint e1 = new Endpoint(x1,y1,x2,y2,1);
            Endpoint e2 = new Endpoint(x1,y1,x2,y2,2);
            Endpoint e3 = new Endpoint(x1,y1,x2,y2,3);
            Endpoint e4 = new Endpoint(x1,y1,x2,y2,4);
            if(!mapy.keySet().contains(y1)){
                mapy.put(y1, new ArrayList<>());
            }
            if(!mapy.keySet().contains(y2)){
                mapy.put(y2, new ArrayList<>());
            }
            mapy.get(y1).add(e1);
            mapy.get(y1).add(e2);
            mapy.get(y2).add(e3);
            mapy.get(y2).add(e4);
        }
        Comparator<Endpoint> c = new Comparator<Endpoint>(){

            @Override
            public int compare(Endpoint o1, Endpoint o2) {
                // TODO Auto-generated method stub
                return Integer.compare(o1.getX(), o2.getX());
            }
        };
        for(int y: mapy.keySet()){
            mapy.get(y).sort(c);
        }
        Integer[] keys = (Integer[]) mapy.keySet().toArray();
        for(int i = 0; i < keys.length-1; i ++){
            int yVal1 = keys[i];
            int yVal2 = keys[i + 1];
            int dist = yVal2 - yVal1;
            List<Endpoint> endpoints = mapy.get(yVal1).stream().filter(e -> (e.type == 1 || e.type == 2)).collect(Collectors.toList());
            endpoints.addAll(mapy.get(yVal2).stream().filter(e -> (e.type == 3 || e.type == 4)).collect(Collectors.toList()));
            endpoints.sort(c);
            int covered = 0;
            for(Endpoint e: endpoints){
                if(e.type == 1){
                    if(yVal1 <= e.y1 && e.y1 <= yVal2){
                        covered ++;
                    }
                }

                if(e.type == 2){
                    if(yVal1 <= e.y1 && e.y1 <= yVal2){
                        covered ++;
                    }
                }
            }
        }
    }
}
class Endpoint{
    int x1, y1, x2, y2, type;
    public Endpoint(int x1, int y1, int x2, int y2, int type){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.type = type;
    }
    public int getX(){
        switch(this.type){
            case 2:
            case 3:
                return this.x2;
            case 1:
            case 4:
                return this.x1;
            default:
                return -1;
        }
    }
}