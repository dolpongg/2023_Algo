import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C, K;
	static char[][] map;
	static int[][] deltas = {{0,1},{1,0},{0,-1},{-1,0}};
	static int answer = 0;
	
	static class Pos{
		int r,c;
		int depth;

		public Pos(int r, int c, int depth) {
			this.r = r;
			this.c = c;
			this.depth = depth;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for(int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
		}
		
		boolean[][] visited = new boolean[R][C];
		visited[R-1][0] = true;
		dfsMatrix(new Pos(R-1, 0, 1), visited);
		
		System.out.println(answer);
		
		
		
	}
	
	static void dfsMatrix(Pos p, boolean[][] visited) {
        // 1. 할일 처리(ex: 방문 처리)
        
//        System.out.println(p.r);
//        System.out.println(p.c);
//        System.out.println(p.depth);
//        System.out.println("----------");
        
        if(p.depth == K) {
        	if(p.r == 0 && p.c == C-1) {
        		answer++;
        	}
        	return;
        }
        
//        if(p.r == 0 && p.c == C-1) {
//        	return;
//        }

        // 2. 자식 탐색
        for (int[] delta : deltas) {
        	int nr = p.r + delta[0];
        	int nc = p.c + delta[1];
        	
        	// 연결되어있고 미방문이면 가보자.
            if (isIn(nr,nc) && map[nr][nc] != 'T' && !visited[nr][nc]) {
            	visited[nr][nc] = true;
                dfsMatrix(new Pos(nr,nc,p.depth+1), visited);
                visited[nr][nc] = false;
            }
        }
    }
	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < R && nc >=0 && nc < C;
	}
}