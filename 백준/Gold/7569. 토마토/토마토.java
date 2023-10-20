import java.util.*;
import java.io.*;

public class Main {
    static boolean[][][] visited;
    static int[][][] box;
    static Queue<int[]> q = new LinkedList<>();
    static int M,N,H,total;
    
    static int[][] deltas = {{0,0,1},{0,0,-1},{0,1,0},{0,-1,0},{1,0,0},{-1,0,0}};
    public static void main(String args[]) throws Exception{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(br.readLine());
      M = Integer.parseInt(st.nextToken());
      N = Integer.parseInt(st.nextToken());
      H = Integer.parseInt(st.nextToken());
      total = M*N*H;
      
      visited = new boolean[M][N][H];
      box = new int [M][N][H];
      for(int h = 0; h < H; h++){
          for(int n = 0; n < N; n++){
              st = new StringTokenizer(br.readLine());
              for(int m = 0; m < M; m++){
                  int num = Integer.parseInt(st.nextToken());
                  
                  if(num == -1){
                    visited[m][n][h] = true;
                    total--;
                  } 
                  if(num == 1) {
                      total--;
                      visited[m][n][h] = true;
                      q.offer(new int[] {m,n,h});
                  }
                  
                  box[m][n][h] = num;
              }
          }
      }
      
      System.out.println(bfs());
      
    }
    
    static int bfs(){
        int depth = 0;
        while(!q.isEmpty()){
            if(total == 0) return depth;
            int size = q.size();
            while(size-- > 0){
                int[] cur = q.poll();
                
                for(int[] delta : deltas){
                    int nr = cur[0] + delta[0];
                    int nc = cur[1] + delta[1];
                    int nh = cur[2] + delta[2];
                    
                    if(isIn(nr,nc,nh) && !visited[nr][nc][nh]){
                        total--;
                        visited[nr][nc][nh] = true;
                        q.offer(new int[] {nr,nc,nh});
                    }
                }
            }
            depth++;
        }
        
        if(total == 0) return depth;
        else return -1;
    }
    
    static boolean isIn(int nr, int nc, int nh){
        return nr >= 0 && nr < M && nc >= 0 && nc < N && nh >=0 && nh < H;
    }
    
}