import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
	static int M, N, L;
	static int[] hunters;
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int answer = 0;
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		hunters = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int m = 0; m < M; m++) {
			hunters[m] = Integer.parseInt(st.nextToken());
		}
		
		//이분 탐색을 위한 정렬
		Arrays.sort(hunters);
		
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(c > L) continue;
//			System.out.println("======");
//			System.out.println(r + " " + c);
			int mid = findHunter(0,M-1, r);
			
			if(Math.abs(hunters[mid] - r) + c <= L) answer++;
			
		}
		
		System.out.println(answer);
		
    }

	private static int findHunter(int left, int right, int target) {

		if(left > right) {
			if(left > M-1) return right;
			if(right < 0) return left;
			
			if(Math.abs(hunters[left] - target) > Math.abs(hunters[right] - target)) return right;
			else return left;
		}
		
		
		int mid = (right + left) / 2;
		
		if(hunters[mid]  == target ) return mid;
		else if(hunters[mid] > target) return findHunter(left, mid-1, target);
		else return findHunter(mid+1, right, target);
	}
 
}
//4 8 4
//6 1 4 9
//7 2
//3 3
//4 5
//5 1
//2 2
//1 4
//8 4
//9 4