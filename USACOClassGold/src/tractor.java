import java.io.*;
import java.util.*;

public class tractor {
    public static void main(String[] args) throws IOException{
        // IO
        // new FileReader("tractor.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("tractor.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());
        int[][] map = new int[1002][1002];
        for(int i = 0; i < N; i ++){
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = -1;
        }
        Deque<OneZeroBFSOpt> d = new LinkedList<>();
        d.add(new OneZeroBFSOpt(sx, sy, 0));
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};
        while(!d.isEmpty()){
            OneZeroBFSOpt oz = d.poll();
            if(oz.x == 0 && oz.y == 0){
                pw.println(oz.weight);
                break;
            }
            for(int i = 0; i < 4; i ++){
                int newx = oz.x + dx[i];
                int newy = oz.y + dy[i];
                if(0 <= newx && newx < 1002){
                    if(0 <= newy && newy < 1002){
                        if(map[newx][newy] == -1){
                            d.addLast(new OneZeroBFSOpt(newx, newy, oz.weight + 1));
                            map[newx][newy] = -2;
                        }else if(map[newx][newy] >= 0){
                            d.addFirst(new OneZeroBFSOpt(newx, newy, oz.weight));
                            map[newx][newy] = -3;
                        }
                    }
                }
            }
        }
        f.close();
        pw.close();
    }
}

class OneZeroBFSOpt{
    int x, y, weight;
    public OneZeroBFSOpt(int x, int y, int weight){
        this.x = x;
        this.y = y;
        this.weight = weight;
    }
}