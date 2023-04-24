import java.util.*;
import java.io.*;

public class Main {

	static int N,M,L,K;
	
	static class Star{
		int r,c;
		
		public Star(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static Star[] stars;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		stars = new Star[K];

	        for(int k =0; k<K;k++){
	            st = new StringTokenizer(br.readLine());
	            int r = Integer.parseInt(st.nextToken());
	            int c = Integer.parseInt(st.nextToken());
	            stars[k] = new Star(r,c);
	        }

	        int answer=0;
	        
	        for(int s=0;s<K;s++){

	            Star star = stars[s];
	            
	            for(int r = star.r-L; r <= star.r; r++) {
	            	int c = star.c;
	            	if(r<0) continue;
	            	
	            	int count = 0;
	            	
	            	for(int i = 0; i < K; i++) {
	            		Star target = stars[i];
	            		if(r <= target.r && target.r <= r+L && c <= target.c && target.c <= c+L) {
	            			count++;
	            		}
	            		
		            	
		            	answer = Math.max(answer, count);
	            	}
	            
	            }
	        }
	        System.out.println(K-answer);
	    }


	

}