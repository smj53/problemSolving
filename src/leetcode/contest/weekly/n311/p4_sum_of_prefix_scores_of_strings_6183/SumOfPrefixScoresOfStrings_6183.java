package leetcode.contest.weekly.n311.p4_sum_of_prefix_scores_of_strings_6183;

import java.util.*;

public class SumOfPrefixScoresOfStrings_6183 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.sumPrefixScores(new String[]{"abc","ab","bc","b"})));
        System.out.println(Arrays.toString(s.sumPrefixScores(new String[]{"abcd"})));
    }

    static class Solution {
        public int[] sumPrefixScores(String[] words) {
            Tree t = new Tree();
            for (int i = 0; i < words.length; i++) {
                t.addString(words[i], 0, t.root);
            }
            System.out.println(t.root);
            int[] ret = new int[words.length];
            for (int i = 0; i < words.length; i++) {
                ret[i] = t.calculateScore(words[i], 0, t.root);
            }
            return ret;
        }

        class Tree {
            Node root;

            public Tree() {
                root = new Node((char) 1);
            }

            public void addString(String s, int idx, Node parent) {
                ++parent.leavesNum;
                if(s.length() != idx) {
                    char c = s.charAt(idx);
                    Node cur = parent.children.getOrDefault(c, new Node(c));
                    parent.children.put(c, cur);
                    addString(s, idx+1, cur);
                }
            }

            public int calculateScore(String s, int idx, Node parent) {
                if(s.length() == idx) {
                    return 0;
                }

                Node child = parent.children.get(s.charAt(idx));
                return child.leavesNum + calculateScore(s, idx+1, child);
            }
        }

        class Node {
            char val;
            Map<Character, Node> children;
            Node parent;
            int leavesNum;

            public Node(char val) {
                this.val = val;
                children = new HashMap<>();
            }

            public Node(char val, Map<Character, Node> children) {
                this.val = val;
                this.children = children;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "val=" + val +
                        ", children=" + children +
                        '}';
            }
        }
    }
}
