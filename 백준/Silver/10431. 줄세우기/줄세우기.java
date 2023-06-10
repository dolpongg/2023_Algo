import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		for(int n = 1; n <= N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int count = 0;
			
			n = Integer.parseInt(st.nextToken());
			int[] arr = new int[20];
			for(int i = 0; i < 20; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
					
				for(int j = 0; j < i; j++) {
					if(arr[j] >= arr[i]) {
						count++;
					}
				}
		
				
			}
			System.out.println(n + " " + count);
			
		}
	}
 
}