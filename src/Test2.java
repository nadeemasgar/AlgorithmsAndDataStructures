import java.io.*;
import java.util.*;
import java.util.function.IntUnaryOperator;

public class Test2 {
    public static void main(String[] args) {

        System.out.println(Arrays.toString(new Test2().manacherEven("abbabba")));
        System.out.println(Arrays.toString(new Test2().manacherOdd("abbabb")));
    }

    int[] manacherOdd(String s) {
        int[] res = new int[s.length()];
        for (int i = 0, l = 0, r = -1; i < s.length(); i++) {
            int k = (i > r) ? 1 : Math.min(res[l + r - i], r - i + 1);
            while (0 <= i - k && i + k < s.length() && s.charAt(i - k) == s.charAt(i + k)) {
                k++;
            }

            res[i] = k--;
            if (i + k > r) {
                l = i - k;
                r = i + k;
            }
        }
        return res;
    }

    int[] manacherEven(String s) {
        int[] res = new int[s.length()];
        for (int i = 0, l = 0, r = -1; i < s.length(); i++) {
            int k = (i > r) ? 0 : Math.min(res[l + r - i + 1], r - i + 1);
            while (0 <= i - k - 1 && i + k < s.length() && s.charAt(i - k - 1) == s.charAt(i + k)) {
                k++;
            }

            res[i] = k--;
            if (i + k > r) {
                l = i - k - 1;
                r = i + k;
            }
        }
        res = Arrays.stream(res).map(num -> {
            if (num == 0) return 1;
            return num;
        }).toArray();
        return res;
    }
}


