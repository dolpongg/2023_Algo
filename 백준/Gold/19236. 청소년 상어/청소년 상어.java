import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int MAX;	
	static fish[] fishes;
 	static int[][] arr, deltas = {{0, 0}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}}; 
 	
	static class fish {
		int r, c, d;
		boolean alive;

		public fish(int r, int c, int d, boolean alive) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
			this.alive = alive;
		}
	}
	
	static class par {
		fish shark;
		int ans, key;
		int[][] arr;
		fish[] fishes;
		public par(fish shark, int ans, int key, int[][] arr, fish[] fishes) {
			super();
			this.shark = shark;
			this.ans = ans;
			this.key = key;
			this.arr = arr;
			this.fishes = fishes;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		arr = new int[4][4];
		fishes = new fish[17];
		for (int r=0;r<4;r++) {
			st = new StringTokenizer(br.readLine());
			for (int c=0;c<4;c++) {
				int num = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				arr[r][c] = num;
				fishes[num] = new fish(r, c, d, true);
			}
		}
		MAX = Integer.MIN_VALUE;
		dfs(new par(fishes[arr[0][0]], 0, arr[0][0], arr, fishes));
		System.out.println(MAX);
	}
	
	static void dfs(par in) {
		int[][] temp = new int[4][4];
		for (int i=0;i<4;i++) { // 맵 복사
			for (int j=0;j<4;j++) {
				temp[i][j] = in.arr[i][j];
			}
		}
		
		fish[] f = new fish[17];
		for (int i=1;i<=16;i++) { // 물고기 복사
			f[i] = in.fishes[i];
		}
		
		temp[in.shark.r][in.shark.c] = -1; // 상어!!
		f[in.key].alive = false; // 물고기 죽여주기
		// 물고기 이동
		fishMove(temp, f);
		
		// 상어 이동
		for (int i=1;i<=3;i++) { 
			int nr = in.shark.r + deltas[in.shark.d][0]*i; // i칸 만큼 이동
			int nc = in.shark.c + deltas[in.shark.d][1]*i; // i칸 만큼 이동
			
			if (!isIn(nr, nc)) {
				MAX = Math.max(MAX, in.ans+in.key); // 물고기 합의 최댓값 구하기
				break;
			} else if (temp[nr][nc]==0) continue;
			else {
				temp[in.shark.r][in.shark.c] = 0; // 상어 이동 = 전에 있었던 자리 0으로 바꿔주기
				int next = temp[nr][nc]; // 다음 먹을 물고기
				dfs(new par(new fish(nr, nc, f[next].d, true), in.ans+in.key, next, temp, f));
				// 초기화!!!!!!!!!
				temp[nr][nc] = next;
				temp[in.shark.r][in.shark.c] = -1;
				f[next].alive = true; // 물고기 다시 살려주기
			} 
		}
		return;
	}
	
	static void fishMove(int[][] temp, fish[] f) {
		for (int i=1;i<=16;i++) {
			if (f[i].alive) {
				fish now = f[i];
				int d = f[i].d;
				while (true) {
					int nr = now.r + deltas[d][0];
					int nc = now.c + deltas[d][1];
					if (!isIn(nr, nc) || temp[nr][nc]==-1) d = (d%8)+1; // 방향 바꿔주기
					else if (isIn(nr, nc)){
						swap(now, nr, nc, i, d, temp, f);
						break;
					}
				}
			}
		}
	}
	
	static void swap(fish now, int nr, int nc, int i, int d, int[][] temp, fish[] f) {
		int tmp = temp[nr][nc]; // 바꿔줄 물고기 번호
		
		temp[nr][nc] = i; // 바꿔줄 물고기 좌표에 현재 좌표 넣어줌
		temp[now.r][now.c] = tmp; // 현재 물고기 좌표에 바꿔줄 물고기 좌표 넣어줌
		if (tmp!=0) f[tmp] = new fish(now.r, now.c, f[tmp].d, true); // 물고기가 없을 때
		f[i] = new fish(nr, nc, d, true); // 현재 물고기 추가
	}
	
	static boolean isIn(int nr, int nc) {
		return nr>=0 && nr<4 && nc>=0 && nc<4;
	}
}