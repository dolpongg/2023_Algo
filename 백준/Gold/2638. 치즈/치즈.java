import java.util.*;
import java.io.*;

public class Main {
    static int[][] map;
    static int R,C;
    static int last = 0;
    static int[][] deltas = {{0,1},{0,-1},{1,0},{-1,0}};
    
    public static void main(String args[]) throws Exception{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      R = Integer.parseInt(st.nextToken());
      C = Integer.parseInt(st.nextToken());
      
      map = new int[R][C];
      
      for(int r = 0; r < R; r++){
          st = new StringTokenizer(br.readLine());
          for(int c = 0; c < C; c++){
              map[r][c] = Integer.parseInt(st.nextToken());
              if(map[r][c] == 1) last++;
          }
      }
      
      int answer = 0;
      while(last > 0){
        Queue<int[]> cheeze = new LinkedList<>();
        
        boolean[][] visited = new boolean[R][C];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0,0});
        visited[0][0] = true;
        while(!q.isEmpty()){
          int[] cur = q.poll();
          
          for(int[] delta: deltas){
              int nr = cur[0] + delta[0];
              int nc = cur[1] + delta[1];
              
              if(isIn(nr,nc) && !visited[nr][nc]){
                  if(map[nr][nc] == 1) cheeze.offer(new int[] {nr,nc});
                  else q.offer(new int[] {nr,nc});
                  visited[nr][nc] = true;
                }
            }
        }
        
        int size = cheeze.size();
        while(size-- > 0){
            int[] cur = cheeze.poll();
            
            int count = 0;
            for(int[] delta : deltas){
                int nr = cur[0] + delta[0];
                int nc = cur[1] + delta[1];
                
                if(isIn(nr,nc) && map[nr][nc] == 0 && visited[nr][nc]){
                    count++;
                }
            }
            
            if(count >= 2){
                map[cur[0]][cur[1]] = 0;
                last--;
                // visited[cur[0]][map[cur[1]] = true;
            } 
            visited[cur[0]][cur[1]] = false;
        }
        
        answer++; 
      }
      
      System.out.println(answer);
        
        
      
     
      }
        
    static boolean isIn(int nr, int nc){
        return nr >=0 && nr < R && nc >= 0 && nc < C;
    }
    
}