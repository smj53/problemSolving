package programmers.kakao_blind_recruitment_2022.양과늑대;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Programmers_양과늑대 {
    class Solution {
        class Node implements Comparable<Node> {
            int v;
            int totalSheep;
            int total;
            boolean isSheep;

            public Node(int v, int totalSheep, int total, boolean isSheep) {
                this.v = v;
                this.totalSheep = totalSheep;
                this.total = total;
                this.isSheep = isSheep;
            }

            @Override
            public int compareTo(Node o) {
                if(isSheep && o.isSheep) return 0;
                else if(!isSheep && o.isSheep) return 1;
                else if(isSheep) return -1;
                else {
                    if(totalSheep == o.totalSheep) {
                        return o.total - total;
                    }
                    return o.totalSheep - totalSheep;
                }
            }

            @Override
            public String toString() {
                return "Node{" +
                        "v=" + v +
                        ", totalSheep=" + totalSheep +
                        ", total=" + total +
                        ", isSheep=" + isSheep +
                        '}';
            }
        }

        int[] totalSheep;
        int[] total;

        public int solution(int[] info, int[][] edges) {
            List<Integer>[] g = new ArrayList[info.length];
            for(int i=0; i<info.length; ++i) {
                g[i] = new ArrayList<>(2);
            }
            for (int[] edge : edges) {
                g[edge[0]].add(edge[1]);
            }

            total = new int[info.length];
            totalSheep = new int[info.length];
            getTotal(g, info, 0);

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(makeNode(0, info));

            int sheepCount = 0;
            int wolfCount = 0;
            while(!pq.isEmpty()) {
                Node n = pq.poll();

                if(n.isSheep) {
                    ++sheepCount;
                } else {
                    ++wolfCount;
                }

                if(sheepCount == wolfCount) {
                    break;
                }

                for(int next : g[n.v]) {
                    pq.offer(makeNode(next, info));
                }
            }

            return sheepCount;
        }

        Node makeNode(int v, int[] info) {
            return new Node(v, total[v], totalSheep[v], info[v] == 0);
        }

        void getTotal(List<Integer>[] g, int[] info, int cur) {
            int totalCount = info[cur] == 0 ? 1 : -1; // 1 - 2 * info[cur];
            int totalSheepCount = info[cur] ^ 1;

            for(int next : g[cur]) {
                getTotal(g, info, next);
                totalCount += total[next];
                totalSheepCount += totalSheep[next];
            }

            total[cur] = totalCount;
            totalSheep[cur] = totalSheepCount;
        }
    }
}
