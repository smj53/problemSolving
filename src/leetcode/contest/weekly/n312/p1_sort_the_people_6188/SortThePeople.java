package leetcode.contest.weekly.n312.p1_sort_the_people_6188;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortThePeople {
    public static void main(String[] args) {

    }

    static class Solution {
        class Node implements Comparable<Node>{
            String name;
            int height;

            @Override
            public int compareTo(Node o) {
                return o.height - height;
            }

            public Node(String name, int height) {
                this.name = name;
                this.height = height;
            }
        }
        public String[] sortPeople(String[] names, int[] heights) {
            List<Node> arr = new ArrayList<>();
            for (int i = 0; i < names.length; i++) {
                arr.add(new Node(names[i], heights[i]));
            }
            Collections.sort(arr);
            String[] ans = new String[names.length];
            for (int i = 0; i < names.length; i++) {
                ans[i] = arr.get(i).name;
            }
            return ans;
        }
    }
}
