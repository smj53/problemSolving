package programmers.kakao_blind_recruitment_2022.양과늑대;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Programmers_양과늑대_4 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[]{0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1}, new int[][]{{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}}));
//        System.out.println(s.solution(new int[]{0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0}, new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 8}, {6, 9}, {9, 10}}));
//        System.out.println(s.solution(makeArray("[0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0]"), make2Array("[[0, 1], [0, 2], [1, 3], [1, 4], [2, 5], [2, 6], [3, 7], [4, 8], [6, 9], [9, 10]]")));
    }
    static int[] makeArray(String str) {
        String[] arr = str.replace("[", "").replace("[", "").split(", ");
        int[] ret = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ret[i] = Integer.parseInt(arr[i]);
        }
        return ret;
    }
    static int[][] make2Array(String str) {
        return null;
    }
    static class Solution {
        class Node implements Comparable<Node> {
            int v;
            int parent;
            int sheep;
            int cost;

            public Node(int v, int sheep, int cost) {
                this.v = v;
                this.sheep = sheep;
                this.cost = cost;
            }

            @Override
            public int compareTo(Node o) {
                if(cost == o.cost) {
                    return o.sheep - sheep;
                }
                return cost - o.cost;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "v=" + v +
                        ", parent=" + parent +
                        ", sheep=" + sheep +
                        ", cost=" + cost +
                        '}';
            }
        }

        Node[] nodes;
        List<Integer>[] g;
        int N;
        int answer;

        public int solution(int[] info, int[][] edges) {
            N = info.length;
            g = new ArrayList[N];
            nodes = new Node[N];
            for(int i=0; i<info.length; ++i) {
                g[i] = new ArrayList<>(2);
                nodes[i] = new Node(i, info[i] ^ 1, info[i]);
            }
            for (int[] edge : edges) {
                g[edge[0]].add(edge[1]);
                nodes[edge[1]].parent = edge[0];
            }
            nodes[0].parent = -1;
            makeTree(nodes[0]);

            System.out.println(Arrays.toString(g));
            System.out.println(Arrays.toString(nodes));

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(nodes[0]);
            nodes[0].sheep = 0;

            int answer = 1;
            int cost = 0;
            while(!pq.isEmpty()) {
                Node cur = pq.poll();

                cost += cur.cost;
                if(answer == cost) {
                    break;
                }
                for(int next : g[cur.v]) {
                    pq.offer(nodes[next]);
                }
                answer += cur.sheep;
            }

            return answer;
        }

        void makeTree(Node current) {
            for (int i = g[current.v].size() - 1; i >= 0; --i) {
                if(makeNode(current, nodes[g[current.v].get(i)])) {
                    g[current.v].remove(i);
                }
            }
        }

        private boolean makeNode(Node parent, Node current) {
            makeTree(current);

            if(g[current.v].size() == 1 && current.sheep == 0) {
                Node child = nodes[g[current.v].get(0)];
                current.sheep += child.sheep;
                current.cost += child.cost;
                g[current.v].remove(0);
            }
            if(g[current.v].size() == 0) {
                if(current.cost == 0) {
                    parent.sheep += current.sheep;
                    return true;
                } else if(current.sheep == 0) {
                    return true;
                }
            }
            return false;
        }
    }
}
