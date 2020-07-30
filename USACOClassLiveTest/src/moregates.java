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
            boolean[][] map = new boolean[4002][4002];
            int curX = 2001;
            int curY = 2001;
            map[curX][curY] = true;
            int minx = curX, miny = curY;
            int maxx = curX, maxy = curY;
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
                        break;
                    case 'E':
                        curX ++;
                        break;
                    case 'W':
                        curX --;
                        break;
                }
                minx = Integer.min(curX, minx);
                miny = Integer.min(curY, minx);
                maxx = Integer.max(curX, maxx);
                maxy = Integer.max(curY, maxy);
                map[curX][curY] = true;
                switch(c){
                    case 'N':
                        curY ++;
                        break;
                    case 'S':
                        curY --;
                        break;
                    case 'E':
                        curX ++;
                        break;
                    case 'W':
                        curX --;
                        break;
                }
                map[curX][curY] = true;
                //System.out.println(curX + " "+curY);
                minx = Integer.min(curX, minx);
                miny = Integer.min(curY, minx);
                maxx = Integer.max(curX, maxx);
                maxy = Integer.max(curY, maxy);
            }
            for(int i = 0; i < 10; i++){
            maxx ++;
            maxy ++;
            minx --;
            miny --;
            }
           // minx --;
           // miny --;
           //System.out.println(minx+" "+miny+" "+maxx+" "+maxy);
            int ans = 0;
            /*for(int i = minx; i <= maxx; i ++){
                for(int j = miny; j <= maxy; j ++){
                    System.out.print((map[i][j] ? "\033[0;31m":"\033[0;32m") +( map[i][j] ? 1:0)+"\033[0m");
                }
                System.out.println();
            }*/

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