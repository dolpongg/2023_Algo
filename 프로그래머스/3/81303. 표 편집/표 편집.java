import java.util.*;
 
class Solution {
    
    public String solution(int n, int k, String[] cmd) { 
        Stack<Integer> remove = new Stack<>();
        int table_size = n;
        
        for(int i = 0; i < cmd.length; i++) {
            char c = cmd[i].charAt(0);
 
            if(c == 'U') {
                //현재 선택된 행에서 X칸 위에 있는 행을 선택
                k -= Integer.valueOf(cmd[i].substring(2));
            } else if(c == 'D') {
                //현재 선택된 행에서 X칸 아래에 있는 행을 선택합니다.
                k += Integer.valueOf(cmd[i].substring(2));
            } else if(c == 'C') {
                //현재 선택된 행을 삭제
                remove.push(k);
                //바로 아래 행을 선택
                table_size -= 1;
                //단, 삭제된 행이 가장 마지막 행인 경우 바로 윗 행을 선택
                if(k == table_size) k -= 1;
            } else {
                //가장 최근에 삭제된 행
                int r = remove.pop(); 
                //원래대로 복구. 단, 현재 선택된 행은 바뀌지 않습니다.
                if(k >= r) k += 1;
                
                table_size += 1;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < table_size; i++) {
            sb.append('O');
        }
        while(!remove.isEmpty()) {
            sb.insert(remove.pop().intValue(), 'X');
        }
        return sb.toString();
    }
}
