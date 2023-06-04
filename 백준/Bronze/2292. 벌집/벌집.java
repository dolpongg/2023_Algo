import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int sum = 0;
		int i = 1;
		
		if(N == 1) {
			System.out.println(1);
			return;
		}
		while(6*sum+1 < N) {
			sum += i;
			i++;
		}
		
		System.out.println(i);
	}
}