import java.io.*;
import java.util.*;
public class hps {
    public static int best(int[] arr){
        System.out.println("Finding best of "+Arrays.toString(arr));
        return Integer.max(arr[0], Integer.max(arr[1],arr[2]));
    }
    public static int[] sub(int[] top, int[] bottom){
        //System.out.println(Arrays.toString(top));
        //System.out.println(Arrays.toString(bottom));
        int[] out = new int[3];
        out[0] = top[0] - bottom[0];
        out[1] = top[1] - bottom[1];
        out[2] = top[2] - bottom[2];
        return out;
    }
    public static void main(String[] args) throws IOException{
         // IO
         String inputfile = "hps.in";
         if (args.length > 0) {
             inputfile = args[0];
         }
         BufferedReader f = new BufferedReader(new FileReader(inputfile));
         // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
         PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
         StringTokenizer st = new StringTokenizer(f.readLine());
         int N = Integer.parseInt(st.nextToken());
         int K = Integer.parseInt(st.nextToken());
         int[][] ps = new int[N + 1][3];
         int h = 0, p = 0, s = 0;
         for(int i = 0; i < N; i ++){
            char type = f.readLine().charAt(0);
            switch(type){
                case 'H':
                    h ++;
                    break;
                case 'P':
                    p ++;
                    break;
                case 'S':
                    s ++;
                    break;
            }
            ps[i + 1][0] = h;
            ps[i + 1][1] = p;
            ps[i + 1][2] = s;
         }
         Set<Integer> divPlaces = new TreeSet<>();
         int lower = 0;
         int upper = N;
         Integer[] dividers = new Integer[K];
         //System.out.println(Arrays.deepToString(ps));
         divPlaces.add(N);
         int bestSum = 0;
         // K changes = K dividers
         for(int i = 0; i < K; i ++){
             System.out.println("======= K: "+i+" =======");
            // Check Divider best
            dividers = new Integer[divPlaces.size()];
            divPlaces.toArray(dividers);
            if(divPlaces.size() > 0){
                upper = dividers[0];
            }
            int upplace = 0;
            int bestPos = -1;
            bestSum = 0;
            for(int j = 0; j < N; j ++){
                if(upper < j){
                    lower = upper;
                    upper = dividers[++upplace];
                }
                System.out.println(lower+" "+upper+" "+j);
                //System.out.println("Right part " + best(sub(ps[upper], ps[j])));
                //System.out.println("Left part " + best(sub(ps[j], ps[lower])));
                System.out.println(best(sub(ps[N],ps[upper])) + " " + best(sub(ps[upper], ps[j])) + " " + best(sub(ps[j], ps[lower])) + " " + best(sub(ps[lower], ps[0])));
                int sum = best(sub(ps[N],ps[upper])) + best(sub(ps[upper], ps[j])) + best(sub(ps[j], ps[lower])) +  best(sub(ps[lower], ps[0]));
                System.out.println("Sum "+sum);
                if(sum > bestSum){
                    if(divPlaces.contains(j)){
                        continue;
                    }
                    bestPos = j;
                    bestSum = sum;
                }
            }
            System.out.println("Added divider at "+bestPos);
            divPlaces.add(bestPos + 1);
         }
         
         pw.println(bestSum);
         pw.close();
    }
}
