package leetcode.contest.weekly.n312.p3_find_all_good_indices_6190;

import java.util.ArrayList;
import java.util.List;

public class FindAllGoodIndices {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.goodIndices(new int[]{2,1,1,1,3,4,1}, 2));
        System.out.println(s.goodIndices(new int[]{2,1,1,2}, 2));
        System.out.println(s.goodIndices(new int[]{83441,339399,879745,789229,406453,100476,71931,60035,18971,172126,420833,798833,945593,987982,993320,994256,997289,998770}, 5)); // 7, 8, 9
        System.out.println(s.goodIndices(new int[]{478184,863008,716977,921182,182844,350527,541165,881224}, 1)); // 1, 2, 3, 4, 5, 6
    }

    static class Solution {
        public List<Integer> goodIndices(int[] nums, int k) {
            List<Integer> ans = new ArrayList<>();

            boolean isNonInc = false;
            boolean prevNonInc = false;
            boolean isNonDec = false;
            boolean prevNonDec = false;
            for (int i = k; i < nums.length-k; i++) {
                prevNonDec = isNonDec;
                prevNonInc = isNonInc;
                isNonDec = checkNonDec(nums, i, k, isNonDec);
                if(!isNonDec) {
                    if(prevNonDec) i += k - 2;
                    isNonInc = false;
                    continue;
                }

                isNonInc = checkNonInc(nums, i, k, isNonInc);
                if(!isNonInc) {
                    if(prevNonInc) i += k - 2;
                    isNonDec = false;
                    continue;
                }

                ans.add(i);
            }

            return ans;
        }

        private boolean checkNonInc(int[] nums, int cur, int k, boolean isNonInc) {
            if(isNonInc) {
                return nums[cur-2] >= nums[cur-1];
            }
            int s = cur - k;
            int e = cur - 1;
            for(int i = s; i < e;++i) {
                if(nums[i] < nums[i+1]) return false;
            }
            return true;
        }

        boolean checkNonDec(int[] nums, int cur, int k, boolean isNonDec) {
            if(isNonDec) {
                return nums[cur+k-1] <= nums[cur+k];
            }
            // 다시 계산
            int s = cur + 1;
            int e = cur + k;
            for(int i=s;i<e;++i) {
                if(nums[i] > nums[i+1]) return false;
            }
            return true;
        }
    }
}
