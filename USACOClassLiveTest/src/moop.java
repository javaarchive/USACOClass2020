import java.io.*;
import java.util.*;

public class moop {
    public static void main(String[] args) throws IOException {
        // IO
        // new FileReader("moop.in")s
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("moop.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(f.readLine());
        List<Particle> particles = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Particle p = new Particle(x, y);
            particles.add(p);
        }
        particles.sort(new Comparator<Particle>() {

            @Override
            public int compare(Particle o1, Particle o2) {
                // TODO Auto-generated method stub
                int xcompare = Integer.compare(o1.x, o2.x);
                if (xcompare == 0) {
                    return Integer.compare(o1.y, o2.y);
                }
                return xcompare;
            }
        });
        int[] l = new int[N], r = new int[N];
        int miny = particles.get(0).y;
        int maxy = particles.get(N-1).y;
        for (int i = 0; i < N - 1; i++) {
            l[i] = miny;r[N-i-1] = maxy;
            miny = Integer.min(miny, particles.get(i + 1).y);
            maxy = Integer.max(maxy, particles.get(N - i - 2).y);
            
        }    
        int count = 1;
        for (int i = 0; i < N-1; i++) {
            if(l[i] > r[i + 1]){
                count ++;
            }
        }
        pw.println(count);
        pw.close();
    }
}

class Particle {
    int x, y;

    public Particle(int x, int y) {
        this.x = x;
        this.y = y;
    }
}