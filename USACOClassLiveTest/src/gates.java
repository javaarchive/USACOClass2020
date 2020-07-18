import java.io.*;
import java.util.*;
public class gates {
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("flyingcow.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("flyingcow.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int[][] grid = new int[2001][2001];
        int N = Integer.parseInt(f.readLine());
        String line = f.readLine();
        int posX = 1001, posY = 1001;
        grid[posX][posY] = 1;
        int ans = 0;
        boolean lastIntersected = false;
        //System.out.println("\u001B[31m");
        for(int i = 0 ; i < N; i ++){
            char c = line.charAt(i);
            switch(c){
                case 'N':
                    posY ++;
                    break;
                case 'S':
                    posY --;
                    break;
                case 'W':
                    posX --;
                    break;
                case 'E':
                    posX ++;
                    break;
            }
            //System.out.println(posX+" "+posY+" "+c);
            if(grid[posX][posY] == 0){
                grid[posX][posY] = 1;
                lastIntersected = false;
            }else if(grid[posX][posY] == 1){
                if(!lastIntersected){
                    ans ++;
                    lastIntersected = true;
                }
                grid[posX][posY] = grid[posX][posY] + 1;
            }
        }
        for(int i = 960; i < 1110; i ++){
            for(int j = 960; j < 1100; j ++){
                System.out.print(((grid[i][j] > 1)?"\u001B[33m":"\u001B[37m")+grid[i][j]+"\u001B[0m");
            }
            System.out.println();
        }
        pw.println(ans);
		pw.close();
		f.close();
	}
}