import java.io.*;
import java.util.*;
public class scramble {
    public static int numberize(final char c) {
        return Character.getNumericValue(c) - 10;
    }

    public static String sortString(final String s, final int mult) {
        final Character[] carr = s.chars().mapToObj(c -> (char)c).toArray(Character[]::new); 
        Arrays.sort(carr, new Comparator<Character>() {
            @Override
            public int compare(final Character o1, final Character o2) {
                // TODO Auto-generated method stub
                return mult * Character.compare(o1, o2);
            }
        });
        String str = "";
        for (Character c : carr)
            str += c.toString();
        System.out.println(str);
        return str;        
        //return new StringBuild
    }

    public static void main(final String[] args) throws IOException {
        // IO
        // BufferedReader f = new BufferedReader(new FileReader("reduce.in"));
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        // PrintWriter pw = new PrintWriter(new BufferedWriter(new
        // FileWriter("reduce.out")));
        int N = Integer.parseInt(f.readLine());
        List<ScrambledCow> cows = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            final String line = f.readLine();
            cows.add(new ScrambledCow(line, i));
        }
        List<ScrambledCow> smallcows = new ArrayList<>(cows);
        List<ScrambledCow> bigcows = new ArrayList<>(cows);
        smallcows.sort(new Comparator<ScrambledCow>() {
            @Override
            public int compare(final ScrambledCow o1, final ScrambledCow o2) {
                return sortString(o1.str, 1).compareTo(sortString(o2.str, 1));
            }
        });
        bigcows.sort(new Comparator<ScrambledCow>() {
            @Override
            public int compare(final ScrambledCow o1, final ScrambledCow o2) {
                return sortString(o1.str, -1).compareTo(sortString(o2.str, -1));
            }
        });
        int a = 0, b = 0;
        String sstring = sortString(smallcows.get(0).str, 1);
        String bstring = sortString(bigcows.get(0).str, -1);
        for(int i = 0; i < N; i ++){
            ScrambledCow smallcow = cows.get(i);
            ScrambledCow bigcow = cows.get(i);
            String smallcowstr = sortString(cows.get(i).str,1);
            String bigcowstr = sortString(cows.get(i).str, -1);
            System.out.println("NL");
            while(a < N && sstring.compareTo(bigcowstr) < 0){
                System.out.println("a: "+a);
                a++;
                if(a < N){sstring = sortString(smallcows.get(a).str, 1);}
            }
            while(b < N && bstring.compareTo(smallcowstr) < 0){
                System.out.println("b: "+b);
                b++;
                if(b < N){bstring = sortString(bigcows.get(b).str, -1);}
            }
            smallcow.minpos = a;
            bigcow.maxpos = b;
        }
        for(int i = 0; i < N; i ++){
            ScrambledCow c = cows.get(i);
            pw.println(c.minpos+" "+c.maxpos);
        }
        System.out.println(cows);
        pw.close();
    }
}

class ScrambledCow {
    String str;
    public int minpos = -1, maxpos = -1;
    int origIndex = 0;
    public ScrambledCow(final String s, int origIndex) {
        this.origIndex = origIndex;
        this.str = s;
    }
    public String toString(){
        return "("+this.minpos+"-"+this.maxpos+":"+this.str+")";
    }
}