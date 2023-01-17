import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static boolean[][] map;
	static int[] trip;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		
		map = new boolean[N][N];
		for(int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 0) {
					map[r][c] = false;
				}else {
					map[r][c] = true;
				}
			}
		}
		
		trip = new int[M];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int m = 0; m < M; m++) {
			trip[m] = Integer.parseInt(st.nextToken())-1;
		}
		
		
		//입력 완료
		
		outer : for(int m = 0; m < M-1; m++) {
			boolean[] visited = new boolean[N];
			
			Queue<Integer> q = new LinkedList<>();
			
			q.add(trip[m]);
			visited[trip[m]] = true;
			while(!q.isEmpty()) {
				int cur = q.poll();
				
				if(cur == trip[m+1]) continue outer;
				
				for(int i = 0; i < N; i++) {
					if(map[cur][i] && !visited[i]) {
						visited[i] = true;
						q.offer(i);
					}
				}
				
			}
			
			System.out.println("NO");
			return;
		}
		System.out.println("YES");
		
	}
}