import java.util.*;
import java.io.*;

public class Main {
	static int N,S;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = 0;
		long sum = 0;
		int answer = Integer.MAX_VALUE;
		sum = 0;
		while(end <= N) {
			if(sum < S) {
				sum += arr[end++];
			}else {
				sum-=arr[start];
				if(answer > end - start) {
					answer = end - start;
				}
				start++;
			}
		}
		
		
		if(answer == Integer.MAX_VALUE) {
			System.out.println(0);
		}else {
			System.out.println(answer);
		}
	}
	

}