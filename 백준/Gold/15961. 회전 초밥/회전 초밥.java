import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
	static int N;
	static int[] eating  = new int[3001];
	static int[] belt;
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int answer = 0;
		
		N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		belt = new int[N];
		for(int n = 0; n < N; n++) {
			belt[n] = Integer.parseInt(br.readLine());
		}//입력 완료
		
		boolean coupon = false;
		int count = 0;
		for(int kk = 0; kk < k; kk++) {
			eating[belt[kk]]++;
			if(eating[belt[kk]] == 1) {
				count++;
				if(belt[kk] == c)coupon = true;
			}
		}
		
		for(int i = 1; i < N; i++) {
			eating[belt[i-1]]--;
			if(eating[belt[i-1]] == 0) {
				count--;
				if(belt[i-1] == c) coupon = false;
			}
			
			eating[belt[(k+i-1) % N]]++;
			if(eating[belt[(k+i-1) % N]] == 1) {
				count++;
				if(belt[(k+i-1) % N] == c) coupon = true;
			}
			
			if(!coupon){
				answer = Integer.max(answer, count+1);
			}else {
				answer = Integer.max(answer,count);
			}
		}
		
		System.out.println(answer);
    }
 
}
//8 30 4 30
//7
//9
//7
//30
//2
//7
//9
//25