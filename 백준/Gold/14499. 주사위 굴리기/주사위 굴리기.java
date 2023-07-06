import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
//     이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다.
//    0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0
//    4 2 0 0 8
//    0 2
//    3 4
//    5 6
//    7 8
//    4 4 4 1 3 3 3 2

    static int[] dice = {0,0,0,0,0,0};
    static int[][] map;
    static int R,C, sR, sC, goNum;
    static int[][] deltas = {{},{0,1},{0,-1},{-1,0},{1,0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        sR = Integer.parseInt(st.nextToken());
        sC = Integer.parseInt(st.nextToken());
        goNum = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for(int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < goNum; n++) {
            int dir = Integer.parseInt(st.nextToken());
            sR += deltas[dir][0];
            sC += deltas[dir][1];

            if(isIn(sR, sC)) {
            	change(dir);
            	if(map[sR][sC] == 0 ) {
            		map[sR][sC] = dice[0];
            	}else {
            		dice[0] = map[sR][sC];
            		map[sR][sC] = 0;
            	}
            	sb.append(dice[5]).append("\n");
            	
            }else {
            	sR -= deltas[dir][0];
                sC -= deltas[dir][1]; 
            }
            
        }
        System.out.println(sb);
    }

    private static boolean isIn(int r, int c) {
		// TODO Auto-generated method stub
		return r >= 0 && r < R && c >= 0 && c < C;
	}

	static void change(int dir) {
        int[] before = dice.clone();
        if(dir == 1) {
            dice[0] = before[1];
            dice[1] = before[5];
            dice[2] = before[0];
            dice[3] = before[3];
            dice[4] = before[4];
            dice[5] = before[2];
        }else if(dir == 2) {
            dice[0] = before[2];
            dice[1] = before[0];
            dice[2] = before[5];
            dice[3] = before[3];
            dice[4] = before[4];
            dice[5] = before[1];
        }else if(dir == 3) {
            // 북쪽
            //1,2는 그대로
            dice[0] = before[3];
            dice[3] = before[5];
            dice[4] = before[0];
            dice[5] = before[4];
        }else {
            //남쪽
            // 1,2는 그대로
            dice[0] = before[4];
            dice[3] = before[0];
            dice[4] = before[5];
            dice[5] = before[3];
        }

    }
}