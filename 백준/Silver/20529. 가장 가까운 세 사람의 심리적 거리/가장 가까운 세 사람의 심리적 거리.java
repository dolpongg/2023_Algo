import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Main {
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		int testCase = Integer.parseInt(st.nextToken());
		
		outer:for(int tc = 0; tc < testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			if(N > 32) {
				sb.append(0).append("\n");
				br.readLine();
				continue;
			}
			
			Map<String, Integer> map = new HashMap<>();
			st = new StringTokenizer(br.readLine());
			
			for(int n = 0; n < N; n++) {
				String mbti = st.nextToken();
				if(!map.containsKey(mbti)) {
					map.put(mbti, 0);
				}
				map.put(mbti, map.get(mbti)+1);
				
				if(map.get(mbti) >= 3) {
					sb.append(0).append("\n");
					continue outer;
				}
			}
			
			int min = Integer.MAX_VALUE;

			for(String key : map.keySet()) {
				if(map.get(key) == 2) {
					for(String key2 : map.keySet()) {
						if(!key2.equals(key)) {
							char[] ch1 = key.toCharArray();
							char[] ch2 = key2.toCharArray();
							int sum = 0;
							for(int i = 0; i < 4; i++) {
								if(ch1[i] != ch2[i]) sum++;
							}
//							System.out.println(sum);
							
							min = Math.min(sum*2, min);
						}
					}
				}
			}
			
			for(String key : map.keySet()) {
				for(String key2 : map.keySet()) {
					for(String key3 : map.keySet()) {
						if(!key2.equals(key) && !key3.equals(key2) && !key.equals(key3)) {
							char[] ch1 = key.toCharArray();
							char[] ch2 = key2.toCharArray();
							char[] ch3 = key3.toCharArray();
							
							int sum = 0;
							for(int i = 0; i < 4; i++) {
								if(ch1[i] != ch2[i]) sum++;
								if(ch1[i] != ch3[i]) sum++;
								if(ch2[i] != ch3[i]) sum++;
							}
							
							min = Math.min(sum, min);
						}
					}
						
				}
			}
			
			sb.append(min).append("\n");
			
		}
		System.out.println(sb);
		
	}
}