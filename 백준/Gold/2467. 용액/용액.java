import java.util.*;
import java.io.*;

public class Main {


	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		List<Integer> list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(list,new Comparator(){ 		
			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				return Integer.compare(Math.abs((Integer) o1), Math.abs((Integer) o2));
			}     
		});   
		
		int answerValue = Integer.MAX_VALUE;
		int answer1 = 0;
		int answer2 = 0;
		for(int i = 0; i < N-1; i++) {
			if(Math.abs(answerValue) > Math.abs(list.get(i) + list.get(i+1))){
				answerValue = Math.abs(list.get(i) + list.get(i+1));
				answer1 = i;
				answer2 = i+1;
			}
		}
		
		int num1 = list.get(answer1);
		int num2 = list.get(answer2);
		
		if(num1 <= num2) {
			System.out.println(num1 + " " + num2);
		}else {
			System.out.println(num2 + " " + num1);
		}
	}

}