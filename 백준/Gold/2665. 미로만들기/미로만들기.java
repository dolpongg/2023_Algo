import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
 
public class Main {
    static int N;
    static int arr[][], map[][];
    static int[][] deltas = {{0,1}, {1,0}, {0,-1}, {-1,0}};
    static int maxValue = Integer.MAX_VALUE;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1][N+1];
        map = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            String str = br.readLine();
            for(int j=1; j<=N; j++) {
                arr[i][j] = str.charAt(j-1)-'0';
                map[i][j] = Integer.MAX_VALUE;
            }
        }
        bfs(1,1);

        System.out.println(map[N][N]);
    }
    
    public static void bfs(int r, int c) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(r,c));
        map[r][c] = 0;
        
        while(!queue.isEmpty()){
            Point p = queue.poll();
            
            for(int[] delta : deltas) {
                int newX = p.x + delta[0];
                int newY = p.y + delta[1];
                
                if(1<=newX && newX<=N && 1<=newY && newY<=N && map[newY][newX] > map[p.y][p.x]) {
                    if(arr[newY][newX] == 1) {
                        queue.add(new Point(newX, newY));
                        map[newY][newX] = map[p.y][p.x];
                    }else {
                        queue.add(new Point(newX, newY));
                        map[newY][newX] = map[p.y][p.x] + 1;
                    }
                }
            }
        }
    }
    
    
}