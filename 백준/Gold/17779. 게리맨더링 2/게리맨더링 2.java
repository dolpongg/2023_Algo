import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
    static int[][] map;
    static int total = 0;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        
        map = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                total += map[i][j];
           
            }
        } 
//        
//        for(int r = 1; r <= N; r++) {
//    		for(int c = 1; c <= N; c++) {
//    			System.out.print(map[r][c] + " ");
//    		}
//    		System.out.println();
//    	}
//
//        System.out.println(total);
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                for (int d1 = 1; d1 < N; d1++) {
                    for (int d2 = 1; d2 < N; d2++) {
                    	// d1, d2 ≥ 1, 1 ≤ x < x+d1+d2 ≤ N, 1 ≤ y-d1 < y < y+d2 ≤ N
                        if (x + d1 + d2 >= N) continue;
                        if (y - d1 < 0 || y + d2 >= N) continue;
                        find(x, y, d1, d2);
                    }
                }
            }
        }

        System.out.println(min);
    }

    static void find(int x, int y, int d1, int d2) {
        boolean[][] five = new boolean[N+1][N+1];
        int[][] debug = new int[N+1][N+1];

        // 경계선
        for (int i = 0; i <= d1; i++) {
            five[x + i][y - i] = true;
            five[x + d2 + i][y + d2 - i] = true;
        }
        
      

        for (int i = 0; i <= d2; i++) {
            five[x + i][y + i] = true;
            five[x + d1 + i][y - d1 + i] = true;
        }

        int[] sum = new int[5];

        // 1 
        //1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
       
        for (int r = 1; r < x + d1; r++) {
            for (int c = 1; c <= y; c++) {
                if (five[r][c]) break;
                sum[0] += map[r][c];
                debug[r][c] = 1;
            }
        }
//        System.out.println("====1");
//        for(int r = 1; r <= N; r++) {
//    		for(int c = 1; c <= N; c++) {
//    			System.out.print(debug[r][c] + " ");
//    		}
//    		System.out.println();
//    	}

        
        // 2
        //2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
        
        for (int r = 1; r <= x + d2; r++) {
            for (int c = N; c > y; c--) {
                if (five[r][c]) break;
                sum[1] += map[r][c];
                debug[r][c] = 2;
            }
        }

        // 3 
        //3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
        for (int r = x + d1; r <= N; r++) {
            for (int c = 1; c < y - d1 + d2; c++) {
                if (five[r][c]) break;
                sum[2] += map[r][c];
                debug[r][c] = 3;
            }
        }
//        System.out.println("====3");
//        for(int r = 1; r <= N; r++) {
//    		for(int c = 1; c <= N; c++) {
//    			System.out.print(debug[r][c] + " ");
//    		}
//    		System.out.println();
//    	}


        // 4
        //번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
        for (int r = N; r > x+d2; r--) {
            for (int c = N ; c >=y - d1 + d2; c--) {
                if (five[r][c]) break;
                sum[3] += map[r][c];
                debug[r][c] = 4;
            }
        }
        

        // 5
        sum[4] = total;

        for (int i = 0; i < 4; i++) {
            sum[4] -= sum[i];
        }

        // 정렬
        Arrays.sort(sum);

        min = Math.min(min, sum[4] - sum[0]);
    }
	
}