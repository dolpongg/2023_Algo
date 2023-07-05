import java.util.*;
import java.io.*;

public class Main {
	static class Pos{
		int force;
		boolean box;
		
		Pos(int force){
			this.force = force;
			box = false;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "[force : " + force + " + box : " + box + "]";
		}
	}
	static int N,K;
	static Pos[] belt;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		belt = new Pos[2*N+1];
		st = new StringTokenizer(br.readLine());
		belt[0] = new Pos(-1);
		for(int i = 1; i <= 2*N; i++) {
			belt[i] = new Pos(Integer.parseInt(st.nextToken()));
		}
		
		int answer = 1;
		while(true) {
			moveContainer();
			moveRobot();
			put();
			int count = 0;
			for(int i = 1; i <= 2*N; i++) {
				if(belt[i].force == 0) count++;
			}
			if(count >= K) break;
			answer++;
			
		}
		System.out.println(answer);
	}

	private static void moveRobot() {
		
		for(int i = N-1; i > 1; i--) {
			if(belt[i].box && !belt[i+1].box && belt[i+1].force>0) {
				belt[i].box = false;
				belt[i+1].box = true;
				belt[i+1].force--;
			}
		}
		
		if(belt[N].box) {
			belt[N].box = false;
		}
	}

	private static void put() {
		//올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
		if(belt[1].force > 0) {
			belt[1].force--;
			belt[1].box = true;
			
			
		}
		
	}

	private static void moveContainer() {
		for(int i = 2*N; i >= 1; i--) {
			
			if(i == 2*N) {
				belt[0].force = belt[2*N].force;
				belt[0].box = belt[2*N].box;
				continue;
			}
			
			belt[i+1].force = belt[i].force;
			belt[i+1].box = belt[i].box;
		}
		
		
		belt[1].force = belt[0].force;
		belt[1].box = belt[0].box;
		
		if(belt[N].box) {
			belt[N].box = false;
		}
		
	}

}