package leetcode.contest.biweekly._87.minimum_money_required_before_transactions_6187;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MinimumMoneyRequiredBeforeTransactions_6187 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.minimumMoney(new int[][] {{2,1},{5,0},{4,2}}));
        System.out.println(s.minimumMoney(new int[][] {{3, 0},{0,3}}));
    }

    static class Solution {
        class Transaction implements Comparable<Transaction>{
            int cost;
            int cashback;

            public Transaction(int cost, int cashback) {
                this.cost = cost;
                this.cashback = cashback;
            }

            @Override
            public int compareTo(Transaction o) {
                if(cost == o.cost) {
                    return cashback - o.cashback;
                }
                return o.cost - cost;
            }
        }
        public long minimumMoney(int[][] transactions) {
            List<Transaction> ts = new ArrayList<Transaction>(transactions.length);
            for (int i = 0; i < transactions.length; i++) {
                ts.add(new Transaction(transactions[i][0], transactions[i][1]));
            }

            Collections.sort(ts);

            long answer = 0;
            long temp = 0;
            for(Transaction t : ts) {
                temp -= t.cost;
                System.out.println(temp);
                if(temp < 0) {
                    answer = -temp;
                }
                temp += t.cashback;
            }

            return answer;
        }
    }
}
