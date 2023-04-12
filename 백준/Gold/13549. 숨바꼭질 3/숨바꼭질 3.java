import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int K;
	static int[] load = new int[100001];
	
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Arrays.fill(load, Integer.MAX_VALUE);
		
		
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {N, 0});
		load[N] = 0;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int place = cur[0];
			int time = cur[1];
			
			if(place + 1 < 100001 && time + 1 < load[place + 1]) {
				load[cur[0] + 1] = time + 1;
				q.offer(new int[] {place + 1, time + 1});
				
				}
			if(place - 1 >= 0 && time + 1 < load[place - 1]) {
				load[cur[0] - 1] = time + 1;
				q.offer(new int[] {place - 1, time + 1});
			}
			if(place * 2 < 100001 && time < load[place * 2]) {
				load[cur[0] * 2] = cur[1];
				q.offer(new int[] {place * 2, time});
			}
			
//			System.out.println(Arrays.toString(load));
		}
		
		System.out.println(load[K]);
		
	}
}