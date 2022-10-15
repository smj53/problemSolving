package bj.gold.IV.prob2602.돌다리건너기;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_돌다리건너기_2602 {
    static String substr;
    static String[] s;
    static int N;
    static int M;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        substr = br.readLine();
        s = new String[2];
        s[0] = br.readLine();
        s[1] = br.readLine();
        N = s[0].length();
        M = substr.length();
        int[][] arr = new int[2][s[0].length()];
        arr[0][N-1] = s[0].charAt(N-1) == substr.charAt(M-1) ? 1 : 0;
        arr[1][N-1] = s[1].charAt(N-1) == substr.charAt(M-1) ? 1 : 0;
        for (int i = N - 1; i >= 0; --i) {

        }

        System.out.println(sum(arr, substr.charAt(0)));
    }

    private static int sum(int[][] arr, char c) {
        int sum = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N; j++) {
                if(s[i].charAt(j) == c) {
                    sum += arr[i][j];
                }
            }
        }
        return sum;
    }

    static int find(int curIdx, int curPos, int isAngel) {
        if (curIdx == substr.length()) {
            return 1;
        }

        int sum = 0;
        char c = substr.charAt(curIdx);
        for (Integer i : maps[isAngel].get(c)) {
            if(i < curPos) continue;
            sum += find(curIdx + 1, i + 1, isAngel ^ 1);
        }
        return sum;
    }
}
