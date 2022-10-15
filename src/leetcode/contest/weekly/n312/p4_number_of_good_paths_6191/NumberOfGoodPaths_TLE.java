package leetcode.contest.weekly.n312.p4_number_of_good_paths_6191;

import java.util.*;

// TLE
public class NumberOfGoodPaths_TLE {
    public static void main(String[] args) {

    }

    static class Solution {
        List<Integer>[] g;
        boolean[][] counted;
        int answer;
        public int numberOfGoodPaths(int[] vals, int[][] edges) {
            g = new ArrayList[vals.length];
            counted = new boolean[vals.length][vals.length];
            for (int i = 0; i < vals.length; i++) {
                g[i] = new ArrayList<>();
            }
            for (int i = 0; i < edges.length; i++) {
                int a = edges[i][0];
                int b = edges[i][1];
                g[a].add(b);
                g[b].add(a);
            }

            for (int i = 0; i < vals.length; i++) {
                find(vals, i, i, new boolean[vals.length]);
            }
            return answer;
        }

        private void find(int[] vals, int start, int cur, boolean[] visited) {
            visited[cur] = true;
            if(vals[start] == vals[cur] && (!counted[start][cur] && !counted[cur][start])) {
                ++answer;
                counted[start][cur] = true;
                counted[cur][start] = true;
            }
            if(vals[start] < vals[cur]) {
                return;
            }

            for(int next : g[cur]) {
                if(visited[next]) continue;
                find(vals, start, next, visited);
            }
        }
    }
}
