import java.util.*;
import java.io.*;


public class Main
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        outer:for(int n = 0; n < N; n++){
            char[] word = br.readLine().toCharArray();

            int left = 0; 
            int right = word.length-1;
            
            boolean flag = false;

            while(left<right){
                if(word[left] != word[right]){
                    //왼쪽 + 1
                    if(!flag){
                    	flag = true;
                        if(palin(word, right, left+1) || palin(word, right-1, left)){
                        	sb.append(1).append('\n');
                        	continue outer;
                        }else {
                        	sb.append(2).append('\n');
                            continue outer;
                        }
                    }
                    
                    if(word[left+1] != word[right] && word[right-1] != word[left]){
                        sb.append(2).append('\n');
                        continue outer;
                    }
                    
                }
                left++;
                right--;
                
            }

            sb.append(0).append('\n');
        }

        System.out.println(sb);
    }
    
    static boolean palin(char[] word, int right, int left) {
    	while(left<right){
            if(word[left] != word[right]){
                return false;
                
            }
            
            left++;
            right--;
            
        }
    	
    	return true;
    }
}