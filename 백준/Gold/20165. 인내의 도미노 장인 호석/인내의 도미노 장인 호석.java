import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
	
	static int R,C;
	static int Round;
	static int[][] map;
	static char[][] answer;
	static int point = 0;
	static HashMap<String,int[]> deltas = new HashMap() {{
		put("E", new int[] {0,1});
		put("W", new int[] {0,-1});
		put("S", new int[] {1,0});
		put("N", new int[] {-1,0});
	}};
	
	public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			Round = Integer.parseInt(st.nextToken());
			
			map = new int[R+1][C+1];//행 하나씩 추가. 입력좌표랑 일치시켜줌 
			answer = new char[R+1][C+1];// 출력할 때 주의
			
			for(int r = 1; r <= R; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 1; c <= C; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					answer[r][c] = 'S';
				}
			}
			
			for(int round = 1; round <= Round; round++) {
				//공격수
				st = new StringTokenizer(br.readLine());
				
				int ar = Integer.parseInt(st.nextToken());
				int ac = Integer.parseInt(st.nextToken());
				int[] delta = deltas.get(st.nextToken());
				
				attack(ar,ac,delta);
				
				//수비수
				st = new StringTokenizer(br.readLine());
				int dr = Integer.parseInt(st.nextToken());
				int dc = Integer.parseInt(st.nextToken());
				
				answer[dr][dc] = 'S';
				
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append(point).append("\n");
			for(int r = 1; r <= R; r++) {
				for(int c = 1; c <= C; c++) {
					sb.append(answer[r][c]).append(" ");
				}
				sb.append("\n");
			}
		
			System.out.println(sb);
			
			
		  
	   }
	
	static void attack(int r, int c, int[] delta) {
		int force = map[r][c];
		if(answer[r][c] == 'S')point++;
		
		while(force > 0) {
			answer[r][c] = 'F';
			
			r = r + delta[0];//다음 좌표
			c = c + delta[1];
			
			if(isIn(r,c)) {
				if(answer[r][c] == 'S') {
					force = Math.max(force-1, map[r][c]);
					point++;
				}
			}else {
				break;
			}
			force--;
			
		}
	}

	private static boolean isIn(int nr, int nc) {
		// TODO Auto-generated method stub
		return nr > 0 && nr <= R && nc > 0 && nc <= C;
	}
 
}