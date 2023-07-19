import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder(br.readLine());
		String input = sb.toString();
		int len = sb.length();
		
		List<String> list = new ArrayList<>();
		list.add(input);
		for(int n = 0; n < N; n++) {
			StringBuilder front = new StringBuilder();
			StringBuilder back = new StringBuilder();
			
			for(int i = 0; i < len; i++) {
				if(i%2 == 0) front.append(sb.charAt(i));
				else back.append(sb.charAt(i));
			}
			
			sb = new StringBuilder();
			sb.append(front).append(back.reverse());
			String after = sb.toString();
			
			if(input.equals(after)) break;
			list.add(after);
		}
		N = N%list.size();
		
		System.out.println(list.get(N));
		
	}
	
}
//4
//acefdb