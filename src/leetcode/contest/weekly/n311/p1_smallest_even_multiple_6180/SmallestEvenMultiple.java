package leetcode.contest.weekly.n311.p1_smallest_even_multiple_6180;

public class SmallestEvenMultiple {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.smallestEvenMultiple(5));
        System.out.println(s.smallestEvenMultiple(6));
    }
    static class Solution {
        public int smallestEvenMultiple(int n) {
            return n * 2 / gcd(n, 2);
        }

        int gcd(int a, int b) {
            if(a%b == 0) return b;
            return gcd(b, a%b);
        }
    }
}
