import java.util.*;
import java.io.*;


public class Main
{
    static int N;
    static List<Integer> prime = new ArrayList<>();
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        boolean[] filter = new boolean[N+1];
        int num = 2;
        while(true){
            if(num > N) break;
            int i = num+num;
            while(i <= N){
                filter[i] = true;
                i += num;
            }
            num++;
        }
        for(int n = 2; n <= N; n++){
            if(!filter[n])prime.add(n);
        }

        int left = 0;
        int right = 0;

        int sum = 2;
        int answer = 0;

        while(left < prime.size() && right < prime.size()){
            if(sum == N) {
                answer++;
                sum -= prime.get(left);
                left++;
            }

            else if(sum < N){
                right++;
                if(right == prime.size()) break;
                sum += prime.get(right);
            }
            else{
                sum -= prime.get(left);
                left++;
            }
            
        }

        System.out.println(answer);

        
    }
}