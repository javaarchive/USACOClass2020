import java.io.*;
import java.util.*;
public class unlock {
    public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("flyingcow.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("flyingcow.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int a,b,c;
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        BlockState A = new BlockState(a);
        BlockState B = new BlockState(b);
        BlockState C = new BlockState(c);
        for(int i = 0; i < a; i ++){
            int x,y;
            st = new StringTokenizer(f.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            A.add(x, y);
        }
        for(int i = 0; i < b; i ++){
            int x,y;
            st = new StringTokenizer(f.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            B.add(x, y);
        }
        for(int i = 0; i < c; i ++){
            int x,y;
            st = new StringTokenizer(f.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            C.add(x, y);
        }
        int[] dx = {0,0,-1,1};
        int[] dy = {1,-1,0,0};
        Queue<State> squeue = new LinkedList<>();
        squeue.add(new State(B,C, 0));
        Set<State> bVisited = new HashSet<>();
        Set<State> cVisited = new HashSet<>();
        while(!squeue.isEmpty()){
            //System.out.println(squeue);
            State s = squeue.poll();
            if(!(s.B.intersectsWith(s.C) && s.B.intersectsWith(A) && s.B.intersectsWith(A))){
                pw.println(s.moves);
                break;
            }
            for(int i = 0; i < 4; i ++){
                BlockState newBlock = B.clone();
                newBlock.add(dx[i], dy[i]);
                State ns = new State(newBlock,C, s.moves + 1);
                if(bVisited.contains(s)){
                    break;
                }
                bVisited.add(ns);
                squeue.add(ns);
            }
            for(int i = 0; i < 4; i ++){
                BlockState newBlock = C.clone();
                newBlock.add(dx[i], dy[i]);
                State ns = new State(C,newBlock, s.moves + 1);
                if(cVisited.contains(ns)){
                    break;
                }
                cVisited.add(ns);
                squeue.add(ns);
            }
        }
    }
}
class BlockState{
    int[] blockxs;
    int[] blockys;
    int x = 0;
    int y = 0;
    int nextBlock;
    int cx = Integer.MAX_VALUE,cy = Integer.MAX_VALUE,bx = Integer.MIN_VALUE,by = Integer.MIN_VALUE;
    public BlockState(int N){
        blockxs = new int[N];
        blockys = new int[N];
        nextBlock = 0;
    }
    public void add(int x, int y){
        blockxs[nextBlock] = x;
        blockys[nextBlock] = y;
        cx = Integer.max(bx, x);
        cy = Integer.max(by, y);
        bx = Integer.min(cx, x);
        by = Integer.min(cy, y);
        nextBlock ++;
    }
    public boolean intersectsWith(BlockState bs){
        if((this.bx <= bs.cx && bs.cx <= this.cx) || (this.bx <= bs.bx && bs.bx <= this.cx)){
            if((this.by <= bs.cy && bs.cy <= this.cy) || (this.by <= bs.by && bs.by <= this.cy)){
                return true;
            }
        }
        return false;
    }
    public BlockState clone(){
        BlockState bs = new BlockState(this.blockxs.length);
        bs.blockxs = Arrays.copyOf(this.blockxs, this.blockxs.length);
        bs.blockys = Arrays.copyOf(this.blockys, this.blockys.length);
        bs.nextBlock = this.nextBlock;
        return bs;
    }
    public void addAll(int x, int y){
        for(int i = 0; i < this.blockxs.length; i ++){
            this.blockxs[i] = this.blockxs[i] + x;
            this.blockys[i] = this.blockys[i] + y;
        }
        this.x += x;
        this.y += y;
        bx += x;
        by += y;
        cx += x;
        cy += y;
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
        if (!(obj instanceof BlockState))
            return false;
        BlockState other = (BlockState) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
    
}
class State{
    BlockState B,C;
    int moves = 0;
    public State(BlockState B, BlockState C, int moves){
        this.B = B;
        this.C = C;
        this.moves = moves;
    }
}