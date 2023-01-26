import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= TC; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int r1 = Integer.parseInt(st.nextToken());
			
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			
			System.out.println(tangent_point(x1, y1, r1, x2, y2, r2));
		}
	}
	
	// 접점 개수 구하는 함수
		public static int tangent_point(int x1, int y1, int r1, int x2, int y2, int r2) {
	    
			int distance_pow = (int) (Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
	 
	 
			//무한대
			if(x1 == x2 && y1 == y2 && r1 == r2) {
				return -1;
			}
			
			//거리 > 반지름 합 
			else if(distance_pow > Math.pow(r1 + r2, 2)) {
				return 0;
			}
	 
			// 거리 < 반지름 차
			else if(distance_pow < Math.pow(r2 - r1, 2)) {
				return 0;
			}
			
			// 내접
			else if(distance_pow == Math.pow(r2 - r1, 2)) {
				return 1;
			}
	        
			
			// 외접 
			else if(distance_pow == Math.pow(r1 + r2,2)) {
				return 1;
			}
			
			else {
				return 2;
			}
			
		}
		
}