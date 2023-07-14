import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[][] deltas = {{-1,0}, {0,1}, {1,0}, {0,-1}};
	static Queue<int[]> blocksTmp;
	static class Group implements Comparable<Group>{
		int rainbows;
		int r;
		int c;
		int blockCount;
		
		Group(int rainbows, int blockCount, int r, int c){
			this.rainbows = rainbows;
			this.blockCount = blockCount;
			this.r = r;
			this.c = c;
		}
		
		
		int removeBlocks() {
			int count = 0;
			int color = map[r][c];
			boolean[][] visitedSub = new boolean[N+1][N+1];
			
			Queue<int[]> q = new LinkedList<>();
			q.offer(new int[] {r,c});
			visitedSub[r][c] = true;
			map[r][c] = -2;
			count++;
			
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				
				
				for(int[] delta : deltas) {
					int nr = cur[0] + delta[0];
					int nc = cur[1] + delta[1];
					
					if(isIn(nr,nc) && !visitedSub[nr][nc]) {
						if(map[nr][nc] == 0 || map[nr][nc] == color) {
							q.offer(new int[] {nr,nc});
							visitedSub[nr][nc] = true;
							map[nr][nc] = -2;
							count++;
						}
					}
				}
				
			}
//			System.out.println(count);
			return blockCount * blockCount;
		}
		
		


		@Override
		public int compareTo(Group o) {
			if(this.blockCount == o.blockCount){
				if(this.rainbows == o.rainbows) {
					if(this.r == o.r) {
						return Integer.compare(this.c, o.c);
					}
					return Integer.compare(this.r, o.r);
				}
				return Integer.compare(this.rainbows, o.rainbows);
			}
			
			return Integer.compare(this.blockCount, o.blockCount);
				
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int answer = 0;
		
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		
		for(int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			PriorityQueue<Group> pq = new PriorityQueue<Group>(Collections.reverseOrder());
			
			//그룹 찾기
			visited = new boolean[N+1][N+1];
			for(int r = 1; r <= N; r++) {
				for(int c = 1; c <= N; c++) {
					if(!visited[r][c] && map[r][c] > 0) {
						int[] ret = findGroup(r,c);
						int groupRainbow = ret[0];
						int blockCount = ret[1];
						if(groupRainbow  > -1) {
							pq.offer(new Group(groupRainbow, blockCount, r,c));
						}
					}
				}
			}
			
			//그룹 안 찾아지면 그만 
			if(pq.isEmpty()) break;
			
			//그룹 있으면 맨 앞에있는 그룹이 진짜 그룹
			Group group = pq.poll();
			
			//블록 삭제 & 점수 획득
//			System.out.println("====");
//			System.out.println(group.r + " " + group.c);
			answer += group.removeBlocks();
//			System.out.println("-");
//			for(int r = 1; r <= N; r++) {
//				System.out.println(Arrays.toString(map[r]));
//			}
			
			//중력
			gravity();
//			System.out.println("-gravity1");
//			for(int r = 1; r <= N; r++) {
//				System.out.println(Arrays.toString(map[r]));
//			}
			
			//90도 회전
			rotation();
//			System.out.println("-rotation");
//			for(int r = 1; r <= N; r++) {
//				System.out.println(Arrays.toString(map[r]));
//			}
			
			//다시 중력
			gravity();
//			System.out.println("-gravity");
//			for(int r = 1; r <= N; r++) {
//				System.out.println(Arrays.toString(map[r]));
//			}
//			System.out.println(answer);
		}
		
		System.out.println(answer);
		
		
		
	}
	
	private static void rotation() {
		int[][] mapCopy = new int[N+1][N+1];
		
		//복사
		for(int r = 1; r <= N; r++) {
			mapCopy[r] = map[r].clone();
		}
		
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= N; c++) {
				map[N-c+1][r] = mapCopy[r][c];
			}
		}
		
	}

	static void gravity() {
		for(int c = 1; c <= N; c++) {
			//아래서부터
			//다음 가야하는 곳이 유효하지 않으면 멈춰서 저장
			for(int r = N; r >= 1; r--) {
				if(map[r][c] >= 0) {
					int nr = r+1;
					while(true) {
						if(nr > N || map[nr][c] >= -1) break;
						nr++;
					}
					map[nr-1][c] = map[r][c];
					if(nr > r+1)map[r][c] = -2;
				}
			}
			
		}
	}
	
	static int[] findGroup(int r, int c) {
		int color = map[r][c];
		int rainbow = 0;
		int count = 0;
		boolean flag = false;
		boolean[][] visitedSub = new boolean[N+1][N+1];
		
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r,c});
		visitedSub[r][c] = true;
		count++;
		
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int[] delta : deltas) {
				int nr = cur[0] + delta[0];
				int nc = cur[1] + delta[1];
				
				if(isIn(nr,nc) && !visitedSub[nr][nc]) {
					if(map[nr][nc] == 0 || map[nr][nc] == color) {
						flag = true;
						visited[nr][nc] = true;
						visitedSub[nr][nc] = true;
						q.offer(new int[] {nr,nc});
						count++;
						if(map[nr][nc] == 0) {
							rainbow++;
							visited[nr][nc] = false;
						}
					}
				}
			}
			
		}
		
		if(flag) return new int[] {rainbow, count};
		return new int[] {-1,-1};
	}
	
	static boolean isIn(int nr, int nc) {
		return nr >= 1 && nr <= N && nc >= 1 && nc <= N;
	}
}
//5 3
//2 2 -1 3 1
//3 3 2 0 -1
//0 0 0 1 2
//-1 3 1 3 2
//0 3 2 2 1