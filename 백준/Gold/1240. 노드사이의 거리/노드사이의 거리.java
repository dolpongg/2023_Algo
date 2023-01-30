import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[][] g;
	
	static class Point{
		int from;
		int to;
		int w;
		public Point(int from, int to, int w) {
			this.from = from;
			this.to = to;
			this.w = w;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		g = new int[N+1][N+1];
		for(int i = 1; i <= N; i++) {
			Arrays.fill(g[i], Integer.MAX_VALUE);
		}
		
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			g[from][to] = w;
			g[to][from] = w;
		}
		
		for(int k = 1; k <= N; k++) {
			for(int a = 1; a <= N; a++) {
				for(int b = 1; b <= N; b++) {
					if(g[a][k] + g[k][b] > 0) {
						g[a][b] = Math.min(g[a][b], g[a][k] + g[k][b]);
						
					}
				}
			}
		}
		
		
		StringBuilder sb = new StringBuilder();
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			sb.append(g[from][to]).append("\n");
			
		}
		System.out.println(sb);
		
		
	}
		
}