import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	
	static class Number{
		int ori;
		int ab;
		public Number(int ori) {
			this.ori = ori;
			if(ori < 0) {
				ab = ori * -1;
			}else {
				ab = ori;
			}
		}
		
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N];
		
		List<Number> list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
//			arr[i] = Integer.parseInt(st.nextToken());
			list.add(new Number(Integer.parseInt(st.nextToken())));
		}
		
		// 문자 길이로 sorting (오름차순)
	    Collections.sort(list, new Comparator<Number>() {
			@Override
			public int compare(Number o1, Number o2) {
				// TODO Auto-generated method stub
				return o1.ab - o2.ab;
			}
	    });
	    
//	    System.out.println(list);
	    int gap = Integer.MAX_VALUE;
	    int num1 = 0;
	    int num2 = 0;
	    for(int i = 0; i < list.size()-1; i++) {
	    	if(gap > Math.abs(list.get(i).ori + list.get(i+1).ori)) {
	    		num1 = list.get(i).ori;
	    		num2 = list.get(i+1).ori;
	    		gap = Math.abs(list.get(i).ori + list.get(i+1).ori);
	    	}
	    }
		
		
		StringBuilder sb = new StringBuilder();
		sb.append(Math.min(num1, num2)).append(" ").append(Math.max(num1, num2));
		System.out.println(sb);
		
		
		
		
		
	}
}