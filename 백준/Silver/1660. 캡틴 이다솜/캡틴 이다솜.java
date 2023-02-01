import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static List<Integer> three = new ArrayList<>();
	static List<Integer> four = new ArrayList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		int num = 0;
		int index = 0;
		while(num < N) {
			num += ++index;
			three.add(num);
		}
		
		int sum = 0;
		for(int i = 0; i < three.size(); i++) {
			sum += three.get(i);
			if(sum > N) {
				break;
			}
			four.add(sum);
		}
		
		int[] dp = new int[N+1];
		
		Arrays.fill(dp,N);//최대값으로
		
		for(int i = 0; i < four.size(); i++) {
			dp[four.get(i)] = 1;
			for(int j = four.get(i)+1; j <= N; j++) {
				dp[j] = Math.min(dp[j], dp[j - four.get(i)] + dp[four.get(i)]);
			}
		}
		System.out.println(dp[N]);

		
		
	}
}