import java.util.*;
import java.io.*;

public class Main {

	static int N;
	static int M;
	static List<Integer>[] left;
	static List<Integer>[] right;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		left = new LinkedList[N+1];
		right = new LinkedList[N+1];
		
		for(int n = 1; n <= N; n++) {
			left[n] = new LinkedList<>();
			right[n] = new LinkedList<>();
		}
		
		
		for(int m = 0; m< M; m++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			left[from].add(to);
			right[to].add(from);
			
		}
		
		for(int i = 1; i <= N; i++) {
			int count = N-1;
			
			boolean[] visited = new boolean[N+1];
			Queue<Integer> q = new LinkedList<>();
			
			q.offer(i);
			visited[i] = true;
			
			while(!q.isEmpty()) {
				int cur = q.poll();
				if(cur != i)count--;
				
				for(Integer next : left[cur]) {
					if(!visited[next]) {
						q.offer(next);
						visited[next] = true;
					}
				}
			}
			
			visited = new boolean[N+1];
			q = new LinkedList<>();
			
			q.offer(i);
			visited[i] = true;
			
			while(!q.isEmpty()) {
				int cur = q.poll();
				if(cur != i)count--;
				
				for(Integer next : right[cur]) {
					if(!visited[next]) {
						q.offer(next);
						visited[next] = true;
					}
				}
			}
			
			sb.append(count).append("\n");
			
		}
		
		System.out.println(sb);
	}

	
	
}