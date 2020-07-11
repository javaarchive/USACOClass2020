import java.io.*;
import java.util.*;
public class censor {
    public static void main(final String[] args) throws IOException{
        //                                    new FileReader("cownomics.in")
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        //                               new BufferedWriter(new FileWriter("censor.out"))
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        String s = f.readLine();
        int N = s.length();
        String target = f.readLine();
        int K = target.length();
        StringBuilder buffer = new StringBuilder();
        int length = 0;
        int start;
        //String lastK;
        for(int i = 0; i  < N; i ++){
            length ++;
            buffer.append(s.charAt(i));
            //lastK += s.charAt(i);
           //System.out.println(buffer.toString()+" "+buffer.length()+" var: "+length);
            if(length > K){
                //System.out.println(buffer.substring(length-K, length));
                start = length-K;
                if(buffer.substring(start, length).equals(target)){
                    //System.out.println("TARGET DETECTED");
                    buffer = new StringBuilder(buffer.substring(0,start));
                    length -= K;
                }
                //lastK.substring(1);
            }
        }
        f.close();
        pw.println(buffer.toString());
        pw.close();
    }
    
}