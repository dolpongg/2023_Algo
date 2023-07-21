import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int K, R, C;
	static int[][] map;
	static boolean[][][] visited;
	static int answer = Integer.MAX_VALUE;
	static int[][] horse= {{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}}; 
	static int[][] monkey = {{-1,0},{0,1},{1,0},{0,-1}};
	
	public static class Point{
		int r,c,k,dep;

		public Point(int r, int c, int k, int d) {
			this.r = r;
			this.c = c;
			this.k = k;
			this.dep=d;
		}
		
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		for(int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}//입력 완료
		
		
		visited = new boolean[R][C][K+1];//방문 확인 
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(0,0,K,0)); // 맨 처음시작점. K번 남음, 
		visited[0][0][K] = true;
		
		while(!q.isEmpty()) {
			Point c = q.poll();
			if(answer < c.dep) break;
			
			if(c.r == R-1 && c.c == C-1) {
				answer = Math.min(answer,c.dep);
			}
			
			if(c.k >0) {
				for(int[] delta : horse) {
					int nr = c.r + delta[0];
					int nc = c.c + delta[1];
					
					if(isin(nr,nc)&& map[nr][nc] == 0 &&!visited[nr][nc][c.k-1]) {
						visited[nr][nc][c.k-1] = true;
						q.offer(new Point(nr,nc,c.k-1, c.dep+1));
					}
				}
			}
			for(int[] delta : monkey) {
				int nr = c.r + delta[0];
				int nc = c.c + delta[1];
				
				if(isin(nr,nc)&&map[nr][nc] == 0 &&!visited[nr][nc][c.k]) {
					visited[nr][nc][c.k] = true;
					q.offer(new Point(nr,nc,c.k, c.dep+1));
				}
			}
		}
		
		System.out.println(answer == Integer.MAX_VALUE? -1 : answer);
	}


	private static boolean isin(int nr, int nc) {
		// TODO Auto-generated method stub
		return nr >=0 && nr < R && nc >= 0 && nc < C;
	}

}
/*
1
4 4
0 0 0 0
1 0 0 0
0 0 1 0
0 1 0 0
*/