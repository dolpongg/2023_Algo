import java.util.*;
import java.io.*;


public class Main
{
    static int N;
    static HashMap<Integer,Integer>[] pairs;
    static int sum;
    static int answer = 0;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());

        pairs = new HashMap[N];

        for(int n = 0; n < N; n++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());

            pairs[n] = new HashMap<>();
            pairs[n].put(a,f);
            pairs[n].put(f,a);
            pairs[n].put(b,d);
            pairs[n].put(d,b);
            pairs[n].put(c,e);
            pairs[n].put(e,c);
        }

        for(int down = 1; down <= 6; down++){
            int up = pairs[0].get(down);

            sum = 0;
            for(int i = 6; i >= 1; i--){
                if(i != down && i != up){
                    sum+= i;
                    break;
                }
            }

            sum += stack(1, up);

            answer = Math.max(answer, sum);
        }
        System.out.println(answer);
    }
    static int stack(int n, int down){
        if(n == N) return 0;

        int up = pairs[n].get(down);

        for(int i = 6; i >= 1; i--){
            if(i != down && i != up) return i + stack(n+1, up);
        }
        return 0;
    }
}