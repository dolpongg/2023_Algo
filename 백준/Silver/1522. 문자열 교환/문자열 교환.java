import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int answer = Integer.MAX_VALUE;
		char[] input = br.readLine().toCharArray();
		int len  = input.length;
		int aSize = 0;
		for(int i = 0; i < len; i++) {
			if(input[i] == 'a') aSize++;
		}
		
		if(aSize == 0) {
			System.out.println(0);
			return;
		}
		int bCount = 0;
		for(int start = 0; start <= len-1; start++) {
			int end = (start + aSize-1) % len;
			if(start == 0) {
				for(int i = start; i <= end; i++) {
					if(input[i] == 'b') bCount++;
				}
			}else {
				if(input[start-1] == 'b') bCount--;
				if(input[end] == 'b') bCount++;
			}
			
			answer = Math.min(answer, bCount);
			
		}
		
		System.out.println(answer);
		
	}
	
}
//abababababababa