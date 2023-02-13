import java.io.*;
import java.util.*;
public class Main {
    static int[] arr;
    static int[] lis;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        lis = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            lis[i] = Integer.MIN_VALUE; // 최소값으로 우선 설정
        }
        
        lis[0] = arr[0]; 
        
        int idx = 0;
        for(int i=1; i < arr.length; i++){
            // 만약 원 배열이 탐색 중 더 작은 숫자라면 그대로 이어서 붙인다.
            if(lis[idx] > arr[i]){
                lis[++idx] = arr[i];

            } else {
                int target_index = binarySearch(lis, arr[i]);
                lis[target_index] = arr[i];
            }
        }
        
        bw.write(String.valueOf(idx+1));
        br.close();
        bw.flush();
    }
    
    // 반복문을 이용한 이진 탐색 방식
    public static int binarySearch(int[] arr, int x){
        int start = 0;
        int end = arr.length-1;

        while(start <= end){
            int mid = (start + end) / 2;
            if(arr[mid] == x){
                return mid;
            } else if(arr[mid] < x){
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        // 일치값을 찾지 못했을 때, -1이 아니라 그 적절한 위치를 반환해야 함
        return start;
    }
}