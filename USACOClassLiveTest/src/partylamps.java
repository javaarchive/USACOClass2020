import java.io.*;
import java.util.*;
public class partylamps {
	public static boolean[] scopy(boolean[] arr) {
		// JUST SIMPLY COPY AN ARRAY sorry bennett
		return Arrays.copyOf(arr, arr.length);
	}

	public static void main(String[] args) throws IOException{
		// IO
		//                                    new FileReader("partylamps.in")
		BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
		//                               new BufferedWriter(new FileWriter("partylamps.out"))
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(f.readLine());
		int C = Integer.parseInt(f.readLine());
		boolean[] lampz = new boolean[6];
		//boolean[] finalstate = new boolean[6];
		StringTokenizer st = new StringTokenizer(f.readLine());
		int term = Integer.parseInt(st.nextToken()) - 1;
		List<Integer> on = new ArrayList<>();
		List<Integer> off = new ArrayList<>();
		Arrays.fill(lampz, true);
		while(term != -2) {
			try {
				on.add(term);
				term = Integer.parseInt(st.nextToken()) - 1;
			}catch(Exception e) {
				break;
			}
		}
		st = new StringTokenizer(f.readLine());
		term = Integer.parseInt(st.nextToken()) - 1;
		while(term != -2) {
			try {
				off.add(term);
				term = Integer.parseInt(st.nextToken()) - 1;
			}catch(Exception e) {
				break;
			}
		}
		//System.out.println(on);
		//System.out.println(off);
		//System.out.println("begin");
		int count = 0;
		List<String> answers = new ArrayList<>();
		for(int a = 0; a < 2; a ++) {
			for(int b = 0; b < 2; b ++) {
				for(int c = 0; c < 2; c ++) {
					for(int d = 0; d < 2; d ++) {
						boolean[] test = scopy(lampz);
						int sum = 0;
						if(a == 1) {
							for(int i = 0; i < test.length; i += 2) {
								test[i] = !(test[i]);
							}
						}
						if(b == 1) {
							for(int i = 1; i < test.length; i += 2) {
								test[i] = !(test[i]);
							}
						}
						if(c == 1) {
							for(int i = 0; i < test.length; i += 3) {
								test[i] = !(test[i]);
							}
						}
						if(d == 1) {
							for(int i = 0; i < test.length; i ++) {
								test[i] = !(test[i]);
							}
						}
						//System.out.println(Arrays.toString(test));
						sum = a + b + c + d;
						if(!(sum <= C && (sum % 2 == C % 2))) {
							continue;
						}
						//System.out.println("C Check passed");
						boolean works = true;
						for(int i = 0; i < on.size(); i ++) {
							if(test[on.get(i) % 6] != true) {
								works = false;
								break;
							}
						}
						//System.out.println("Check on "+works);
						for(int i = 0; i < off.size(); i ++) {
							if(test[off.get(i) % 6] != false) {
								works = false;
								break;
							}
						}
						//System.out.println("After check off"+works);
						if(works) {
							count ++;
							String s = "";
							for(int i = 0; i < test.length; i ++) {
								s = s + (test[i] ? 1:0);
							}
							s = (new String(new char[(int) Math.ceil(N/6)+1]).replace("\0",s).substring(0, N));
							answers.add(s);
						}
					}
				}
			}
		}
		answers.sort(null);
		for(String s: answers) {
			System.out.println(s);
		}
		if(count == 0) {
			pw.println("IMPOSSIBLE");
		}
		pw.close();
		f.close();
	}

}
