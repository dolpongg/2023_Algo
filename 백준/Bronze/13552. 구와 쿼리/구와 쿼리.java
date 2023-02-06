import java.util.*;
import java.io.*;

import java.util.*;
import java.io.*;

public class Main {
	static int N,M;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N][3];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			long rr = (long) r * (long) r;
			int cnt = 0;

			for(int n = 0; n < N; n++) {
				int a = arr[n][0]-x;
		        int b = arr[n][1]-y;
		        int c = arr[n][2]-z;
		        
		        long dis = (long) a * a + (long) b * b + (long) c * c;
		        if(dis <= rr) cnt++;
			}
			
			sb.append(cnt).append("\n");
		}
		
		System.out.println(sb);
	}
}