import java.util.*;
import java.io.*;

public class Main {
    static int M,N;
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        
        int[][] map = new int[M][M];
        for(int r = 0; r < M; r++){
            Arrays.fill(map[r],1);
        }
        

        for(int n = 1; n <= N; n++){
            st = new StringTokenizer(br.readLine());
            int zero = Integer.parseInt(st.nextToken());
            int r = M-1;
            int c = 0;
            while(zero-- > 0){
                map[r][c] += 0;
                if(r > 0) {
                    r--;
                }
                else c++;
            }
            
            int one = Integer.parseInt(st.nextToken());
            while(one-- > 0){
                map[r][c] += 1;
                if(r > 0) r--;
                else c++;
            }
            
            int two = Integer.parseInt(st.nextToken());
            while(two-- > 0){
                map[r][c] += 2;
                if(r > 0) r--;
                else c++;
            }
            
        }
        
        for(int r = 1; r < M; r++){
            for(int c = 1; c < M; c++){
                map[r][c] = Math.max(map[r-1][c], Math.max(map[r][c-1], map[r-1][c-1]));
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int r = 0; r < M; r++){
            for(int c = 0; c < M; c++){
                sb.append(map[r][c]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}