import java.io.*;
import java.util.*;

public class Main {
    static int T;
    static int N;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String args[]) throws Exception{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      T = Integer.parseInt(br.readLine());
      for(int t = 1; t <= T; t++){
          N = Integer.parseInt(br.readLine());
          
          get(0, new int[N-1]);
          sb.append("\n");
      }
      
      System.out.println(sb);
    }
    
    static void get(int nth, int[] opp){
        if(nth == N-1){
            // System.out.println(Arrays.toString(opp));
            int num = 1;
            int sum = 0;
            int before = 2;
            for(int i = 1; i < N; i++){
                if(opp[i-1] == 1){
                    num = num*10 + (i+1);
                }else{
                    if(before == 2){
                        sum+=num;
                    }else if(before == 3){
                        sum-=num;
                    }
                    before = opp[i-1];
                    num = i+1;
                }
                // System.out.println("num : " + num);
                // System.out.println(sum);
            }
            if(before == 2) sum += num;
            else sum-= num;
            // System.out.println("num : " + num);
            // System.out.println(sum);
            // System.out.println("======");
            
            // System.out.println(sum);
            if(sum == 0){
                StringBuilder sbIn = new StringBuilder();
                sbIn.append(1);
                for(int i = 1; i < N; i++){
                    if(opp[i-1] == 1) sbIn.append(" ");
                    else if(opp[i-1] == 2)sbIn.append("+");
                    else sbIn.append("-");
                    
                    sbIn.append(i+1);
                }
                sb.append(sbIn).append("\n");
            }
            
            return;
        }
        
        for(int j = 1; j <=3; j++){
            opp[nth] = j;
            get(nth+1, opp);
        }
    }
}