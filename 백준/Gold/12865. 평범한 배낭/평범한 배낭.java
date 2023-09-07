import java.util.*;
import java.io.*;

public class Main {
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[][] item = new int[N+1][2];
        for(int n = 1; n <= N; n++){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            item[n] = new int[] {w,v};
        }
        
        int[][] dp = new int[N+1][K+1];
        
        for(int n = 1; n <= N; n++){
            for(int k = 1; k <= K; k++){
                if(k < item[n][0]) dp[n][k] = dp[n-1][k];
                else dp[n][k] = Math.max(dp[n-1][k], dp[n-1][k-item[n][0]] + item[n][1]);
            }
        }
        
        System.out.println(dp[N][K]);
    }
}