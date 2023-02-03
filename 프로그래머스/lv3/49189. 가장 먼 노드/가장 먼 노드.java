import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        boolean[][] arr = new boolean[n+1][n+1];
        
        for(int[] nodes : edge){
            arr[nodes[0]][nodes[1]] = true;
            arr[nodes[1]][nodes[0]] = true;
        }
        
        boolean[] visited = new boolean[n+1];
        
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(1);
        visited[1] = true;
        
        int depth = 0;
        while(!q.isEmpty()){
            int size = q.size();
            answer = size;
            while(size-- > 0){ // 빌 때까지 반복
                int cur = q.poll();
                
                for(int i = 0; i < n+1; i++){
                    if(arr[cur][i] && !visited[i]){
                        q.offer(i);
                        visited[i] = true;
                    }
                }
            }
            depth++;
        }
        
        return answer;
    }
}