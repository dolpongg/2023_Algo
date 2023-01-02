import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//전체 
		int N = Integer.parseInt(st.nextToken());
		//지민이 
		int jimin = Integer.parseInt(st.nextToken());
		//한수
		int hansu = Integer.parseInt(st.nextToken());
		
		int answer = 0;
		
		while(jimin != hansu) {
			jimin = (jimin + 1)/2;
			hansu = (hansu + 1)/2;
			answer++;
		}
		
		System.out.println(answer);
		
	}
}