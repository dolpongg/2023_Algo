import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

	static class Node{
		int to, w;
		
		public Node(int to, int dist) {
			this.to = to; 
			this.w = dist;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		List<Node>[] list = new ArrayList[n+1];
		for(int i=0; i<=n; i++) list[i] = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			String[] arr = br.readLine().split(" ");
			int from = Integer.parseInt(arr[0]);
			for(int j=1; j<arr.length-2; j+=2) {
				int to = Integer.parseInt(arr[j]);
				int w = Integer.parseInt(arr[j+1]);
				list[from].add(new Node(to, w));
			}
		}
		
		int[] dist = bfs(list, 1, n);
		int start = 1;
		for(int i=2; i<=n; i++) 
			if(dist[i]>dist[start]) start = i;
		
		dist = bfs(list, start, n);
		Arrays.sort(dist);
		System.out.print(dist[n]);
	}
	
	static int[] bfs(List<Node>[] list, int start, int n) {
		int[] dist = new int[n+1];
		boolean[] visit = new boolean[n+1];
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(start);
		visit[start] = true;
		
		while (!queue.isEmpty()) {
			int k = queue.poll();
			for(Node node : list[k]) {
				if(!visit[node.to]) {
					visit[node.to] = true;
					queue.add(node.to);
					dist[node.to] = dist[k] + node.w;
				}
			}
		}
		return dist;
	}
}