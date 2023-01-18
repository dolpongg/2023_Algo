import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static boolean[][] display = {
			{true, true, true, true, true, true, false},
			{false, false, false, false, true, true, false},
			{true, false, true, true, false, true, true},
			{true, false, false, true, true, true, true},
			{false, true, false, false, true, true, true},
			{true, true, false, true, true, false, true},
			{true, true, true, true, true, false, true},
			{true, false, false, false, true, true, false},
			{true, true, true, true, true, true, true},
			{true, true, false, true, true, true, true}
	};
	
	static int N, K, P, X;
	static int[] now;
	static List<Integer> toes = new ArrayList<>();
	static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		char[] tmp = ("" + X).toCharArray();
		now = new int[K];
		
		if(tmp.length != K) {	
			// 거꾸로 배열
	        for(int i = 0; i < tmp.length; i++) {
	        	now[K-1-i] = tmp[tmp.length-1-i] - '0';
	        }
		}else {
			for(int i = 0;i < K; i++) {
				now[i] = tmp[i] - '0';
			}
		}
		
//		System.out.println(Arrays.toString(now));
		
		//1. 현재 층수에서 반전 가능한 층 확인하기

		reverse(0,0,"");
		//2. 가능한 층수 에서 N 이하 구하기
//		for
		System.out.println(answer);
		
	}

	private static void reverse(int pos, int cnt, String to) {
		if(pos == K) {
			if(Integer.parseInt(to) >= 1 && Integer.parseInt(to) <= N && cnt != 0) {
//				System.out.println("an : " + to);
				answer++;
				toes.add(Integer.parseInt(to));
			}
			return;
		}
//		System.out.println("to : " + to);
		for(int num = 0; num <= 9; num++) {
			int ccnt = 0;
			for(int dis = 0; dis < 7; dis++) {
				if(display[num][dis] != display[now[pos]][dis]) {
					ccnt++;
				}
			}
			if(ccnt+cnt <= P) {
				reverse(pos+1, cnt + ccnt, to + num);
			}
		}
		
	}
}