package programmers.kakao_blind_recruitment_2022.양과늑대;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Programmers_양과늑대_질문25736 {
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
        List<Integer>[] g;
        int N;
        int answer;

        public int solution(int[] info, int[][] edges) {
            N = info.length;
            g = new ArrayList[N];
            for(int i=0; i<info.length; ++i) {
                g[i] = new ArrayList<>(2);
            }
            for (int[] edge : edges) {
                g[edge[0]].add(edge[1]);
            }

            System.out.println(Arrays.toString(g));

            find(info, 0, g[0], 0, 0);

            return answer;
        }

        void find(int[] info, int current, List<Integer> candidates, int sheep, int wolf) {
            if(info[current] == 0) {
                ++sheep;
            } else {
                ++wolf;
            }

            if(sheep == wolf) {
                return;
            }

            answer = Math.max(answer, sheep);

            for(int next : candidates) {
                List<Integer> copy = new ArrayList<>();
                for(int v : candidates) {
                    if(next == v) {
                        continue;
                    }
                    copy.add(v);
                }
                copy.addAll(g[next]);
                find(info, next, copy, sheep, wolf);

            }
        }


    }
}
