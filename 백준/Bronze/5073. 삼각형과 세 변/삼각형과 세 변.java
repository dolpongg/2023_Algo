import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		outer : while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] arr = new int[3];
			
			for(int i = 0; i < 3; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				if(arr[i] == 0) break outer;
			}
			Arrays.sort(arr);
			
			if(arr[0] == arr[1] && arr[1] == arr[2])System.out.println("Equilateral");
			else if(arr[2] < arr[0] + arr[1]) {
				if(arr[0] == arr[1] || arr[1] == arr[2]) System.out.println("Isosceles");
				else System.out.println("Scalene");
			}else {
				System.out.println("Invalid");
			}
		}
		
		
		
	}
}