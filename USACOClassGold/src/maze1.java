import java.io.*;
import java.util.*;
public class maze1 {
    public static void main(String[] args) throws IOException{
         // IO
        // new FileReader("tractor.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("tractor.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int W, H;
        StringTokenizer st = new StringTokenizer(f.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        int rows = 2 * H + 1;
        int cols = 2 * W + 1;
        int gatesFound = 0;
        int ex1 = -1, ey1 = -1;
        int ex2 = -1, ey2 = -1; 
        int[][] data = new int[rows][cols];
        for(int i = 0 ; i < rows; i ++){
            String line = f.readLine();
            //System.out.println(line+" "+i);
            for(int j = 0; j  < cols; j ++){
                data[i][j] = (line.charAt(j) == ' ')?Integer.MAX_VALUE:-1;
                if(line.charAt(j) == '+' || line.charAt(j) == '|' || line.charAt(j) == '-'){

                }
                if(data[i][j] == Integer.MAX_VALUE){
                if(i == 0 || i == rows - 1){
                    if(gatesFound >= 1){
                        ex2 = i;
                        ey2 = j;
                    }else{
                        ex1 = i;
                        ey1 = j;
                    }
                    gatesFound ++;
                }else if(j == 0 || j == cols - 1){
                    if(gatesFound >= 1){
                        ex2 = i;
                        ey2 = j;
                    }else{
                        ex1 = i;
                        ey1 = j;
                    }
                    gatesFound ++;
                }
            }
            }
        }
        //System.out.println(ex1 + " "+ey1+"    "+ex2+" "+ey2);
        Queue<PQueueOption> pqo = new LinkedList<>();
        pqo.add(new PQueueOption(ex1, ey1, 0));
        pqo.add(new PQueueOption(ex2, ey2, 0));
        data[ex1][ey1] = 0;
        data[ex2][ey2] = 0;
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};
        while(!pqo.isEmpty()){
            PQueueOption opt = pqo.poll();
            for(int i = 0; i < 4; i ++){
                int newx = opt.x + dx[i];
                int newy = opt.y + dy[i];
                //int add = ((newx % 2 == 0) || (newy % 2 == 0))?0:1;
                int add = 1;
                if(0 <= newx && newx < rows && 0 <= newy && newy < cols && (opt.depth + add) < data[newx][newy]){
                    data[newx][newy] = opt.depth + add;
                    pqo.add(new PQueueOption(newx, newy, opt.depth + add));
                }
            }
        }
        int max = 0;
        /*for(int i = 0; i < rows; i ++){
            System.out.println(Arrays.toString(data[i]));
        }*/
        for(int i = 0; i < rows; i ++){
            for(int j = 0; j < cols; j ++){
                
                max = Integer.max(max, data[i][j]);
            }
        }
        pw.println((int) Math.ceil((double) max/2));
        pw.close();

        
    }
}
class PQueueOption{
    int x, y, depth;
    public PQueueOption(int x, int y, int depth){
        this.x = x;
        this.y = y;
        this.depth = depth;
    }
}
