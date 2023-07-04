import java.util.*;
import java.io.*;

public class Main {
	static int N,L,R;
	static int[][] map;
	static int[][] deltas = {{-1,0},{0,1},{1,0},{0,-1}};
	static boolean[][] visited;
	
	
	static class Country{
		int popul;
		boolean[] borders;
		
		Country(int popul) {
			this.popul = popul;
			borders = new boolean[4];
		}
	}
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		int answer = 0;
		while(borderOpen()) {
			visited = new boolean[N][N];
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					if(!visited[r][c]) {
						movement(r, c);
					}
				}
			}
			answer++;
//			for(int r = 0; r < N; r++) {
//				System.out.println(Arrays.toString(map[r]));
//			}
//			System.out.println("========");
		}
		System.out.println(answer);
	}


	private static void movement(int startR, int startC) {
		List<int[]> unions = new LinkedList<>();
		int sum = 0;
		int count = 0;
		Queue<int[]> q = new LinkedList<>();
		
		q.offer(new int[] {startR, startC});
		visited[startR][startC] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			unions.add(new int[] {cur[0], cur[1]});
			sum += map[cur[0]][cur[1]];
			count++;
			
			for(int[] delta : deltas) {
				int nr = cur[0] + delta[0];
				int nc = cur[1] + delta[1];
				
				if(isIn(nr,nc) && !visited[nr][nc] && Math.abs(map[cur[0]][cur[1]]-map[nr][nc]) >= L && Math.abs(map[cur[0]][cur[1]]-map[nr][nc]) <= R) {
					visited[nr][nc] = true;
					
					q.offer(new int[] {nr,nc});
				}
			}
		}
		
		for(int[] union : unions) {
			map[union[0]][union[1]] = sum/count;
		}
		
	}


	private static boolean borderOpen() {
		boolean isOpen = false;
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				int i = 0;
				for(int[] delta : deltas) {
					int nr = r + delta[0];
					int nc = c + delta[1];
					if(isIn(nr,nc) && Math.abs(map[r][c]-map[nr][nc]) >= L && Math.abs(map[r][c]-map[nr][nc]) <= R) {
//						System.out.println(Math.abs(map[r][c]-map[nr][nc]));
						return true;
					}
					i++;
				}
			}
		}
		
		return false;
	}


	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}