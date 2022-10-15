package leetcode.contest.biweekly._87.maximum_matching_of_players_with_trainers_6185;

import java.util.Arrays;

public class MaximumMatchingOfPlayersWithTrainers_6185 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.matchPlayersAndTrainers(new int[] {4,7,9}, new int[] {8,2,5,8}));
        System.out.println(s.matchPlayersAndTrainers(new int[] {1,1,1}, new int[] {10}));
    }

    static class Solution {
        public int matchPlayersAndTrainers(int[] players, int[] trainers) {
            Arrays.sort(players);
            Arrays.sort(trainers);

            int answer = 0;
            int ti = 0;
            outer: for (int i = 0; i < players.length; i++) {
                while(ti < trainers.length && players[i] > trainers[ti]){
                    ++ti;
                }
                if(ti++ == trainers.length) break;
                ++answer;
            }
            return answer;
        }
    }
}
