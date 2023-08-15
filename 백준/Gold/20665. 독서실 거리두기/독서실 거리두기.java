import java.util.*;
import java.io.*;


public class Main
{
    static int N, T, P;
    static boolean[][] seats;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        seats = new boolean[N+1][721];
        
        PriorityQueue<int[]> reservation = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[0] == o2[0]) return Integer.compare(o1[1], o2[1]);
                return Integer.compare(o1[0], o2[0]);
            }
        });

        for(int t = 1; t <= T; t++){
            //입력 받고 분단위로 바꾸기
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            String e = st.nextToken();
            int start = Integer.parseInt(s.substring(0,2))*60 + Integer.parseInt(s.substring(2,4)) - 540;
            int end = Integer.parseInt(e.substring(0,2)) * 60 + Integer.parseInt(e.substring(2,4)) - 540;

            reservation.offer(new int[] {start, end});
        }

        int answer = 720;
        //모든 사용자 반복 
        while(!reservation.isEmpty()){
            int[] user = reservation.poll();
            int start = user[0];
            int end = user[1];

            //만약 지금 사용자 한명도 없으면 1번 배치
            if(isEmpty(start)){
                for(int s = start; s < end; s++){
                    seats[1][s] = true;
                }
                
                if(1 == P){
                    answer -= end-start;
                }
                continue;
            }
            int maxIdx = 0;
            int maxDis = 0;

            for(int n = 1; n<= N; n++){
                if(!seats[n][start]){
                    int dis = 0;
                    while(true){
                    	dis++;
                        if(n-dis < 1) {
                        	if(seats[n+dis][start]) break;
                        	continue;
                        }
                        if(n+dis > N) {
                        	if(seats[n-dis][start]) break;
                        	continue;
                        }
                        if(seats[n-dis][start] || seats[n+dis][start]) break;
                    }
                    if(dis > maxDis){
                        maxDis = dis;
                        maxIdx = n;
                        continue;
                    }
                    if(dis == maxDis){
                        if(maxIdx > n) maxIdx = n;
                        continue;
                    }
                }
            }

            for(int s = start; s < end; s++){
                seats[maxIdx][s] = true;
            }

            if(maxIdx == P){
                answer -= end - start;
            }


        }

        System.out.println(answer);
    }

    static boolean isEmpty(int time){
        for(int n = 1; n <= N; n++){
            if(seats[n][time]) return false;
        }
        return true;
    }

    
}