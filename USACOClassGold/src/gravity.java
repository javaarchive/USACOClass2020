import java.io.*;
import java.util.*;

public class gravity {
    public static void main(String[] args) throws IOException {
        // IO
        // new FileReader("tractor.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("tractor.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] data = new int[N][M];
        int sx = -1, sy = -1;
        int fx = -1, fy = -1;
        for (int i = 0; i < N; i++) {
            String line = f.readLine();
            for (int j = 0; j < M; j++) {
                char state = line.charAt(j);
                if (state == 'C') {
                    sx = i;
                    sy = j;
                } else if (state == 'D') {
                    fx = i;
                    fy = j;
                    data[i][j] = 0;
                } else if (state == '#') {
                    data[i][j] = -1;
                } else if (state == '.') {

                }
            }
        }
        //System.out.println("TARGET " + fx + " " + fy);
        Deque<DQBFSOpt> dq = new LinkedList<>();
        int[] dx = { 0, 0 };
        int[] dy = { -1, 1 };
        boolean success = false;
        while (sx < N - 1) {
            if (data[sx + 1][sy] == -1) {
                dq.add(new DQBFSOpt(sx, sy, 0, 0));
                break;
            }
            sx++;
        }
        while (!dq.isEmpty()) {
            DQBFSOpt dopt = dq.poll();
            if(data[dopt.x][dopt.y] == -1){
                continue; // Weird bug
            }
            //System.out.println(dopt);
            if (data[dopt.x][dopt.y] == 0) {
                data[dopt.x][dopt.y] = dopt.gravChanges;
            }
            
            if (dopt.x == fx && dopt.y == fy) {
                pw.println(dopt.gravChanges);
                success = true;
                break;
            }
            boolean shouldEnd = false;
            if(dopt.y == fy){
                for(int i = dopt.x; i >= 0; i --){
                    if(data[i][dopt.y] == -1){
                        break;
                    }
                    if(i == fx){
                        pw.println(dopt.gravChanges + 1);
                        success = true;
                        shouldEnd = true;
                        break;
                    }
                }
                if(shouldEnd){
                    break;
                }
            }
            if(dopt.y == fy){
                for(int i = dopt.x; i < N; i ++){
                    if(data[i][dopt.y] == -1){
                        break;
                    }
                    if(i == fx){
                        pw.println(dopt.gravChanges + 1);
                        success = true;
                        shouldEnd = true;
                        break;
                    }
                }
                if(shouldEnd){
                    break;
                }
            }
            for (int i = 0; i < 2; i++) {
                int newx = dopt.x + dx[i];
                int newy = dopt.y + dy[i];
                /*
                 * if(dopt.curGrav == 0 && i == 3){ continue; } if(dopt.curGrav == 2 && i == 1){
                 * continue; }
                 */
                if(0 <= newx && newx < N && 0 <= newy && newy < M && data[newx][newy] == -1) {
                    continue;
                }
                if (0 <= newy && newy < M) {
                    if (dopt.curGrav == 0) {
                        boolean works = false;
                        for (; newx < N - 1; newx++) {
                            if (data[newx + 1][newy] == -1) {
                                works = true;
                                break;
                            }
                        }
                        if (!works) {
                            continue; // Cow falls into blackness
                        }
                    }
                    if (dopt.curGrav == 2) {
                        //System.out.println("Sim g2");
                        boolean works = false;
                        for (; newx > 0; newx--) {
                            if (data[newx - 1][newy] == -1) {
                                works = true;
                                //System.out.println(works);
                                break;
                            }
                            
                        }
                        if (!works) {
                            continue; // Cow falls into blackness
                        }
                    }
                }
                if (0 <= newx && newx < N && 0 <= newy && newy < M) {
                    //System.out.println(newx+" "+newy);
                    if (data[newx][newy] == 0) {
                        data[newx][newy] = dopt.gravChanges + 1;
                        dq.addFirst(new DQBFSOpt(newx, newy, dopt.gravChanges, dopt.curGrav));
                    }
                }

            }
            if (dopt.curGrav == 0) {
                for (int i = dopt.x; i >= 0; i--) {
                    if (data[i][dopt.y] == -1 && data[i + 1][dopt.y] == 0) {
                        dq.addLast(new DQBFSOpt(i + 1, dopt.y, dopt.gravChanges + 1, 2));
                        break;
                    }
                }
            }
            if (dopt.curGrav == 2) {
                for (int i = dopt.x; i < N; i++) {
                    if (data[i][dopt.y] == -1 && data[i - 1][dopt.y] == 0) {
                        dq.addLast(new DQBFSOpt(i - 1, dopt.y, dopt.gravChanges + 1, 0));
                        break;
                    }
                }
            }
        }
        if (!success) {
            pw.println("-1");
        }
        pw.close();
    }
}

class DQBFSOpt {
    int x, y, gravChanges, curGrav;

    public DQBFSOpt(int x, int y, int gc, int curGrav) {
        this.x = x;
        this.y = y;
        this.gravChanges = gc;
        this.curGrav = curGrav;
    }

    @Override
    public String toString() {
        return "DQBFSOpt [curGrav=" + curGrav + ", gravChanges=" + gravChanges + ", x=" + x + ", y=" + y + "]";
    }
}
