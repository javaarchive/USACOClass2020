import java.util.*;
import java.io.*;

public class planting{
    public static void main(String[] args) throws IOException {
        // IO
        // new FileReader("minesweeper.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("minesweeper.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int ans = 0;
        int N = Integer.parseInt(f.readLine());
        List<Integer> xList = new ArrayList<>(2*N);
        List<YCoord> yList = new ArrayList<>(2*N);
        int[] arrx1 = new int[N];
        int[] arrx2 = new int[N];
        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(f.readLine());
            int xx1 = Integer.parseInt(st.nextToken());
            int yy1 = Integer.parseInt(st.nextToken());
            int xx2 = Integer.parseInt(st.nextToken());
            int yy2 = Integer.parseInt(st.nextToken());
            int x1 = Integer.min(xx1, xx2);
            int x2 = Integer.max(xx1, xx2);
            int y1 = Integer.min(yy1, yy2);
            int y2 = Integer.max(yy1, yy2);
            xList.add(x1);
            xList.add(x2);
            arrx1[i] = x1;
            arrx2[i] = x2;
            yList.add(new YCoord(y1, -1));
            yList.add(new YCoord(y2, 1));
        }
        xList.sort(null); // Natural Ordering, low to high
        //int j = 0;
        int i = 0;
        while(i < 2 * N) {
            int x = xList.get(i);
            if(i != 0 && x == xList.get(i - 1)) {
            	i ++;
            	continue;
            }
            List<YCoord> rel = new ArrayList<>();
            for(int j = 0; j < N; j ++){
                if(arrx1[j] <= x && x < arrx2[j]){
                    rel.add(yList.get(j * 2));
                    rel.add(yList.get(j * 2 + 1));
                }
            }
                if(rel.size() == 0){
                    i ++; // Don't reuse x
                    continue; // SKip
                }
                int cCount = 0;
                int vertical = 0;
                //int lasty = rel.get(0).y;
                //System.out.println(i);
                int count = 0;
                rel.sort(new Comparator<YCoord>() {

					@Override
					public int compare(YCoord arg0, YCoord arg1) {
						// TODO Auto-generated method stub
						return Integer.compare(arg0.y, arg1.y);
					}
                	
                });
                while(count < rel.size()){
                	//System.out.println(count);
                    YCoord bottom = rel.get(count);
                    int by = bottom.y;
                    cCount -= bottom.type;
                    while(cCount > 0){
                        count ++;
                        cCount -= rel.get(count).type;
                    }
                    int ty = rel.get(count).y;
                    //System.out.println("top n bottom "+ty+" "+by);
                    vertical += ty-by;
                    count ++;
                }
            //System.out.println(i);
            i ++;
            while(i < 2*N && xList.get(i) == x){
                i ++;
            }
            //System.out.println("X: "+x);
            //System.out.println(i+" "+(xList.get(i)-x)+" "+vertical);
            ans += vertical * (xList.get(i) - x);
        }
        pw.println(ans);
        pw.close();
    }
}
class YCoord{
    int y;
    int type;
    public YCoord(int y, int type){
        this.y = y;
        this.type = type;
    }
}