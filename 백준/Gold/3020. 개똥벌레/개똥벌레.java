import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
	static int N,H;
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		int[] answer = new int[N+1];
		
		int[] up = new int[H+1];
		int[] down = new int[H+1];
		
		for(int i = 0; i < N/2; i++) {
			int down_num = Integer.parseInt(br.readLine());
			int up_num = Integer.parseInt(br.readLine());
			
			down[down_num]++;
			up[up_num]++;
		}
		
		//누적
		for(int i = 1; i <= H; i++) {
			down[i] += down[i-1];
			up[i] += up[i-1];
		}
		
		for(int h = 1; h <= H; h++) {
			int obstacle = (down[H] - down[h-1]) + (up[H] - up[H-h]);
			
			answer[obstacle]++;
		}
		
		for(int n = 0; n <= N; n++) {
			if(answer[n] >= 1) {
				System.out.println(n + " " + answer[n]);
				return;
			}
		}
		
    }

	
 
}
//
//6 7
//1
//5
//3
//3
//5
//1