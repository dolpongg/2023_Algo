import java.util.*;
import java.io.*;


public class Main
{
    static class Piece{
        int r,c;
        int dir;

        Piece(int r, int c, int dir){
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

        int[] next(){
            if(dir == 1)return new int[] {r, c + 1};
            else if(dir == 2)return new int[] {r, c - 1};
            else if(dir == 3)return new int[] {r-1, c};
            else return new int[] {r+1, c};
        }

        void changeDir(){
            if(dir == 1) dir = 2;
            else if(dir == 2) dir = 1;
            else if(dir == 3) dir = 4;
            else dir = 3;
        }

    }
    static class Point{
        Deque<Integer> deque;
        int color;

        Point(int color){
            deque = new ArrayDeque<>();
            this.color = color;
        }
    }
    static int N, K;
    static Point[][] map;
    static Piece[] pieces;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new Point[N][N];
        pieces = new Piece[K];
        for(int r = 0; r < N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++){
                map[r][c] = new Point(Integer.parseInt(st.nextToken()));
            }
            
        }

        for(int k = 0; k < K; k++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int dir = Integer.parseInt(st.nextToken());
            pieces[k] = new Piece(r,c,dir);
            map[r][c].deque.offer(k);
        }//입력 완료

        int turn = 0;
        while(true){
            turn++;
            for(int k = 0; k < K; k++){
                int yr = pieces[k].r;
                int yc = pieces[k].c;
                int[] next = pieces[k].next();
                int nr = next[0];
                int nc = next[1];
                //범위 넘어가는지 확인
                if(!isIn(nr, nc)){
                    pieces[k].changeDir();
                    next = pieces[k].next();
                    if(!isIn(next[0], next[1]) || map[next[0]][next[1]].color == 2) continue;
                    nr = next[0];
                    nc = next[1];
                }
                //파란색인지 확인
                if(map[nr][nc].color == 2){
                    pieces[k].changeDir();
                    next = pieces[k].next();
                    if(!isIn(next[0], next[1]) || map[next[0]][next[1]].color == 2) continue;
                    nr = next[0];
                    nc = next[1];
                }

                //이번 포인트에 있는 짐들 확인
                //짐들 옮길 때 위치도 바꾸기
                int color = map[nr][nc].color;
                if(color == 0){
                    int size = map[yr][yc].deque.size();
                    while(size-- > 0){
                        int num = map[yr][yc].deque.pollFirst();
                        if(num != k){
                            map[yr][yc].deque.offerLast(num);
                        }else {
                            map[nr][nc].deque.offerLast(num);
                            pieces[k].r = nr;
                            pieces[k].c = nc;
                            break;
                        }
                    }
                    while(size-- > 0){
                        int num = map[yr][yc].deque.pollFirst();
                        map[nr][nc].deque.offerLast(num);
                        pieces[num].r = nr;
                        pieces[num].c = nc;
                    }

                }else if(color == 1){
                	int size = map[yr][yc].deque.size();
                    while(size-- > 0){
                        int num = map[yr][yc].deque.pollLast();
                        map[nr][nc].deque.offerLast(num);
                        pieces[num].r = nr;
                        pieces[num].c = nc;
                        if(num == k) break;
                    }
                }

                if(map[nr][nc].deque.size() >= 4){
                    System.out.println(turn);
                    return;
                }

            }
            
            if(turn > 1000){
                System.out.println(-1);
                break;
            }
        }
    }

    static boolean isIn(int nr, int nc){
        return nr >= 0 && nr < N && nc >= 0 && nc < N;
    }
}