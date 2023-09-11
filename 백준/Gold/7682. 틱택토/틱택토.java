import java.util.*;
import java.io.*;

public class Main {
    static char[][] map = new char[3][3];
    static int o,x,no;
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringBuilder sb = new StringBuilder();
        
        while(true){
            char[] input = br.readLine().toCharArray();
            if(input[0] == 'e') break;
            
            int i = 0;
            o = 0;
            x = 0;
            no = 9;
            for(int r = 0; r < 3; r++){
                for(int c = 0; c < 3; c++){
                    if(input[i] == 'O'){
                        o++;
                        no--;
                    }
                    else if(input[i] == 'X'){
                        x++;
                        no--;
                    }
                    map[r][c] = input[i++];
                }
            }
            
            
            //x,o 갯수 비교
            if(x - o == 1 || x-o == 0){
                
                if(bingo()){
                    sb.append("valid").append("\n");
                    continue;
                }
                
            }
            sb.append("invalid").append("\n");
        }
        
        
        System.out.println(sb);
    }
    
    static boolean bingo(){
        int count = 0;
        //가로
        char ch = 'X';
        if(x > o) ch = 'O';
        for(int r = 0;r < 3; r++){
            if(map[r][2] == map[r][1] && map[r][1] == map[r][0] && map[r][2] != '.'){
                if(map[r][2] == ch)return false;
                count++;
            }
        }
        
        //세로
        for(int c = 0; c < 3; c++){
            if(map[0][c] == map[1][c] && map[1][c] == map[2][c] && map[0][c] != '.'){
                if(map[0][c] == ch) return false;
                count++;
            }
        }
        
        //대각선1
        if(map[0][0] == map[1][1] && map[1][1] == map[2][2] && map[0][0] != '.'){
            if(map[0][0] == ch) return false;
            count++;
        }
        
        //대각선2
        if(map[0][2] == map[1][1] && map[1][1] == map[2][0] && map[0][2] != '.'){
            if(map[0][2] == ch) return false;
            count++;
        }
        if(x <= 4 && count == 0) return false;
        return true;
    }
}