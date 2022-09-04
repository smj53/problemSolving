package bj.gold.III.prob6087.레이저통신;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_6087_레이저통신_2 {
    static int[][] d = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // 하우상좌
    static class Position implements Comparable<Position> {
        int x;
        int y;
        int distance;
        int mirrorCount;
        int previousDirection;

        public Position(int x, int y, int mirrorCount, int previousDirection, int distance) {
            this.x = x;
            this.y = y;
            this.mirrorCount = mirrorCount;
            this.previousDirection = previousDirection;
            this.distance = distance;
        }

        @Override
        public int compareTo(Position o) {
            if(mirrorCount == o.mirrorCount) {
                return distance - o.distance;
            }
            return mirrorCount - o.mirrorCount;
        }
        // 틀린 compareTo: 거리를 우선 정렬하는 게 맞다고 생각했는데, 거울 개수가 우선 정렬되어야 하는듯...
//        @Override
//        public int compareTo(Position o) {
//            if(distance == o.distance) {
//                return mirrorCount - o.mirrorCount;
//            }
//            return distance - o.distance;
//        }
    }
        public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        char[][] map = new char[H][W];

        List<Position> lazer = new ArrayList<>(2);
        for (int i = 0; i < H; i++) {
            String string = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = string.charAt(j);
                if(map[i][j] == 'C') {
                    lazer.add(new Position(i, j, 0, -1, 0));
                }
            }
        }

        int[][] visited = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                Arrays.fill(visited[i], Integer.MAX_VALUE);
            }
        }
        PriorityQueue<Position> pq = new PriorityQueue<>();
        Position start = lazer.get(0);
        visited[start.x][start.y] = 0;
        for (int i = 0; i < 4; i++) {
            int nx = start.x + d[i][0];
            int ny = start.y + d[i][1];

            if(nx < 0 || nx >= H || ny < 0 || ny >= W || map[nx][ny] == '*' || visited[nx][ny] < 0) continue;

            visited[nx][ny] = 0;
            pq.offer(new Position(nx, ny, 0, i, start.distance + 1));
        }
        while(!pq.isEmpty()) {
            Position current = pq.poll();

            if(map[current.x][current.y] == 'C') {
                break;
            }

            for (int i = -1; i <= 1; i++) {
                int di = (current.previousDirection + i + 4) % 4;
                int nx = current.x + d[di][0];
                int ny = current.y + d[di][1];

                int mirrorCount = current.mirrorCount + Math.abs(i);
                if (nx < 0 || nx >= H || ny < 0 || ny >= W || map[nx][ny] == '*' || visited[nx][ny] < mirrorCount) continue;

                visited[nx][ny] = mirrorCount;
                pq.offer(new Position(nx, ny, mirrorCount, di, current.distance + 1));
            }
        }
        System.out.println(visited[lazer.get(1).x][lazer.get(1).y]); // 시간이 더 드네.. 그냥 answer에 저장하는 게 시간이 빠름
    }
}
