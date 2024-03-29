import java.io.*;
import java.util.*;

public class cereal {
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE

    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

    // Underline
    public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
    public static final String RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
    public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
    public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
    public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
    public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

    // Background
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE

    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE    
    public static void main(String[] args) throws IOException {
        // IO
        String inputfile = "cereal.in";
        if(args.length > 0){
            inputfile = args[0];
        }
        BufferedReader f = new BufferedReader(new FileReader(inputfile));
        // BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cereal.out")));
        // PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] cerealTakeId = new int[M + 1];
        // Arrays.fill(a, val);
        List<CerealCow> cows = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(f.readLine());
            int a, b;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            CerealCow cc = new CerealCow(a, b);
            cows.add(cc);
        }
        // int satsified = 0;
        List<Integer> answers = new ArrayList<>();
        // Arrays.fill(cre, val);
        int happyCows = 0;
        for (int i = N - 1; i >= 0; i--) {
            System.out.println(BLUE+"Add "+i+RESET);
            CerealCow cc = cows.get(i);
            //happyCows++;
            cc.taken = 1;
            int kickCow = cerealTakeId[cc.first] - 1;
            happyCows++;
            //int overridedCow = cerealTakeId[cc.first];
            cerealTakeId[cc.first] = i + 1;
            if (kickCow > -1) {
                while (true) {
                    System.out.println(GREEN_BRIGHT+ kickCow+" Kicked "+Arrays.toString(cerealTakeId) +RESET);
                    CerealCow nextCow = cows.get(kickCow);
                    int cowToWrite = kickCow;
                    if (cerealTakeId[nextCow.second] == 0 && nextCow.taken == 1) {
                        cerealTakeId[nextCow.second] = kickCow + 1;
                        //happyCows ++;
                        break;
                    }
                    System.out.println(PURPLE+"kickCow: "+kickCow+" "+nextCow+RESET);
                    if (nextCow.taken == 1) {
                        System.out.println(GREEN+"Moving from "+nextCow.first+" to "+nextCow.second+RESET);
                        nextCow.taken = 2; // Switched to second cow
                        kickCow = cerealTakeId[nextCow.second] - 1;
                        System.out.println("Set the kicked cow to "+kickCow);
                        boolean otherCowLostSpot = true;
                        if(cerealTakeId[nextCow.second] == 0){
                            otherCowLostSpot = false;
                        }else{
                            cows.get(kickCow).taken = 0;
                            happyCows --;
                        }
                        cerealTakeId[nextCow.second] = cowToWrite + 1;
                        if(!otherCowLostSpot){
                            break;
                        }
                    } else if (nextCow.taken == 2) {
                        System.out.println(YELLOW+"Last resort, cow leaving "+nextCow.second+RESET);
                        happyCows--;
                        nextCow.taken = 0;
                        //cerealTakeId[nextCo]
                        break; // Yay! End of the kicking chain
                    }
                    if(cowToWrite == kickCow){
                        System.out.println(RED+"Self kick "+kickCow+RESET);
                        break; // No self kicking
                    }
                }
            }else{
                //happyCows ++;
            }
            System.out.println(CYAN_BOLD_BRIGHT+"Final state for "+i+" "+Arrays.toString(cerealTakeId)+RESET);
            answers.add(happyCows);
        }
        Collections.reverse(answers);
        System.out.println(happyCows+" "+answers);
    }
}

class CerealCow {
    int first, second;
    int taken = 0;

    public CerealCow(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "CerealCow [first=" + first + ", second=" + second + ", taken=" + taken + "]";
    }
}