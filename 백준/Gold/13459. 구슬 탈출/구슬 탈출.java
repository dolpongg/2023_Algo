import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R,C;
	static int[][] deltas = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	static char[][] map;
	
	static class Marble{
		int r, c;
		boolean isRed;
		public Marble(int r, int c, boolean isRed) {
			this.r = r;
			this.c = c;
			this.isRed = isRed;
		}
		
		Marble go(int d) {
			int i = 1;
			while(true) {
				int nr = r + deltas[d][0] * i;
				int nc = c + deltas[d][1] * i;
				//구멍
				if(map[nr][nc] == 'O') {
					return new Marble(nr,nc,isRed);
				}
				else if(map[nr][nc] == '#') {
					Marble nM = new Marble(nr - deltas[d][0], nc - deltas[d][1], isRed);
					map[nM.r][nM.c]='#';
					return nM;
				}
				i++;
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		Marble red = null;
		Marble blue = null;
		for(int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
			for(int c = 0; c < C; c++) {
				if(map[r][c] == 'R') red = new Marble(r,c,true);
				if(map[r][c] == 'B') blue = new Marble(r,c,false);
			}
		}
		
		bfs(red,blue);
		
		
	}
	
	static void bfs(Marble red, Marble blue) {
		Queue<Marble[]> q = new LinkedList<>();
		boolean [][][][] visited = new boolean[R][C][R][C];
		
		q.offer(new Marble[] {red, blue});
		visited[red.r][red.c][blue.r][blue.c] = true;
		
		int depth = 1;
		while(!q.isEmpty()) {
			if(depth > 10) break;
			int size = q.size();
			while(size-->0) {
//				System.out.println(size);
				Marble[] cur = q.poll();
				
				for(int d = 0; d < deltas.length; d++) {
//					System.out.println(d);
					Marble first = null;
					Marble second = null;
					if(d==0) {//r작은 애가 먼
						if(cur[0].r > cur[1].r) {
							first = cur[1].go(d);
							second = cur[0].go(d);
							
						}else {
							first = cur[0].go(d);
							second = cur[1].go(d);
						}
						
					}else if(d == 1) {//r큰애가 먼저 
						if(cur[0].r < cur[1].r) {
							first = cur[1].go(d);
							second = cur[0].go(d);
						}else {
							first = cur[0].go(d);
							second = cur[1].go(d);
						}
					}else if(d==2) {//c작은 애가 먼저 
						if(cur[0].c > cur[1].c) {
							first = cur[1].go(d);
							second = cur[0].go(d);
						}else {
							first = cur[0].go(d);
							second = cur[1].go(d);
						}
					}else {
						if(cur[0].c < cur[1].c) {
							first = cur[1].go(d);
							second = cur[0].go(d);
						}else {
							first = cur[0].go(d);
							second = cur[1].go(d);
						}
					}
					
					if(map[first.r][first.c] == '#') map[first.r][first.c] = '.';
					if(map[second.r][second.c] == '#') map[second.r][second.c] = '.';
					
					
					//빨간공 선
					Marble nR = first.isRed ? first : second;
					Marble nB = !first.isRed ? first : second;
					
					if(map[nB.r][nB.c] == 'O') continue;
					else if(map[nR.r][nR.c] == 'O') {
						System.out.println(1);
						return;
					}else {
						if(!visited[nR.r][nR.c][nB.r][nB.c]) {
							visited[nR.r][nR.c][nB.r][nB.c] = true;
							q.offer(new Marble[] {nR, nB});
						}
					}
				}
			}
			depth++;
		}
		System.out.println(0);
		return;

	}
}