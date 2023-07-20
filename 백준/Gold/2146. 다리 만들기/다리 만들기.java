import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static boolean[][] map;
	static boolean[][] visited;
	static int N;
	static int[][] deltas = {{1,0}, {0,1}, {-1,0}, {0,-1}};
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new boolean[N][N];
		visited = new boolean[N][N];
		
		for(int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c = 0; c< N; c++) {
				map[r][c] = (Integer.parseInt(st.nextToken()) != 0);
			}
		}
		
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(map[r][c] && !visited[r][c]) findBridge(r,c);
			}
		}
		
		System.out.println(answer);
	}

	private static void findBridge(int r, int c) {
		
		Queue<int[]> edges = new LinkedList<>();
		Queue<int[]> land = new LinkedList<>();
		
		land.offer(new int[] {r,c});
		visited[r][c] = true;
		boolean[][] visitedSub = new boolean[N][N];
		
		while(!land.isEmpty()) {
			int[] cur = land.poll();
			
			for(int[] delta : deltas) {
				int nr = cur[0] + delta[0];
				int nc = cur[1] + delta[1];
				
				if(isIn(nr,nc) && !visited[nr][nc]) {
					if(!map[nr][nc]) {
						edges.offer(new int[] {nr,nc});
						visitedSub[nr][nc] = true;
					}
					else {
						visited[nr][nc] = true;
						land.offer(new int[] {nr,nc});
					}
				}
			}
		}
		
		int distance = 1;
		
		while(!edges.isEmpty()) {
			int size = edges.size();
			while(size-- > 0) {
				int[] cur = edges.poll();
				
				for(int[] delta : deltas) {
					int nr = cur[0] + delta[0];
					int nc = cur[1] + delta[1];
					
					if(isIn(nr,nc) && !visitedSub[nr][nc]) {
						if(visited[nr][nc]) continue;
						if(map[nr][nc]) {
							answer = Math.min(distance,answer);
							return;
						}else {
							visitedSub[nr][nc] = true;
							edges.offer(new int[] {nr,nc});
						}
					}
				}
				
			}
			
			distance++;
			if(distance > answer) return;
		}
		
	}

	static boolean isIn(int nr, int nc) {
		return nr >=0 && nr < N && nc >= 0 && nc < N;
	}
	
}
//1 1 0 0 0 1
//1 0 0 0 0 0
//0 0 0 0 0 0
//0 0 0 0 0 0
//1 0 0 0 0 0
//0 0 0 0 0 0