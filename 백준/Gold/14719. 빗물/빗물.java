import java.util.*;
import java.io.*;

public class Main {
	
	static int H,W;
	static int[] load;
	static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		
		st = new StringTokenizer(br.readLine());
		load = new int[W];
		for(int i = 0; i < W; i++) {
			load[i] = Integer.parseInt(st.nextToken());
		}
		
		
		//바닥부터
		for(int h = 1; h <= H; h++) {
			//왼쪽 벽 있었나 체크
			boolean left = false;
			int sum = 0;
			//왼쪽부터
			for(int w = 0; w < W; w++) {
				//벽이면 
				if(h <= load[w]) {
					if(left){
						answer += sum;
						sum = 0;
					}else {
						left = true;
					}
				}else {//벽이 아니면
					if(left) {
						sum++;
					}
				}
			}
		}
		System.out.println(answer);
	}

	
	
}