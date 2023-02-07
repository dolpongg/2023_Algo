import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static String B;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		B = br.readLine();
		String finish = br.readLine();
		
		if(dfs(finish)) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
		
	}
	private static boolean dfs(String finish) {
		
		if(finish.length() == B.length()) {
			if(finish.equals(B))return true;
			return false;
		}
		
		if(finish.charAt(finish.length()-1) == 'A') {
			if(dfs(finish.substring(0, finish.length()-1))) {
				return true;
			}
		}
		
		if(finish.charAt(0) == 'B') {
			StringBuilder sb = new StringBuilder();
			sb.append(finish.substring(1, finish.length()));
			if(dfs(sb.reverse().toString())) {
				return true;
			}
		}
		
		return false;
	}
}