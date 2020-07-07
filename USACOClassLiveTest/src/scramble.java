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
        final BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        final PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        // PrintWriter pw = new PrintWriter(new BufferedWriter(new
        // FileWriter("reduce.out")));
        final int N = Integer.parseInt(f.readLine());
        final List<ScrambledCow> cows = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            final String line = f.readLine();
            cows.add(new ScrambledCow(line, i));
        }
        cows.sort(new Comparator<ScrambledCow>() {
            @Override
            public int compare(final ScrambledCow o1, final ScrambledCow o2) {
                return sortString(o1.str, 1).compareTo(sortString(o2.str, 1));
            }
        });
        List<ScrambledCow> smallcows = new ArrayList<>(cows);
        System.out.println(cows);
        for(int i = 0; i < N; i ++){
            cows.get(i).minpos = i + 1;
        }
        cows.sort(new Comparator<ScrambledCow>() {
            @Override
            public int compare(final ScrambledCow o1, final ScrambledCow o2) {
                return sortString(o1.str, -1).compareTo(sortString(o2.str, -1));
            }
        });
        List<ScrambledCow> bigcows = new ArrayList<>(cows);
        cows.sort(new Comparator<ScrambledCow>() {
            @Override
            public int compare(final ScrambledCow o1, final ScrambledCow o2) {
                return sortString(o1.str, -1).compareTo(sortString(o2.str, -1));
            }
        });
        System.out.println(cows);
        for(int i = 0; i < N; i ++){
            cows.get(i).maxpos = i + 1;
        }
        cows.sort(new Comparator<ScrambledCow>() {
            @Override
            public int compare(final ScrambledCow o1, final ScrambledCow o2) {
                return Integer.compare(o1.origIndex, o2.origIndex);
            }

        });
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