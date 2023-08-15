import java.util.*;
import java.io.*;

class Solution {
    static int answerSubs = 0;
    static int answerSales = 0;
    static int N,M;
    static int[][] inputUsers;
    static int[] inputEmoticons;
    public int[] solution(int[][] users, int[] emoticons) {
        inputUsers = users;
        inputEmoticons = emoticons;
        N = users.length;
        M = emoticons.length;
        
        discount(0, new int[M]);
        
        return new int[] {answerSubs, answerSales};
    }
    
    static void discount(int nth, int[] choosed){
        if(nth == M){
            int subs = 0;
            int sales = 0;
            for(int n = 0; n < N; n++){
                int cost = 0;
                for(int m = 0; m < M; m++){
                    if(inputUsers[n][0] <= choosed[m]) cost += inputEmoticons[m] * (100-choosed[m])/100;
                }
                if(cost >= inputUsers[n][1]) {
                    subs++;
                }
                else sales+=cost;
            }
            
            if(subs > answerSubs){
                answerSubs = subs;
                answerSales = sales;
            }else if(subs == answerSubs){
                if(sales > answerSales) {
                    answerSales = sales;
                }
            }
            
            return;
        }
        
        for(int i = 10; i <= 40; i+=10){
            choosed[nth] = i;
            discount(nth+1, choosed);
        }
    }
}