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
        List<List<Integer>> adjlist = new ArrayList<>(N);
        int lastNode = 1;
        //adjlist.add(new ArrayList<>());
        adjlist.add(new ArrayList<>());
        grid[posX][posY] = 1;
        int nodeCount = 0;
        for(int i = 0; i < N; i ++){
            char c = line.charAt(i);
            //int prevX = posX, prevY = posY;
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
                nodeCount ++;
                //System.out.println("No node for "+i);
                if(lastNode >= adjlist.size()){
                    adjlist.add(new ArrayList<>());
                }
                grid[posX][posY] = adjlist.size();
                adjlist.get(lastNode).add(grid[posX][posY]);
                lastNode = grid[posX][posY];
            }else{
                if(lastNode >= adjlist.size()){
                    adjlist.add(new ArrayList<>());
                }
                adjlist.get(lastNode).add(grid[posX][posY]);
                lastNode = grid[posX][posY];
            }
        }
        int edgeCount = 0;
        for(int i = 0; i < adjlist.size(); i ++){
            edgeCount += adjlist.get(i).size();
        }
        int node = grid[posX][posY];
        adjlist.add(new ArrayList<>()); // Last node touches nothing
        /*
         for(int i = 996; i < 1050; i ++){
            for(int j = 999; j < 1050; j ++){
                System.out.print(((grid[i][j] > 0)?"\u001B[33m":"\u001B[37m")+Integer.toString(grid[i][j]).substring(0,1)+"\u001B[0m");
            }
            System.out.println();
        }*/
        //System.out.println(adjlist);
        pw.println(edgeCount - (nodeCount) + 2 - 1);
        f.close();
        pw.close();

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
