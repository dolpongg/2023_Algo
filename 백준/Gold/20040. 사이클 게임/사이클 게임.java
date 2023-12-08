import java.io.*;
import java.util.*;

public class Main {

	static int[] parent;//부모 정리 배열
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());//점 개수
		int m = Integer.parseInt(st.nextToken());//차례
		
        //부모 배열 초기화
		parent = new int[n];
		for(int i=0; i<n; i++) {
			parent[i] = i;
		}
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(find(x)==find(y)) {//사이클 만들어짐
				System.out.println(i+1);
				return;
			}else {
                //선잇기
				union(x,y);
			}
		}
		
		System.out.println(0);
	}
	static int find(int x) {
		if(x == parent[x]) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x < y) {
			parent[y] = x;
		}else {
			parent[x] = y;
		}
	}
}