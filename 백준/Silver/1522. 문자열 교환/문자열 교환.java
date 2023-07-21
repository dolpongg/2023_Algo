import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int answer = Integer.MAX_VALUE;
		char[] input = br.readLine().toCharArray();
		//문자열 전체 길
		int len  = input.length;
		
		//모든 a가 연속되었을 때 길
		int aSize = 0;
		//a가 들어있는 만큼 aSize++
		for(int i = 0; i < len; i++) {
			if(input[i] == 'a') aSize++;
		}
		
		//음... 딱히 문제에 설명이 없긴 한데.. 혹시 b만 단독으로 주어졌을 경우..가 있을까 싶어서 추가해봄
		if(aSize == 0) {
			System.out.println(0);
			return;
		}
		
		//슬라이딩 윈도우할건데,
		//윈도우에 들어있는 b의 갯수만큼 교환해줘야하는 거니까 b의 갯수를 세어봄
		int bCount = 0;
		//이게 마지막이랑 처음이 연결되어있는 원형 구조니까, 마지막 원소까지 start지점으로 잡음
		for(int start = 0; start <= len-1; start++) {
			//인덱스 조정
			int end = (start + aSize-1) % len;
			//초기화
			if(start == 0) {
				for(int i = start; i <= end; i++) {
					if(input[i] == 'b') bCount++;
				}
			}else {
				//빠진 값이 b면 bCount--
				if(input[start-1] == 'b') bCount--;
				//새로 들어온 값이b면 bCount++
				if(input[end] == 'b') bCount++;
			}
			
			//최솟값 갱신
			answer = Math.min(answer, bCount);
			
		}
		
		System.out.println(answer);
		
	}
	
}
//abababababababa