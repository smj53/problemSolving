package leetcode.contest.weekly.n311.p3_reverse_odd_levels_of_binary_tree_6182;

import java.util.*;

public class ReverseOddLevelsOfBinaryTree_6182 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.reverseOddLevels(s.makeTree(new int[] {0,2,3,5,8,13,21,34}, 1)));
        System.out.println(s.reverseOddLevels(s.makeTree(new int[] {0,7,13,11}, 1)));
        System.out.println(s.reverseOddLevels(s.makeTree(new int[] {0, 0,1,2,0,0,0,0,1,1,1,1,2,2,2,2}, 1)));
    }

    static class Solution {
        public TreeNode reverseOddLevels(TreeNode root) {

            List<Integer> list = new ArrayList<>();
            list.add(0);
            Deque<TreeNode> candidate = new ArrayDeque<>();
            candidate.add(root);
            makeArr(list, candidate, 0);

            int[] arr = new int[list.size()];
            int i = 0;
            for(int v : list) {
                arr[i++] = v;
            }

            reverse(arr, 1);
            System.out.println(Arrays.toString(arr));
            return makeTree(arr, 1);
        }

        void reverse(int[] arr, int level) {
            int start = 1<<level;
            if(arr.length <= start) return;
            if(level % 2 != 0) {
                int end = Math.min(arr.length-1, (1<<(level+1)) - 1);
                System.out.println(arr.length+" "+(1<<(level+1)));
                int ei = end;
                for (int i = start, j=end; i <= (start+end)/2; i++, --j) {
                    int t = arr[i];
                    arr[i] = arr[j];
                    arr[j] = t;
                }
            }
            reverse(arr, level+1);
        }

        void makeArr(List<Integer> list, Deque<TreeNode> candidate, int level) {
            int s = candidate.size();
            if(s == 0) return;
            while(s-->0) {
                TreeNode t = candidate.poll();
                list.add(t.val);
                if(t.left != null) candidate.offer(t.left);
                if(t.right != null) candidate.offer(t.right);
            }
            makeArr(list, candidate, level+1);
        }

        TreeNode makeTree(int[] arr, int cur) {
            if(arr.length <= cur) return null;
            TreeNode ret = new TreeNode(arr[cur], makeTree(arr, cur*2), makeTree(arr, cur*2+1));

            return ret;
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
