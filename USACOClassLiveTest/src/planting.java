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
        for(int i = 0; i < 2*N; i ++){
            int x = xList.get(i);
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
                int lasty = rel.get(0).y;
                for(YCoord cy:rel){
                    if(cy.type == -1){
                        cCount ++;
                    }
                    if(cCount > 0){
                        vertical += cy.y - lasty;
                    }
                    if(cy.type == 1){
                        cCount --;
                    }
                    lasty = cy.y;
                }
            
            
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