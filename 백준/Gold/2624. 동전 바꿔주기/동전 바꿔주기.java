import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int money = Integer.parseInt(br.readLine());// 만들어야하는 지폐
		int[] dp = new int[money+1];
		dp[0] = 1;
		
		int coinNum = Integer.parseInt(br.readLine());// 동전종류 
		
		for(int c = 0; c < coinNum; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int coin = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
				for(int num = money; num >= 0; num--) {
					for(int cc = cnt; cc >= 1; cc--) {
						if(dp[num] > 0) {
							if(num + cc * coin <= money) {
								dp[num + cc * coin] += dp[num];
							}
						}
				}
			}
//				System.out.println(coin);
//				System.out.println(Arrays.toString(dp));
		}
		System.out.println(dp[money]);
		
	}
}