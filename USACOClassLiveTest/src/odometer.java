import java.io.*;
import java.util.*;
public class odometer{
        public static String repeat(String s, int times){
            String out = "";
            for(int i = 0 ;i < times; i ++){
                out = out + s;
            }
            return out;
        }
    	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("cownomics.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("cownomics.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(f.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        //List<Integer> intrestings = new ArrayList<>();
        int maxdigits = Long.toString(B).length();
        //boolean breakout = false;
        int ans = 0;
        for(int i = 2; i < (maxdigits); i ++){
            for(int fill = 0; fill < 10; fill ++){
                String filled = repeat(Integer.toString(fill), i);
                for(int replace = 0; replace < 10; replace ++){
                    for(int rpos = 0; rpos <= i; rpos ++){
                        if(rpos == 0 && replace == 0){
                            continue;
                        }
                        if(fill == 0 && rpos != 0){
                            continue;
                        }
                        if(replace == fill){
                            continue;
                        }
                        long num = Long.parseLong(filled.substring(0, rpos) + Integer.toString(replace) + filled.substring(rpos, i));
                        //System.out.println(num);
                        /*if(num >= B){
                            breakout = true;
                            break;
                        }*/
                        if(A <= num && num <= B){
                            //System.out.println(""+num);
                            ans ++;
                        }else{
                            //System.out.println("FAIL!: "+num);
                        }
                        
                    }
                    //if(breakout){break;}
                }
                //if(breakout){break;}
            }
            //if(breakout){break;}
        }
        pw.println(ans);
        pw.close();
		f.close();
	}

}
