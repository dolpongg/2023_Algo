import java.util.*;
import java.io.*;

public class Main {
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[][] dp = new int[N+1][K+1];
        
        for(int i = 0; i <= N; i++){
            dp[i][1] = 1;
        }
        for(int i = 1; i <= K; i++){
            dp[0][i] = 1;
        }
        for(int n = 1; n <= N; n++){
            for(int k = 2; k <= K; k++){
                dp[n][k] = (dp[n-1][k] + dp[n][k-1]) % 1_000_000_000;
            }
        }
        System.out.println(dp[N][K]);
    }
}