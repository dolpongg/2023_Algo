import java.util.*;
import java.io.*;

public class Main {
    static int N, map[][], empty[][], num[], like[][];
    static ArrayList<Node> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];    //자리배치
        empty = new int[N][N];    //사방 비어있는 칸
        num = new int[N * N];    //학생 번호  배열
        like = new int[N * N][4];    //좋아하는 학생 배열(num배열이랑짝)
        //비어있는 칸 배열 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int n = 4;
                if (i == 0 || i == N-1) n--; //끝쪽이면 마이너스
                if (j == 0 || j == N-1) n--;
                empty[i][j] = n;
            }
        }
        //학생번호, 좋아하는학생 입력
        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            num[i] = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                like[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //학생 하나씩 자리정하기
        for (int i = 0; i < N * N; i++) {
            list = new ArrayList<>();    //한 학생이 들어갈수있는 모든 자리를 담은 리스트
            add(i);                        //리스트에 추가하기
            Collections.sort(list);        //규칙에 따라 정렬하기 (좋아하는 학생 많은,비어있는칸 많은,행번호가 작은)
            Node node = list.get(0);    //첫번째가 최적위치

            map[node.x][node.y] = num[i];    //위치에 학생번호
            empty[node.x][node.y] = -1;        //안비어있다고 표시
            //사방 비어있는칸 1씩 마이너스
            for (int j = 0; j < 4; j++) {
                int xx = node.x + dx[j];
                int yy = node.y + dy[j];
                if (xx < 0 || xx >= N || yy < 0 || yy >= N || empty[xx][yy] < 1) continue;
                empty[xx][yy]--;
            }
        }

        int result = 0;
        //총 만족도 구하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //학생수만큼 반복
                for (int k = 0; k < N * N; k++) {
                    if (map[i][j] == num[k]) {
                        int cnt = 0;
                        //사방에 좋아하는 학생 있는지 확인
                        for (int kk = 0; kk < 4; kk++) {
                            int xx = i + dx[kk];
                            int yy = j + dy[kk];
                            if (xx < 0 || xx >= N || yy < 0 || yy >= N) continue;
                            //좋아하는 학생 배열에서 확인
                            for (int r = 0; r < 4; r++) {
                                if (map[xx][yy] == like[k][r]) {
                                    cnt++;    //있다면 카운트업
                                    break;
                                }
                            }
                        }
                        //계산하기
                        if (cnt == 1) result += 1;
                        else if (cnt == 2) result += 10;
                        else if (cnt == 3) result += 100;
                        else if (cnt == 4) result += 1000;
                        break;
                    }
                }
            }
        }
        System.out.println(result);

    }

    static int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};

    static void add(int idx) {
        //한 학생이 들어갈수있는 모든 자리 리스트에 추가
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0) continue;

                Node node = new Node();
                node.x = i;
                node.y = j;
                node.empty = empty[i][j];

                int cnt = 0;
                //자리마다 정보넣기
                for (int k = 0; k < 4; k++) {
                    int xx = i + dx[k];
                    int yy = j + dy[k];
                    if (xx < 0 || xx >= N || yy < 0 || yy >= N || map[xx][yy] == 0) continue;
                    //인접한곳의 좋아하는 학생 수
                    for (int kk = 0; kk < 4; kk++) {
                        if (map[xx][yy] == like[idx][kk]) {
                            cnt++;
                            break;
                        }
                    }
                }
                node.like = cnt;
                list.add(node);
            }
        }
    }

    static class Node implements Comparable<Node> {
        int x, y, like, empty;
        @Override
        public int compareTo(Node o) {
            if (this.like != o.like) return o.like - this.like;
            if (this.empty != o.empty) return o.empty - this.empty;
            if (this.x != o.x) return this.x - o.x;
            return this.y - o.y;
        }
    }
}