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
        List<List<Integer>> adjlist = new ArrayList<>();
        int lastNode = 0;
        adjlist.add(new ArrayList<>());
        for(int i = 0; i < N; i ++){
            char c = line.charAt(i);
            int prevX = posX, prevY = posY;
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
            int node = grid[posX][posY];
            if(node == 0){
                if(lastNode >= adjlist.size()){
                    adjlist.add(new ArrayList<>());
                }
                grid[posX][posY] = adjlist.size();
                adjlist.get(lastNode).add(grid[posX][posY]);
                lastNode = grid[posX][posY];
            }else{
                lastNode = grid[posX][posY];
            }
        }
        System.out.println(adjlist);
        f.close();
        pw.println();

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
