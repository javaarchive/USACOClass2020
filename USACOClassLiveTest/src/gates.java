import java.io.*;
import java.util.*;
public class gates {
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("flyingcow.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("flyingcow.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        boolean[][] grid = new boolean[4001][4001];
        int N = Integer.parseInt(f.readLine());
        String line = f.readLine();
        int posX = 1001, posY = 1001;
        grid[posX][posY] = true;
        int ans = 0;
        boolean lastIntersected = false;
        //System.out.println("\u001B[31m");
        Place p = new Place(0,0);
        Set<Place> visited = new HashSet<>();
        char sc = line.charAt(0);
        switch(sc){
            case 'N':
                posY --;
                break;
            case 'S':
                posY ++;
                break;
            case 'W':
                posX ++;
                break;
            case 'E':
                posX --;
                break;
        }
        for(int i = 0 ; i < N; i ++){
            char c = line.charAt(i);
            switch(c){
                case 'N':
                    posY ++;
                    posY ++;
                    break;
                case 'S':
                    posY --;
                    posY --;
                    break;
                case 'W':
                    posX --;
                    posX --;
                    break;
                case 'E':
                    posX ++;
                    posX ++;
                    break;
            }
            
        }
        //grid[1001][1001] = false;
        for(int i = 996; i < 1110; i ++){
            for(int j = 999; j < 1100; j ++){
                System.out.print(((grid[i][j])?"\u001B[33m":"\u001B[37m")+(grid[i][j] ? 1 : 0 )+"\u001B[0m");
            }
            System.out.println();
        }
        pw.println(ans);
		pw.close();
		f.close();
	}
}
class Place{
    int x,y;
    public Place(int x,int y){
        this.x = x;
        this.y =y;
    }
    public void setCoord(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Place))
            return false;
        Place other = (Place) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
    
}