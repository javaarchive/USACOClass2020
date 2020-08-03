import java.io.*;
import java.util.*;

public class lightson {
    public static boolean[][] map;
    public static boolean[][] visited;
    public static Map<Room, List<Room>> adjlist = new TreeMap<>();
    public static int N;
    public static int M;

    public static boolean range(int i) {
        return (0 <= i) && (i <= N);
    }

    public static int total = 0;

    public static List<Room> adj(int x, int y) {
        List<Room> out = new ArrayList<>();
        if (range(x - 1) && !map[x - 1][y]) {
            out.add(new Room(x - 1, y));
        }
        if (range(x + 1) && !map[x + 1][y]) {
            out.add(new Room(x + 1, y));
        }
        if (range(y - 1) && !map[x][y - 1]) {
            out.add(new Room(x, y - 1));
        }
        if (range(y + 1) && !map[x][y + 1]) {
            out.add(new Room(x, y + 1));
        }
        return out;
    }

    public static boolean nearBright(int x, int y) {
        if (range(x - 1) && map[x - 1][y]) {
            return true;
        }
        if (range(x + 1) && map[x + 1][y]) {
            return true;
        }
        if (range(y - 1) && map[x][y - 1]) {
            return true;
        }
        if (range(y + 1) && map[x][y + 1]) {
            return true;
        }
        return false;
    }

    public static void recur(int x, int y) {
        map[x][y] = true;
        // System.out.println("recur("+x+","+y+")");
        visited[x][y] = true;
        Room r = new Room(x, y);
        
        if (adjlist.keySet().contains(r)) {
            List<Room> connected = adjlist.get(r);
            for (Room room : connected) {
                if (!map[room.x][room.y]) {
                    //total++;
                    // System.out.println("Lit "+room.x+" "+room.y);
                    if (nearBright(room.x, room.y)) {
                        map[room.x][room.y] = true;
                        visited[room.x][room.y] = true;
                        recur(room.x, room.y);
                    }
                }
                map[room.x][room.y] = true;
            }
        }
        // for(int i = 0; i < 2; i ++){
        if (range(x + 1) && map[x + 1][y] && !visited[x + 1][y]) {
            //visited[x + 1][y] = true;
            recur(x + 1, y);
        }
        if (range(x - 1) && map[x - 1][y] && !visited[x - 1][y]) {
            //visited[x - 1][y] = true;
            recur(x - 1, y);
        }
        if (range(y + 1) && map[x][y + 1] && !visited[x][y + 1]) {
            //visited[x][y + 1] = true;
            recur(x, y + 1);
        }
        if (range(y - 1) && map[x][y - 1] && !visited[x][y - 1]) {
            //visited[x][y - 1] = true;
            recur(x, y - 1);
        }
    
        // }
    }

    public static void main(String[] args) throws IOException {
        // IO
        // new FileReader("lightson.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("lightson.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(f.readLine());
            int ax, ay;
            ax = Integer.parseInt(st.nextToken());
            ay = Integer.parseInt(st.nextToken());
            int bx, by;
            bx = Integer.parseInt(st.nextToken());
            by = Integer.parseInt(st.nextToken());
            Room a = new Room(ax, ay);
            Room b = new Room(bx, by);
            if (!adjlist.keySet().contains(a)) {
                adjlist.put(a, new ArrayList<Room>());
            }
            adjlist.get(a).add(b);
        }
        visited[1][1] = true;
        map[1][1] = true;
        recur(1, 1);
        for(int i =0; i < map.length; i ++){
            for(int j = 0; j < map[i].length; j ++){
                if(map[i][j]){
                    total ++;
                }
            }
        }
        pw.println(total);
        pw.close();
    }
}

class Room implements Comparable<Room> {
    int x, y;

    public Room(int x, int y) {
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
        if (!(obj instanceof Room))
            return false;
        Room other = (Room) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }

    @Override
    public int compareTo(Room o) {
        // TODO Auto-generated method stub
        int xcompare = Integer.compare(this.x, o.x);
        if (xcompare == 0) {
            return Integer.compare(this.y, o.y);
        }
        return xcompare;
    }

}