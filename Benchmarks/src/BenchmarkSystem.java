import java.util.*;
public class BenchmarkSystem {
	public static Map<String, Long> benchmarkStarts = new HashMap<String, Long>();
	public static Map<String, Long> benchmarkResults = new HashMap<String, Long>();
	public static void bmstart(String name) {
		benchmarkStarts.put(name, System.nanoTime());
	}
	public static void bmstop(String name) {
		long curTime = System.nanoTime();
		long startTime = benchmarkStarts.get(name);
		if(!benchmarkResults.keySet().contains(name)) {
			benchmarkResults.put(name, 0L);
		}
		long l = benchmarkResults.get(name);
		l += curTime - startTime;
		benchmarkResults.put(name, l);
	}
	public static long bmresults(String name, int runs) {
		return benchmarkResults.get(name)/runs;
	}
	public static void main(String[] args) {
		long a,b,c,d;
		bmstart("Bitshifting");
		for(int i = 0 ;i < 1000; i ++) {
			a = i << 5;
		}
		bmstop("Bitshifting");
		System.out.println("Bitshifting "+bmresults("Bitshifting", 1000)+" ns");
		bmstart("Multiplying");
		for(int i = 0 ;i < 1000; i ++) {
			a = i * 2 * 2 * 2 * 2 * 2;
		}
		bmstop("Multiplying");
		System.out.println("Multiplying "+bmresults("Multiplying", 1000)+" ns");
		bmstart("Bitshifting2");
		for(int i = 0 ;i < 1000; i ++) {
			a = 1 << i;
		}
		bmstop("Bitshifting2");
		System.out.println("Bitshifting Exponetially "+bmresults("Bitshifting2", 1000)+" ns");
		bmstart("Powing");
		for(int i = 0 ;i < 1000; i ++) {
			a = (long) Math.pow(2, i);
		}
		bmstop("Powing");
		System.out.println("Powers "+bmresults("Powing", 1000)+" ns");
		
	}

}
