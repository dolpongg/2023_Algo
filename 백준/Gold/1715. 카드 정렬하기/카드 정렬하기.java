import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int answer = 0;
		
		PriorityQueue<Integer> q = new PriorityQueue<>();
		for(int n = 0; n < N; n++) {
			q.offer(Integer.parseInt(br.readLine()));
		}
		
		while(true) {
			int cur = q.poll();
			if(q.isEmpty())break;
			int sum = cur+q.poll();
			answer += sum;
			q.offer(sum);
		}
		
		System.out.println(answer);
	}
 
}
//3
//10
//20
//40