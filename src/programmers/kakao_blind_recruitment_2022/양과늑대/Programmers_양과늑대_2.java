package programmers.kakao_blind_recruitment_2022.양과늑대;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Programmers_양과늑대_2 {
    class Solution {
        class Node {
            Node left;
            Node right;
            int v;
            boolean isSheep;

            public Node(int v, boolean isSheep) {
                this.v = v;
                this.isSheep = isSheep;
            }

            void addChild( Node child) {
                if(left == null) {
                    left = child;
                } else {
                    right = child;
                }
            }
        }
        int answer;

        public int solution(int[] info, int[][] edges) {
            Node[] nodes = new Node[info.length];
            for (int i = 0; i < info.length; i++) {
                nodes[i] = new Node(i, info[i] == 0);
            }
            for (int[] edge : edges) {
                nodes[edge[0]].addChild(nodes[edge[1]]);
            }
//            find(nodes[0]);
            return answer;
        }
    }
}
