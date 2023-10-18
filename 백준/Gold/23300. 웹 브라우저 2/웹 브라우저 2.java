import java.util.*;
import java.io.*;

public class Main {
    static int N, Q;
    static Stack<Integer> bStack = new Stack<Integer>();
    static Stack<Integer> fStack = new Stack<Integer>();
    static int now = -1;
    public static void main(String args[]) throws Exception{
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      N = Integer.parseInt(st.nextToken());
      Q = Integer.parseInt(st.nextToken());
      
      for(int q = 0; q < Q; q++){
          st = new StringTokenizer(br.readLine());
          String op = st.nextToken();
          
          if(op.equals("B")) back();
          else if(op.equals("F")) forward();
          else if(op.equals("A")) {
              access(Integer.parseInt(st.nextToken()));
          }
          else compress();
      }
      
      StringBuilder sb = new StringBuilder();
      
      sb.append(now).append("\n");
      if(bStack.empty()) sb.append("-1");
      else while(!bStack.empty()) sb.append(bStack.pop()).append(" ");
      sb.append("\n");
      if(fStack.empty()) sb.append("-1");
      else while(!fStack.empty()) sb.append(fStack.pop()).append(" ");
      
      System.out.println(sb);
    }
    
    static void back(){
        if(bStack.empty()) return;
        
        fStack.push(now);
        now = bStack.pop();
        
        return;
    }
    
    static void forward(){
        if(fStack.empty()) return;
        
        bStack.push(now);
        now = fStack.pop();
    }
    
    static void access(int next){
        fStack.clear();
        
        if(now != -1) {
            bStack.push(now);
        }
        
        now = next;
    }
    
    static void compress(){
        if(bStack.empty()) return;
        Stack<Integer> tmp = new Stack<Integer>();
        int num1 = bStack.pop();
        tmp.push(num1);
        while(!bStack.empty()){
            int num2 = bStack.pop();
            if(num1 != num2){
                tmp.push(num2);
                num1 = num2;
            }
        }
        
        while(!tmp.empty()) bStack.push(tmp.pop());
        
    }
    
}