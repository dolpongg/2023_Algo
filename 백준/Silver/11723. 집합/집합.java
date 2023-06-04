import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] arr = new int[21];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		for(int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String op = st.nextToken();
			if(op.equals("all") || op.equals("empty")) {
				if(op.equals("all")) {
					Arrays.fill(arr, 1);
				}else {
					Arrays.fill(arr, 0);
				}
			}else {
				int x = Integer.parseInt(st.nextToken());
				
				if(op.equals("add")) {
					arr[x]= 1;
				}else if(op.equals("remove")) {
					arr[x] = 0;
				}else if(op.equals("check")) {
					sb.append(arr[x]).append("\n");
				}else {
					arr[x] = (arr[x] + 1) % 2;
				}
			}
		}
		System.out.println(sb);
		
		
	}
}