import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		long M = Integer.parseInt(st.nextToken());
		
		long[] arr = new long[N];
		long min = Integer.MAX_VALUE;
		long max = 0;
		for(int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
			if(arr[i] < min) min = arr[i];
			if(arr[i] > max) max = arr[i];
		}
		
		long low = 0; 
		long high = max * M;
//		System.out.println(low + " " + high);
	
		long answer = high;
		while(low <= high) {
//			System.out.println(low + " " + high);
			long mid = (low + high) / 2; // 중앙 시간
			
			long sum = 0;
			for(long time : arr) {
				sum += mid/time;
			}
			
			//이게 m보다 크면
			if(sum >= M) {
				// 여유롭다는 거니까 시간 더 줄임
				high = mid-1;
				answer = Math.min(answer, mid);
			}else {
				//이 시간내에 불가하므로 시간 더 늘림
				low = mid+1;
			}
		}
		
		System.out.println(answer);
	}
}