package leetcode.contest.weekly.n311.p2_length_of_the_longest_alphabetical_continuous_substring_6181;

public class LengthOfTheLongestAlphabeticalContinuousSubstring_6181 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.longestContinuousSubstring("abacaba"));
        System.out.println(s.longestContinuousSubstring("abcde"));
    }
    static class Solution {
        public int longestContinuousSubstring(String s) {
            int max = 1;
            int length = 1;
            for (int i = 1; i < s.length(); i++) {
                if(s.charAt(i-1)+1 == s.charAt(i)) {
                    ++length;
                } else {
                    max = Math.max(max, length);
                    length = 1;
                }
            }
            max = Math.max(max, length);

            return max;
        }
    }
}
