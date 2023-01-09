import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static int[] arr = new int[26];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		Arrays.fill(arr, -1);
		N = Integer.parseInt(br.readLine());
		for(int i = 0;i < N; i++) {
			char[] cArr = br.readLine().toCharArray();
			int from = cArr[0] - 97;
			int to = cArr[5] - 97;
			
			arr[from] = to;
		}
		
		M = Integer.parseInt(br.readLine());
		outer : for(int i = 0; i < M; i++) {
			char[] cArr = br.readLine().toCharArray();
			int from = cArr[0] - 97;
			int to = cArr[5] - 97;
			int depth = 0;
			while(arr[from] != to) {
//				System.out.println(arr[from]);
				if(arr[from] == -1) {
					sb.append("F").append("\n");
					continue outer;
				}
				if(depth > 26) {
					sb.append("F").append("\n");
					continue outer;
				}
				from = arr[from];
				depth++;
				
			}
			sb.append("T").append("\n");
		}
		
		System.out.println(sb);
	}

	
}