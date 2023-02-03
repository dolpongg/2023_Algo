import java.util.*;
import java.io.*;


public class Main {
    static int N, M;
    
    static class Point implements Comparable<Point>{
        int node;
        int w;
        

		public Point(int node, int w){
            this.node = node;
            this.w = w;
        }

		
		public int compareTo(Point p) {
			return Integer.compare(this.w, p.w);
		}

    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        List<int[]>[] graph = new ArrayList[N+1];
        for(int i=0; i<=N; i++){
           graph[i] = new ArrayList<int[]>();
        }
        
        int[] dp = new int[N+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
           
            graph[from].add(new int[] {to, weight});
            graph[to].add(new int[] {from, weight});
        }
        
        PriorityQueue<Point> queue = new PriorityQueue<>();
        
        queue.offer(new Point(1,0));
        dp[1] = 0;
   
        while(!queue.isEmpty()){
            Point cur = queue.poll();
            if(cur.node == N) {
                System.out.println(cur.w);
                break;
            }
            
            for(int[] next : graph[cur.node]){
            	if(dp[next[0]] > dp[cur.node] + next[1]) {
            		dp[next[0]] = dp[cur.node] + next[1];
            		queue.offer(new Point(next[0], dp[next[0]]));
            	}
                
            }
        }
        
        
        
    }
}