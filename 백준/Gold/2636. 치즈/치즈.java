import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {
	static int R,C;
	static int[][] map;
	static int[][] deltas = {{0,1},{1,0},{0,-1},{-1,0}};
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int [R][C];
		int rest = 0;
		
		for(int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 1) rest++;
			}
		}
		int hour = 0;
		while(true) {
			int cnt = delete();
			hour++;
			if(cnt == rest) break;
			else {
				rest-=cnt;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(hour).append("\n").append(rest);
		System.out.println(sb);
		
	}
	private static int delete() {
		int cnt = 0;
		boolean[][] visited = new boolean[R][C];
		
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0,0});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int[] delta : deltas) {
				int nr = cur[0] + delta[0];
				int nc = cur[1] + delta[1];
				
				if(isIn(nr,nc) && !visited[nr][nc]) {
					visited[nr][nc] = true;
					if(map[nr][nc] == 0) {
						q.offer(new int[] {nr,nc});
					}else {
						map[nr][nc] = 0;
						cnt++;
					}
				}
			}
		}
		
		return cnt;
	}
	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
}