import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int[][] newMap;
	static int R,C,M;
	static int answer = Integer.MAX_VALUE;
	static int[][] deltas = {{1,0},{0,1},{-1,0},{0,-1}};
	static List<Chicken> chickens = new ArrayList<>();
	static int sum = 0;
	
	
	static class Chicken {
		int r,c;

		public Chicken(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = R;
		M = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		for(int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < C; c++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 2) {
					chickens.add(new Chicken(r, c));
					map[r][c] = 0;
				}else {
					map[r][c] = num;
				}
			}
		}
		
		// 치킨 M개 고르rl
		choice(0, new Chicken[M], 0);
		
		System.out.println(answer);
		
	}

	private static void choice(int startIdx, Chicken[] choosed, int nth) {
		if(nth == M) {
			//집과 거리 구하기 
			newMap = new int[R][C];
			for(int r = 0; r < R; r++) {
				newMap[r] = map[r].clone();
			}
			
			for(Chicken ck : choosed) {
				newMap[ck.r][ck.c] = 2;
			}
			
			
			int total = 0;
			for(int r = 0; r < R; r++) {
				for(int c = 0; c < C; c++) {
					if(newMap[r][c] == 1) {
						sum = 0;
						bfs(r,c);
						total += sum;
					}
				}
			}
			
			if(total < answer) answer = total;
			
			return;
		}
		
		for(int i = startIdx; i < chickens.size(); i++) {
			choosed[nth] = chickens.get(i);
			choice(i+1, choosed, nth+1);
		}
	}

	private static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		
		q.offer(new int[] {r,c});
		visited[r][c] = true;
		
		int depth = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			while(size-- > 0) {
				int[] cur = q.poll();
				if(newMap[cur[0]][cur[1]] == 2) {
					sum = depth;
					return;
				}
				
				for(int[] delta : deltas) {
					int nr = cur[0] + delta[0];
					int nc = cur[1] + delta[1];
					if(isIn(nr,nc) && !visited[nr][nc]) {
						q.offer(new int[] {nr, nc});
						visited[nr][nc] = true;
					}
				}
			}
			depth++;
		}
		
		
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
	
	
}