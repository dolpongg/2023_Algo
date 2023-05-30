import java.util.*;
import java.io.*;

public class Main {
	static int N,D,K,C;
	static int[] belt;
	static int[] window;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		belt = new int[N]; // 회전초밥 벨트
		window = new int[D+1];//슬라이딩 윈도우
		
//		System.out.println(Arrays.toString(window));
		for(int i = 0; i < N; i++) {
			belt[i] = Integer.parseInt(br.readLine());
		}
		
		int start = 0;
		int end = K-1;
		int max = 0;
		int before = 0;
		
		for(int i = 0; i <= end; i++) {
			if(window[belt[i]] == 0) {
				before++;
			}
			window[belt[i]]++;
		}
		if(window[C] == 0) {
			before++;
		}
		max = before;
		if(window[C] == 0) {
			before--;
		}
		
		
		start++;
		if(end +1 >= N) {
			end = 0;
		}else {
			end++;
		}
//		System.out.println(Arrays.toString(window));
//		System.out.println("=========");
//		System.out.println(max);
		while(start < N) {
			int sum = before;
			if(window[belt[start-1]] == 1) {
				sum--;
			}
			window[belt[start-1]]--;
			
			if(window[belt[end]] == 0) {
				sum++;
			}
			window[belt[end]]++;
			
			if(window[C] == 0) {
				sum++;
			}
			if(sum > max) {
				max = sum;
			}
			if(window[C] == 0) {
				before = sum-1;
			}else {
				before = sum;
			}
			
			
			start++;
			if(end +1 >= N) {
				end = 0;
			}else {
				end++;
			}
//			System.out.println(Arrays.toString(window));
//			System.out.println("=========");
//			System.out.println(sum);
//			
		}
		
		
		System.out.println(max);
	    }
}