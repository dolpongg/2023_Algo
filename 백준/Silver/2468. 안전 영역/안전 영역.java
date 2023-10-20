import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int answer = 0;
    static int[][] map;
    static int[][] deltas = {{0,1},{0,-1},{1,0},{-1,0}};
    public static void main(String args[]) throws Exception{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      map = new int[N][N];
      
      for(int r = 0; r < N; r++){
          StringTokenizer st = new StringTokenizer(br.readLine());
          for(int c = 0; c < N; c++){
              map[r][c] = Integer.parseInt(st.nextToken());
          }
      }
      
      for(int n = 0; n <= 100; n++){
          bfs(n);
      }
      
      System.out.println(answer);
    }

    static void bfs(int n){
        int count = 0;
        boolean[][] visited = new boolean[N][N];
        
        for(int r = 0; r < N; r++){
            for(int c = 0; c < N; c++){
                if(map[r][c] > n && !visited[r][c]) {
                    count++;
                    
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[] {r,c});
                    visited[r][c] = true;
                    
                    while(!q.isEmpty()){
                        int[] cur = q.poll();
                        
                        for(int[] delta : deltas){
                            int nr = cur[0] + delta[0];
                            int nc = cur[1] + delta[1];
                            
                            if(isIn(nr,nc) && map[nr][nc] > n && !visited[nr][nc]){
                                q.offer(new int[] {nr,nc});
                                visited[nr][nc] = true;
                            }
                        }
                    }
                }
            }
        }

        answer = Math.max(answer,count);
    }
    
    static boolean isIn(int nr, int nc){
        return nr >= 0 && nr < N && nc >= 0 && nc < N;
    }
    
}