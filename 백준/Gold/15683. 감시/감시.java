import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
	static int R,C;
	static int[][] map;
	static int[][] deltas = {{0,1},{1,0},{0,-1},{-1,0}};
	static int[][][] di = {
			{
				{0},{1},{2},{3}
			},
			{
				{0,2},{1,3}
			},
			{
				{0,1},{1,2},{2,3},{3,0}
			},
			{
				{0,2,3},{1,3,0},{2,0,1},{3,1,2}
			},
			{
				{0,1,2,3}
			}
	};
	
	static List<int[]> cctvs = new LinkedList<>();
	static int cctvCount = 0;
	static int answer = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		int blind = 0;
		
		for(int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] > 0 && map[r][c] <6) cctvs.add(new int[] {r,c, map[r][c]-1});
				if(map[r][c] == 0) blind++;
			}
		}//입력 완료
		
		cctvCount = cctvs.size();
		dfs(0, blind);
		
		System.out.println(answer);
		
		
	}
	
	private static void dfs(int cctvNum, int blind) {
		
		if(cctvNum == cctvCount) {
//			answer = Integer.min(answer, blind);
			
//			for(int r = 0; r < R; r++) {
////				System.out.println(Arrays.toString(map[r]));
//				for(int c = 0; c < C; c++) {
//					System.out.printf(String.format("%-5d", map[r][c]));
//				}
//				System.out.println("");
//			}
//			System.out.println("------");
			int count = (int) Arrays.stream(map)  // 2차원 배열을 스트림으로 변환
				    .flatMapToInt(Arrays::stream)  // 내부 배열을 펼쳐서 하나의 스트림으로 만듦
				    .filter(num -> num == 0)  // 0인 요소만 필터링
				    .count();  // 요소 개수를 세어 반환
			
			answer = Integer.min(answer,count);
			return;
		}
		
		
		int[] cctv = cctvs.get(cctvNum);
//		System.out.println(cctv[2]);
		
		for(int[] cctvDels : di[cctv[2]]) {
			int nCount = 0;
			boolean[][] visited = new boolean[R][C];
			for(int delta : cctvDels) {
					int i = 1;
					while(true) {
						int nr = cctv[0] + deltas[delta][0]*i;
						int nc = cctv[1] + deltas[delta][1]*i;
						
						//범위 밖이거나 벽을 만나면 그만
						if(!isIn(nr,nc)) break;
						
						if(map[nr][nc] == 0) {
							nCount++;
							map[nr][nc] = -1;
							visited[nr][nc] = true;
						}
						
						i++;
				}
				
			}
//			for(int r = 0; r< R; r++) {
//				System.out.println(Arrays.toString(map[r]));
//			}
			dfs(cctvNum+1, blind - nCount);
			
			for(int delta : cctvDels) {
				int i = 1;
				while(true) {
					int nr = cctv[0] + deltas[delta][0]*i;
					int nc = cctv[1] + deltas[delta][1]*i;
					
					//범위 밖이거나 벽을 만나면 그만
					if(!isIn(nr,nc)) break;
					
					if(visited[nr][nc]) {
						nCount++;
						map[nr][nc] = 0;
					}
					
					i++;
				}
			
			}
//			for(int r = 0; r< R; r++) {
//				System.out.println(Arrays.toString(map[r]));
//			}
//			System.out.println("-------");
		}
		
	}

	static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] < 6;
	}
			
 
}
//4 6
//0 0 0 0 0 0
//0 0 0 0 0 0
//0 0 1 0 6 0
//0 0 0 0 0 0