import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int[] numArr;
	static int answer = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		numArr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(numArr);
		
		for(int i = 0; i < N; i++) {
			int left = 0;
			int right = N-1;
			
			while(left < right) {
				if(left == i) {
					left++;
					continue;
				}
				if(right == i) {
					right--;
					continue;
				}
				
				if(numArr[i] > numArr[left] + numArr[right]) {
					left++;
				}else if(numArr[i] == numArr[left] + numArr[right]) {
					answer++;
					break;
				}else {
					right--;
				}
			}
		}
		
		System.out.println(answer);
	}

	
	
}