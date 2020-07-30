import java.util.*;

public class moregates {
    public static void main(String[] args) throws java.io.IOException {
        try {
            // IO
            // new FileReader("flyingcow.in")
            java.io.BufferedReader f = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
            // new BufferedWriter(new FileWriter("flyingcow.out"))
            java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.OutputStreamWriter(System.out));
            int N = Integer.parseInt(f.readLine());
            String line = f.readLine();
            boolean[][] map = new boolean[2002][2002];
            int curX = 1001;
            int curY = 1001;
            map[curX][curY] = true;
            int minx = Integer.MAX_VALUE, miny = Integer.MAX_VALUE;
            int maxx = Integer.MIN_VALUE, maxy = Integer.MIN_VALUE;
            int[] dx = {0,0,-1,1};
            int[] dy = {-1,1,0,0};
            for(int i = 0; i < N; i ++){
                char c = line.charAt(i);
                switch(c){
                    case 'N':
                        curY ++;
                        break;
                    case 'S':
                        curY --;
                    case 'E':
                        curX ++;
                    case 'W':
                        curX --;
                }
                map[curX][curY] = true;
                minx = Integer.min(curX, minx);
                miny = Integer.min(curY, minx);
                maxx = Integer.max(curX, maxx);
                maxy = Integer.max(curY, maxy);
            }
            /*maxx ++;
            maxy ++;
            minx --;
            miny --;*/
            int ans = 0;
            for(int i = minx; i <= maxx; i ++){
                for(int j = miny; j <= maxy; j ++){
                    if(map[i][j]){
                        continue;
                    }
                    Queue<Integer> qx = new LinkedList<>();
                    Queue<Integer> qy = new LinkedList<>();
                    qx.add(i);
                    qy.add(j);
                    map[i][j] = true;
                    ans ++;
                    //System.out.print("New!");
                    while(!((qx.isEmpty() || qy.isEmpty()))){
                        int cx = qx.poll();
                        int cy = qy.poll();
                        //System.out.println(cx+" "+cy);
                        for(int k = 0 ; k < 4; k ++){
                            int newx = cx + dx[k];
                            int newy = cy + dy[k];
                            if(minx <= newx && newx <= maxx && miny <= newy && newy <= maxy && map[newx][newy] == false){
                                map[newx][newy] = true;
                                qx.add(newx);
                                qy.add(newy);
                            }
                        }
                    }
                }
                //for(int i )
            }
            pw.println(ans - 1); // Gates = regions  -1
            pw.close();
        } catch (java.io.IOException ex) {

        }
    }
}