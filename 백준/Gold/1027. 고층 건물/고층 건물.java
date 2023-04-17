import java.util.*;
import java.io.*;

public class Main {
	static int W;
	static int[] buildings;
	static double[] desc;
	static int answer = 0;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		W = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		buildings = new int[W];
		
		for(int i = 0; i < W; i++) {
			buildings[i] = Integer.parseInt(st.nextToken());
		}
		for(int first = 0; first < W; first++) {
			desc = new double[W];
			for(int second = 0; second < W; second++) {
				if(buildings[second] - buildings[first] == 0 || first == second) {
					desc[second] = 0.0;
				}
				else {
					desc[second] = (double)(buildings[second] - buildings[first])/(double)(second - first);
				}
			}
			
			//왼쪽
			double min = (double) Integer.MAX_VALUE;
			int sum = 0;
			for(int left = first-1; left >= 0; left--) {
				//가장 작은 값 갱신하면서 카운트 
				if(min > desc[left]) {
					min = desc[left];
					sum++;
				}
			}
			//오른쪽
			double max = (double) Integer.MIN_VALUE;
			for(int right = first + 1; right < W; right++) {
				if(max < desc[right]) {
					max = desc[right];
					sum++;
				}
			}
			answer = Math.max(sum, answer);
		}
		
		System.out.println(answer);
	}

	
	
}