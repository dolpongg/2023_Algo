import java.util.*;
import java.io.*;


public class Main
{
    static int N;
    static char[] input;
    static int answer = Integer.MIN_VALUE;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        input = br.readLine().toCharArray();

        dfs(2, input[0]-'0');
        System.out.println(answer);
    }

    static void dfs(int n, int sum){
        if(n >= N){
            answer = Math.max(answer, sum);
            return;
        }
        // 괄호 X
        dfs(n+2, op(sum, input[n]-'0', input[n-1]));
        // 괄호 O
        if(n+2 < N){
            int right = op(input[n] - '0', input[n+2]-'0', input[n+1]);
            int left = op(sum, right, input[n-1]);
            dfs(n+4, left);
        }
    }

    static int op(int left, int right, char oper){
        if(oper == '+') return left + right;
        else if(oper == '-') return left - right;
        else return left * right;
    }
}