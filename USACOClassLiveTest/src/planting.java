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
            rectList.add(new Rect(x1, y1, x2, y2));
        }
        xList.sort(null); // Natural Ordering, low to high
        int j = 0;
        for(int i = 0; i < 2*N; i ++){
            while(i < 2*N){
                Rect r = rectList.get(j);

            }
        }
        pw.println(ans);
        pw.close();
    }
}
class Ycoord{
    int y;
    int type;
    public Ycoord(int y, int type){
        this.y = y;
        this.type = type;
    }
}