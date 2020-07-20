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
        //List<List<Integer>> adjlist = new ArrayList<>(N);
        int lastNode = 1;
        //adjlist.add(new ArrayList<>());
        //adjlist.add(new ArrayList<>());
        grid[posX][posY] = 1;
        int nodeCount = 0;
        Set<Connection> connectedEdges = new HashSet<Connection>();
        Set<Place> pos = new HashSet<Place>();
        pos.add(new Place(posX, posY));
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
            pos.add(new Place(posX, posY));
            connectedEdges.add(new Connection(prevX, prevY, posX, posY));
        }
        nodeCount = pos.size();
        int edgeCount = connectedEdges.size();
        /*for(int i = 0; i < adjlist.size(); i ++){
            edgeCount += adjlist.get(i).size();
        }
        int node = grid[posX][posY];
        adjlist.add(new ArrayList<>()); // Last node touches nothing*/

        /*
         for(int i = 996; i < 1050; i ++){
            for(int j = 999; j < 1050; j ++){
                System.out.print(((grid[i][j] > 0)?"\u001B[33m":"\u001B[37m")+Integer.toString(grid[i][j]).substring(0,1)+"\u001B[0m");
            }
            System.out.println();
        }*/
        //System.out.println(adjlist);
        //System.out.println(connectedEdges);
        //System.out.println(pos);
        pw.println(edgeCount - (nodeCount) + 2 - 1);
        f.close();
        pw.close();

	}
}
class Connection{
    int x1,y1,x2,y2;
    public Connection(int x1, int y1, int x2, int y2){
        if(x1 > x2){
            this.x1 = x2;
            this.x2 = x1;
            this.y1 = y2;
            this.y2 = y1;
        }else if(x1 == x2){
            this.x1 = x2;
            this.x2 = x1;
            this.y1 = Integer.min(y1,y2);
            this.y2 = Integer.max(y1,y2);
        }else{
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x1;
        result = prime * result + x2;
        result = prime * result + y1;
        result = prime * result + y2;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Connection))
            return false;
        Connection other = (Connection) obj;
        if (x1 != other.x1)
            return false;
        if (x2 != other.x2)
            return false;
        if (y1 != other.y1)
            return false;
        if (y2 != other.y2)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Connection [x1=" + x1 + ", x2=" + x2 + ", y1=" + y1 + ", y2=" + y2 + "]";
        //return "("+(this.x1-this.x2)+","+(this.y1-this.y2)+")";
    
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

    @Override
    public String toString() {
        return "Place [x=" + x + ", y=" + y + "]";
    }

    
}   
