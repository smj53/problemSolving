package bj.gold.IV.prob2602.돌다리건너기;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_돌다리건너기_2602_tle {

    static String substr;
    static Set<Integer> emptySet = new HashSet<Integer>();
    static String[] s;
    static Map<Character, Set<Integer>> charToIdx;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        substr = br.readLine();
        s = new String[2];
        s[0] = br.readLine();
        s[1] = br.readLine();

        charToIdx = new HashMap<>();
        for (int i = 0; i < substr.length(); ++i) {
            char c = substr.charAt(i);
            Set<Integer> t = charToIdx.getOrDefault(c, new HashSet<Integer>());
            t.add(i);
            charToIdx.put(c, t);
        }

        System.out.println(find(0, 0, 0) + find(0, 0, 1));
    }

    static int find(int curIdx, int curPos, int isAngel) {
        if (curIdx == substr.length()) {
            return 1;
        }

        int sum = 0;
        for (int i = curPos; i < s[0].length(); ++i) {
            char c = s[isAngel].charAt(i);
            for (int idx : charToIdx.getOrDefault(c, emptySet)) {
                if (idx == curIdx) {
                    sum += find(curIdx + 1, i + 1, isAngel ^ 1);
                }
            }
        }
        return sum;
    }
}

class Main_돌다리건너기_2602_ {

    static String substr;
    static Set<Integer> emptySet = new HashSet<Integer>();
    static String[] s;
    static Map<Character, List<Integer>>[] maps;

    static class Node {
        List<Integer> list;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        substr = br.readLine();
        s = new String[2];
        s[0] = br.readLine();
        s[1] = br.readLine();
        maps = new HashMap[2];

        for (int i = 0; i < 2; i++) {
            maps[i] = new HashMap<Character, List<Integer>>();
            for (int j = 0; j < s[i].length(); j++) {
                char c = s[i].charAt(j);
                List<Integer> l = maps[i].getOrDefault(c, new ArrayList<Integer>());
                l.add(j);
                maps[i].put(c, l);
            }
        }

        System.out.println(find(0, 0, 0) + find(0, 0, 1));
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
