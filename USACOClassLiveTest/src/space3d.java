import java.io.*;
import java.util.*;
public class space3d {
    public static void main(String[] args) throws IOException{
        // IO
		//                                    new FileReader("space3d.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("space3d.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(f.readLine());
        byte[][][] map = new byte[N][N][N];
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                String line = f.readLine();
                for(int k = 0; k < N; k ++){
                    map[i][j][k] = (byte) ((line.charAt(k) == '*') ? 1 : 0);
                }
            }
        }
        int ans = 0;
        int[] dx = {0,0,0,0,1,-1};
        int[] dy = {0,0,-1,1,0,0};
        int[] dz = {-1,1,0,0,0,0};
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                for(int k = 0; k < N; k ++){
                    
                    if(map[i][j][k] == 1){
                        //System.out.println("BFSing "+i+" "+j+" "+k);
                        ans ++;
                        Queue<bfs3DChoice> nextChoices = new LinkedList<>();
                        nextChoices.add(new bfs3DChoice(i, j, k));
                        map[i][j][k] = 0;
                        while(!nextChoices.isEmpty()){
                            bfs3DChoice b3c = nextChoices.poll();
                            for(int o = 0; o < 6; o ++){
                                int newx = dx[o] + b3c.x;
                                int newy = dy[o] + b3c.y;
                                int newz = dz[o] + b3c.z;
                                if(0 <= newx  && newx < N){
                                    if(0 <= newy && newy < N){
                                        if(0 <= newz && newz < N){
                                            if(map[newx][newy][newz] == 1){
                                                map[newx][newy][newz] = 0;
                                                nextChoices.add(new bfs3DChoice(newx, newy, newz));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        pw.println(ans);
        pw.close();
    }
}
class bfs3DChoice{
    int x,y,z;
    public bfs3DChoice(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
}