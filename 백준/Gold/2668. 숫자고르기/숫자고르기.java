import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TreeSet<Integer> treeSet = new TreeSet<>();
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N+1];
		for(int n = 1; n <= N; n++) {
			arr[n] = Integer.parseInt(br.readLine());
		}
		
		for(int i = 1; i <= N; i++) {
			int num = i;
			boolean[] visit = new boolean[N+1];
			while(!visit[num]) {
				visit[num] = true;
				num = arr[num];
			}
			
			
			if(i == num) {
				for(int n = 0; n <= N; n++) {
					if(visit[n])treeSet.add(n);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(treeSet.size()).append("\n");
		while(!treeSet.isEmpty()) {
			sb.append(treeSet.pollFirst()).append("\n");
		}
		System.out.println(sb);
	}
	
}
//7
//3
//1
//1
//5
//5
//4
//6