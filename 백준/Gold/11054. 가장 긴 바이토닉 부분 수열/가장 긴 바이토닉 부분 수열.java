import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[][] dp = new int[N][3];
		int max = 1;

		for (int i = 0; i < N; i++) {

			int num = Integer.parseInt(st.nextToken());
			dp[i][0] = num;
			dp[i][1] = 1; 
			dp[i][2] = 1;
			for (int j = 0; j < i; j++) {
				if (dp[j][0] < num)
                   dp[i][1] = Math.max(dp[i][1], dp[j][1] + 1);
                if (dp[j][0] > num) {
                   dp[i][2] = Math.max(dp[i][2], dp[j][2] + 1);
                   dp[i][2] = Math.max(dp[i][2], dp[j][1] + 1);
                   }
                }
			max = Math.max(max, Math.max(dp[i][1], dp[i][2]));
			}
		System.out.println(max);
		}
}