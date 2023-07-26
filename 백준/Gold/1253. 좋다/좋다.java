import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
    static int N;
    static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = 0;
        
        arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr);
        
       outer:for(int target = N-1; target >= 0; target--){
            int start = 0;
            int end = N-1;
            
            while(start < end){
                if(start == target){
                    start++;
                    continue;
                }
                if(end == target){
                    end--;
                    continue;
                }
                int sum = arr[start] + arr[end];
                if(sum == arr[target]){
                    answer++;
                    continue outer;
                }
                if(sum < arr[target]) start++;
                else end--;
            }
        }
        
        System.out.println(answer);
		
	}
	
}