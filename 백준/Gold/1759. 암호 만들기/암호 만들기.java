import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int L, C;
	static boolean[] visited;
	static char[] chars;
	static boolean[] alpha = new boolean[28];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		chars = new char[C];
		for(int c = 0 ; c < C; c++) {
			
			alpha[st.nextToken().charAt(0)-'a'] = true;
		}//입력 완료
		
		int index = 0;
		for(int i = 0; i < 28; i++) {
			if(alpha[i]) chars[index++] = (char)(i+97);
		}
		
		visited = new boolean[C];
		per(0, new char[L], 0, false, 0);
		System.out.println(sb);
	}

	private static void per(int nth, char[] choosed, int start, boolean flag, int count) {
		if(nth == choosed.length) {
			if(flag && count >= 2) {
				for(char c : choosed) {
					sb.append(c);
				}
				sb.append("\n");
			}
			return;
		}
		
		for(int i = start; i < chars.length; i++) {
			choosed[nth] = chars[i];
			boolean tmpflag = flag;
			int tmpCount = count;
			if(chars[i] == 'a' ||chars[i] == 'e' ||chars[i] == 'i' ||chars[i] == 'o' ||chars[i] == 'u') tmpflag = true;
			else tmpCount++;
			
			per(nth+1, choosed, i+1, tmpflag, tmpCount);

		}
		
	}
}
