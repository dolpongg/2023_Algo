import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int R,C;
	static int K;
	static int[][][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		map = new int[R+1][C+1][3];
		
		for(int r = 1; r <= R; r++) {
			char[] tmp = br.readLine().toCharArray();
			for(int c = 1; c <= C; c++) {
				if(r > 1) {
					map[r][c][0] += map[r-1][c][0];
					map[r][c][1] += map[r-1][c][1];
					map[r][c][2] += map[r-1][c][2];
				}
				if(c > 1) {
					map[r][c][0] += map[r][c-1][0];
					map[r][c][1] += map[r][c-1][1];
					map[r][c][2] += map[r][c-1][2];
				}
				if(r > 1 && c > 1) {
					map[r][c][0] -= map[r-1][c-1][0];
					map[r][c][1] -= map[r-1][c-1][1];
					map[r][c][2] -= map[r-1][c-1][2];
				}

				
				
				if(tmp[c-1] == 'J') map[r][c][0]++;
				else if(tmp[c-1] == 'O') map[r][c][1]++;
				else map[r][c][2]++;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			sb.append(map[c][d][0] - map[c][b-1][0] - map[a-1][d][0] + map[a-1][b-1][0]).append(" ");
			sb.append(map[c][d][1] - map[c][b-1][1] - map[a-1][d][1] + map[a-1][b-1][1]).append(" ");
			sb.append(map[c][d][2] - map[c][b-1][2] - map[a-1][d][2] + map[a-1][b-1][2]).append("\n");
		}
		
		System.out.println(sb);
	}
}
//4 7
//4
//JIOJOIJ
//IOJOIJO
//JOIJOOI
//OOJJIJO
//3 5 4 7
//2 2 3 6
//2 2 2 2
//1 1 4 7