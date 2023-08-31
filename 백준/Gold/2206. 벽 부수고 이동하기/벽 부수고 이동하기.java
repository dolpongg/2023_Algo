import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] deltas = {{0,1}, {1,0}, {0,-1}, {-1,0}};
	static int [][] map;
	static int R, C;
	static int answer = Integer.MAX_VALUE;
	static boolean[][][] visited;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		visited = new boolean[R][C][2];
		
		for(int r = 0; r < R; r++) {
			char[] line =  br.readLine().toCharArray();
			for(int c = 0; c < C; c++) {
				map[r][c] = line[c] - '0';
			}
		}
		//입력 완료
	
		Queue<int[]> q = new LinkedList<>();
		
		q.offer(new int[] {0,0,0,1});
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			if(cur[0] == R-1 && cur[1] == C-1) {
				System.out.println(cur[3]);
				return;
			}
			
			for(int[] delta : deltas) {
				int nr = cur[0] + delta[0];
				int nc = cur[1] + delta[1];
				if(isIn(nr,nc)) {
					if(map[nr][nc] == 0) {
						if(!visited[nr][nc][cur[2]]) {
							
							visited[nr][nc][cur[2]] = true;
							q.offer(new int[] {nr, nc, cur[2], cur[3] + 1});
						}
					}else {
						if(cur[2] == 0) {
							if(!visited[nr][nc][1]) {
								visited[nr][nc][1] = true;
								q.offer(new int[] {nr, nc, 1, cur[3] + 1});
							}
						}
					}
				}
			}
		}
		System.out.println(-1);
		
	}
	





	public static boolean isIn(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}

}