import java.io.*;
import java.util.*;

public class ballet {
    // static byte[][] map = new byte[4002][4002];
    public static int decodeStr(String in) {
        if (in.equals("FL")) {
            return 0;
        } else if (in.equals("FR")) {
            return 1;
        } else if (in.equals("RL")) {
            return 2;
        } else if (in.equals("RR")) {
            return 3;
        }
        return -1;
    }

    public static Hoof pivotAround(Hoof target, Hoof origin) {
        return new Hoof(origin.x - (origin.y - target.y), origin.y + (origin.x - target.x));
    }

    public static void main(String[] args) throws IOException {
        // IO
        // new FileReader("cownomics.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        // new BufferedWriter(new FileWriter("cownomics.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(f.readLine());
        Hoof[] hooves = new Hoof[4];
        int offset = 2000;
        hooves[0] = new Hoof(0 + offset, 1 + offset);
        hooves[1] = new Hoof(1 + offset, 1 + offset);
        hooves[2] = new Hoof(0 + offset, 0 + offset);
        hooves[3] = new Hoof(1 + offset, 0 + offset);
        int maxx = Integer.MIN_VALUE;
        int maxy = Integer.MIN_VALUE;
        int minx = Integer.MAX_VALUE;
        int miny = Integer.MAX_VALUE;
        int turn = 0;
        for (int l = 0; l < 4; l++) {
            maxx = Integer.max(maxx, hooves[l].x);
            minx = Integer.min(maxx, hooves[l].x);
            maxy = Integer.max(miny, hooves[l].y);
            miny = Integer.min(miny, hooves[l].y);
        }
        for (int i = 0; i < N; i++) {
            String instruct = f.readLine();
            String hoof = instruct.substring(0, 2);
            char action = instruct.charAt(2);
            int hoofId = decodeStr(hoof);
            // System.out.println(hoof);
            if (action == 'P') {
                Hoof h = hooves[hoofId];
                for (int j = 0; j < 4; j++) {
                    if (j == hoofId) {
                        continue;
                    }
                    hooves[j] = pivotAround(hooves[j], h);
                }
                for (int j = 0; j < 4; j++) {
                    h = hooves[j];
                    if (turn == 0) {
                        h.m11 = 0;
                        h.m12 = 1;
                        h.m21 = -1;
                        h.m22 = 0;
                    } else if (turn == 1) {
                        h.m11 = -1;
                        h.m12 = 0;
                        h.m21 = 0;
                        h.m22 = -1;
                    } else if (turn == 2) {
                        h.m11 = 0;
                        h.m12 = -1;
                        h.m21 = 1;
                        h.m22 = 0;
                    } else if (turn == 3) {
                        h.m11 = 1;
                        h.m12 = 0;
                        h.m21 = 0;
                        h.m22 = 1;
                    }
                }
                turn++;
                turn = turn % 4;
                // System.out.println(Arrays.toString(hooves));
            } else {
                Hoof h = hooves[hoofId];
                if (action == 'F') {
                    h.move(0, 1);
                } else if (action == 'R') {
                    h.move(1, 0);
                } else if (action == 'B') {
                    h.move(0, -1);
                } else if (action == 'L') {
                    h.move(-1, 0);
                }
                // System.out.println(h);
            }
            /*for (int i2 = 1997; i2 < 2007; i2++) {
                for (int j = 1997; j < 2007; j++) {
                    boolean occupied = false;
                    int foundLoc = -1;
                    for (int k = 0; k < 4; k++) {

                        if (hooves[k].x == i2 && hooves[k].y == j) {
                            occupied = true;
                            foundLoc = k + 1;
                        }
                    }
                    if (occupied) {
                        System.out.print("\033[0;31m" + foundLoc + "\033[0m");
                    } else {
                        System.out.print("0\033[0m");
                    }
                }
                System.out.println();
            }*/
            //System.out.println(Arrays.toString(hooves));
            for (int l = 0; l < 4; l++) {
                maxx = Integer.max(maxx, hooves[l].x);
                minx = Integer.min(minx, hooves[l].x);
                maxy = Integer.max(maxy, hooves[l].y);
                miny = Integer.min(miny, hooves[l].y);
            }
        }
        //System.out.println(minx+" "+miny+" "+maxx+" "+maxy);
        pw.println((maxx - minx + 1) * (maxy - miny + 1));
        pw.close();
    }
}

class Hoof {
    int x, y;
    int m11 = 1, m12 = 0;
    int m21 = 0, m22 = 1;

    public Hoof(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int x, int y) {
        this.x = this.x + (m11 * x + m12 * y);
        this.y = this.y + (m21 * x + m22 * y);
        //System.out.println(this.x + " " + this.y);
    }

    @Override
    public String toString() {
        return "Hoof [x=" + x + ", y=" + y + "]";
    }
}