import java.util.Scanner;

public class Main {

    static String N;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static int temp = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextLine();

        divide(N);
        System.out.println(min + " " + max);
    }

    private static void divide(String input) {
        //길이가 3이상일경우
        if (input.length() >= 3) {
            for (int i = 1; i < input.length() - 1; i++) {
                for (int j = i + 1; j < input.length(); j++) {
                    //3개의 수로 분할
                    String a = input.substring(0, i);
                    String b = input.substring(i, j);
                    String c = input.substring(j);

                    //각 수의 홀수 개수 계산
                    temp += calc(a);
                    temp += calc(b);
                    temp += calc(c);

                    divide(String.valueOf(Integer.parseInt(a) + Integer.parseInt(b) + Integer.parseInt(c)));
                    //더한것 되돌리기
                    temp -= calc(a);
                    temp -= calc(b);
                    temp -= calc(c);
                }
            }
        }

        //길이가 2일 경우
        else if (input.length() == 2) {
            //각 수의 홀수 개수 계산
            temp += input.charAt(0) % 2;
            temp += input.charAt(1) % 2;

            divide(String.valueOf(Integer.parseInt(String.valueOf(input.charAt(0))) + Integer.parseInt(String.valueOf(input.charAt(1)))));
            //이전 함수로 돌아가기전에 더한 것 되돌리기
            temp -= input.charAt(0) % 2;
            temp -= input.charAt(1) % 2;
        }

        //길이가 1일 경우
        else if (input.length() == 1) {
            //홀수 인지 아닌지 계산
            temp += Integer.parseInt(input) % 2;

            max = Math.max(temp, max);
            min = Math.min(temp, min);

            //이전 함수로 돌아가기전에 더한 것 되돌리기
            temp -= Integer.parseInt(input) % 2;
            return;
        }
    }

    private static int calc(String number) {
        int count = 0;
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) % 2 != 0) {
                count++;
            }
        }

        return count;
    }
}