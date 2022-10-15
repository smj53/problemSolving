package leetcode.contest.weekly.n312.p2_longest_subarray_with_maximum_bitwise_and_6189;

import java.util.ArrayList;
import java.util.List;

public class LongestSubarrayWithMaximumBitwiseAND {
    public static void main(String[] args) {

    }

    static class Solution {
        public int longestSubarray(int[] nums) {
            int max = nums[0];
            for (int i = 1; i < nums.length; i++) {
                max = Math.max(nums[i], max);
            }
            int answer = 0;
            int count = 0;
            for(int i=0;i<nums.length;++i) {
                if(nums[i] == max) {
                    ++count;
                } else {
                    answer = Math.max(answer, count);
                    count = 0;
                }
            }
            answer = Math.max(answer, count);
            return answer;
        }
    }
}
