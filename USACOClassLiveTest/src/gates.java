import java.io.*;
import java.util.*;
public class gates {
	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("flyingcow.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("flyingcow.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int[][] grid = new int[4001][4001];
        int N = Integer.parseInt(f.readLine());
        String line = f.readLine();
        int posX = 1001, posY = 1001;
        //grid[posX][posY] = ;
        int ans = 0;
        //boolean lastIntersected = false;
        //System.out.println("\u001B[31m");
        Place p = new Place(0,0);
        Set<Place> visited = new HashSet<>();
        int lastX, lastY;
        for(int i = 0 ; i < N; i ++){
            char c = line.charAt(i);
            lastX = posX;
            lastY = posY;
            int type = 0;
            switch(c){
                case 'N':
                    posY ++;
                    posY ++;
                    type = 1;
                    break;
                case 'S':
                    posY --;
                    posY --;
                    type = 1;
                    break;
                case 'W':
                    posX --;
                    posX --;
                    type = 2;
                    break;
                case 'E':
                    posX ++;
                    posX ++;
                    type = 2;
                    break;
            }
            int midX = (lastX+posX)/2, midY =  (posY+lastY)/2;
            if(grid[midX][midY] != 0 && grid[midX][midY] != type){
                p.setCoord(midX, midY);
                if(!visited.contains(p)){
                    ans ++;
                    visited.add(new Place(p.x, p.y)); // Clone
                    //lastIntersected = true;
                }
            }else{
                //lastIntersected = false;
        }
            
            //grid[lastX][lastY] = type;
            grid[midX][midY] = type;
            //grid[posX][posY] = type;
        }
        //grid[1001][1001] = false;
        /*for(int i = 996; i < 1110; i ++){
            for(int j = 999; j < 1100; j ++){
                System.out.print(((grid[i][j])?"\u001B[33m":"\u001B[37m")+(grid[i][j] ? 1 : 0 )+"\u001B[0m");
            }
            System.out.println();
        }*/
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