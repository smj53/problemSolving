package leetcode.contest.biweekly._87.smallest_subarrays_with_maximum_bitwise_or_6186;

import java.util.Arrays;

public class SmallestSubArraysWithMaximumBitwiseOr_6186 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.smallestSubarrays(new int[] {1,0,2,1,3})));
        System.out.println(Arrays.toString(s.smallestSubarrays(new int[] {1,2})));
    }

    // 틀림
    // 근데 [0, 0, 0, 0] 이면 [1, 1, 1, 1] 아닌가?
    static class Solution {
        public int[] smallestSubarrays(int[] nums) {
            int N = nums.length;
            int[] answer = new int[N];
            int subLength = 0;
            int[] bitCount = new int[32];
            for (int i = N - 1; i >= 0; --i) {
                addBit(nums[i], bitCount);
                ++subLength;
                int[] newBitCount = bitCount.clone();
                do {
                    subtractBit(nums[i + subLength - 1], newBitCount);
                    --subLength;
                } while(compare(newBitCount, bitCount) && !isAllZero(newBitCount));
                bitCount = newBitCount;
                ++subLength;
//                System.out.println(Arrays.toString(bitCount));
                addBit(nums[i + subLength - 1], bitCount);
                answer[i] = subLength;
            }
            return answer;
        }

        private boolean isAllZero(int[] bitCount) {
            for (int i = 0; i < 32; i++) {
                if(bitCount[i] != 0) return false;
            }
            return true;
        }

        void addBit(int num, int[] bitCount) {
            int i = 0;
            while(num > 0) {
                if(num%2 == 1) ++bitCount[i];
                num /= 2;
                ++i;
            }
        }

        void subtractBit(int num, int[] bitCount) {
            int i = 0;
            while(num > 0) {
                if(num%2 == 1) --bitCount[i];
                num /= 2;
                ++i;
            }
        }

        boolean compare(int[] a, int[] b) {
            for (int i = 31; i >= 0; --i) {
                if(a[i] > 0 && b[i] == 0) return true;
                else if(a[i] == 0 && b[i] > 0) return false;
            }
            return true; // 같을 때
        }
    }
}
