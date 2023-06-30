import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;
;
public class Main {
	static class Person{
		int index;
		int len;
		int[] cards;
		
		Person(int len){
			this.index = 0;
			this.len = len;
			this.cards = new int[len];
		}
		
	}
	
	static String[] cardArr;
	static int P, C;
	static Person[] people;
	static int[] peopleNum;
	static TreeSet<String> answer = new TreeSet<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		P = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		people = new Person[P];
		cardArr = new String[C+1];
		peopleNum = new int[C];
		int nIdx = 0;
	
		//사람 배열 
		for(int p = 0; p < P; p++) {
			st = new StringTokenizer(br.readLine());
			
			people[p] = new Person(Integer.parseInt(st.nextToken()));
			for(int c = 0; c < people[p].len; c++) {
				people[p].cards[c] = Integer.parseInt(st.nextToken());
				peopleNum[nIdx++] = p;
			}
			
		}
		
		//카드 배열 
		for(int c = 1; c <= C; c++) {
			cardArr[c] = br.readLine();
		}
		
		//중복 순열로 순서 정하기
		makeOrder(0, new int[C], new boolean[C]);
		
		Iterator iter = answer.iterator();	// Iterator 사용
		while(iter.hasNext()) {//값이 있으면 true 없으면 false
		    System.out.println(iter.next());
		}
	}
	
	static void makeOrder(int nth, int[] choosed, boolean[] visited) {
		   if(nth == choosed.length) {
//			   System.out.println(Arrays.toString(choosed));
			   
			   StringBuilder sb = new StringBuilder();
			   
			   outer : for(int p : choosed) {
				   //1. 이 사람이 지금 쓸 카드
				   int card = people[p].cards[people[p].index++];
				   
				   //2. 카드에 있는 연산
				   String[] cals = cardArr[card].split(",");
				   for(String cal : cals) {
					   if(cal.substring(0, 3).equals("ADD")) {
						   sb.append(cal.substring(4,5));
					   }else {
						   if(Integer.parseInt(cal.substring(4,5)) > sb.length()-1) {
							   sb = new StringBuilder();
							   sb.append("ERROR");
							   break outer;
						   }else {
							   sb.delete(Integer.parseInt(cal.substring(4,5)), Integer.parseInt(cal.substring(4,5))+1);
						   }
					   }
				   }
			   }
			   
			   for(int p = 0; p < P; p++) {
				   people[p].index = 0;
			   }
			   
			   if(sb.length() == 0) {
				   sb.append("EMPTY");
			   }
			   
			   answer.add(sb.toString());
			   
			   return;
		   }
		   for(int i = 0; i < peopleNum.length; i++) {
			   if(!visited[i]) {
				   visited[i] = true;
				   choosed[nth] = peopleNum[i];
				   makeOrder(nth+1, choosed, visited);
				   
				   visited[i] = false;
			   }
		   }
	   }
 
}