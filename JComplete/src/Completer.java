import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
public class Completer implements NativeKeyListener{
	static Map<String, List<String>> trie = new HashMap<>();
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}
		BufferedReader f = new BufferedReader(new FileReader("words.txt"));
		List<String> words = new ArrayList<>();
		String line = f.readLine();
		while(line != null) {
			//words.add(line);
			String prefix="", current;
			for(int i = 1; i <= line.length(); i ++) {
				current = line.substring(0, i);
				if(!trie.containsKey(prefix)) {
					trie.put(prefix, new ArrayList<String>());
				}
				trie.get(prefix).add(current);
				prefix = current;
			}
			line = f.readLine();
		}
		GlobalScreen.addNativeKeyListener(new Completer());
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);
		// Don't forget to disable the parent handlers.
		logger.setUseParentHandlers(false);
	}
	final String letters = "abcdefghijklmnopqrstuvwxyz";
	String cur = "";
	static int selpos = 0;
	@Override
	public void nativeKeyPressed(NativeKeyEvent arg0) {
		//System.out.println(arg0.getKeyCode());
		String type = NativeKeyEvent.getKeyText(arg0.getKeyCode());
		if(trie.containsKey(cur)) {
			List<String> pred = trie.get(cur);
			selpos = (selpos + pred.size()) % pred.size();
			if(type.equals("Right")) {
				selpos++;
				if(trie.containsKey(cur)) {
					int selected = (selpos + pred.size()) % pred.size();
					for(int i = 0 ;i < pred.size(); i ++) {
						if(i == selected) {
							System.out.print("|"+pred.get(i)+"|");
						}else {
							System.out.print(" "+pred.get(i)+" ");
						}
					}
					System.out.println();
				}
			}
			if(type.equals("Left")) {
				selpos--;
				if(trie.containsKey(cur)) {
					int selected = (selpos + pred.size()) % pred.size();
					for(int i = 0 ;i < pred.size(); i ++) {
						if(i == selected) {
							System.out.print("|"+pred.get(i)+"|");
						}else {
							System.out.print(" "+pred.get(i)+" ");
						}
					}
					System.out.println();
				}
			}
		}
		//System.out.println(arg0.getKeyCode());
		if(letters.contains(type.toLowerCase())) {
			cur += type.toLowerCase();
			//System.out.println(cur);
			if(trie.containsKey(cur)) {
				List<String> pred = trie.get(cur);
				int selected = (selpos + pred.size()) % pred.size();
				for(int i = 0 ;i < pred.size(); i ++) {
					if(i == selected) {
						System.out.print("|"+pred.get(i)+"|");
					}else {
						System.out.print(" "+pred.get(i)+" ");
					}
				}
				System.out.println();
			}
		}
		
		if(arg0.getKeyCode() == 15) {
			
		}
		if(type.equals("Space")) {
			cur="";
		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
