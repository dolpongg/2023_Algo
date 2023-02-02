import java.util.*;
import java.io.*;

public class Main {

	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
        int[][] dpMin = new int[N][3];
        int[][] dpMax = new int[N][3];
        
        //입력받기
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()); // 한줄 
            int n0 = Integer.parseInt(st.nextToken());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            
            if(i == 0){
                dpMin[i][0] = n0;
                dpMin[i][1] = n1;
                dpMin[i][2] = n2;
                dpMax[i][0] = n0;
                dpMax[i][1] = n1;
                dpMax[i][2] = n2;
            }else{
                dpMin[i][0] = Math.min(dpMin[i-1][0], dpMin[i-1][1]) + n0;
                dpMin[i][1] = Math.min(Math.min(dpMin[i-1][0], dpMin[i-1][1]), dpMin[i-1][2]) + n1;
                dpMin[i][2] = Math.min(dpMin[i-1][1], dpMin[i-1][2]) + n2;
                
                dpMax[i][0] = Math.max(dpMax[i-1][0], dpMax[i-1][1]) + n0;
                dpMax[i][1] = Math.max(Math.max(dpMax[i-1][0], dpMax[i-1][1]), dpMax[i-1][2]) + n1;
                dpMax[i][2] = Math.max(dpMax[i-1][1], dpMax[i-1][2]) + n2;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(Math.max(Math.max(dpMax[N-1][0],dpMax[N-1][1]), dpMax[N-1][2])).append(" ");
        sb.append(Math.min(Math.min(dpMin[N-1][0],dpMin[N-1][1]), dpMin[N-1][2]));
        
        System.out.println(sb);
        
        
        
		
		
	}
}