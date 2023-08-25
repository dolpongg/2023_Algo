import java.util.*;
import java.io.*;


public class Main
{
	static int N,M;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    	
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
 
        int i = 0, j = 0;
        int answer = Integer.MAX_VALUE;
        
        while (i < N) {
            if (arr[i] - arr[j] < M) {
                i++;
                continue;
            }
 
            if (arr[i] - arr[j] == M) {
            	answer = M;
                break;
            }
 
            answer = Math.min(answer, arr[i] - arr[j]);
            j++;
        }
    	
    	
        System.out.println(answer);
    }


    
}