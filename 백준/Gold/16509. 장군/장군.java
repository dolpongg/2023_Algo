import java.util.*;
import java.io.*;


public class Main
{
	static HashMap<int[], int[][]> deltas = new HashMap<>() {{
		put(new int[] {-3,-2}, new int[][] {{-1,0}, {-2,-1}});
		put(new int[] {-3,2}, new int[][] {{-1,0}, {-2,1}});
		put(new int[] {-2,3}, new int[][] {{0,1}, {-1,2}});
		put(new int[] {2,3}, new int[][] {{0,1}, {1,2}});
		put(new int[] {3,2}, new int[][] {{1,0}, {2,1}});
		put(new int[] {3,-2}, new int[][] {{1,0}, {2,-1}});
		put(new int[] {2,-3}, new int[][] {{0,-1}, {1,-2}});
		put(new int[] {-2,-3}, new int[][] {{0,-1}, {-1,-2}});
	}};

	static int[] king;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    	
        
    	int startR = Integer.parseInt(st.nextToken());
    	int startC = Integer.parseInt(st.nextToken());
    	
    	st = new StringTokenizer(br.readLine());
    	
    	king = new int[2];
    	king[0] = Integer.parseInt(st.nextToken());
    	king[1] = Integer.parseInt(st.nextToken());
    	
    	
    	boolean[][] visited = new boolean[10][9];
    	Queue<int[]> q = new LinkedList<>();
    	q.offer(new int[] {startR, startC});
    	visited[startR][startC] = true;
    	
    	int answer = 0;
    	while(!q.isEmpty()) {
    		int size = q.size();
    		while(size-- > 0) {
    			int[] cur = q.poll();
    			
    			if(cur[1] == king[1] && cur[0] == king[0]) {
    				System.out.println(answer);
    				return;
    			}
    			
    			outer : for(Map.Entry<int[], int[][]> delta : deltas.entrySet()) {
    				int[] next = delta.getKey();
    				int[][] obs = delta.getValue();
    				
    				for(int[] ob : obs) {
    					if(cur[0] + ob[0] == king[0] && cur[1] + ob[1] == king[1]) continue outer;
    				}
    				
    				int nr = cur[0] + next[0];
    				int nc = cur[1] + next[1];
    				
    				if(isIn(nr,nc) && !visited[nr][nc]) {
    					q.offer(new int[] {nr,nc});
    					visited[nr][nc] = true;
    				}
    			}
    		}
    		answer++;
    	}
    	
    	System.out.println(-1);
    	
    	
        
    }
	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < 10 && nc >= 0 && nc < 9;
	}

    
}