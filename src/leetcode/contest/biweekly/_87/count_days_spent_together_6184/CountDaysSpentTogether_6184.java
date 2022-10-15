package leetcode.contest.biweekly._87.count_days_spent_together_6184;

public class CountDaysSpentTogether_6184 {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();
        System.out.println(s.countDaysTogether("08-15", "08-18", "08-16", "08-19"));
        System.out.println(s.countDaysTogether( "10-01", "10-31", "10-01", "12-31"));
    }

    static class Solution {
        int[] numOfMonths = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
            int[] arriveAliceInfo = stringToIntDate(arriveAlice);
            int[] leaveAliceInfo = stringToIntDate(leaveAlice);
            int[] arriveBobInfo = stringToIntDate(arriveBob);
            int[] leaveBobInfo = stringToIntDate(leaveBob);

            if(isBefore(leaveAliceInfo, arriveBobInfo) || isBefore(leaveBobInfo, arriveAliceInfo)) return 0;

            int[] start = max(arriveAliceInfo, arriveBobInfo);
            int[] end = min(leaveAliceInfo, leaveBobInfo);

            int answer = 1 + (start[0] == end[0] ? end[1] - start[1] : numOfMonths[start[0]] - start[1] + end[1]);
            for (int i = start[0] + 1; i < end[0]; i++) {
                answer += numOfMonths[i];
            }
            return answer;
        }

        int[] stringToIntDate(String date) {
            String[] t = date.split("-");
            return new int[] {Integer.parseInt(t[0]), Integer.parseInt(t[1])};
        }

        // a가 b보다 이전인지
        boolean isBefore(int[] a, int[] b) {
            return a[0] < b[0] || (a[0] == b[0] && a[1] < b[1]);
        }

        int[] min(int[] a, int[] b) {
            if(a[0] == b[0]) {
                if(a[1] <= b[1]) return a;
                else return b;
            } else if (a[0] < b[0]) return a;
            else return b;
        }

        int[] max(int[] a, int[] b) {
            if(a[0] == b[0]) {
                if(a[1] >= b[1]) return a;
                else return b;
            } else if (a[0] > b[0]) return a;
            else return b;
        }
    }
}
