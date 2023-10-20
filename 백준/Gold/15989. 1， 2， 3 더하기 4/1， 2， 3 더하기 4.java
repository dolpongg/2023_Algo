import java.util.*;
import java.io.*;

public class Main {
    static int[][] dp = new int[10001][4];
    public static void main(String args[]) throws Exception{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringBuilder sb = new StringBuilder();
      
      int T = Integer.parseInt(br.readLine());
      
      makeDp();
      
      for(int t = 1; t <= T; t++){
          int n = Integer.parseInt(br.readLine());
          sb.append(dp[n][1] + dp[n][2] + dp[n][3]).append("\n");
      }
      
      System.out.println(sb);
    }
    
    static void makeDp(){
        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;
        
        for(int i = 4; i <10001; i++){
            dp[i][1] = dp[i-1][1];
            dp[i][2] = dp[i-2][1] + dp[i-2][2];
            dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3];
        }
        
    }
}