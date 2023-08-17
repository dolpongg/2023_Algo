import java.util.*;

class Dest {
    int location, count;

    public Dest(int location, int count) {
        this.location = location;
        this.count = count;
    }
}

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i <= n; i++){
            map.add(new ArrayList<>());
        } 

        for (int[] road: roads) {
            int from = road[0];
            int to = road[1];

            map.get(from).add(to);
            map.get(to).add(from);
        }

        int[] distance = new int[n + 1];
        
        Arrays.fill(distance, -1);//-1로 초기화
        
        distance[destination] = 0;//도착지점은 0
        
        //도착지부터 출발해서 각 지점까지 최단거리 구하기
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {destination, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int next: map.get(cur[0])) {
                if (distance[next] < 0) {
                    distance[next] = cur[1] + 1;
                    q.add(new int[] {next, cur[1] + 1});
                }
            }
        }

        int sourceLength = sources.length;
        int[] answer = new int[sourceLength];

        for (int i = 0; i < sourceLength; i++) {
            answer[i] = distance[sources[i]];
        }
        return answer;
    }
}